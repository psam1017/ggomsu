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
    <meta name="author" content="테스트용">
    <meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다.">
    <title>아이디 체크 테스트</title>
</head>
<body>
	<form action="#" name="signUpForm" id="signUpForm">
		<input type="text" name="email" id="email" value="psm"><span id="emailCheckResult" name="emailCheckResult"> </span>
		<br>
		<span><input type="checkbox" name="term" class="term"> 약관1</span>   <span><input type="checkbox" name="term" class="term" checked> 약관2</span>   
		<input type="button" name="signUpSubmit" id="signUpSubmit" value="제출">
	</form>
</body>
<script>
	const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/assets/js/SignUp.js" defer></script>
</html>