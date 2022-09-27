package com.rongxin.web.util;

import com.rongxin.common.utils.uuid.IdUtils;
import org.apache.commons.codec.binary.Base64;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageBase64ToFile {

    public File base64ToFile(String base64) throws IOException {
        if(base64.contains("data:image")){
            base64 = base64.substring(base64.indexOf(",")+1);
        }
        base64 = base64.toString().replace("\r\n", "");
        //创建文件目录
        String prefix=".jpg";
        File file = File.createTempFile(IdUtils.simpleUUID(), prefix);
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            // BASE64Decoder decoder = new BASE64Decoder();
            // byte[] bytes =  decoder.decodeBuffer(base64);
            byte[] bytes = Base64.decodeBase64(base64);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        }finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
}
