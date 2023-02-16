<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="author" content="손하늘" />
    <meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다."/>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/UpdateMyInfo.js" defer></script>
    <title>회원정보</title>
</head>
	<body>
	<jsp:include page="/app/fix/MyPageAside.jsp" />
	<main id="main">
	      <ul>
	        <li>이메일 <span><c:out value="${member.getEmail()}" /></span></li>
	        <li>비밀번호 변경
	          <span><input type="password" name="password" id="password" value=""></span>
	        </li>
	        <li>이름<span><c:out value="${member.getName()}"/></span></li>
	        <c:set var="gender" value="${member.getSex()}" />
	        <li>성별<span><c:if test="${gender.equals('M')}"><c:out value="남자"/></c:if>
	        <c:if test="${gender.equals('F')}"><c:out value="여자"/></c:if></span></li>
	        <li>생년월일<span><c:out value="${member.getBirthDate()}"/></span></li>
	        <li>가입일<span><c:out value="${member.getCreatedAt()}"/></span></li>
		  </ul>
      </main>
<!--       이메일인증 기능구현 완료 후MemberChangePw.jsp 모달창으로 작업 -->
      <script>
		  	const password = document.getElementById("password");
		  	password.addEventListener("click", function(){
		  		location.href = "${pageContext.request.contextPath}/app/member/MemberChangePw.jsp"
		   	});
      </script>
    </body>
</html>
