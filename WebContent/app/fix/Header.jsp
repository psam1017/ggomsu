<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath}/assets/js/Header.js" defer></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<header class="navbar navbar-expand fixed-top main-color height-12">
	<h1 class="visually-hidden">꼼수닷컴</h1>
	<div class="container-fluid">
		<div class="row w-100">
			<a class="navbar-brand col-2 text-center align-self-center" href="/ggomsu">꼼수닷컴</a>
			<div class="collapse navbar-collapse col-8" id="navbarCollapse">
				<form class="d-flex justify-content-center col-8 padding-left-10" role="search">
					<input class="form-control me-2 width-50" type="search" placeholder="검색어를 입력하세요." aria-label="search">
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
						<a href="${pageContext.request.contextPath}/member/member-view-my-info-ok" id="myPage" class="nav-link btn btn-light">마이페이지</a>
					</li>
					<li class="nav-item ms-1">
						<button id="myAlarm" class="nav-link btn btn-dark text-light">알림: ${alarmCount}</button>
					</li>
					<div class="alarmOpen off">
						<c:forEach var="alarm" items="${alarmList}">
							<c:choose>
								<c:when test="${alarmList ne null}">
									<c:if test="${alarm.getCommentIndex() eq alarm.getRefIndex()}">
										<a href="${pageContext.request.contextPath}/board/article-view-detail-ok?articleIndex=${alarm.getArticleIndex()}" class="commentAlarm"
										onclick= "location.href='${pageContext.request.contextPath}/board/comment-alarm-delete-ok?commentIndex=${alarm.getCommentIndex()}'">
											<c:forEach var="articleTitle" items="${articleTitle}">
												<c:if test="${alarm.getArticleIndex() eq articleTitle.getArticleIndex()}">
													<span class="alarmNickname">${alarm.getNickname()}님이 게시글 "${articleTitle.getTitle()}"에 댓글을 추가하셨습니다.</span><br>
													<span class="alarmContent">${alarm.getContent()}</span><br>
												</c:if>
											</c:forEach>
										</a>
									</c:if>
									<c:if test="${alarm.getCommentIndex() ne alarm.getRefIndex()}">
										<a href="${pageContext.request.contextPath}/board/article-view-detail-ok?articleIndex=${alarm.getArticleIndex()}" class="RefcommentAlarm"
										onclick= "location.href='${pageContext.request.contextPath}/board/ref-comment-alarm-delete-ok?commentIndex=${alarm.getCommentIndex()}'">
											<c:forEach var="commentContent" items="${commentContent}">
												<c:if test = "${alarm.getRefIndex() eq commentContent.getCommentIndex()}">
													<span class="alarmNickname">${alarm.getNickname()}님이 댓글 "${commentContent.getContent()}"에 댓글을 추가하셨습니다.</span><br>
													<span class="alarmContent">${alarm.getContent()}</span><br>
												</c:if>
											</c:forEach>
										</a>
									</c:if>
								</c:when>
								<c:otherwise>
									<p>알림이 없습니다</p>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</c:if>
				</ul>
			</div>
		</div>
	</div>
</header>