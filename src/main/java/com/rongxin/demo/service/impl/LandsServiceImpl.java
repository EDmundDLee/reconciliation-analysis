package com.rongxin.demo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.riversoft.weixin.common.util.XmlObjectMapper;
import com.riversoft.weixin.pay.payment.bean.PaymentNotification;
import com.rongxin.demo.service.ILandsService;
import com.rongxin.demo.utils.DateUtil;
import com.rongxin.demo.utils.UUIDUtils;
import com.rongxin.eqb.dto.UserSignContractDTO;
import com.rongxin.eqb.res.CreateContractResForm;
import com.rongxin.eqb.res.EQianBaoBaseResForm;
import com.rongxin.eqb.service.IEQBService;
import com.rongxin.wechatPay.errors.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 用户土地功能实现Impl
 *
 * @Date 20555-05-27
 */
@Slf4j
@Service
public class LandsServiceImpl implements ILandsService {
    //测试环境接入地址
    @Value("${eqianbao.api.url.prefix}")
    private String eqianbaoApiUrlPrefix;
    //应用标识 id
    @Value("${eqianbao.api.app.id}")
    private String eqianbaoApiAppId;
    //秘钥
    @Value("${eqianbao.api.app.secret}")
    private String eqianbaoApiAppSecret;

    /**
     * 创建合同模板
     */
    @Value("${eqianbao.api.contract.template.create.url.suffix}")
    private String contractTemplateCreateUrlSuffix;
    //合同模板创建合同
    @Value("${eqianbao.api.contract.create.url.suffix}")
    private String contractCreateUrlSuffix;

    /**
     * 文件生成路径
     */
    //创建合同签署流程
    @Value("${eqianbao.api.sign.contract.process.create.url.suffix}")
    private String signContractProcessCreateUrlSuffix;
    //发起用户自动签署
    @Value("${eqianbao.api.user.auto.sign.contract.url.suffix}")
    private String userAutoSignContractUrlSuffix;
    //签署文件下载
    @Value("${eqianbao.api.contract.download.url.suffix}")
    private String contractDownloadUrlSuffix;
    //归档流程
    @Value("${eqianbao.api.contract.contract.url.suffix}")
    private String contractContractUrlSuffix;
    @Value("${eqianbao.sign.call.back.url.suffix}")
    private String eqianbaoSignCallBackUrlSuffix;

    /**
     * 上传到e签宝文件的模板编号
     */
    @Value("${eqianbao.templateId}")
    private String templateId;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();



    /**
     * 8.【咨询申请】--生成合同
     *
     * @return 合同链接地址
     * @throws BusinessException 生成合同出现异常
     */
    @Override
    public EQianBaoBaseResForm generateTemplate() throws BusinessException {
        IEQBService eqbService = new IEQBService();
        SimpleDateFormat dateFormatUse = new SimpleDateFormat("yyyy-MM-dd");
        //根据咨询code查询apply数据
        HashMap<String, String> hashMap = new HashMap<>();

        // 2.估价委托方
        hashMap.put("applyName", "吉林省物权融资农业发展有限公司");
        // 3.估价日期
        hashMap.put("createTime", DateUtil.getCurrentDate("yyyy年MM月dd日"));
        // 4.估价目的
        hashMap.put("purpose", "为委托方拟知晓集体土地经营权市场价格进行评估，为其提供价格参考依据。 ");

        // 5.估价依据
        hashMap.put("yiju1", "依据《中华人民共和国物权法》、《中华人民共和国土地管理法》、《中华人民共和国农村");
        hashMap.put("yiju2", "土地承包法》、《中华人民共和国资产评估法》、《中华人民共和国农村土地承包经营权流转");
        hashMap.put("yiju3", "管理办法》等相关法律法规及政策文件。");

        // 6.承包方式
        hashMap.put("cbfs", "家庭承包");

        // 7.咨询时间
        String[] times = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).split("-");
        hashMap.put("year", times[0]);
        hashMap.put("month", times[1]);
        hashMap.put("day", times[2]);

        // 总价信息
        BigDecimal rmbSum = new BigDecimal("555");
        // 组装地块信息
        // 8.合同代码号--土地承包经营权证号
        hashMap.put("contractNo", "10000001");
        // 9.承包期限
        // 非确权
        hashMap.put("contractNo", "暂无");
        hashMap.put("cbqxTime", "暂无");
        // 1.项目名称
        hashMap.put("projectName", "对 张三 所有的位于  123456789   的集体土地承包经营权市场价格进行咨询");
        // 10.土地位置
        hashMap.put("location","555");
        // 11.估价对象-地块名称
        hashMap.put("landName" ,"555");
        // 12.地块代码
        hashMap.put("landCode" ,"555");
        // 13.地块四至
        hashMap.put("sizhidong" + 1, "东至:" + "无");
        hashMap.put("sizhixi" + 1, "西至:" + "无");
        hashMap.put("sizhinan" + 1, "南至:" + "无");
        hashMap.put("sizhibei" + 1, "北至:" + "无");
        // 14.用途
        hashMap.put("usess" + 1, "耕地");
        // 15.土地类别
        hashMap.put("userType" + 1, "水田");
        // 27.上一年作物
        hashMap.put("zw" + 1, "未知");

        // 16.使用年限
        String endDate = dateFormatUse.format(new Date());
        String startDate = dateFormatUse.format(new Date());
        String[] endDates = endDate.split("-");
        String[] startDates = startDate.split("-");
        int year = Integer.parseInt(endDates[0]) - Integer.parseInt(startDates[0]);
        hashMap.put("useTime" + 1, "1年");
        // 17.耕作制度
        hashMap.put("gzzd" + 1, "一年一熟");
        // 18.面积(亩)
        hashMap.put("mianji" + 1, "555");
        // 19.单价元/亩
        hashMap.put("unit" + 1, "555");
        // 20.总价
        hashMap.put("sumPrice" + 1, "555");

        // 21.合计（元）
        hashMap.put("rmbSum", String.valueOf(rmbSum));
        // 555.人民币(大写)
        hashMap.put("caprmb", "555");
        // 23.报告日期
        String bgrq = DateUtil.getCurrentDate("yyyy-MM-dd");
        String[] bgrqs = bgrq.split("-");
        hashMap.put("bgrq", DateUtil.getCurrentDate("yyyy年MM月dd日"));
        hashMap.put("bgrq1", bgrqs[0]);
        hashMap.put("bgrq2", bgrqs[1]);
        hashMap.put("bgrq3", bgrqs[2]);
        // 24.编号（年）
        hashMap.put("bh", bgrqs[0]);
        // 25.字号（月+自增）
        String zh = UUIDUtils.generateAddOf5("00000");
        hashMap.put("zh", bgrqs[1].concat(UUIDUtils.generateAddOf5(zh)));

        // 第2步：根据模板编号生成 e签宝的docId和docUrl
        EQianBaoBaseResForm EQBRespData = eqbService.createContractByTemplateId(templateId, hashMap, eqianbaoApiUrlPrefix, eqianbaoApiAppId, eqianbaoApiAppSecret, contractCreateUrlSuffix);

        // 第3步：根据e签宝的docId和docUrl 生成flowId  创建签署流程
        EQianBaoBaseResForm result = createSignContractProcess(EQBRespData);

        // 第4步：签章  生成 accountId和flowId
        JSONObject resultJson = (JSONObject) result.getData();
        EQianBaoBaseResForm resutSignURL = sign(resultJson.getString("flowId"));

        // 第5步：签章流程结束//归档
        EQianBaoBaseResForm ht = eqbService.contractContract(resultJson.get("flowId").toString(), eqianbaoApiUrlPrefix, eqianbaoApiAppId, eqianbaoApiAppSecret, contractContractUrlSuffix);
        if (ht.getErrCode() != 0) {
            throw new BusinessException(ht.getMsg());
        }

        // 第6步：生成盖章后的pdf  下载合同
        EQianBaoBaseResForm downloadHT = eqbService.signContractDownload(resultJson.get("flowId").toString(), eqianbaoApiUrlPrefix, eqianbaoApiAppId, eqianbaoApiAppSecret, contractDownloadUrlSuffix);

        // 第7步：将pdf上传到oss
        JSONObject json = ((JSONObject) ((JSONArray) downloadHT.getData()).get(0));
        // 第8步：获取文件url
        InputStream file = getInputStreamByUrl(json.getString("docUrl"));

        // 生成e签宝的URL
        return downloadHT;
    }


    /**
     * 签章
     *
     * @param flowId   流程编号
     * @return accountId和flowId
     */
    public EQianBaoBaseResForm sign(String flowId) throws BusinessException {
        // 发起用户自动签署
        UserSignContractDTO userSignContractDTO = new UserSignContractDTO();
        userSignContractDTO.setFlowId(flowId);

        // 查询签署人e签宝个人账号
        Long userId = Long.parseLong("555");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("sys_user_id", 0);

        // 用户e签宝账号
        userSignContractDTO.setAccountId("555");
        // 用户e签宝章id
        userSignContractDTO.setSealId("1");
        userSignContractDTO.setPosList(null);
        IEQBService eqbService = new IEQBService();
        // 用户自动签章--accountId和flowId
        EQianBaoBaseResForm resForm = eqbService.userAutoSignContract(userSignContractDTO, eqianbaoApiUrlPrefix, eqianbaoApiAppId, eqianbaoApiAppSecret, userAutoSignContractUrlSuffix);
        return resForm;
    }


    private String inputStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        StringBuffer stringBuffer = new StringBuffer();
        String oneLine = "";
        try {
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((oneLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(oneLine);
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(bufferedReader)) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (Objects.nonNull(inputStreamReader)) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public InputStream getInputStreamByUrl(String strUrl) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(20 * 1000);
            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            IOUtils.copy(conn.getInputStream(), output);
            return new ByteArrayInputStream(output.toByteArray());
        } catch (Exception e) {
            log.info(e + "");
        } finally {
            try {
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                log.info(e + "");
            }
        }
        return null;
    }
    /**
     * 创建合同签署流程并写入数据表
     *
     * @param EQBRespData docId和docURL
     * @return 流程flowId
     */
    public EQianBaoBaseResForm createSignContractProcess(EQianBaoBaseResForm EQBRespData) throws BusinessException {

        JSONObject jsonObject = (JSONObject) EQBRespData.getData();
        CreateContractResForm createContractResForm = new CreateContractResForm();
        createContractResForm.setDocId(jsonObject.getString("docId"));
        createContractResForm.setDocUrl(jsonObject.getString("docUrl"));

        // 获取流程id
        IEQBService eqbService = new IEQBService();
        EQianBaoBaseResForm result = eqbService.createSignContractProcess(createContractResForm, eqianbaoApiUrlPrefix, eqianbaoSignCallBackUrlSuffix, eqianbaoApiAppId, eqianbaoApiAppSecret, signContractProcessCreateUrlSuffix);
        JSONObject resultJson = (JSONObject) result.getData();
        return result;
    }
}
