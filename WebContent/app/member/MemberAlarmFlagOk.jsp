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
	<form name="memberAlarmFlag" id="memberAlarmFlag" action="${pageContext.request.contextPath}/member/member-update-alarm-flag-ok" method="post" >
		<fieldset>
	      	<legend>게시글 알람</legend>
			<label>on<input type="radio" name="articleAlarmFlag" value="1" id="articleAlarmFlagOn"></label>
			<label>off<input type="radio" name="articleAlarmFlag" value="0" id="articleAlarmFlagOff"></label>
		</fieldset>
		<fieldset>
	      	<legend>댓글 알람</legend>
			<label>on<input type="radio" name="commentAlarmFlag" value="1" id="commentAlarmFlagOn"></label>
			<label>off<input type="radio" name="commentAlarmFlag" value="0" id="commentAlarmFlagOff"></label>
		</fieldset>
		<fieldset>
	      	<legend>모드</legend>
			<label>white<input type="radio" name="darkModeFlag" value="0" id="darkModeFlagOff"></label>
			<label>dark<input type="radio" name="darkModeFlag" value="1" id="darkModeFlagOn"></label>
		</fieldset>
		<button type="submit">설정</button>
  	</form>
  	<script>
	  	const afon = document.getElementById("articleAlarmFlagOn");
	  	const afoff = document.getElementById("articleAlarmFlagOff");
	  	const cfon = document.getElementById("commentAlarmFlagOn");
	  	const cfoff = document.getElementById("commentAlarmFlagOff");
	  	const dfoff = document.getElementById("darkModeFlagOff");
	  	const dfon = document.getElementById("darkModeFlagOn");
	  	"${setting.getArticleAlarmFlag()}" == 1?afon.setAttribute('checked',true):afoff.setAttribute('checked',true);
	  	"${setting.getCommentAlarmFlag()}" == 1?cfon.setAttribute('checked',true):cfoff.setAttribute('checked',true);
	  	"${setting.getDarkModeFlag()}" == 1?dfon.setAttribute('checked',true):dfoff.setAttribute('checked',true);
  	</script>
</body>
</html>