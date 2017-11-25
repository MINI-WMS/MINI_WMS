package com.ltsznh.common.utils;

/**
 * Redis所有Keys
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-07-18 19:51
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }

    public static String getShiroSessionKey(String key){
        return "sessionid:" + key;
    }
}
