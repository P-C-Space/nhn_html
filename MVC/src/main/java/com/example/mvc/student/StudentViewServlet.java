package com.example.mvc.student;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "studentViewServlet", urlPatterns = "/student/view")
public class StudentViewServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Student student = studentRepository.getStudentById(id);

        // url parameter를 통해 접속하였지만 데이터가 없다면, /student/list로 전송
        if(Objects.isNull(student)){
            request.setAttribute("view","/student/list.jsp");
        }
        request.setAttribute("student", student);
        // RequestDispatcher rd = request.getRequestDispatcher("/student/view.jsp");
        // rd.forward(request, response);
        request.setAttribute("view","/student/view.jsp");
    }
}
