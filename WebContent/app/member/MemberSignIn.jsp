<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="김지혜, 박성민">
    <meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다.">
    <title>로그인</title>
</head>
<body>
	<c:if test="${param.code eq 'fail'}">
		<script>alert("로그인 정보가 일치하지 않습니다.");</script>
	</c:if>
	<h2>로고</h2>
    <form action="${pageContext.request.contextPath}/member/member-sign-in-ok" name="signInForm" method="post">
        <ul>
            <li id="signInEmail"><input type="email" name="email"></li>
            <li id="signInPassword"><input type="password" name="password"></li>
        </ul>
        <input type="submit" id="signInButton" value="로그인">
    </form>
    <a href="${pageContext.request.contextPath}/app/member/MemberFindIdTest.jsp">아이디 찾기</a>
</body>
</html>
