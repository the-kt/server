package com.kumquat.common.shiro;

import com.alibaba.fastjson.JSON;
import com.kumquat.common.domain.ResponseBo;
import com.kumquat.common.util.HttpUtils;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:deepblue
 * @Date:2019/4/30 11:57
 * @Description:
 **/
public class CustomUserFilter extends UserFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (HttpUtils.isAjaxRequest((HttpServletRequest) request)) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
            httpServletResponse.setContentType("application/json; charset=utf-8");
            httpServletResponse.getWriter().print(JSON.toJSON(ResponseBo.error()));
            return false;
        } else {
            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }
}
