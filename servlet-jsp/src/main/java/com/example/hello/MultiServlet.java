package com.example.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MultiServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(MultiServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String[] values = request.getParameterValues("class");
        String url = getServletContext().getInitParameter("url");
        try (PrintWriter out = response.getWriter()) {
            out.println(String.join(",", values));
            out.printf("url:%s\n", url);
        } catch (IOException exception) {
            log.info(exception.getMessage());
        }
    }
}
