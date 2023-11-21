package com.example.hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;
import javax.servlet.ServletInputStream;

public interface ServletRequest {
    Object getAttribute(String val);
    void setAttribute(String var1, String var2);

    String getParameter(String var1);

    Locale getLocale();

    String getContentType();

    int getContentLength();

    long getContentLengthLong();

    BufferedReader getReader() throws IOException;

    ServletInputStream getInputStream() throws IOException;
}
