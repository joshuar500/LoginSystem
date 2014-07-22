package com.joshrincon.web.controller;

import com.joshrincon.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //if not exist, don't create
        HttpSession session=request.getSession(false);
        User user=(User) session.getAttribute("user");
        if(user!=null){
            session.removeAttribute("user");
            request.setAttribute("message","恭喜你，注销成功！3s后跳转到登录界面。。。<meta http-equiv='refresh'" +
                    "content='3; url=/logout'>" );
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request,response);
    }

}
