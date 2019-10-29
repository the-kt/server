package com.kumquat.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 13260 on 2019/9/7.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanFieldAnnotation {

    /**
     * 标注该属性的顺序
     * @return 该属性的顺序
     */
    int order();
}
