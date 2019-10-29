package com.kumquat.attachment.dao;

import com.kumquat.common.config.MyMapper;
import com.kumquat.attachment.domain.FileInfo;

import java.util.List;
import java.util.Map;

public interface FileInfoMapper extends MyMapper<FileInfo> {

    int updateFileInfoByIds(Map<String,Object> map);

    List<FileInfo> getFileInfoByTableName(Map<String,String> map);

}