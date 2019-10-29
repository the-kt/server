package com.kumquat.common.domain;

import com.kumquat.attachment.domain.FileInfo;

import java.util.List;

/**
 * Created by 13260 on 2019/7/14.
 */
public class BaseExtendEntity {

     private List<FileInfo> attachmentList;

    public List<FileInfo> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<FileInfo> attachmentList) {
        this.attachmentList = attachmentList;
    }
}
