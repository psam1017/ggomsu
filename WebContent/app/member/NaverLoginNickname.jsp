<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 	<form action="${pageContext.request.contextPath}/member/naver-nickname-ok" method="get">
 		닉네임 : <input type="text" name="nickname">
 		<input hidden="memberSnsVo" value="${memberSnsVo}">
 		<input hidden="memberVo" value="${memberVo}">
 		<input type="submit" value="등록">
 	</form>
</body>
</html>