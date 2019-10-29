package com.kumquat.common.interceptor;

import com.kumquat.common.cache.EHCacheUtils;
import net.sf.ehcache.CacheManager;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;


/**
 * Created by 13260 on 2019/9/1.
 */
public class CrossInterceptor extends HandlerInterceptorAdapter {

    private  CacheManager cacheManager;

    public CrossInterceptor(CacheManager cache){
        cacheManager = cache;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {


        String method = request.getMethod();
        if(HttpMethod.OPTIONS.toString().equals(method)){
            return super.preHandle(request, response, handler);
        }

        String authid = request.getHeader("AUTH_ID");
        if (null == authid) {
            authid = request.getParameter("AUTH_ID");
        }
        Enumeration<String> srrr = request.getHeaderNames();

        String url = request.getRequestURI();
        if (url.indexOf("/swagger-") > -1) {
            return super.preHandle(request, response, handler);
        }


        System.out.print(url);
        String[] Urls = new String[]{"/login","/error" };

        if (null == authid) {
            for (String string : Urls) {

                if (url.indexOf(string)>-1) {
                    return super.preHandle(request, response, handler);
                }
            }
            response.setStatus(403);
            return false;
        } else {

            for (String string : Urls) {
                if (url.indexOf(string)>-1) {
                    return super.preHandle(request, response, handler);
                }
            }
            Object user = EHCacheUtils.getCache(cacheManager,authid);
            if (null == user) {
                response.setStatus(403);
                return false;
            }

        }

        return super.preHandle(request, response, handler);
    }
}
