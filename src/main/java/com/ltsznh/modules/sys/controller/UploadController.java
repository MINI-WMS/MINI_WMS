package com.ltsznh.modules.sys.controller;


import com.google.gson.Gson;
import com.ltsznh.common.exception.FamilyException;
import com.ltsznh.common.utils.ConfigParam;
import com.ltsznh.common.utils.R;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

//import sun.nio.ch.FileChannelImpl;

/**
 * 系统配置信息
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2016年12月4日 下午6:55:53
 */
@RestController
@RequestMapping("/upload")
public class UploadController extends AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 允许上传的扩展名
     */
    private static String[] extensionPermit = {"xls", "xlsx"};

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("Hello, server start ok.");
    }

    /**
     * 上传excel
     */
    @RequestMapping(value = "/excel", method = RequestMethod.POST)
    public R excel(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");

        File uploadedFile = null;
        try {

            String fileName = file.getOriginalFilename();
            String fileExtension = FilenameUtils.getExtension(fileName);
            if (!Arrays.asList(extensionPermit).contains(fileExtension)) {
                throw new Exception("文件格式错误！");
            }

            if (file.isEmpty()) return R.error("空文件，上传失败！");

            if (file.getSize() > 100 * 1024 * 1024L) {
                return R.error("文件过大");
            }


            logger.info("Servlet", getUserId(), fileName + " " + file.getSize());

            if (fileExtension != null && fileExtension.length() > 0) {
                fileName = UUID.randomUUID() + "." + fileExtension;
            } else {
                fileName = UUID.randomUUID() + "_" + fileName;
            }

            uploadedFile = new File(ConfigParam.getUploadExcelPath(), fileName);

            logger.info("Servlet", "get upload file:", uploadedFile.getAbsolutePath());
            if (!uploadedFile.getParentFile().exists())
                if (!uploadedFile.getParentFile().mkdirs())
                    return R.error("创建上传文件夹失败，请联系管理员！");
            uploadedFile.delete();
            if (uploadedFile.createNewFile()) {
                FileUtils.copyInputStreamToFile(file.getInputStream(), uploadedFile);
                response.setStatus(HttpServletResponse.SC_CREATED);
                return R.ok(fileName);
            } else {
                throw new IOException("The file already exists in repository.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "An error occurred while creating the file : "
                                + e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (uploadedFile.exists())
                uploadedFile.delete();
            return R.error("系统内部错误！");
        }
    }
}
