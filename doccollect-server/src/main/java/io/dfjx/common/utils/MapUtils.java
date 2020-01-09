/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjx.common.utils;

import java.util.HashMap;


/**
 * Map工具类
 *
 * @author Mark mazong@gmail.com
 */
public class MapUtils extends HashMap<String, Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
