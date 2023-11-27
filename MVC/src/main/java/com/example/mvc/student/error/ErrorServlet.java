package com.example.mvc.student.error;

import static javax.servlet.RequestDispatcher.*;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "errorServlet", urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("status_code",request.getAttribute(ERROR_STATUS_CODE));
        request.setAttribute("exception_type",request.getAttribute(ERROR_EXCEPTION_TYPE));
        request.setAttribute("message",request.getAttribute(ERROR_MESSAGE));
        request.setAttribute("exception",request.getAttribute(ERROR_EXCEPTION));
        request.setAttribute("request_uri",request.getAttribute(ERROR_REQUEST_URI));


        //todo /error.jsp forward 처리
        RequestDispatcher rd = request.getRequestDispatcher("/error/error.jsp");
        rd.forward(request, response);
    }
}
