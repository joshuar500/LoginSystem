package com.joshrincon.web.controller;

import com.joshrincon.domain.User;
import com.joshrincon.service.impl.BusinessService;
import com.joshrincon.util.UserExistException;
import com.joshrincon.util.WebUtil;
import com.joshrincon.web.formbean.UserBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by on 7/21/2014.
 */
public class RegisterServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserBean userForm=WebUtil.request2bean(request,UserBean.class);
        BusinessService service=new BusinessService();

        if(!userForm.validate()){
            request.setAttribute("form", userForm);
            request.getRequestDispatcher("/registerui").forward(request, response);
            return ;
        }

        User user=new User();
        WebUtil.copyBean(user, userForm);
        try {
            service.register(user);
            request.getSession().setAttribute("message", "Registration Complete!");
            response.sendRedirect("/message.jsp");
            return;
        } catch (UserExistException e) {
            userForm.getErrors().put("username", "User already exists!");
            request.setAttribute("form", userForm);
            request.getRequestDispatcher("/registerui").forward(request, response);
            return;
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("message", "Unknown Error Exception");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }


    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request,response);
    }

}
