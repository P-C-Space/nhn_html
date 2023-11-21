package com.example.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import javax.servlet.ServletOutputStream;

public interface ServletResponse {
    public void setLocale(Locale loc);

    public void setCharacterEncoding(String charset);

    public void setContentType(String type);

    public void setContentLength(int len);
    public void setContentLengthLong(long len);

    public PrintWriter getWriter() throws IOException;
    public ServletOutputStream getOutputStream() throws IOException;

    public void setBufferSize(int size);
    public int getBufferSize();
    public void flushBuffer() throws IOException;
    public void resetBuffer();

    public boolean isCommitted();

}
