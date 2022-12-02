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
    <title>header</title>
</head>
<body>
	<header id="header">
        <h1 id="mainLogo">로고</h1>
        <div id="mainLoginWrap">
            <button id="mainLogin" onclick="login()"><span>로그인</span></button>
            <button id="mainJoin" onclick="signUp()"><span>회원가입</span> </button>
        </div>
    </header>
</body>
</html>