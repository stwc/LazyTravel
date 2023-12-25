package com.lazytravel.customer.util;

import com.lazytravel.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class TestBase64 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
        final String text = "字串文字";
        final byte[] textByte = text.getBytes("UTF-8");
        //編碼
        final String encodedText = encoder.encodeToString(textByte);
        System.out.println(encodedText);
        //解碼
        System.out.println(new String(decoder.decode(encodedText), "UTF-8"));

        JedisPool pool = JedisPoolUtil.getJedisPool();
        Jedis jedis = pool.getResource();
        jedis.set("RegisterAuth:11007", "abcd1234");
    }
}
