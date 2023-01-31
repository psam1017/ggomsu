<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="navbar navbar-expand fixed-top main-color height-12">
	<h1 class="visually-hidden">꼼수닷컴</h1>
	<div class="container-fluid">
		<div class="row w-100">
			<a class="navbar-brand col-2 text-center align-self-center" href="/ggomsu">꼼수닷컴</a>
			<div class="collapse navbar-collapse col-8" id="navbarCollapse">
				<form class="d-flex justify-content-center col-8 padding-left-10" role="search" action="${pageContext.request.contextPath}/wiki/wiki-view-ok">
					<input class="form-control me-2 width-50" type="search" placeholder="검색어를 입력하세요." aria-label="search" name="subject">
					<button class="btn btn-dark" type="submit">Search</button>
				</form>
				<ul class="navbar-nav col-4 d-flex justify-content-end">
				<c:if test='${sessionScope.email == null}'>
					<li class="nav-item ms-1">
						<a href="${pageContext.request.contextPath}/app/member/MemberSignIn.jsp" id="mainLogin" class="nav-link btn btn-light">로그인</a>
					</li>
					<li class="nav-item ms-1">
						<a href="${pageContext.request.contextPath}/app/member/MemberSignUp.jsp" id="mainJoin" class="nav-link btn btn-dark text-light">회원가입</a>
					</li>
				</c:if>
				<c:if test='${sessionScope.email != null}'>
					<li class="nav-item ms-1">
						<a href="#" id="myPage" class="nav-link btn btn-light">마이페이지</a>
					</li>
					<li class="nav-item ms-1">
						<a href="#" id="myAlarm" class="nav-link btn btn-dark text-light">알림</a>
					</li>
				</c:if>
				</ul>
			</div>
		</div>
	</div>
</header>
