package com.glacier.earthquake.monitor.browser.servlet;

import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by glacier on 15-5-22.
 */
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        if ( !UserMonitor.getUserMonitor(request).isAdministor() ) {
            response.getWriter().print("permission denied");
            return;
        }

        //判断提交过来的表单是否为文件上传菜单
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            //构造一个文件上传处理对象
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            Iterator items;
            try {
                //解析表单中提交的所有文件内容
                items = upload.parseRequest(request).iterator();

                while (items.hasNext()) {
                    FileItem item = (FileItem) items.next();
                    if (!item.isFormField()) {
                        //取出上传文件的文件名称
                        String name = item.getName();
                        //取得上传文件以后的存储路径
                        String fileName = name.substring(name.lastIndexOf('\\') + 1, name.length());
                        //上传文件以后的存储路径
                        String path = "./tmp/download_file/" + File.separatorChar;
                        File path_dir = new File(path);
                        if ( !path_dir.exists() ) {
                            path_dir.mkdirs();
                        }
                        //上传文件
                        File uploaderFile = new File(path + fileName);
                        if ( !uploaderFile.exists() ) {
                            uploaderFile.createNewFile();
                        }
                        System.out.println(uploaderFile.getAbsolutePath());
                        item.write(uploaderFile);
                        System.out.println("文件写入完毕");

                        System.out.println("文件删除完毕 - " + uploaderFile.delete());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
