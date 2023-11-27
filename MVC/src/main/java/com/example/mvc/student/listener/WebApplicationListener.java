package com.example.mvc.student.listener;

import com.example.mvc.student.MapStudentRepository;
import com.example.mvc.student.Student;
import com.example.mvc.student.StudentRepository;
import com.example.mvc.student.json.JsonStudentRepository;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApplicationListener implements ServletContextListener {
    // ServletContextListener 웹 애플리케이션 시작, 종료시
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        StudentRepository studentRepository = new JsonStudentRepository();

        for (int i = 1; i <= 10; i++) {
            String gender = Math.round(Math.random()) == 0 ? "M" : "W";
            int age = (int)(Math.random() * 10 + 20);


            Student student = new Student("NHN academy" + i, "student" + i, gender, age);
            studentRepository.save(student);
            context.setAttribute("studentRepository",studentRepository);
        }
    }
}

