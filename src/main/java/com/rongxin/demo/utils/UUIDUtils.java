package com.rongxin.demo.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * 生成32位主键
 *
 * @Date 2022-05-28
 */
public class UUIDUtils {

    /**
     * 生成32位的uuid
     * @return 32位的uuid
     */
    public static String generateUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static String generateAddOf5(String str) {
        // 取出后5位
        String numStr = str.substring(str.length() - 4);
        if (!StringUtils.isEmpty(numStr)) {
            // 取出字符串的长度
            int size = numStr.length();
            // 将该数字加一
            int num = Integer.parseInt(numStr) + 1;
            String added = String.valueOf(num);
            size = Math.min(size, added.length());
            //拼接字符串
            return str.subSequence(0, str.length() - size) + added;
        } else {
            throw new NumberFormatException();
        }
    }

    public static String generateAddOf9(String str) {
        // 取出后5位
        String numStr = str.substring(str.length() - 8);
        if (!StringUtils.isEmpty(numStr)) {
            // 取出字符串的长度
            int size = numStr.length();
            // 将该数字加一
            int num = Integer.parseInt(numStr) + 1;
            String added = String.valueOf(num);
            size = Math.min(size, added.length());
            //拼接字符串
            return str.subSequence(0, str.length() - size) + added;
        } else {
            throw new NumberFormatException();
        }
    }

    public static void main(String[] args) {

        System.out.println((int)Math.random()*10000);
    }

}
