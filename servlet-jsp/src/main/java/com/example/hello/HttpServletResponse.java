package com.example.hello;

import java.io.IOException;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;

public interface HttpServletResponse extends ServletResponse {
    public void setHeader(String name, String value);
    public void addHeader(String name, String value);

    public void setStatus(int sc);

    public void sendError(int sc, String msg) throws IOException;

    public void sendRedirect(String location) throws IOException;

    public void addCookie(Cookie cookie);
}
