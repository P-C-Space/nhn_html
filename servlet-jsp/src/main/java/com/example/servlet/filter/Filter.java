package com.example.servlet.filter;

public interface Filter {
    void doFilter(Request request, FilterChain filterChain);
}
