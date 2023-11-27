package com.example.servlet.filter;

public class NotFoundResponse implements Response{

    @Override
    public void doResponse(Request request) {
        System.out.println("##### response : NotFoundResponse #####");
        System.out.println("고객님 죄송하지만 이 페이지는 미구현 페이지로 존재하지 않는 페이지 입니다! ");
    }
}
