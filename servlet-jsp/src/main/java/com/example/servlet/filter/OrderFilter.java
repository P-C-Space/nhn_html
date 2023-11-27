package com.example.servlet.filter;

import java.util.Objects;

public class OrderFilter implements Filter {
    @Override
    public void doFilter(Request request, FilterChain filterChain) {
        if (request.getPath().equals("/order")) {
            Member member = (Member) request.get("member");
            if (Objects.nonNull(member)) {
                if (!member.hasRole(Member.Role.NONE)) {
                    System.out.println("path : " + request.getPath() + " : has" + member.getRole());
                    filterChain.doFilter(request);
                } else{
                    System.out.println("path : " + request.getPath() + " : cannot NONE");
                }
            }
        } else {
            System.out.println("OrderCheckFilter : 다음 팔터로 넘김!");
            filterChain.doFilter(request);
        }
    }
}
