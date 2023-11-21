package com.example.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;
import java.util.Locale;


public class ReadCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Cookie cookie = CookieUtils.getCookie(request,"locale");

        if(Objects.isNull(cookie)){
            response.sendError(500,"cookie not found");
            return ;
        }

        String locale = cookie.getValue();

        String helloValue = ResourceBundle.getBundle("message", new Locale(locale)).getString("hello");

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        try(PrintWriter out = response.getWriter()){
            out.println(helloValue);
        }

    }
}
