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
    <title>${articleLike.getBoardValue()}</title>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js "></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-migrate-1.4.1.min.js "></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
</head>
<body>

	<h1>즐겨찾기</h1>
        <div class="ulWrap">
            <ul class="articleLine">
                <c:choose>
                    <c:when test="${articleLikeList != null and fn:length(articleLikeList) > 0}">
                        <c:forEach var="articleLike" items="${articleLikeList}">
                            <li>
                                <div> 
                                <a href="${pageContext.request.contextPath}/board/article-view-detail-ok?articleIndex=${articleLike.getArticleIndex()}"><c:out value="제목 : ${articleLike.getTitle()}" /></a>
                                <div><a href="#"><c:out value="닉네임 : ${articleLike.getNickname()}"/></a></div>
                                <div><c:out value="작성일 : ${articleLike.getWrittenAt()}"/></div>
                                <div><c:out value="조회수 : ${articleLike.getViewCount()}"/></div>
                                <c:if test="${articleLike.getArticleLikeCount()!=null}">
                                	<div><c:out value="추천수 : ${articleLike.getArticleLikeCount()}"/></div>
                                </c:if>
                                <c:if test="${articleLike.getArticleLikeCount() == null}">
                                	<div><c:out value="추천수 : 0"/></div>
                                </c:if>
                                </div>
                            </li>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <p>즐겨찾기한 게시글이 없습니다.</p>
                        </li>
                    </c:otherwise>
                </c:choose>
             </ul>
        </div>
        <table>
        	<tr>
        		<td>
        			<c:if test="${nowPage > 1}">
        				<a href="${pageContext.request.contextPath}/board/article-like-get-list-ok?page=${prevPage}">&lt;</a>
        			</c:if>
        			
        			<c:forEach var="i" begin="${startPage}" end="${endPage}">
        				<c:choose>
        					<c:when test="${i eq nowPage}">
        						<c:out value="[${i}]"/>&nbsp;
        					</c:when>
        					<c:otherwise>
        						<a href="${pageContext.request.contextPath}/board/article-like-get-list-ok?page=${i}"><c:out value="${i}"/></a>
        					</c:otherwise>
        				</c:choose>
        			</c:forEach>
        			
        			<c:if test="${nowPage != realEndPage}">
        				<a href="${pageContext.request.contextPath}/board/article-like-get-list-ok?page=${nextPage}">&gt;</a>
        			</c:if>
        		</td>
        	</tr>
        </table>
</body>
</html>