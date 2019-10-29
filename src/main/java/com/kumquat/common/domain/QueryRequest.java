package com.kumquat.common.domain;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * @Author:deepblue
 * @Date:2019/4/29 18:00
 * @Description:
 **/
public class QueryRequest implements Serializable {
    private int pageSize;
    private int pageNum;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("pageSize", pageSize)
                .add("pageNum", pageNum)
                .toString();
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
