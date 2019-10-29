package com.kumquat.common.service.impl;

import com.kumquat.common.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/4/29 14:05
 * @Description:
 **/
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true,rollbackFor = Exception.class)
public abstract class BaseService<T> implements IService<T> {

    @Autowired
    protected Mapper<T> mapper;


    public Mapper<T> getMapper() {
        return mapper;
    }


    /**
     * @Description: 返回所有的数据
     * @Param: []
     * @return: java.util.List<T>
     * @Author: deepblue
     * @Date: 2019/4/29
     */
    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    /**
     * @Description: 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     * @Param: [key]
     * @return: T
     * @Author: deepblue
     * @Date: 2019/4/29
     */
    @Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }


    /**
     * @Description: 保存一个实体，null的属性也会保存，不会使用数据库默认值
     * @Param: [entity]
     * @return: int
     * @Author: deepblue
     * @Date: 2019/4/29
     */
    @Override
    @Transactional
    public int save(T entity) {
        return mapper.insert(entity);
    }

    /**
     * @Description: 根据主键字段进行删除，方法参数必须包含完整的主键属性
     * @Param: [key]
     * @return: int
     * @Author: deepblue
     * @Date: 2019/4/29
     */
    @Override
    @Transactional
    public int deleteByKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }


    /**
     * @Description: 批量删除
     * @Param: [list, property, clazz]
     * @return: int
     * @Author: deepblue
     * @Date: 2019/4/29
     */
    @Override
    @Transactional
    public int batchDelete(List<String> list, String property, Class<T> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return mapper.deleteByExample(example);
    }


    /**
     * @Description: 根据主键更新实体全部字段，null值会被更新
     * @Param: [entity]
     * @return: int
     * @Author: deepblue
     * @Date: 2019/4/29
     */
    @Override
    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }


    /**
     * @Description: 根据主键更新属性不为null的值
     * @Param: [entity]
     * @return: int
     * @Author: deepblue
     * @Date: 2019/4/29
     */
    @Override
    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }


    /**
     * @Description: 根据Example条件进行查询, 这个查询支持通过Example类指定查询列，通过selectProperties方法指定查询列
     * @Param: [example]
     * @return: java.util.List<T>
     * @Author: deepblue
     * @Date: 2019/4/29
     */
    @Override
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }
}
