package com.example.hello;

public interface HttpSession {
    public String getId();

    public void setMaxInactiveInterval(int interval);    // 0이나 음수: 세션 만료되지 않음
    public int getMaxInactiveInterval();

    public Object getAttribute(String name);
    public void setAttribute(String name, Object value);
    public void removeAttribute(String name);

    public void invalidate();

    public boolean isNew();
}
