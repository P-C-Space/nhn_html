package com.example.servlet.filter;

public class AdminPageResponse implements Response {
    @Override
    public void doResponse(Request request) {
        System.out.println("###### response:AdminPageResponse #####");
        Member member = (Member) request.get("member");
        System.out.println("아이디:" + member.getId());
        System.out.println("이름:" + member.getName());
        System.out.println("등급:" + Member.Role.ADMIN);
        System.out.println("이메일: sun62073812@gmail.com");
        System.out.println("do something ... ADMIN ...");
    }
}
