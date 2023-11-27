<%--
  Created by IntelliJ IDEA.
  User: jeongwoo
  Date: 11/24/23
  Time: 9:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:choose>
    <c:when test="${not empty student}">
        <%-- For "update" action, set "student" attribute if not empty --%>
        <c:set var="action" value="update.do"/>
        <c:set var="student" value="${not empty student ? student : null}"/>
    </c:when>
    <c:otherwise>
        <%-- For "register" action, set "student" attribute as null --%>
        <c:set var="action" value="register.do"/>
        <c:set var="student" value="${null}"/>
    </c:otherwise>
</c:choose>
<head>
    <title>student - ${action}</title>
    <link rel="stylesheet" href="/student/viewStyle.css"/>
</head>
<body>
<h1>학생 등록</h1>
<form method="post" action="/student/${action}">
    <table>
        <tbody>
        <tr>
            <td class="header">ID</td>
            <td><c:choose>
                <c:when test="${empty student}}">
                    <input type="text" name="id" value="${student.id}">
                </c:when>
                <c:otherwise>
                    <input type="text" name="id" value="${student.id}">
                </c:otherwise>
            </c:choose></td>
        </tr>
        <tr>
            <td class="header">이름</td>
            <td><input type="text" name="name" value="${student.name}"></td>
        </tr>
        <tr>
            <td class="header">성별</td>
            <c:choose>
                <c:when test="${not empty student}">
                    <c:choose>
                        <c:when test="${student eq 'M'}">
                            <td><input type="radio" name="gender" value="M" checked>남<input type="radio"
                                                                                            name="gender"
                                                                                            value="W">여
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td><input type="radio" name="gender" value="M">남<input type="radio"
                                                                                            name="gender"
                                                                                            value="W" checked>여
                            </td>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <td><input type="radio" name="gender" value="M">남<input type="radio" name="gender"
                                                                                    value="W">여
                    </td>
                </c:otherwise>
            </c:choose>

            </td>
        </tr>
        <tr>
            <td class="header">나이</td>
            <td><input type="text" name="age" value="${student.age}"></td>
        </tr>
        </tbody>

    </table>
    <br/>

    <button type="submit">
        <c:choose>
            <c:when test="${empty student}">
                등록
            </c:when>
            <c:otherwise>
                수정
            </c:otherwise>
        </c:choose>
    </button>
</form>

</body>
</html>

