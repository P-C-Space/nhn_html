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
                try {
                    // Controller 클래스의 기본 생성자를 이용하여 인스턴스 생성
                    Object controllerInstance = controllerClass.getDeclaredConstructor().newInstance();

                    // Controller 클래스에서 사용 가능한 메서드들을 가져와서 처리
                    for (Method method : controllerClass.getDeclaredMethods()) {
                        // @RequestMapping 어노테이션이 있는 메서드만 처리
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            RequestMapping annotation = method.getAnnotation(RequestMapping.class);

                            // beanMap에 key = method + servletPath, value = Controller instance를 저장
                            String key = annotation.method() + annotation.value();
                            beanMap.put(key, controllerInstance);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace(); // 예외 처리는 실제 상황에 맞게 수정하세요.
                }
            }
        }

        public Object getBean(String method, String path){
            return beanMap.get(method+path);
        }
    }

