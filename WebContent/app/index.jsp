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
                    <a href="#" id="recboardRecBest">
                        <h3>추천베스트</h3>
                        <div>
                            <p>글 제목</p>
                            <p>글 내용</p>
                        </div>
                        <div class="recboardIc"></div>
                    </a>
                    <a href="#" id="recboardViewBest">
                        <h3>조회베스트</h3>
                        <div>
                            <p>글 제목</p>
                            <p>글 내용</p>
                        </div>
                        <div class="recboardIc"></div>
                    </a>
                </div>
            </div>
            <div id="mainBestWrap">
                <div id="mainRecBest">
                    <div id="recBestTitle"><h2>추천베스트</h2></div>
                    <ul id="recBestUi">
                        <li class="recBestLi">
                            <a href="#">
                                <div class="recBestTopWrap">
                                    <div class="recBestName">닉네임</div>
                                    <div class="recBextCount">추천수</div>
                                </div>
                                <p class="recBestText1">글 제목</p>
                                <p class="recBestText2">글 내용글 내용글 내용글 내용글 내용글 내용글 내용글 내용글 내용글 내용글 내용글 내용글 내용</p>
                            </a>
                        </li>
                        <li class="recBestLi">
                            <a href="#">
                                <div class="recBestTopWrap">
                                    <div class="recBestName">닉네임</div>
                                    <div class="recBextCount">추천수</div>
                                </div>
                                <p class="recBestText1">글 제목</p>
                                <p class="recBestText2">글 내용</p>
                            </a>
                        </li>
                        <li class="recBestLi">
                            <a href="#">
                                <div class="recBestTopWrap">
                                    <div class="recBestName">닉네임</div>
                                    <div class="recBextCount">추천수</div>
                                </div>
                                <p class="recBestText1">글 제목</p>
                                <p class="recBestText2">글 내용</p>
                            </a>
                        </li>
                        <li class="recBestLi">
                            <a href="#">
                                <div class="recBestTopWrap">
                                    <div class="recBestName">닉네임</div>
                                    <div class="recBextCount">추천수</div>
                                </div>
                                <p class="recBestText1">글 제목</p>
                                <p class="recBestText2">글 내용</p>
                            </a>
                        </li>
                        <li class="recBestLi">
                            <a href="#">
                                <div class="recBestTopWrap">
                                    <div class="recBestName">닉네임</div>
                                    <div class="recBextCount">추천수</div>
                                </div>
                                <p class="recBestText1">글 제목</p>
                                <p class="recBestText2">글 내용</p>
                            </a>
                        </li>
                    </ul>
                </div>
                <div id="mainViewBest">
                    <div id="viewBestTitle"><h2>조회베스트</h2></div>
                    <ul id="viewBestUi">
                        <li class="viewBestLi">
                            <a href="#">
                                <div class="viewBestTopWrap">
                                    <div class="viewBestName">닉네임</div>
                                    <div class="viewBextCount">조회수</div>
                                </div>
                                <p class="viewBestText1">글 제목</p>
                                <p class="viewBestText2">글 내용글 내용글 내용글 내용글 내용글 내용글 내용글 내용글 내용글 내용글 내용글 내용글 내용</p>
                            </a>
                        </li>
                        <li class="viewBestLi">
                            <a href="#">
                                <div class="viewBestTopWrap">
                                    <div class="viewBestName">닉네임</div>
                                    <div class="viewBextCount">조회수</div>
                                </div>
                                <p class="viewBestText1">글 제목</p>
                                <p class="viewBestText2">글 내용</p>
                            </a>
                        </li>
                        <li class="viewBestLi">
                            <a href="#">
                                <div class="viewBestTopWrap">
                                    <div class="viewBestName">닉네임</div>
                                    <div class="viewBextCount">조회수</div>
                                </div>
                                <p class="viewBestText1">글 제목</p>
                                <p class="viewBestText2">글 내용</p>
                            </a>
                        </li>
                        <li class="viewBestLi">
                            <a href="#">
                                <div class="viewBestTopWrap">
                                    <div class="viewBestName">닉네임</div>
                                    <div class="viewBextCount">조회수</div>
                                </div>
                                <p class="viewBestText1">글 제목</p>
                                <p class="viewBestText2">글 내용</p>
                            </a>
                        </li>
                        <li class="viewBestLi">
                            <a href="#">
                                <div class="viewBestTopWrap">
                                    <div class="viewBestName">닉네임</div>
                                    <div class="viewBextCount">조회수</div>
                                </div>
                                <p class="viewBestText1">글 제목</p>
                                <p class="viewBestText2">글 내용</p>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
    </main>
    <jsp:include page="/app/fix/Footer.jsp" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>