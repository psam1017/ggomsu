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
	
	<c:set var="totalCount" value="${totalCount}"/>
	<c:set var="realEndPage" value="${realEndPage}"/>
	<c:set var="startPage" value="${startPage}"/>
	<c:set var="endPage" value="${endPage}"/>
	<c:set var="nowPage" value="${nowPage}"/>
	<c:set var="articleList" value="${articleList}"/>
	<c:set var="prevPage" value="${prevPage}"/>
	<c:set var="nextPage" value="${nextPage}"/>
	<c:set var="articleLikeCount" value="${articleLikeCount}"/>
	<c:set var="category" value="${category}"/>
	
	<h1>게시판</h1>
        <h2>꼼수게시판 : ${articles.getBoardValue()}</h2>
        <form action="#" method="get">
            <input type="text" name="search">
            <input type="submit" value="검색">
        </form>
        <a href="${pageContext.request.contextPath}/board/article-get-best-list-ok">추천순</a>
        <a href="${pageContext.request.contextPath}/board/article-get-list-ok">최신순</a>
        <!-- 검색 버튼 입력시 검색단어로 출력 -->
        <div class="ulWrap">
            <ul class="articleLine">
                <c:choose>
                    <c:when test="${articleList != null and fn:length(articleList) > 0}">
                        <c:forEach var="articles" items="${articleList}">
                            <li>
                                <div>
                                <a href="#">제목 : ${articles.getTitle()}</a>
                                <div><a href="#">닉네임 : ${articles.getUserNickname()}</a></div>
                                <div>작성일 : ${articles.getWrittenAt()}</div>
                                <div>조회수 : ${articles.getViewCount()}</div>
                                <c:if test="${articles.getArticleLikeCount()!=null}">
                                	<div>추천수 : ${articles.getArticleLikeCount()}</div>
                                </c:if>
                                <c:if test="${articles.getArticleLikeCount() == null}">
                                	<div>추천수 : 0</div>
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
        				<a href="${pageContext.request.contextPath}/board/article-get${category}-list-ok?page=${prevPage}">&lt;</a>
        			</c:if>
        			
        			<c:forEach var="i" begin="${startPage}" end="${endPage}">
        				<c:choose>
        					<c:when test="${i eq nowPage}">
        						<c:out value="[${i}]"/>&nbsp;
        					</c:when>
        					<c:otherwise>
        						<a href="${pageContext.request.contextPath}/board/article-get${category}-list-ok?page=${i}"><c:out value="${i}"/></a>
        					</c:otherwise>
        				</c:choose>
        			</c:forEach>
        			
        			<c:if test="${nowPage != realEndPage}">
        				<a href="${pageContext.request.contextPath}/board/article-get${category}-list-ok?page=${nextPage}">&gt;</a>
        			</c:if>
        		</td>
        	</tr>
        </table>
</body>
</html>