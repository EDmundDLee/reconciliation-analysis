package com.rongxin.web.framework.web.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObject;
import com.rongxin.common.utils.oss.OSSFactory;

import com.rongxin.web.framework.web.service.ISysOssService;
import com.rongxin.web.util.WordToPDFHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;


/**
 * 参数配置 服务层实现
 * 
 * @author rx
 */
@Service
public class SysOssServiceImpl implements ISysOssService
{

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Value("${oss.FOLDER}")
    private String FOLDER;

    @Value("${oss.endpoint}")
    private String endpoint;

    public  String getImportPath() {
        return FOLDER + "import";
    }

    public  String getAvatarPath() {
        return FOLDER + "avatar";
    }

    public  String getDownloadPath() {
        return FOLDER + "download/";
    }

    public  String getUploadPath() {
        return FOLDER + "/upload";
    }
    @Override
    public String upload(MultipartFile file,String fileName,String opath) throws IOException {

        // oss中的文件夹名
        OSSFactory ossUtil = new OSSFactory();
        // 上传oss
        ossUtil.uploadFile2OSS(file.getInputStream(), endpoint, accessKeyId, accessKeySecret, bucketName, opath, fileName);
        //获取文件的URl地址
        String fileUrl = ossUtil.getImgUrl(fileName, endpoint, accessKeyId, accessKeySecret, bucketName, opath);

        return fileUrl;
    }

    @Override
    public boolean deleteFile(String fileName, String endpoint, String accessKeyId, String accessKeySecret, String bucketName) {
        OSSFactory ossUtil = new OSSFactory();
        boolean flag = ossUtil.deleteFile(fileName, endpoint, accessKeyId, accessKeySecret, bucketName);
        return flag;
    }

    @Override
    public List<String> deleteFileAll(List<String> keys, String endpoint, String accessKeyId, String accessKeySecret, String bucketName) {
        OSSFactory ossUtil = new OSSFactory();
        List<String> deletedObjects = ossUtil.deleteFileAll(keys, endpoint, accessKeyId, accessKeySecret, bucketName);
        return deletedObjects;
    }
    @Override
    public void convertWordtoPdf(String fileName) throws FileNotFoundException {
        // oss服务获取文件流
        OSSFactory ossUtil = new OSSFactory();
        InputStream inputStream = null;
        OSS K = ossUtil.getOSSClient(endpoint, accessKeyId, accessKeySecret);
        OSSObject ossObject = K.getObject(bucketName, FOLDER+ fileName);
        inputStream = ossObject.getObjectContent();
        //将文件流转换
      //  WordToHtml w = new WordToHtml();
       // String parentDirectory = "D:\\test\\";
       // if (!parentDirectory.endsWith("\\")) {
       //     parentDirectory = parentDirectory + "\\";
      //  }
      //  w.docxConvert(parentDirectory,inputStream,"success");
        WordToPDFHelper  wp = new WordToPDFHelper();
        if("docx".equals(fileName.substring(fileName.lastIndexOf(".")+1))){
            wp.wordOfDocxToPdf(inputStream,  "D:/test/tes2.pdf");
        }
        if("doc".equals(fileName.substring(fileName.lastIndexOf(".")+1))){
           // wp.wordOfDocToPdf(inputStream,  "D:/test/tes2.pdf");
        }

    }
    /**
     * 批量下载为zip
     * @param req
     * @param response
     * @param keyList oss文件路径集合
     * @param fileName 下载出来的zip文件名(一般以合同名命名) 如：测试合同,zip
     */
//    public static void downForZip(HttpServletRequest req, HttpServletResponse response,
//                                  List keyList,String fileName ){
//        // 创建临时文件
//        File zipFile = null;
//        try {
//            //临时文件名称
//            zipFile = File.createTempFile("test", ".zip");
//            FileOutputStream f = new FileOutputStream(zipFile);
///**
// * 作用是为任何OutputStream产生校验和
// * 第一个参数是制定产生校验和的输出流，第二个参数是指定Checksum的类型 (Adler32(较快)和CRC32两种)
// */
//            CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
//            // 用于将数据压缩成Zip文件格式
//            ZipOutputStream zos = new ZipOutputStream(csum);
//            OSSClient ossClient= OSSUtil.getInstance();
//            for (String ossFile : keyList) {
//                // 获取Object，返回结果为OSSObject对象
//                OSSObject ossObject = ossClient.getObject(OSSUtil.BUCKET, ossFile);
//                // 读去Object内容 返回
//                InputStream inputStream = ossObject.getObjectContent();
//                // 对于每一个要被存放到压缩包的文件，都必须调用ZipOutputStream对象的putNextEntry()方法，确保压缩包里面文件不同名
//                String name=ossFile.substring(ossFile.lastIndexOf("/")+1);
//                zos.putNextEntry(new ZipEntry(name));
//                int bytesRead = 0;
//                // 向压缩文件中输出数据
//                while ((bytesRead = inputStream.read()) != -1) {
//                    zos.write(bytesRead);
//                }
//                inputStream.close();
//                zos.closeEntry(); // 当前文件写完，定位为写入下一条项目
//            }
//            zos.close();
//            String header = req.getHeader("User-Agent").toUpperCase();
//            if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
//                fileName = URLEncoder.encode(fileName, "utf-8");
//                //IE下载文件名空格变+号问题
//                fileName = fileName.replace("+", "%20");
//            } else {
//                fileName = new String(fileName.getBytes(), "ISO8859-1");
//            }
//            response.reset();
//            response.setContentType("text/plain");
//            response.setContentType("application/octet-stream; charset=utf-8");
//            response.setHeader("Location", fileName);
//            response.setHeader("Cache-Control", "max-age=0");
//            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
//            FileInputStream fis = new FileInputStream(zipFile);
//            BufferedInputStream buff = new BufferedInputStream(fis);
//            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
//            byte[] car = new byte[1024];
//            int l = 0;
//            while (l < zipFile.length()) {
//                int j = buff.read(car, 0, 1024);
//                l += j;
//                out.write(car, 0, j);
//            }
//            // 关闭流
//            fis.close();
//            buff.close();
//            out.close();
//            ossClient.shutdown();
//            // 删除临时文件
//            zipFile.delete();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}
