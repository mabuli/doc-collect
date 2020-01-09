/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjx.common.validator;

import io.dfjx.common.exception.RRException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author Mark mazong@gmail.com
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }
}
