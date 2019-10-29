package com.kumquat.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Author:liuhao
 * @Date:2019/4/28 18:10
 * @Description:获取HttpServletReques
 **/
public class HttpContextUtils {

    private HttpContextUtils(){

    }


    /**
    * @Description:
    * @Param: []
    * @return: javax.servlet.http.HttpServletRequest
    * @Author: haol
    * @Date: 2019/4/28
    */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
