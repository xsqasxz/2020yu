package com.small.utils.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5Utils {

    /**
     * 获得字符串的MD5签名
     *
     * @param text
     * @return 32位16进制字符串
     */
    public static String getMD5Sign(String text) {

        return getMD5Sign(text.getBytes());
    }

    /**
     * 获得文件的MD5签名
     *
     * @param file
     * @return 32位16进制字符串
     */
    public static String getFileMD5Sign(File file) {

        try (final FileInputStream ins = new FileInputStream(file)) {

            final int size = ins.available();
            if (size > MAX_BYTE_SIZE) {
                throw new IndexOutOfBoundsException(String.format("文件大小超过%dM的限制，如果想提取打文件的签名，"
                        + "请使用【getMD5Sign(InputStream inStream)】方法", MAX_BYTE_SIZE / 1024 / 1024));
            }

            byte[] bytes = new byte[size];
            ins.read(bytes);

            return getMD5Sign(bytes);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得文件的MD5签名
     *
     * @param fileName 文件名
     * @return 32位16进制字符串
     */
    public static String getFileMD5Sign(String fileName) {

        return getFileMD5Sign(new File(fileName));
    }

    public static String getMD5Sign(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            StringBuilder sb = new StringBuilder();

            byte[] buff = md.digest(bytes);
            for (byte b : buff) {

                int _val = b & 0xff; //去byte类型的符号位
                //如果小于16则前补0
                if (_val < 16) {
                    sb.append(0);
                }

                sb.append(Integer.toHexString(_val));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error("MD5签名失败", e);
        }
        return null;
    }

    private static final int MAX_BYTE_SIZE = 1024 * 1024 * 50; //文件最大字节数
    private static Log logger = LogFactory.getLog(MD5Utils.class);
}
