package com.kumquat.common.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:liuhao
 * @Date:2019/4/29 14:00
 * @Description:
 **/
@Service
public interface IService<T> {
    List<T> selectAll();

    T selectByKey(Object key);

    int save(T entity);

    int deleteByKey(Object key);

    int batchDelete(List<String> list,String property,Class<T> clazz);

    int updateAll(T entity);

    int updateNotNull(T entity);

    List<T> selectByExample(Object example);
}
