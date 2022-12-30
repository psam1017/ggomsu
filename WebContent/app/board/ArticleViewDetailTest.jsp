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
    <title>${article.getTitle()}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ArticleViewDetail.css" />
    <script src="${pageContext.request.contextPath}/assets/js/ArticleViewDetail.js" defer></script>
    <script src="${pageContext.request.contextPath}/assets/js/comment.js" defer></script>

</head>
<body>
	
	<c:set var="boardValue" value="${sessionScope.boardValue}"/>
	
    <main id="main">
        <!-- 게시글 작성자와 조회수 등 -->
        <section id="articleHeader">
            <h2>제목 ${article.getTitle()}</h2>
            <p>작성자 ${article.getNickname()}</p>
            <p>조회수 ${article.getViewCount()}</p>
            <p>추천수 <span id="articleLikeCount" name="articleLikeCount">${article.getArticleLikeCount()}</span></p>
            <p>작성일 ${article.getWrittenAt()}</p>
        </section>
	
        <!-- 게시글 본문 -->
        <section id="articleMain">
            <p>작성내용 ${article.getContent()}</p>
        </section>

        <!-- 첨부파일 목록 보기 -->
        <section id="attachment">
            <c:choose>
            <c:when test="${attachment eq null}">
                <p>첨부파일이 없습니다.</p>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/board/attachment-download-ok?attachmentName=${attachment.getAttachmentName()}">
                    <c:out value="${attachment.getAttachmentName()}"/>
                </a>
            </c:otherwise>
        </c:choose>
        </section>
        <a href="${pageContext.request.contextPath}/app/board/ArticleWriteTest.jsp">게시글 작성</a>
        <button onclick="location.href='ArticleModify.jsp'">수정</button>
        <button onclick="location.href='${pageContext.request.contextPath}/board/article-delete-ok?articleIndex=${articleIndex}&boardValue=${article.getBoardValue()}'">삭제</button>
        <!-- 댓글 작성 -->
        <section id="commentWrite" name="commentWrite">
            <form id="cWrite" method="post" action="${pageContext.request.contextPath}/board/comment-write-ok?articleIndex=${articleIndex}">
                <textarea name="content" id="content" rows="5" cols="100" style="resize:none;" placeholder="남에게 상처를 주는 말을 하지 말아주세요."></textarea>
                <input type="button" id="register" value="등록">
            </form>
        </section>

        <!-- 댓글 보기 -->
        <section id="commentList" name="commentList">
            <ul>
                <c:forEach var="comment" items="${commentList}">
	                <c:choose>
	                    <c:when test="${comment.getRefIndex() eq comment.getCommentIndex()}">
	                        <li class="originComment">
	                            <c:out value="작성자 : ${comment.getNickname()}test"/>
	                            <p><c:out value="댓글 내용 : ${comment.getContent()}"/></p>
	                            <c:out value="작성일시 : ${comment.getWrittenAt()}"/><br>
	                            추천개수 : <span class="commentLikeCount" name="commentLikeCount"><c:out value="아직입니다."/></span><br>
	                            <input type="button" class="commentLikeBtn" name="commentLikeBtn" value="댓글추천">
	                            <input type="button" class="refCommentWrite" name="refCommentWrite" value="답글쓰기">
                            	<!-- 답글쓰기 버튼을 누르면 js로 답글쓰기 양식이 나타나도록 한다. -->
	                        </li>
	                        <li class="oneRefComment off">
                                <div>
                                	<form id="rcWrite" method="post" action="${pageContext.request.contextPath}/board/ref-comment-write-ok?articleIndex=${articleIndex}&refIndex=${comment.getRefIndex()}">
                                		<textarea name="content" id="content" rows="5" cols="100" style="resize:none;" placeholder="남에게 상처를 주는 말을 하지 말아주세요."></textarea>
                                    	<button value="refCommentCancle" class="BtnRefCommentCancel">취소</button>
                                    	<button value="refCommentEnter" class="BtnRefCommentEnter">등록</button>
                                	</form>
                                </div>
                            </li>
	                    </c:when>
	                    <c:otherwise>
	                        <li class="refComment">
	                            <c:out value="작성자 : ${comment.getNickname()}"/>
	                            <p><c:out value="댓글 내용 : ${comment.getContent()}"/></p>
	                            <c:out value="작성일시 : ${comment.getWrittenAt()}"/>
	                            추천개수 : <span class="commentLikeCount" name="commentLikeCount"></span>
	                            <input type="button" class="commentLikeBtn" name="commentLikeBtn" value="댓글추천">
	                        </li> 
	                    </c:otherwise>
	                </c:choose>
                </c:forEach>
            </ul>
            <!-- 대댓글 달기 및 대댓글 보기 -->
            <div id="refcomment">
                <p> 대댓글 달기 및 보기는 댓글을 구현한 이후 다시 보도록 하겠습니다. </p>
            </div>
            <a href="${pageContext.request.contextPath}/board/article-get-list-ok?boardValue=${boardValue}">목록으로</a>
        </section>
    </main>
    
    <script>
     	const boardValue = '${sessionScope.boardValue}';
    </script>
</body>
</html>