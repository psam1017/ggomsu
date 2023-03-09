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
    <meta name="author" content="박성민">
    <meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다.">
    <title>summernote</title>
    <!-- 현행 부트스트랩과 호환되지 않을 경우 사용
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" defer></script>
     -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/summernote/summernote-lite.css">
</head>
<body>
	<div id="original">
		<c:choose>
			<c:when test="${type eq 'boardUpdate'}">
				<c:out value="${article.content}"></c:out>
			</c:when>
			<c:when test="${type eq 'wikiRevise'}">
				<c:forEach var="contentList" items="${wikiContents}">
					<c:out value="${contentList.content}"></c:out>
				</c:forEach>
			</c:when>
		</c:choose>
	</div>
	<div class="container">
		<form action="#" method="post" id="contentForm">
			<input type="text" name="title">
				<textarea class="summernote" name="content"></textarea>
			<input type="submit" id="submitBtn" value="작성완료">
		</form>
	</div>
	<c:set var="type" value="boardWrite"></c:set>
<script>
	// type에 따라 초기화가 달라집니다. /assets/js/Summernote.js를 참고하세요.
	const contextPath = "${pageContext.request.contextPath}";
	const type = "${type}";
</script>
<!-- 서머노트를 위해 추가해야할 부분 -->
<script src="${pageContext.request.contextPath}/assets/summernote/summernote-lite.js"></script>
<script src="${pageContext.request.contextPath}/assets/summernote/lang/summernote-ko-KR.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/Summernote.js"></script>
</body>
</html>
