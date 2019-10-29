package com.kumquat.attachment.service.impl;

import com.kumquat.attachment.dao.FileInfoMapper;
import com.kumquat.attachment.domain.FileInfo;
import com.kumquat.attachment.service.FileInfoService;
import com.kumquat.common.config.KumquatProperties;
import com.kumquat.common.domain.ResponseBo;
import com.kumquat.common.service.impl.BaseService;
import com.kumquat.common.util.StrUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by 13260 on 2019/5/16.
 */
@Service("FileInfoService")
public class FileInfoServiceImpl extends BaseService<FileInfo> implements FileInfoService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Autowired
    private KumquatProperties kumquatProperties;

    @Override
    public ResponseBo upload(MultipartFile multifile, HttpServletRequest request) {

        String guid = UUID.randomUUID().toString();

        String fromID = request.getParameter("refid");

//        Multipart part = new Multipart();
//        MultipartFile multifile = part.getUploadFile(request);
        if (null == multifile) {
            return ResponseBo.error();
        }
        String ext = null;
        String storeFile = null;
        String fileName = null;
        String fileSavePath = "";
        String storageFolder="";

        try {
            fileName = StrUtil.isNullOrEmpty(fileName) ? multifile.getOriginalFilename() : fileName;
            fileName = new String(fileName.getBytes("UTF-8"), "UTF-8");
            ext = FilenameUtils.getExtension(fileName); //fileName.split("\\.")[1];
            storeFile = guid + "." + ext;

            // 保存文件
            storageFolder = GetFileStorageFolder(guid);
            fileSavePath = storageFolder + storeFile;
            File file = new File(storageFolder);

            if (!file.exists()) {
                file.mkdirs();
            }

            File file1 = new File(fileSavePath);
            multifile.transferTo(file1);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileinfoRefId(fromID);
        fileInfo.setFileinfoId(guid);
        fileInfo.setFileinfoFileType(ext);
        fileInfo.setFileinfoServerFileName(storeFile);
        fileInfo.setFileinfoClientFileName(fileName);
        fileInfo.setFileinfoServerPath(fileSavePath);

        fileInfoMapper.insert(fileInfo);

        if(isDocFile(ext)){
            //转化office pdf
//            office2PDF.office2PDF(fileSavePath,storageFolder+guid+".pdf", kumquatProperties.getOpenoffice());
        }

        return ResponseBo.ok(guid);
    }

    private boolean isDocFile(String extName) {
        boolean result = false;
        String fileExt = ".doc;.txt;.docx;";
        result = fileExt.contains(extName.toLowerCase());

        return result;
    }

    @Override
    public String GetFileStorageFolder(String actualFile) {
//        RainbowProperties rp = new RainbowProperties();

        String webInfPath = kumquatProperties.getUploadFolder();

        String path = String.format("%s%s/%s/", webInfPath, actualFile.charAt(0), actualFile.charAt(1));
        return path;
    }

    @Override
    public ResponseBo getFileList(String id) {

        Example example = new Example(FileInfo.class);
        example.createCriteria().andEqualTo("fileinfoRefId", id);
        List<FileInfo> list = fileInfoMapper.selectByExample(example);
        return ResponseBo.ok(list);

    }

    @Override
    public void downloadAccessoryByid(String id, int type,HttpServletResponse response) {

        FileInfo fileInfo = fileInfoMapper.selectByPrimaryKey(id);

        if (fileInfo != null) {
            String path = fileInfo.getFileinfoServerPath();

            //pdf
            if(type==2){
                path = path.substring(0,path.lastIndexOf("."))+".pdf";
            }

            File file = new File(path);

            if (!file.exists()) {
                return;
            }

            try {
                response.setHeader("content-disposition", "Attachment;filename=" + URLEncoder.encode(fileInfo.getFileinfoClientFileName(), "utf-8"));
                OutputStream out = response.getOutputStream();
                out.write(FileUtils.readFileToByteArray(file));
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void updateFileInfoByIds(List<FileInfo> list, String id) {
        if (list != null && list.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("fileIds", list);
            fileInfoMapper.updateFileInfoByIds(map);
        }
    }
}
