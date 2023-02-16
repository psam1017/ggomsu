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
    <title>최근활동</title>
</head>
<body>
<jsp:include page="/app/fix/MyPageAside.jsp" />
	<div>
		<h3>내가 쓴 게시글</h3>
		<ul>
			<c:forEach var="articleHistory" items="${articleHistory}">
				<li>
					<p>카테고리<c:out value="${articleHistory.getBoardValue()}"></c:out></p>
					<p>제목<a href="${pageContext.request.contextPath}/board/article-view-detail-ok?articleIndex=${articleHistory.getArticleIndex()}"><c:out value="${articleHistory.getTitle()}"/></a></p>
					<p>닉네임<c:out value="${articleHistory.getNickname()}"></c:out></p>
					<p>내용<c:out value="${articleHistory.getContent()}"></c:out></p>
					<p>작성일<c:out value="${articleHistory.getWrittenAt()}"></c:out></p>
					<p>조회수<c:out value="${articleHistory.getViewCount()}"></c:out></p>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div>
	<h3>내가 쓴 댓글</h3>
	<ul>
		<c:forEach var="commentHistory" items="${commentHistory}">
			<li>
				<p>게시글넘버<a href="${pageContext.request.contextPath}/board/article-view-detail-ok?articleIndex=${commentHistory.getArticleIndex()}"><c:out value="${commentHistory.getArticleIndex()}"/></a></p>
				<p>닉네임<c:out value="${commentHistory.getNickname()}"></c:out></p>
				<p>내용<c:out value="${commentHistory.getContent()}"></c:out></p>
				<p>작성일<c:out value="${commentHistory.getWrittenAt()}"></c:out></p>
				<p>리플갯수<c:out value="${commentHistory.getRefIndex()}"></c:out></p>
		</c:forEach>
	</ul>
	</div>
</body>
</html>