/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjx.datasource.annotation;

import java.lang.annotation.*;

/**
 * 多数据源注解
 *
 * @author Mark mazong@gmail.com
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    String value() default "";
}
