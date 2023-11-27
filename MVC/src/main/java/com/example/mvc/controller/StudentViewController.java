package com.example.mvc.controller;

import com.example.mvc.RequestMapping;
import com.example.mvc.student.Student;
import com.example.mvc.student.StudentRepository;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
@RequestMapping(value = "/student/view.do", method = RequestMapping.Method.GET)
@Slf4j
public class StudentViewController implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");
        String id = request.getParameter("id");
        Student student = studentRepository.getStudentById(id);
        log.error(id);
        if(Objects.isNull(student)){
            return "/student/list.do";
        }
        request.setAttribute("student", student);
        return "/student/view.jsp";
    }
}
