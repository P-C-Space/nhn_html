<%--
  Created by IntelliJ IDEA.
  User: jeongwoo
  Date: 11/24/23
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfmt" uri="http://nhnacademy.com/cfmt" %>
<html>
<head>
    <title>student - view</title>
    <link rel="stylesheet" href="/student/viewStyle.css"/>
</head>
<body>
<table>
    <tbody>
    <c:set var="student" value="${student}"/>
    <tr>
        <td class="header">아이디</td>
        <td>${student.id}</td>
    </tr>
    <tr>
        <td class="header">이름</td>
        <td>${student.name}</td>
    </tr>
    <tr>
        <td class="header">성별</td>
        <td>${student.gender}</td>
    </tr>
    <tr>
        <td class="header">나이</td>
        <td>${student.age}</td>
    </tr>
    <tr>
        <td class="header">등록일</td>
        <td>
            ${cfmt:formatDate(student.createdAt, 'yyyy-MM-dd HH:mm:ss')}
        </td>
    </tr>
    </tbody>
</table>
<br/>
<ul>
    <li><a href="/student/list.do">리스트</a>
        <c:url var="updateUrl" value="/student/update.do">
            <c:param name="id" value="${student.id}"/>
        </c:url>
        <a href="${updateUrl}">수정</a>
        <form method="post" action="/student/delete.do">
            <input type="hidden" name="id" value="${student.id}">
            <input type="submit" value="삭제"/>
        </form>
    </li>
</ul>


</body>
</html>
