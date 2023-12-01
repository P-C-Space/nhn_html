package com.example.mvc.controller;

import com.example.mvc.RequestMapping;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ContollerFactory {
    private final ConcurrentMap<String, Object> beanMap = new ConcurrentHashMap<>();
        public void init(Set<Class<?>> clazz){
            for (Class<?> controllerClass : clazz) {
                RequestMapping requestMapping = controllerClass.getAnnotation(RequestMapping.class);
                System.out.println("====================");
                System.out.println(requestMapping.value());
                System.out.println(requestMapping.method());
                System.out.println("====================");

                String servletPath = requestMapping.value();
                String method = String.valueOf(requestMapping.method());

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
                }else if("/student/error.do".equals(servletPath)){
                    command = new ErrorController();
                }

                beanMap.put(method+servletPath,command);
            }
        }

        public Object getBean(String method, String path){
            return beanMap.get(method+path);
        }
    }

