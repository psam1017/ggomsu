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
    <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js "></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-migrate-1.4.1.min.js "></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
</head>
<body>

	<h1>게시판</h1>
        <h2>${boardText}</h2>
        <form action="${pageContext.request.contextPath}/board/article-get-search-list-ok" method="get">
        	<input type="hidden" name="boardValue" value="${boardValue}" >
        	<select name="searchCategory">
              <option value="Total" selected>전체</option>
              <option value="Writer">작성자</option>
              <option value="TitleContent">제목 및 내용</option>
              <option value="Tag">태그</option>
            </select>
            <select name="searchPeriod">
              <option value="1 SECOND" selected>전체</option>
              <option value="7 DAY">일주일</option>
              <option value="1 MONTH">1개월</option>
              <option value="1 YEAR">1년</option>
            </select>
            <input type="text" name="search">
            <input type="submit" value="검색">
        </form>
        <a href="${pageContext.request.contextPath}/board/article-get-best-list-ok?boardValue=${boardValue}">추천순</a>
        <a href="${pageContext.request.contextPath}/board/article-get-list-ok?boardValue=${boardValue}">최신순</a>
        <a href="${pageContext.request.contextPath}/board/article-get-viewed-order-list-ok?boardValue=${boardValue}">조회순</a>
        <!-- 검색 버튼 입력시 검색단어로 출력 -->
        <div class="ulWrap">
            <ul class="articleLine">
                <c:choose>
                    <c:when test="${articleList != null and fn:length(articleList) > 0}">
                        <c:forEach var="article" items="${articleList}">
                            <li>
                                <div> 
                                <a href="${pageContext.request.contextPath}/board/article-view-detail-ok?articleIndex=${article.getArticleIndex()}"><c:out value="제목 : ${article.getTitle()}" /></a>
                                <div><a href="#"><c:out value="닉네임 : ${article.getNickname()}"/></a></div>
                                <div><c:out value="작성일 : ${article.getWrittenAt()}"/></div>
                                <div><c:out value="조회수 : ${article.getViewCount()}"/></div>
                                <c:if test="${article.getArticleLikeCount()!=null}">
                                	<div><c:out value="추천수 : ${article.getArticleLikeCount()}"/></div>
                                </c:if>
                                <c:if test="${article.getArticleLikeCount() == null}">
                                	<div><c:out value="추천수 : 0"/></div>
                                </c:if>
                                </div>
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
        <table>
        	<tr>
        		<td>
        			<c:if test="${nowPage > 1}">
        				<a href="${pageContext.request.contextPath}/board/article-get${sortBy}-list-ok?page=${prevPage}&boardValue=${boardValue}&search=${search}&searchCategory=${searchCategory}&searchPeriod=${searchPeriod}">&lt;</a>
        			</c:if>
        			
        			<c:forEach var="i" begin="${startPage}" end="${endPage}">
        				<c:choose>
        					<c:when test="${i eq nowPage}">
        						<c:out value="[${i}]"/>&nbsp;
        					</c:when>
        					<c:otherwise>
        						<a href="${pageContext.request.contextPath}/board/article-get${sortBy}-list-ok?page=${i}&boardValue=${boardValue}&search=${search}&searchCategory=${searchCategory}&searchPeriod=${searchPeriod}"><c:out value="${i}"/></a>
        					</c:otherwise>
        				</c:choose>
        			</c:forEach>
        			
        			<c:if test="${nowPage != realEndPage}">
        				<a href="${pageContext.request.contextPath}/board/article-get${sortBy}-list-ok?page=${nextPage}&boardValue=${boardValue}&search=${search}&searchCategory=${searchCategory}&searchPeriod=${searchPeriod}">&gt;</a>
        			</c:if>
        		</td>
        	</tr>
        </table>
</body>
</html>