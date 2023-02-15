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
    <meta name="author" content="손하늘">
    <meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다.">
    <title>비밀번호 변경</title>
</head>
<body>
	<h2>로고</h2>
    
    <form action="${pageContext.request.contextPath}/member/member-change-pw-ok" name="changePwForm" method="post">
            <p>이메일<input type="text" name="email" value="${email}"><a href="${pageContext.request.contextPath}/member/member-certification-ok">인증</a></p>
        <ul>
            <li>인증번호<input type="text" name="certification"></li>
            <b style="color:red"><c:out value="${msg}"/></b>
            <li>비밀번호<input type="text" name="password"></li>
            <li>비밀번호확인<input type="text" name="password"></li>
            <li><input type="submit" value="비밀번호변경" id="changePwButton"></li>
        </ul>
    </form>
</body>
</html>