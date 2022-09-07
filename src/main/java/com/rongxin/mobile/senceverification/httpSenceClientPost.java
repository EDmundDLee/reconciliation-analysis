package com.rongxin.mobile.senceverification;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.SignatureException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 商汤科技 人脸识别、各种卡识别等技术接口
 * 如果相关内容需要确认或者需要更新接口参照一下官网链接
 * https://v2-devcenter.visioncloudapi.com/#/doc/v2/index
 * 注：
 * 方法内的图片必须满足如下条件：
 *
 * 格式必须为 JPG（JPEG），BMP，PNG，GIF，TIFF 之一
 * 宽和高必须大于 8px，小于等于 5000px
 * 文件尺寸小于等于 5 MB
 */
public class httpSenceClientPost {

    public static String username = "";//姓名
    public static String id_number = "";//身份证号
    public static String filepath = "C:\\Users\\Administrator\\Desktop\\2.jpg";//图片路径
    /**
     * 上传一些配置参数并获取当次交互活体检测H5的biztoken。
     */
    public static String getHTbiztoken() throws ClientProtocolException, IOException, SignatureException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost(ConstantsSence.POST_URL);
        MultipartEntity entity = new MultipartEntity();
        entity.addPart("redirect_url", new StringBody("https://dev-jlrx-backend-basic.cacfintech.com/wxredirect.html", Charset.forName("UTF-8")));//返回路径
        entity.addPart("is_show_default_result_page", new StringBody("true"));//是否显示默认的检测结果页面，显示默认页面则设置为true，否则设置为false
        //业务流水号，关联请求和返回结果，会在 获取结果 中原样返回
        entity.addPart("biz_no", new StringBody("12314", Charset.forName("UTF-8")));
        //该字段用于设置活体识别模式类型，目前只支持设置为interactive交互活体识别模式
        entity.addPart("liveness_mode", new StringBody("interactive", Charset.forName("UTF-8")));
        entity.addPart("return_image", new StringBody("true"));
        entity.addPart("return_face_image", new StringBody("true"));


        return callHttpResponse(httpclient,post,entity);
    }

    /**
     * 用姓名、身份证号到第三方核查后台查询预留照片，然后与客户通过接口上传的活体数据进行比对，来判断是否为同一个人。
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String nameAndIdNoPhotoCheckByLiveness() throws ClientProtocolException, IOException, SignatureException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost(ConstantsSence.POST_URLA);
        StringBody name = new StringBody(username,Charset.forName("UTF-8"));
        StringBody number = new StringBody(id_number);
        FileBody fileBody = new FileBody(new File(filepath));
        MultipartEntity entity = new MultipartEntity();
        entity.addPart("name", name);
        entity.addPart("idnumber", number);
        entity.addPart("liveness_file", fileBody);
        return callHttpResponse(httpclient,post,entity);
    }
    /**
     * 用姓名、身份证号到第三方核查后台查询预留照片，然后与客户通过接口上传的活体数据进行比对，来判断是否为同一个人。
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String nameAndIdNoPhotoCheckByImage(String uname,String idnumber,String imageBase64) throws ClientProtocolException, IOException, SignatureException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost(ConstantsSence.POST_URLJ);
        StringBody name = new StringBody(uname,Charset.forName("UTF-8"));
        StringBody number = new StringBody(idnumber);
        MultipartEntity entity = new MultipartEntity();
        StringBody imageBase64Body = new StringBody(imageBase64);
        entity.addPart("image_base64", imageBase64Body);
        entity.addPart("name", name);
        entity.addPart("idnumber", number);
        return callHttpResponse(httpclient,post,entity);
    }
    /**
     * 用于验证身份证上的姓名与身份证号是否匹配。
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String checkNameAndIdcard(String uname,String idnumber) throws ClientProtocolException, IOException, SignatureException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost(ConstantsSence.POST_URLB);
        StringBody name = new StringBody(uname,Charset.forName("UTF-8"));
        StringBody number = new StringBody(idnumber);
        MultipartEntity entity = new MultipartEntity();
        entity.addPart("name", name);
        entity.addPart("idnumber", number);
        return callHttpResponse(httpclient,post,entity);
    }
    /**
     * 用于识别通过接口上传的静态身份证图片上的文字信息。
     * @param outsString front 正面   back 背面
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String getInfoFromOcrIdCard(String outsString,String imagePath) throws ClientProtocolException, IOException, SignatureException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost(ConstantsSence.POST_URLD);
        FileBody fileBody = new FileBody(new File(imagePath));
        MultipartEntity entity = new MultipartEntity();
        entity.addPart("image_file", fileBody);
        //用于说明idcard正反面，默认值为auto表示自动，值为front表示正面（人像面），值为back表示背面（即为国徽面），
        StringBody outsideResrult = new StringBody(outsString,Charset.forName("UTF-8"));
        entity.addPart("side",outsideResrult);
        return callHttpResponse(httpclient,post,entity);
    }
    /**
     * 用于识别通过接口上传的银行卡图片上的卡号和相关信息。
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String getInfoFromOcrBankCard() throws ClientProtocolException, IOException, SignatureException {
        return getInfoFromImage(ConstantsSence.POST_URLC,"image_file");
    }
    /**
     * 用于识别行驶证图片上的文字信息
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String getInfoFromOcrVehicleLicense() throws ClientProtocolException, IOException, SignatureException {
        return getInfoFromImage(ConstantsSence.POST_URLE,"image_file");
    }
    /**
     * 用于识别驾驶证图片上的文字信息。
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String getInfoFromOcrDrivingLicense() throws ClientProtocolException, IOException, SignatureException {
        return getInfoFromImage(ConstantsSence.POST_URLF,"image_file");
    }
    /**
     * 用于识别营业执照上的文字信息。
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String getInfoFromOcrBusinessLicense() throws ClientProtocolException, IOException, SignatureException {
        return getInfoFromImage(ConstantsSence.POST_URLG,"license_image");
    }
    /**
     * 用于识别组织机构代码上的文字信息
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String getInfoFromOcrOrganCode() throws ClientProtocolException, IOException, SignatureException {
        return getInfoFromImage(ConstantsSence.POST_URLH,"organization_image");
    }
    /**
     * 用于识别图片上的文字信息
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String getInfoFromOcrGeneralImage() throws ClientProtocolException, IOException, SignatureException {
        return getInfoFromImage(ConstantsSence.POST_URLI,"image_file");
    }
    /**
     * 用于获取识别人脸识别后的结果信息
     * @param biztoken 当次SenseID交互活体检测H5的biztoken
     * @param result_id 当次SenseID交互活体检测H5的result_id, 用于获取当次活体检测结果；如果不传该参数，则每次均返回最新的检测结果
     * @throws IOException
     * @throws URISyntaxException
     */
    public static String getDetectionResult(String biztoken,String result_id) throws IOException, URISyntaxException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(ConstantsSence.GET_URL);
        URI uri = new URIBuilder(httpGet.getURI())
                .addParameter("biztoken", biztoken)
                .addParameter("result_id", result_id)
                .build();
        httpGet.setURI(uri);
        return callHttpAllResponse(httpclient,null,httpGet);

    }
    /**
     * 用户图片类型的数据读取
     * @param postUrl
     * @param partString
     * @throws ClientProtocolException
     * @throws IOException
     */
    private static String getInfoFromImage(String postUrl,String partString) throws ClientProtocolException, IOException, SignatureException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost(postUrl);
        FileBody fileBody = new FileBody(new File(filepath));
        MultipartEntity entity = new MultipartEntity();
        entity.addPart(partString, fileBody);
        return callHttpResponse(httpclient,post,entity);
    }
    /**
     * 请求并获取响应结果
     * @param httpclient
     * @param post
     * @param entity
     * @throws IOException
     */
    private static String callHttpResponse(   HttpClient httpclient ,HttpPost post,MultipartEntity entity) throws IOException, SignatureException {
        post.setEntity(entity);
        //将AUTHORIZATION替换为根据API_KEY和API_SECRET得到的签名认证串
        post.setHeader("Authorization", senceGenerateString.genHeaderParam(ConstantsSence.AUTHORIZATION, ConstantsSence.SECRET));
       return callHttpAllResponse(httpclient,post,null);
    }
    /**
     * 请求并获取响应结果
     * @param httpclient
     * @param post
     * @param httpGet
     * @throws IOException
     */
    private static String callHttpAllResponse(HttpClient httpclient ,HttpPost post,HttpGet httpGet ) throws IOException {
        String resultStr = "";
        HttpResponse response = null;
        if(post != null){
             response = httpclient.execute(post);
        }else{
             response = httpclient.execute(httpGet);
        }
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entitys = response.getEntity();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(entitys.getContent()));
            resultStr = reader.readLine();
            System.out.println(resultStr);
        }else{
            HttpEntity r_entity = response.getEntity();
            resultStr = EntityUtils.toString(r_entity);
            System.out.println("错误码是："+response.getStatusLine().getStatusCode()+"  "+response.getStatusLine().getReasonPhrase());
            System.out.println("出错原因是："+resultStr);
            //你需要根据出错的原因判断错误信息，并修改
        }
        httpclient.getConnectionManager().shutdown();
        return resultStr;
    }


    public static void main(String[] args) throws ClientProtocolException, IOException, SignatureException, URISyntaxException {
       // getHTbiztoken();
    }
}