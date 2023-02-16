<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <meta name="author" content="손하늘">
 <meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다.">
<title>게시글 신고</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/board/article-report-Ok" method="post">
		<label>게시글 유저<input text="text" name="articleIndex" value="<c:out value="${param.articleIndex}"/>" readonly></label>
		<label>게시글 번호<input text="text" name="articleNickname" value="<c:out value="${param.articleNickname}"/>" readonly></label>
		<br>
		<label>확실하지않은 정보입니다.<input type="radio" value="확실하지 않은 정보입니다" name="declaration"></label><br>
		<label>누군가를 비방하는 글입니다.<input type="radio" value="누군가를 비방하는 글입니다" name="declaration"></label><br>
		<label>이용 방해할 정도의 도배.<input type="radio" value="이용 방해할 정도의 도배" name="declaration"></label><br>
		<label>불법광고.<input type="radio" value="불법광고 " name="declaration"></label><br>
		<label>음란물.<input type="radio" value="음란물 " name="declaration"></label><br>
		<input type="submit" value="확인">
	</form>
</body>
</html>