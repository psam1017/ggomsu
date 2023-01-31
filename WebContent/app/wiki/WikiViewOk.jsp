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
    <title><c:out value="${wikiInfo.subject}"/> - 꼼수위키</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">
</head>
<body class="back-color">
	<c:if test="${wikiInfo.subject eq null}">
	<script>
		location.replace("${pageContext.request.contextPath}/wiki/wiki-view-ok?subject=청춘예찬");
	</script>
	</c:if>
    <jsp:include page="/app/fix/WikiHeader.jsp" />
	<jsp:include page="/app/fix/WikiAsideRight.jsp" />
	<main>
        <div>
            <h2><c:out value="${wikiInfo.subject}" /></h2>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/wiki/wiki-view-ok?subject=${wikiInfo.subject}&rvs=${wikiInfo.rvs}">편집</a>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/wiki/wiki-get-history?subject=${wikiInfo.subject}">역사</a>
            <span><c:out value="${wikiInfo.revisedAt}"/></span>
        </div>
        <hr>
        <div>
        </div>
	</main>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</html>