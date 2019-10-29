package com.kumquat.common.exception;

import com.kumquat.common.domain.ResponseBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:deepblue
 * @Date:2019/5/15 17:14
 * @Description:
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ResponseBo defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        return ResponseBo.error("操作失败");
    }
}

