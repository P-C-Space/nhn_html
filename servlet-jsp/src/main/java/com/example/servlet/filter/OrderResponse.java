package com.example.servlet.filter;

public class OrderResponse implements Response{
    @Override
    public void doResponse(Request request) {
        System.out.println("##### response : OrderResponse #####");
        Member member = (Member) request.get("member");
        System.out.println("아이디 : " + member.getId());
        System.out.println("이름 : " + member.getName());
        System.out.println("등급 : " + member.getRole());
        System.out.println("주문 내역 : " + "빈츠 5박스, 콜라 350ml 20캔 한박스");
        System.out.println("do something ... " + member.getRole() + " ...");
    }
}
