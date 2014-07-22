package com.joshrincon.web.controller;

import com.joshrincon.domain.User;
import com.joshrincon.service.impl.BusinessService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by on 7/21/2014.
 */
public class LoginServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BusinessService service=new BusinessService();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User user = service.login(username, password);
        if(user==null){
            request.setAttribute("message", "User not found." );
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        request.getSession().setAttribute("user", user);
        response.sendRedirect("/index.jsp");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request,response);
    }

}
