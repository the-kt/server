package com.kumquat.common.util;

import javax.servlet.ServletException;
import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import static org.springframework.util.StreamUtils.BUFFER_SIZE;

/**
 * Created by 13260 on 2019/8/28.
 */
public class ZipUtils {

    public static void doCompress(String srcFile, String zipFile) throws Exception {
        doCompress(new File(srcFile), new File(zipFile));
    }

    /**
     * 文件压缩
     *
     * @param srcFile
     *            目录或者单个文件
     * @param destFile
     *            压缩后的ZIP文件
     */
    public static void doCompress(File srcFile, File destFile) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(destFile));
        if (srcFile.isDirectory()) {
            File[] files = srcFile.listFiles();
            for (File file : files) {
                doCompress(file, out);
            }
        } else {
            doCompress(srcFile, out);
        }
    }

    public static void doCompress(String pathname, ZipOutputStream out) throws IOException {
        doCompress(new File(pathname), out);
    }

    public static void doCompress(File file, ZipOutputStream out) throws IOException {
        if (!file.exists()) {
            return;
        }
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(file);
        out.putNextEntry(new ZipEntry(file.getName()));
        int len = 0;
        // 读取文件的内容,打包到zip文件
        while ((len = fis.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }


        out.flush();
        out.closeEntry();
        fis.close();
    }

    public static void doCompress(ByteArrayOutputStream baos, String name, ZipOutputStream out) throws IOException {

        out.putNextEntry(new ZipEntry(name));
        // 读取文件的内容,打包到zip文件
        byte[] xlsBytes = baos.toByteArray();
        out.write(xlsBytes);

        out.flush();
        out.closeEntry();
        baos.close();
    }

    /**
     * 压缩文件列表中的文件
     *
     * @param files
     * @param outputStream
     * @throws IOException
     */
    public static void zipFile(List files, ZipOutputStream outputStream) throws IOException, ServletException {
        try {
            int size = files.size();
            // 压缩列表中的文件
            for (int i = 0; i < size; i++) {
                File file = (File) files.get(i);
                zipFile(file, outputStream);
            }
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 将文件写入到zip文件中
     *
     * @param inputFile
     * @param outputstream
     * @throws Exception
     */
    public static void zipFile(File inputFile, ZipOutputStream outputstream) throws IOException, ServletException {
        try {
            if (inputFile.exists()) {
                if (inputFile.isFile()) {
                    FileInputStream inStream = new FileInputStream(inputFile);
                    BufferedInputStream bInStream = new BufferedInputStream(inStream);
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    outputstream.putNextEntry(entry);

                    final int MAX_BYTE = 10 * 1024 * 1024; // 最大的流为10M
                    long streamTotal = 0; // 接受流的容量
                    int streamNum = 0; // 流需要分开的数量
                    int leaveByte = 0; // 文件剩下的字符数
                    byte[] inOutbyte; // byte数组接受文件的数据

                    streamTotal = bInStream.available(); // 通过available方法取得流的最大字符数
                    streamNum = (int) Math.floor(streamTotal / MAX_BYTE); // 取得流文件需要分开的数量
                    leaveByte = (int) streamTotal % MAX_BYTE; // 分开文件之后,剩余的数量

                    if (streamNum > 0) {
                        for (int j = 0; j < streamNum; ++j) {
                            inOutbyte = new byte[MAX_BYTE];
                            // 读入流,保存在byte数组
                            bInStream.read(inOutbyte, 0, MAX_BYTE);
                            outputstream.write(inOutbyte, 0, MAX_BYTE); // 写出流
                        }
                    }
                    // 写出剩下的流数据
                    inOutbyte = new byte[leaveByte];
                    bInStream.read(inOutbyte, 0, leaveByte);
                    outputstream.write(inOutbyte);
                    outputstream.closeEntry();
                    bInStream.close(); // 关闭
                    inStream.close();
                }
            } else {
                throw new ServletException("文件不存在！");
            }
        } catch (IOException e) {
            throw e;
        }
    }


    /**
     * zip解压
     * @param srcFile        zip源文件
     * @param destDirPath     解压后的目标文件夹
     * @throws RuntimeException 解压失败会抛出运行时异常
     */
    public static void unZip(File srcFile, String destDirPath) throws RuntimeException {

        long start = System.currentTimeMillis();

        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }
        // 开始解压
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(srcFile);
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                System.out.println("解压" + entry.getName());
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + "/" + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath + "/" + entry.getName());
                    // 保证这个文件的父文件夹必须要存在
                    if(!targetFile.getParentFile().exists()){
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[BUFFER_SIZE];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    fos.close();
                    is.close();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("解压完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtils", e);
        } finally {
            if(zipFile != null){
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
