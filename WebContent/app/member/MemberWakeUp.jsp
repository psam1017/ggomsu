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
    <title>휴면계정 전환</title>
</head>
<body>
	<h2>로고</h2>
	<h4>휴면계정 안내</h4>
	<p>안녕하세요 !<br>${email}님은  <b>ggomsu</b>계정에 1년이상 로그인 하지않아<br>관련법률에 따라 휴면상태로 전환되었습니다.</p>
    <form action="${pageContext.request.contextPath}/member/member-wake-up-ok" name="wakeUpForm" method="post">
    	<div>
    		마지막 접속일 : <b>${signAt}</b>
    		<br>
    		휴면 전환일 : <b>${signAt}</b>
    	</div>
    	<button type="button" id="backBtn">취소</button>
    	<button type="button" id="wakeUpBtn">휴면 해제하기</button>
    </form>
</body>
    <script defer="defer">
	    const wakeUpBtn = document.querySelector("#wakeUpBtn");
	    const backBtn = document.querySelector("#backBtn");
	    wakeUpBtn.addEventListener("click", function(){
	    	wakeUpForm.submit();
	    });
	    backBtn.addEventListener("click", function(){
	    	history.back();
	    });
    </script>
</html>
