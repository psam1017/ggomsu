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
    <title>회원가입 : 꼼수닷컴</title>
</head>
	<c:if test="${param.email eq 'emailFail'}">
		<script>alert("본인의 이메일계정을 입력해주세요");</script>
	</c:if>
<body>
	<jsp:include page="/app/fix/MyPageAside.jsp" />
   <form action="${pageContext.request.contextPath}/member/member-withdrawal-ok" id="signUpForm" name="signUpForm" method="post">
     	 이메일<input type="text" name="email" placeholder="탈퇴할 이메일을 입력하세요">
     	<br>
        <input type="submit" value="회원탈퇴" name="withdrawal">
    </form>
</body>
</html>