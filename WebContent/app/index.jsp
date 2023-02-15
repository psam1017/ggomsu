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
    <meta name="author" content="김지혜">
    <meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다.">
    <title>꼼수닷컴</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">
</head>
<body>
	<jsp:include page="/app/fix/Header.jsp" />
	<jsp:include page="/app/fix/MainAsideLeft.jsp" />
	<jsp:include page="/app/fix/MainAsideRight.jsp" />
	
    <main id="main">
        <section id="searchWrap">
            <form action="" name="searchForm">
                <input type="search" name="searchBar" id="searchBar">
            </form>
            <div id="searchIc"></div>
            <div id="recSearchText">
                <a href="#">검색어1</a>
                <div class="SearchTextBar"></div>
                <a href="#">검색어2</a>
                <div class="SearchTextBar"></div>
                <a href="#">검색어3</a>
                <div class="SearchTextBar"></div>
                <a href="#">검색어4</a>
            </div>
        </section>
        <section id="boardWrap">
            <div id="recBoardWrap">
                <h2>내 추천 게시판</h2>
                <div id="recBoard">
                	<c:choose>
                		<c:when test="${boardValue ne null}">
		                    <a href="${pageContext.request.contextPath}/board/article-view-detail-ok?articleIndex=${articleBestLikeOne.getArticleIndex()}" id="recboardRecBest">
		                        <h3>추천베스트</h3>
		                        <div>
		                            <p>${articleBestLikeOne.getTitle()}</p>
		                            <p>${articleBestLikeOne.getContent()}</p>
		                        </div>
		                        <div class="recboardIc"></div>
		                    </a>
		                    
		                    <a href="${pageContext.request.contextPath}/board/article-view-detail-ok?articleIndex=${articleBestViewOne.getArticleIndex()}" id="recboardViewBest">
		                        <h3>조회베스트</h3>
		                        <div>
		                            <p>${articleBestViewOne.getTitle()}</p>
		                            <p>${articleBestViewOne.getContent()}</p>
		                        </div>
		                        <div class="recboardIc"></div>
		                    </a>
	                    </c:when>
	                    <c:otherwise>
		                    <a href="${pageContext.request.contextPath}/board/article-view-detail-ok?articleIndex=${articleBestLikeBoard.getArticleIndex()}" id="recboardRecBest">
		                        <h3>추천베스트</h3>
		                        <div>
		                            <p>${articleBestLikeBoard.getTitle()}</p>
		                            <p>${articleBestLikeBoard.getContent()}</p>
		                        </div>
		                        <div class="recboardIc"></div>
		                    </a>
		                    <a href="${pageContext.request.contextPath}/board/article-view-detail-ok?articleIndex=${articleBestViewBoard.getArticleIndex()}" id="recboardViewBest">
		                        <h3>조회베스트</h3>
		                        <div>
		                            <p>${articleBestViewBoard.getTitle()}</p>
		                            <p>${articleBestViewBoard.getContent()}</p>
		                        </div>
		                        <div class="recboardIc"></div>
		                    </a>  
						</c:otherwise>
					</c:choose>
                </div>
            </div>
            <div id="mainBestWrap">
                <div id="mainRecBest">
                    <div id="recBestTitle"><h2>추천베스트</h2></div>
                    <ul id="recBestUi">
	                    <c:choose>
	                    	<c:when test="${articleLikeList != null and fn:length(articleLikeList) > 0}">
		                    	<c:forEach var="articleLikeList" items="${articleLikeList}">
			                        <li class="recBestLi">
			                            <a href="${pageContext.request.contextPath}/board/article-view-detail-ok?articleIndex=${articleLikeList.getArticleIndex()}">
			                                <div class="recBestTopWrap">
			                                    <div class="recBestName">${articleLikeList.getNickname()}</div>
			                                    <div class="recBextCount">${articleLikeList.getArticleLikeCount()}</div>
			                                </div>
			                                <p class="recBestText1">${articleLikeList.getTitle()}</p>
			                                <p class="recBestText2">${articleLikeList.getContent()}</p>
			                            </a>
			                        </li>
		                        </c:forEach>
	                        </c:when>
	                        <c:otherwise>
	                        	<p>추천된 게시판이 없습니다</p>
	                        </c:otherwise>
	                   </c:choose>
                    </ul>
                </div>
                <div id="mainViewBest">
                
                    <div id="viewBestTitle"><h2>조회베스트</h2></div>
                    
                    <ul id="viewBestUi">
                    	<c:choose>
	                    	<c:when test="${articleViewList != null and fn:length(articleViewList) > 0}">
		                    	<c:forEach var="articleViewList" items="${articleViewList}">
			                        <li class="recBestLi">
			                            <a href="${pageContext.request.contextPath}/board/article-view-detail-ok?articleIndex=${articleViewList.getArticleIndex()}">
			                                <div class="recBestTopWrap">
			                                    <div class="recBestName">${articleViewList.getNickname()}</div>
			                                    <div class="recBextCount">${articleViewList.getViewCount()}</div>
			                                </div>
			                                <p class="recBestText1">${articleViewList.getTitle()}</p>
			                                <p class="recBestText2">${articleViewList.getContent()}</p>
			                            </a>
			                        </li>
		                        </c:forEach>
	                        </c:when>
	                        <c:otherwise>
	                        	<p>조회된 게시판이 없습니다</p>
	                        </c:otherwise>
	                   </c:choose>
                    </ul>
                </div>
            </div>
        </section>
    </main>
	<jsp:include page="/app/fix/Footer.jsp" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>