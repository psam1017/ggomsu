<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header id="header">
    <h1 id="mainLogo"><a href="${pageContext.request.contextPath}/app/index.jsp">로고</a></h1>
    <div id="mainLoginWrap">
        <a href="${pageContext.request.contextPath}/app/member/MemberSignIn.jsp" id="mainLogin"><span>로그인</span></a>
        <a href="${pageContext.request.contextPath}/app/member/MemberSignUp.jsp" id="mainJoin"><span>회원가입</span></a>
    </div>
</header>