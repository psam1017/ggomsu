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
    <meta name="author" content="이성호">
    <meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다.">
    <title>${article.getBoardValue()}</title>
</head>
<body>
	<h1>게시판</h1>
        <h2>꼼수게시판 : ${article.getBoardValue()}</h2>
        <form action="#" method="get">
            <input type="text" name="search">
            <input type="submit" value="검색">
        </form>
        <!-- 검색 버튼 입력시 검색단어로 출력 -->
        <div class="ulWrap">
            <ul class="articleLine">
                <c:choose>
                    <c:when test="${boardList != null and fn:length(boardList) > 0}">
                        <c:forEach var="articles" items="${boardList}">
                            <li>
                                <div><a href="#">닉네임 : ${article.getUserNickname()}</a></div>
                                <div>작성일 : ${article.getWrittenAt()}</div>
                                <div>조회수 : ${article.getViewCount()}</div>
                                <div>추천수 : ${article.getArticleLike()}</div>
                                <p><a href="#">제목 : ${article.getTitle()}</a> </p>
                            </li>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <p>등록된 게시글이 없습니다.</p>
                        </li>
                    </c:otherwise>
                </c:choose>
             </ul>
        </div>
</body>
</html>