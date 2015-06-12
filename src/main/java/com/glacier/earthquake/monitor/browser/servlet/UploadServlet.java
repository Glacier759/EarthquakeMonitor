package com.glacier.earthquake.monitor.browser.servlet;

import com.glacier.earthquake.monitor.server.configure.user.FilterRuleMonitor;
import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.FilterPublicSentiment;
import com.glacier.earthquake.monitor.server.pojo.FilterWhiteList;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created by glacier on 15-5-22.
 */
public class UploadServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(UploadServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        String type = request.getParameter("type");
        String op = request.getParameter("op");

        FilterRuleMonitor monitor = UserMonitor.getUserMonitor(request).getFilterRuleMonitor();
        if ( op != null && op.equals("1") ) {
            if ( type != null && type.equals("disaster") ) {
                logger.info("[覆盖规则] - 清空disaster表");
                monitor.truncateDisaster();
            }
            else if ( type != null && type.equals("public") ) {
                logger.info("[覆盖规则] - 清空public sentiment表");
                monitor.truncatePubSentiment();
            }
            else if ( type != null && type.equals("whitelist") ) {
                logger.info("[覆盖规则] - 清空white list表");
                monitor.truncateWhiteList();
            }
        }

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
                        logger.info("[文件上传] - 绝对路径: " + uploaderFile.getAbsolutePath());
                        item.write(uploaderFile);
                        logger.info("[文件上传] - 文件写入完毕");

                        if ( type != null && type.equals("disaster") ) {
                            logger.info("[文件上传] - 得到一个Disaster文件");
                            List<String> lines = FileUtils.readLines(uploaderFile);
                            for ( String line : lines ) {
                                try {
                                    FilterDisaster disaster = new FilterDisaster();
                                    disaster.setFilterRule(line);
                                    disaster.setSubmiter(UserMonitor.getUserMonitor(request).getUsername());
                                    monitor.addRuleDisaster(disaster);
                                    logger.info("[规则导入] - 导入一条新规则 " + disaster);
                                }catch (Exception e) {
                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    e.printStackTrace(new PrintStream(baos));
                                    logger.error(baos.toString());
                                }
                            }
                        } else if ( type != null && type.equals("public") ) {
                            logger.info("[文件上传] - 得到一个Public Sentiment文件");
                            List<String> lines = FileUtils.readLines(uploaderFile);
                            for ( String line : lines ) {
                                try {
                                    FilterPublicSentiment publicSentiment = new FilterPublicSentiment();
                                    String[] option = line.split("<p>");
                                    publicSentiment.setName(option[0]);
                                    publicSentiment.setMatcher(option[1]);
                                    publicSentiment.setUnexist(option[2]);
                                    publicSentiment.setSubmiter(UserMonitor.getUserMonitor(request).getUsername());
                                    monitor.addRulePubSentiment(publicSentiment);
                                    logger.info("[规则导入] - 导入一条新规则 " + publicSentiment);
                                }catch (Exception e) {
                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    e.printStackTrace(new PrintStream(baos));
                                    logger.error(baos.toString());
                                }
                            }
                        } else if ( type != null && type.equals("whitelist") ) {
                            logger.info("[文件上传] - 得到一个White List文件");
                            List<String> lines = FileUtils.readLines(uploaderFile);
                            for ( String line : lines ) {
                                try {
                                    FilterWhiteList whiteList = new FilterWhiteList();
                                    whiteList.setUrl(line);
                                    whiteList.setSubmiter(UserMonitor.getUserMonitor(request).getUsername());
                                    monitor.addRuleWhiteList(whiteList);
                                    logger.info("[规则导入] - 导入一条新规则 " + whiteList);
                                }catch (Exception e) {
                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    e.printStackTrace(new PrintStream(baos));
                                    logger.error(baos.toString());
                                }
                            }
                        }
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
