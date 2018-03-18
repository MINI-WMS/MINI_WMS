package com.ltsznh.modules.sys.controller;


import com.ltsznh.common.exception.FamilyException;
import com.ltsznh.common.utils.ConfigParam;
import com.ltsznh.common.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
//import sun.nio.ch.FileChannelImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

/**
 * 系统配置信息
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2016年12月4日 下午6:55:53
 */
@RestController
@RequestMapping("/download")
public class DownloadController extends AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("Hello, server start ok.");
    }

    /**
     * 下载excel
     */
    @RequestMapping("/excel")
    public R excel(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
        //获取excel文件名及要下载的文件名
        // 解决中文乱码，
        response.setCharacterEncoding("utf-8");
        try {

            String fileName = null;
            if (request.getParameter("fileName") != null)
                fileName = URLDecoder.decode(request.getParameter("fileName"), "utf-8");
            if (fileName == null || fileName.length() <= 0) {
                response.getWriter().write("download file server is running!");
                return R.ok("download file server is running!");
            }
            fileName.replace(".", "");
            fileName.replace(":", "");
            fileName.replace("\"", "");
            fileName.replace("\'", "");
            String filePath = ConfigParam.getDownloadExcelPath() + File.separator + fileName;
            File file = new File(filePath);

            if (!file.exists()) {//如果文件不存在
                response.getWriter().write("err,no file:" + fileName);
                return R.error(0, "文件不存在！");
            }

            FileChannel fc = null;
            RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "r");
            fc = randomAccessFile.getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0L, fc.size()).load();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // System.out.println(byteBuffer.isLoaded());
            byte[] bos = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                byteBuffer.get(bos, 0, byteBuffer.remaining());
            }

            String downloadFileName = null;
            if (request.getParameter("downloadFileName") != null)
                downloadFileName = URLDecoder.decode(request.getParameter("downloadFileName"), "utf-8");
            if (downloadFileName != null && !downloadFileName.equals("")) {
                fileName = downloadFileName;
            }

            // response.setContentType("application/msexcel");//magic is here
            // 解决下载文件名中文乱码问题
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + URLEncoder.encode(fileName, "utf-8") + "\"");

            response.setContentLength(bos.length);
            response.getOutputStream().write(bos);


//			try {// 释放文件
//				Method method = FileChannelImpl.class.getDeclaredMethod("unmap", MappedByteBuffer.class);
//				method.setAccessible(true);
//				method.invoke(FileChannelImpl.class, byteBuffer);
//			} catch (IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IllegalArgumentException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InvocationTargetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (NoSuchMethodException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} finally {
//				bos = null;
////				byteBuffer.clear();//9之后不用了
//				byteBuffer = null;
//				fc.close();
//				randomAccessFile.close();
//			}

            // 删除已下载文件
            File path = new File(filePath);//
            logger.error(this.getClass().toString(), "下载文件后删除文件", path.delete());

        } catch (FamilyException e) {
            logger.error(this.getClass().toString(), e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok();
    }

    /**
     * 下载excel
     */
    @RequestMapping("/template/excel")
    public R excelTemplate(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
        //获取excel文件名及要下载的文件名
        // 解决中文乱码，
        response.setCharacterEncoding("utf-8");
        try {

            String fileName = null;
            if (request.getParameter("fileName") != null)
                fileName = URLDecoder.decode(request.getParameter("fileName"), "utf-8");
            if (fileName == null || fileName.length() <= 0) {
                response.getWriter().write("download file server is running!");
                return R.ok("download file server is running!");
            }
            File file = new File(ConfigParam.getExcelTemplatePath(),fileName);

            if (!file.exists()) {//如果文件不存在
                response.getWriter().write("err,no file:" + fileName);
                return R.error(0, "文件不存在！");
            }

            FileChannel fc = null;
            RandomAccessFile randomAccessFile = new RandomAccessFile(file.getAbsolutePath(), "r");
            fc = randomAccessFile.getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0L, fc.size()).load();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // System.out.println(byteBuffer.isLoaded());
            byte[] bos = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                byteBuffer.get(bos, 0, byteBuffer.remaining());
            }

            String downloadFileName = null;
            if (request.getParameter("downloadFileName") != null)
                downloadFileName = URLDecoder.decode(request.getParameter("downloadFileName"), "utf-8");
            if (downloadFileName != null && !downloadFileName.equals("")) {
                fileName = downloadFileName;
            }

            // response.setContentType("application/msexcel");//magic is here
            // 解决下载文件名中文乱码问题
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + URLEncoder.encode(fileName, "utf-8") + "\"");

            response.setContentLength(bos.length);
            response.getOutputStream().write(bos);

        } catch (FamilyException e) {
            logger.error(this.getClass().toString(), e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok();
    }


}
