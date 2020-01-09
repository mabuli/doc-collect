/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjx.common.utils;

/**
 * Redis所有Keys
 *
 * @author Mark mazong@gmail.com
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }

    public static String getShiroSessionKey(String key){
        return "sessionid:" + key;
    }
}
