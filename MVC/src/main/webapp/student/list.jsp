<%--
  Created by IntelliJ IDEA.
  User: jeongwoo
  Date: 11/23/23
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfmt" uri="http://nhnacademy.com/cfmt" %>
<html>
<head>
    <title>student - list</title>
    <link rel="stylesheet" href="/student/style.css"/>
</head>
<body>
<h1>학생 리스트</h1>
<p><a href="/student/register.do">학생(등록)</a></p>
<table>
    <thead>
    <tr>
        <th>아이디</th>
        <th>이름</th>
        <th>성별</th>
        <th>나이</th>
        <th>cmd</th>
    </tr>
    </thead>
    <tbody>
    <!--todo list 구현하기 c:foreach -->
    <c:forEach var="item" items="${studentList}">
        <tr>
            <td>${item.id}</td>
            <td style="text-align: center">${item.name}</td>
            <td style="text-align: center">${item.gender}</td>
            <td style="text-align: center">${item.age}</td>
            <td><c:url var="view_link" value="/student/view.do" scope="request">
                    <c:param name="id" value="${item.id}"/>
            </c:url> <a href="${view_link}">조회</a> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
