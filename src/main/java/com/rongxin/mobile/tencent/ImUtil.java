package com.rongxin.mobile.tencent;


import com.alibaba.fastjson.JSONObject;
import com.rongxin.web.util.HttpUtils;
import java.util.*;

public class ImUtil {
    final private long sdkappid;
    final private String key;
    private static int random =new Random().nextInt(Integer.MAX_VALUE);//随机数

    public ImUtil(long sdkappid, String key) {
        this.sdkappid = sdkappid;
        this.key = key;
    }

    /**
     * im 导入用户
     * @param userSig  管理员签名
     * @param userId   需要导入的用户id
     * @return
     * @throws Exception
     */

    public JSONObject importTencentImUser(String userSig,String userId) throws Exception {
        JSONObject result = new JSONObject();
        Map<String,String> map =  new HashMap<String,String>();
        map.put("UserID",userId);//用户Id
        map.put("Nick","");//昵称
        map.put("FaceUrl","");//头像
        String json = JSONObject.toJSONString(map);
        JSONObject jsonObject = checkTencentImUser(userSig,userId);
        if("0".equals(jsonObject.getString("ErrorCode"))){
            JSONObject resultItem = jsonObject.getJSONArray("ResultItem").getJSONObject(0);
            resultItem.getString("ResultCode");
            if("0".equals(resultItem.getString("ResultCode"))){
                if("Imported".equals(resultItem.getString("AccountStatus"))){
                    result.put("code","tencent_im_001");
                    result.put("msg","腾讯云已存在用户");
                    return result;
                }
            }else{
                result.put("code","tencent_im_002");
                result.put("tencent_code",resultItem.getString("ResultCode"));
                result.put("msg", resultItem.getString("ResultInfo"));
                return result;
            }
        }else{
            result.put("code","tencent_im_003");
            result.put("msg", jsonObject.getString("ErrorInfo"));
            return result;
        }
        String importTencentImUserUrl = "https://console.tim.qq.com/v4/im_open_login_svc/account_import?" +
                "sdkappid="+sdkappid+"&identifier=administrator&usersig="+userSig+"&random="+random+"&contenttype=json";

        result.put("code","tencent_im_000");
        result.put("result",HttpUtils.doPost(importTencentImUserUrl,json));
        result.put("msg","导入成功");
        return result;

    }
    /**
     * im 检查用户是否存在
     * @param userSig  管理员签名
     * @param userId   需要导入的用户id
     * @return
     * @throws Exception
     */

    public JSONObject checkTencentImUser(String userSig,String userId) throws Exception {
        Map<String,List<Map<String,String>>> map =  new HashMap<String,List<Map<String,String>>>();

        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> mapUser =  new HashMap<String,String>();

        mapUser.put("UserID",userId);//用户Id
        list.add(mapUser);
        map.put("CheckItem",list);
        String json = JSONObject.toJSONString(map);

        String importTencentImUserUrl = "https://console.tim.qq.com/v4/im_open_login_svc/account_check?" +
                "sdkappid="+sdkappid+"&identifier=administrator&usersig="+userSig+"&random="+random+"&contenttype=json";
        return HttpUtils.doPost(importTencentImUserUrl,json);
    }

    /**
     * im 删除账号信息
     * @param userSig  管理员签名
     * @param userId   需要导入的用户id
     * @return
     * @throws Exception
     */

    public JSONObject deleteTencentImUser(String userSig,String userId) throws Exception {
        Map<String,List<Map<String,String>>> map =  new HashMap<String,List<Map<String,String>>>();
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> mapUser =  new HashMap<String,String>();

        mapUser.put("UserID",userId);//用户Id
        list.add(mapUser);
        map.put("DeleteItem",list);
        String json = JSONObject.toJSONString(map);

        JSONObject result = new JSONObject();

        String importTencentImUserUrl = "https://console.tim.qq.com/v4/im_open_login_svc/account_delete?" +
                "sdkappid="+sdkappid+"&identifier=administrator&usersig="+userSig+"&random="+random+"&contenttype=json";

        JSONObject jsonObject = HttpUtils.doPost(importTencentImUserUrl,json);
        if("0".equals(jsonObject.getString("ErrorCode"))){
            JSONObject resultItem = jsonObject.getJSONArray("ResultItem").getJSONObject(0);
            resultItem.getString("ResultCode");
            if("0".equals(resultItem.getString("ResultCode"))){
                if(userId.equals(resultItem.getString("UserID"))){
                    result.put("code","tencent_im_000");
                    result.put("msg","用户删除成功");
                    return result;
                }
            }else{
                result.put("code","tencent_im_002");
                result.put("tencent_code",resultItem.getString("ResultCode"));
                result.put("msg", resultItem.getString("ResultInfo"));
                return result;
            }
        }else{
            result.put("code","tencent_im_003");
            result.put("tencent_code",jsonObject.getString("ErrorCode"));
            result.put("msg", jsonObject.getString("ErrorInfo"));
            return result;
        }
        return result;
    }


    public static void main(String[] args) throws Exception {
        ImUtil imUtil = new ImUtil(1400751532,"87de0e1681fe3fa5ed744adf5e1393bc31dd36fcbd0e74081f4e4a7d68406d50");
        TLSSigAPIv2 tlsSigAPIv2 = new TLSSigAPIv2(1400751532,"87de0e1681fe3fa5ed744adf5e1393bc31dd36fcbd0e74081f4e4a7d68406d50");// TRTC 和 IM 服务中必须要使用的 UserSig
        //先获取管理员签名
        String adminUserSig =  tlsSigAPIv2.genUserSig("administrator",86400);
        //获取用户签名
        String commonUserSig = tlsSigAPIv2.genUserSig("User_002",86400);
        //使用管理员导入用户信息，如果不导入后续可能获取用户信息失败
        JSONObject jsonObject = imUtil.importTencentImUser(adminUserSig,"User_002");
        System.out.println(jsonObject.toJSONString());
    }
}