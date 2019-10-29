package com.kumquat.common.domain;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 13260 on 2019/6/30.
 */
public class PagingEntity<T> implements Serializable {

    public int pageNo;

    public int pageCount;

    public int pageSize;

    public int recordCount;

    public List<T> currentList;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public List<T> getCurrentList() {
        return currentList;
    }

    public void setCurrentList(List<T> currentList) {
        this.currentList = currentList;
    }
    public PagingEntity(PageInfo<T> pageInfo) {
        super();
        this.pageNo = pageInfo.getPageNum();
        this.pageCount = pageInfo.getPages();
        this.pageSize = pageInfo.getPageSize();
        this.recordCount = new Long(pageInfo.getTotal()).intValue();
        this.setCurrentList(pageInfo.getList());
    }

}
