package com.kumquat.attachment.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "file_info")
public class FileInfo {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "fileinfo_id")
    public String fileinfoId;

    /**
     * 引用此附件的ID
     */
    @Column(name = "fileinfo_ref_id")
    public String fileinfoRefId;

    /**
     * 文件类型
     */
    @Column(name = "fileinfo_file_type")
    public String fileinfoFileType;

    /**
     * 上传到服务器后的文件名
     */
    @Column(name = "fileinfo_server_file_name")
    public String fileinfoServerFileName;

    /**
     * 上传前用户本地的文件名
     */
    @Column(name = "fileinfo_client_file_name")
    public String fileinfoClientFileName;

    /**
     * 服务器上面的文件路径
     */
    @Column(name = "fileinfo_server_path")
    public String fileinfoServerPath;

    /**
     * 上传人id
     */
    @Column(name = "fileinfo_upload_user_id")
    public String fileinfoUploadUserId;

    /**
     * 上传时间
     */
    @Column(name = "fileinfo_upload_date")
    public Date fileinfoUploadDate;

    /**
     * 内容
     */
    @Column(name = "fileinfo_content")
    public String fileinfoContent;

    /**
     * 是否导入0 否 1 是
     */
    @Column(name = "is_import")
    public Integer isImport;
    /**
     * 获取主键ID
     *
     * @return fileinfo_id - 主键ID
     */
    public String getFileinfoId() {
        return fileinfoId;
    }

    /**
     * 设置主键ID
     *
     * @param fileinfoId 主键ID
     */
    public void setFileinfoId(String fileinfoId) {
        this.fileinfoId = fileinfoId == null ? null : fileinfoId.trim();
    }

    /**
     * 获取引用此附件的ID
     *
     * @return fileinfo_ref_id - 引用此附件的ID
     */
    public String getFileinfoRefId() {
        return fileinfoRefId;
    }

    /**
     * 设置引用此附件的ID
     *
     * @param fileinfoRefId 引用此附件的ID
     */
    public void setFileinfoRefId(String fileinfoRefId) {
        this.fileinfoRefId = fileinfoRefId == null ? null : fileinfoRefId.trim();
    }

    /**
     * 获取文件类型
     *
     * @return fileinfo_file_type - 文件类型
     */
    public String getFileinfoFileType() {
        return fileinfoFileType;
    }

    /**
     * 设置文件类型
     *
     * @param fileinfoFileType 文件类型
     */
    public void setFileinfoFileType(String fileinfoFileType) {
        this.fileinfoFileType = fileinfoFileType == null ? null : fileinfoFileType.trim();
    }

    /**
     * 获取上传到服务器后的文件名
     *
     * @return fileinfo_server_file_name - 上传到服务器后的文件名
     */
    public String getFileinfoServerFileName() {
        return fileinfoServerFileName;
    }

    /**
     * 设置上传到服务器后的文件名
     *
     * @param fileinfoServerFileName 上传到服务器后的文件名
     */
    public void setFileinfoServerFileName(String fileinfoServerFileName) {
        this.fileinfoServerFileName = fileinfoServerFileName == null ? null : fileinfoServerFileName.trim();
    }

    /**
     * 获取上传前用户本地的文件名
     *
     * @return fileinfo_client_file_name - 上传前用户本地的文件名
     */
    public String getFileinfoClientFileName() {
        return fileinfoClientFileName;
    }

    /**
     * 设置上传前用户本地的文件名
     *
     * @param fileinfoClientFileName 上传前用户本地的文件名
     */
    public void setFileinfoClientFileName(String fileinfoClientFileName) {
        this.fileinfoClientFileName = fileinfoClientFileName == null ? null : fileinfoClientFileName.trim();
    }

    /**
     * 获取服务器上面的文件路径
     *
     * @return fileinfo_server_path - 服务器上面的文件路径
     */
    public String getFileinfoServerPath() {
        return fileinfoServerPath;
    }

    /**
     * 设置服务器上面的文件路径
     *
     * @param fileinfoServerPath 服务器上面的文件路径
     */
    public void setFileinfoServerPath(String fileinfoServerPath) {
        this.fileinfoServerPath = fileinfoServerPath == null ? null : fileinfoServerPath.trim();
    }

    /**
     * 获取上传人id
     *
     * @return fileinfo_upload_user_id - 上传人id
     */
    public String getFileinfoUploadUserId() {
        return fileinfoUploadUserId;
    }

    /**
     * 设置上传人id
     *
     * @param fileinfoUploadUserId 上传人id
     */
    public void setFileinfoUploadUserId(String fileinfoUploadUserId) {
        this.fileinfoUploadUserId = fileinfoUploadUserId == null ? null : fileinfoUploadUserId.trim();
    }

    /**
     * 获取上传时间
     *
     * @return fileinfo_upload_date - 上传时间
     */
    public Date getFileinfoUploadDate() {
        return fileinfoUploadDate;
    }

    /**
     * 设置上传时间
     *
     * @param fileinfoUploadDate 上传时间
     */
    public void setFileinfoUploadDate(Date fileinfoUploadDate) {
        this.fileinfoUploadDate = fileinfoUploadDate;
    }

    /**
     * 获取内容
     *
     * @return fileinfo_content - 内容
     */
    public String getFileinfoContent() {
        return fileinfoContent;
    }

    /**
     * 设置内容
     *
     * @param fileinfoContent 内容
     */
    public void setFileinfoContent(String fileinfoContent) {
        this.fileinfoContent = fileinfoContent == null ? null : fileinfoContent.trim();
    }
}