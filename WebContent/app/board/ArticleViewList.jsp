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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    
    <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js "></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-migrate-1.4.1.min.js "></script>
</head>
<body>
	<jsp:include page="../fix/Header.jsp" />
	<jsp:include page="../fix/MainAsideLeft.jsp" />
	<jsp:include page="../fix/MainAsideRight.jsp" />
	<div class="position-absolute width-65 height-100 left-18 top-14">
	<h1 class="d-none">게시판</h1>
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
 		<c:choose>
             <c:when test="${articleList != null and fn:length(articleList) > 0}">
                 <c:forEach var="article" items="${articleList}">
                 <div class="container border margin-y-2 padding-1">
				  <div class="row margin-bottom-2">
				    <div class="col-8">
				      <a href="#"><c:out value="${article.getNickname()}"/></a>
				    </div>
				    <div class="col-4 text-end">
				      <c:out value="${article.getWrittenAt()}"/>
				    </div>
				  </div>
				  <div class="row">
				    <div class="col-10">
				      <a href="${pageContext.request.contextPath}/board/article-view-detail-ok?articleIndex=${article.getArticleIndex()}"><c:out value="${article.getTitle()}" /></a>
				    </div>
				    <div class="col-1 text-end">
				      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
						  <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
						  <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
						</svg>
				      <c:out value="${article.getViewCount()}"/>
				    </div>
				    <div class="col-1 text-end">
				    	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-hand-thumbs-up" viewBox="0 0 16 16">
						  <path d="M8.864.046C7.908-.193 7.02.53 6.956 1.466c-.072 1.051-.23 2.016-.428 2.59-.125.36-.479 1.013-1.04 1.639-.557.623-1.282 1.178-2.131 1.41C2.685 7.288 2 7.87 2 8.72v4.001c0 .845.682 1.464 1.448 1.545 1.07.114 1.564.415 2.068.723l.048.03c.272.165.578.348.97.484.397.136.861.217 1.466.217h3.5c.937 0 1.599-.477 1.934-1.064a1.86 1.86 0 0 0 .254-.912c0-.152-.023-.312-.077-.464.201-.263.38-.578.488-.901.11-.33.172-.762.004-1.149.069-.13.12-.269.159-.403.077-.27.113-.568.113-.857 0-.288-.036-.585-.113-.856a2.144 2.144 0 0 0-.138-.362 1.9 1.9 0 0 0 .234-1.734c-.206-.592-.682-1.1-1.2-1.272-.847-.282-1.803-.276-2.516-.211a9.84 9.84 0 0 0-.443.05 9.365 9.365 0 0 0-.062-4.509A1.38 1.38 0 0 0 9.125.111L8.864.046zM11.5 14.721H8c-.51 0-.863-.069-1.14-.164-.281-.097-.506-.228-.776-.393l-.04-.024c-.555-.339-1.198-.731-2.49-.868-.333-.036-.554-.29-.554-.55V8.72c0-.254.226-.543.62-.65 1.095-.3 1.977-.996 2.614-1.708.635-.71 1.064-1.475 1.238-1.978.243-.7.407-1.768.482-2.85.025-.362.36-.594.667-.518l.262.066c.16.04.258.143.288.255a8.34 8.34 0 0 1-.145 4.725.5.5 0 0 0 .595.644l.003-.001.014-.003.058-.014a8.908 8.908 0 0 1 1.036-.157c.663-.06 1.457-.054 2.11.164.175.058.45.3.57.65.107.308.087.67-.266 1.022l-.353.353.353.354c.043.043.105.141.154.315.048.167.075.37.075.581 0 .212-.027.414-.075.582-.05.174-.111.272-.154.315l-.353.353.353.354c.047.047.109.177.005.488a2.224 2.224 0 0 1-.505.805l-.353.353.353.354c.006.005.041.05.041.17a.866.866 0 0 1-.121.416c-.165.288-.503.56-1.066.56z"/>
						</svg>
				      <c:if test="${article.getArticleLikeCount()!=null}">
                   		<c:out value="${article.getArticleLikeCount()}"/>
                   	  </c:if>
                   	  <c:if test="${article.getArticleLikeCount() == null}">
                   		<c:out value="0"/>
                     </c:if> 
				    </div>
				  </div>
				</div>
 				</c:forEach>
             </c:when>
             <c:otherwise>
             	<div class="container">
                 <div class="col-12"></div>
             	</div>
             </c:otherwise>
          </c:choose>
      
        <div class="d-flex justify-content-center">
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
        </div>
  	 </div>
</body>
</html>