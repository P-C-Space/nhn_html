package com.example.mvc.student;

import static javax.servlet.RequestDispatcher.ERROR_EXCEPTION;
import static javax.servlet.RequestDispatcher.ERROR_EXCEPTION_TYPE;
import static javax.servlet.RequestDispatcher.ERROR_MESSAGE;
import static javax.servlet.RequestDispatcher.ERROR_REQUEST_URI;
import static javax.servlet.RequestDispatcher.ERROR_STATUS_CODE;

import com.example.mvc.controller.Command;
import com.example.mvc.controller.ContollerFactory;
import com.example.mvc.controller.ErrorController;
import com.example.mvc.controller.StudentDeleteController;
import com.example.mvc.controller.StudentListController;
import com.example.mvc.controller.StudentRegisterController;
import com.example.mvc.controller.StudentRegisterFormController;
import com.example.mvc.controller.StudentUpdateController;
import com.example.mvc.controller.StudentUpdateFormController;
import com.example.mvc.controller.StudentViewController;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX = "redirect";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        try {
            //실제 요청 처리할 servlet을 결정
            //String servletPath = resolveServlet(request.getServletPath());
            RequestDispatcher rd;
            // request.getRequestDispatcher(servletPath);
            // rd.include(request, response);

            // Command command = resolveCommand(request.getServletPath(), request.getMethod());
            //실제 요청을 처리한 servlet이 'view'라는 request 속성값으로 view를 전달해 줌.
            //String view = (String) request.getAttribute("view");

            ContollerFactory controllerFactory = (ContollerFactory) request.getServletContext().getAttribute("controllerFactory");

            Command command = (Command) controllerFactory.getBean(request.getMethod(), request.getServletPath());
            String view = command.execute(request,response);
            if (view.startsWith(REDIRECT_PREFIX)) {
                String redirectUrl = view.substring(REDIRECT_PREFIX.length() + 1);
                log.error("redirect-url : {}", redirectUrl);
                // todo  `redirect:`로 시작하면 redirect 처리.
                response.sendRedirect(redirectUrl);

            } else {
                //todo redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include시킴.
                rd = request.getRequestDispatcher(view);
                rd.include(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("status_code", request.getAttribute(ERROR_STATUS_CODE));
            request.setAttribute("exception_type", request.getAttribute(ERROR_EXCEPTION_TYPE));
            request.setAttribute("message", request.getAttribute(ERROR_MESSAGE));
            request.setAttribute("exception", request.getAttribute(ERROR_EXCEPTION));
            request.setAttribute("request_uri", request.getAttribute(ERROR_REQUEST_URI));


            RequestDispatcher rd = request.getRequestDispatcher("/error/error.jsp");
            rd.forward(request, response);
        }
    }

    private String resolveServlet(String servletPath) {
        String processingServlet = null;
        if ("/student/list.do".equals(servletPath)) {
            processingServlet = "/student/list";
        } else if ("/student/register.do".equals(servletPath)) {
            processingServlet = "/student/register";
        } else if (servletPath.contains("/student/view")) {
            processingServlet = servletPath.substring(0, servletPath.length() - 3);
        } else if ("/student/update.do".equals(servletPath)) {
            processingServlet = "/student/update";
        } else if ("/student/delete.do".equals(servletPath)) {
            processingServlet = "/student/delete";
        }
        return processingServlet;
    }


    private Command resolveCommand(String servletPath, String method){
        Command command = null;
        if("/student/list.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentListController();
        }else if("/student/view.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentViewController();
        }else if("/student/delete.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentDeleteController();
        }else if("/student/update.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentUpdateFormController();
        }else if("/student/update.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentUpdateController();
        }else if("/student/register.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentRegisterFormController();
        }else if("/student/register.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentRegisterController();
        }else if("/error.do".equals(servletPath)){
            command = new ErrorController();
        }
        return command;
    }
}
