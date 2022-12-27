<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<header id="header">
    <h1 id="mainLogo"><a href="${pageContext.request.contextPath}/app/index.jsp">로고</a></h1>
    <div id="mainLoginWrap">
    	<c:if test='${sessionScope.email == null}'>
    		<a href="${pageContext.request.contextPath}/app/member/MemberSignIn.jsp" id="mainLogin"><span>로그인</span></a>
        	<a href="${pageContext.request.contextPath}/app/member/MemberSignUp.jsp" id="mainJoin"><span>회원가입</span></a>
    	</c:if>
    	<c:if test='${sessionScope.email != null}'>
    		<a href="#" id="myPage"><span>마이페이지</span></a>
        	<a href="#" id="myAlarm"><span>알림</span></a>
    	</c:if>
        
    </div>
</header>