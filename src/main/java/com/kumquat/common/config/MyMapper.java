package com.kumquat.common.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author:liuhao
 * @Date:2019/4/29 13:22
 * @Description:
 **/
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
