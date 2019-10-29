package com.kumquat.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:deepblue
 * @Date:2019/4/30 11:59
 * @Description:
 **/
public class HttpUtils {
    private static Logger log = LoggerFactory.getLogger(HttpUtils.class);

    private static final String USER_AGENT = "user-agent";
    private static final String USER_AGENT_VALUE = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)";
    private static final String CONNECTION = "connection";
    private static final String CONNECTION_VALUE = "Keep-Alive";
    private static final String ACCEPT = "accept";
    private static final String UTF8 = "utf-8";
    private static final String ACCEPT_CHARSET = "Accept-Charset";
    private static final String CONTENTTYPE = "contentType";
    private static final String SSL = "ssl";

    protected HttpUtils() {

    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }
}
