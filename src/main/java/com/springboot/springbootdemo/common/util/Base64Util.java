package com.springboot.springbootdemo.common.util;

import java.io.UnsupportedEncodingException;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

/**
 * base64 编码、解码util
 *
 * @author cbq
 * @date 2018-12-7
 */
@Slf4j
public class Base64Util {
    /**
     * 将 s 进行 BASE64 编码
     *
     * @return String
     * @author cbq
     * @date  2018-12-7
     */
    public static String encode(String s) {
        if (s == null)
            return null;
        String res = "";
        try {
            res = new sun.misc.BASE64Encoder().encode(s.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 将 BASE64 编码的字符串 s 进行解码
     *
     * @return String
     * @author lifq
     * @date 2015-3-4 上午09:24:26
     */
    public static String decode(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b,"UTF-8");
        } catch (Exception e) {
            return null;
        }
    }
    /**
     *
     * @return void
     * @author lifq
     * @date 2015-3-4 上午09:23:17
     */
    public static void main(String[] args) {
        //String base64String="/home/yanfa/test/ffmpeg-4.1-64bit-static/test.mp4";
        String p = "/home/yanfa/test/ffmpeg-4.1-64bit-static/test2.mp4";
        String u = "rtmp://192.168.100.181:1935/live/cai";
        /*System.out.println(Base64Util.encode(p));
        System.out.println(Base64Util.decode(u));*/
        log.info("http://192.168.100.181:7999/api/rtmp?srcurl={}&rtmpurl={}",Base64Util.encode(p),Base64Util.encode(u));


    }
}
