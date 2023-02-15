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
<title>알람</title>
</head>
<body>
<jsp:include page="/app/fix/MyPageAside.jsp" />
	<form name="memberAlarmFlag" id="memberAlarmFlag" action="${pageContext.request.contextPath}/member/member-alarm-flag-ok" method="post" >
		<fieldset>
	      	<legend>게시글 알람</legend>
			<label>on<input type="radio" value="1"></label>
			<label>off<input type="radio" value="0"></label>
		</fieldset>
		<fieldset>
	      	<legend>댓글 알람</legend>
			<label>on<input type="radio" value="1"></label>
			<label>off<input type="radio" value="0"></label>
		</fieldset>
		<fieldset>
	      	<legend>모드</legend>
			<label>white<input type="radio" value="0"></label>
			<label>dark<input type="radio" value="1"></label>
		</fieldset>
  	</form>
</body>
</html>