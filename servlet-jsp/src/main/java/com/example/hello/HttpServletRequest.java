package com.example.hello;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

public interface HttpServletRequest extends ServletRequest {
    String getMethod();

    String getPathInfo();

    String getServletPath();

    String getContextPath();

    String getRequestURI();

    StringBuffer getRequestURL();

    String getHeader(String var1);

    Cookie[] getCookies();

    HttpSession getSession(boolean var1);

    HttpSession getSession();
}
