package com.glacier.earthquake.monitor.browser.servlet;

import com.glacier.earthquake.monitor.server.pojo.User;
import com.glacier.earthquake.monitor.server.util.Data2Object;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by glacier on 15-5-22.
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mobile = request.getParameter("username");
        String password = request.getParameter("password");

        User loginUser = Data2Object.getUserInfoByMobile(mobile);
        if ( loginUser.getPassword().equals(password) ) {
            request.getSession().setAttribute("login_user", loginUser);
            request.getSession().setAttribute("login", "true");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome.jsp");
            requestDispatcher.forward(request, response);
        }
        else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginfaild.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
