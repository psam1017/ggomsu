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
    <title>아이디찾기 : 꼼수닷컴</title>
</head>
<body>
	<h2>로고</h2>
    <form action="" name="searchIdForm" method="post">
        <ul>
            <li><input type="text" name="email"></li>
            <li><input type="text" name="contact"></li>
            <li><input type="button" value="아이디찾기" id="findIdButton"></li>
        </ul>
    </form>
</body>
</html>