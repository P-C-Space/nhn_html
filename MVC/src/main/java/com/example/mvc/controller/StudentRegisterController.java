package com.example.mvc.controller;

import com.example.mvc.RequestMapping;
import com.example.mvc.student.Student;
import com.example.mvc.student.StudentRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RequestMapping(value = "/student/register.do", method = RequestMapping.Method.POST)
@Slf4j
public class StudentRegisterController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StudentRepository studentRepository =
                (StudentRepository) request.getServletContext().getAttribute("studentRepository");
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
            request.setAttribute("student", student);
            return "redirect:/student/view.do?id="+id;

        } catch (IllegalArgumentException exception) {
            log.error("Wrong Value or Age Not Number");
            return "/student/register.jsp";
        }
    }
}
