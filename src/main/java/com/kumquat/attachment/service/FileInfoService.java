package com.kumquat.attachment.service;

import com.kumquat.attachment.domain.FileInfo;
import com.kumquat.common.domain.ResponseBo;
import com.kumquat.common.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 13260 on 2019/5/16.
 */
public interface FileInfoService extends IService<FileInfo> {

    ResponseBo upload(MultipartFile file,HttpServletRequest request);

    ResponseBo getFileList(String id);

    void downloadAccessoryByid(String id,int type,HttpServletResponse response);

    void updateFileInfoByIds(List<FileInfo> list,String id);

    String GetFileStorageFolder(String id);
}
