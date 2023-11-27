package com.example.mvc.student;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "studentRegisterServlet", urlPatterns = "/student/register")
public class StudentRegisterServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // RequestDispatcher rd = request.getRequestDispatcher("/student/register.jsp");
        // rd.forward(request, response);
        request.setAttribute("view", "/student/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            int age = Integer.parseInt(request.getParameter("age"));

            if (id.isEmpty() || name.isEmpty() || gender.isEmpty()) {
                throw new IllegalArgumentException();
            }

            Student student = new Student(id, name, gender, age);
            studentRepository.save(student);
            request.setAttribute("student",student);
            request.setAttribute("view","redirect:/student/view.do?id="+id);

        } catch (IllegalArgumentException exception) {
            log.error("Wrong Value or Age Not Number");
            // RequestDispatcher rd = request.getRequestDispatcher("/student/register.jsp");
            // rd.forward(request, response);
            request.setAttribute("view","/student/register.jsp");
        }
    }

}