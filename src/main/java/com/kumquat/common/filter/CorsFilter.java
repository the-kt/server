package com.kumquat.common.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by 13260 on 2019/5/12.
 */
@Component
public class CorsFilter implements Filter {

    @Override
    public  void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type,AUTH_ID");
        response.addHeader("Access-Control-Allow-Credentials", "false");
        System.out.println("*********************************过滤器被使用**************************");
        chain.doFilter(req, res);
    }
    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}
