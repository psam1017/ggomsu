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
    <title>아이디찾기 : 꼼수닷컴</title>
</head>
<body>
	<h2>로고</h2>
    <form action="${pageContext.request.contextPath}/member/member-find-id-Ok" name="searchIdForm" method="post">
	     <ul>
	         <li>이름 <input type="text" name="name" id="name"><span id="nameResult"></span></li>
	         <li>성별:
	           <span>남성<input type="radio" name="sex" class="sex" value="M"></span>
	           <span>여성<input type="radio" name="sex" class="sex" value="F"></span>
	         </li>
	         <li>생년월일 <input type="date" name="birthDate" id="birthDate"></li>
	         <li>통신사
	           <select name="telecomValue" id="telecomValue">
	             <option value="NO" selected>통신사 선택</option>
	             <option value="KT">KT</option>
	             <option value="SKT">SKT</option>
	             <option value="LG">LGU+</option>
	             <option value="CPK">알뜰폰KT</option>
	             <option value="CPS">알뜰폰SKT</option>
	             <option value="CPL">알뜰폰LGU+</option>
	           </select>
	         </li>
	         <li>전화번호
	         	 <input type="text" name="contact" class="eachContact">
<!-- 	           - <input type="text" maxlength ="4" class="eachContact"> -->
<!-- 	           - <input type="text" maxlength ="4" class="eachContact"> -->
				<span id="contactResult"></span>
	           	 <input type="hidden" name="contact" id="contact" value="">
	        </li>
	        <li><input type="submit" value="아이디찾기" id="findIdButton"></li>
	    </ul>
    </form>
</body>
</html>