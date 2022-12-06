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
    <title>mainAsideLeft</title>
</head>
<body>
	<aside id="mainAsideLeft">
        <div id="navWrap">
            <h2 class="hidden">네비게이션</h2>
            <ul id="navUl">
                <li class="navLi"><a href="${pageContext.request.contextPath}/board/article-get-list-ok">공지사항</a></li>
                <li class="navLi"><a href="${pageContext.request.contextPath}/board/article-get-list-ok">코딩게시판</a></li>
                <li class="navLi"><a href="${pageContext.request.contextPath}/board/article-get-list-ok">게임게시판</a></li>
                <li class="navLi"><a href="${pageContext.request.contextPath}/board/article-get-list-ok">자유게시판</a></li>
            </ul>
        </div>
        <a href="#"><div id="mainLeftAd">광고</div></a>
    </aside>
</body>
</html>