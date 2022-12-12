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
    <meta name="author" content="김지혜">
    <meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다.">
    <title>mainAsideRight</title>
</head>
<body>
	<aside id="mainAsideRight">
		<div id="MyWrapBfWrap">
			<div id="MyWrapBf">
	            <a href="${pageContext.request.contextPath}/app/member/MemberSignIn.jsp" id ="asideWriteBf">글쓰기</a>
	            <a href="${pageContext.request.contextPath}/app/member/MemberSignIn.jsp" id ="asideLoginBf">꼼수닷컴을<br>더 즐겁게 이용하세요</a>
	        </div>
	        <a href="#"><div id="mainRightAd">광고</div></a>
	        <button id="screenMode"></button>
		</div>
    </aside>
</body>
</html>