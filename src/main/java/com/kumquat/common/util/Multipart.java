package com.kumquat.common.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.util.Iterator;

/**
 * Created by 13260 on 2019/5/16.
 */
public class Multipart {

    public MultipartFile getUploadFile(HttpServletRequest request) {

        // 解析器解析request的上下文
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        MultipartFile multifile = null;
        FileInputStream inputStream = null;

        // 先判断request中是否包涵multipart类型的数据
        if (multipartResolver.isMultipart(request)) {
            // 再将request中的数据转化成multipart类型的数据
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = multiRequest.getFileNames();
            while (iterator.hasNext()) {
                String name = (String) iterator.next();
                multifile = multiRequest.getFile(name);
            }
        }



        return multifile;
    }


}
