package com.example.mvc;

import com.example.mvc.controller.ContollerFactory;
import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@HandlesTypes(
        value = {
                com.example.mvc.controller.Command.class
        }
)
public class WebAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        ContollerFactory contollerFactory = new ContollerFactory();
        contollerFactory.init(set);
        servletContext.setAttribute("controllerFactory",contollerFactory);
    }
}
