package com.glacier.earthquake.monitor.browser.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by glacier on 15-5-22.
 */
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("login", "false");
        session.removeAttribute("login_user");
        session.removeAttribute("privilege");
        Cookie[] cookies = request.getCookies();
        for ( Cookie cookie : cookies ) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
