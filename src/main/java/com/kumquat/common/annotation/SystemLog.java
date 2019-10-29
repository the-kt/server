package com.kumquat.common.annotation;

import java.lang.annotation.*;

/**
 * Created by 13260 on 2019/9/18.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    String description() default "";
}
