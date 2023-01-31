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
    <meta name="author" content="손하늘">
    <meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다.">
    <title>게시글 작성</title>
    <script src="${pageContext.request.contextPath}/assets/js/ArticleWriting.js" defer></script>
    <script src="${pageContext.request.contextPath}/assets/lib/dist/tagify.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ArticleWrite.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/lib/dist/tagify2.css" type="text/css"/>
</head>
  <body>
    <div class="modal_background"></div>
    <div class="modal_wrap">
      <button class="modal_close">X</button>
      <div class="text">미리보기 모달</div>
    </div>
    <h3>게시판 글쓰기</h3>
    <form name="writeArticle" action="${pageContext.request.contextPath}/board/article-write-ok" method="post" enctype="multipart/form-data">
      <div class="title">
        <ul>
          <li>
            <select id="boardValue" name="boardValue">
              <option value="free">자유</option>
              <option value="coding">코딩</option>
              <option value="game">게임</option>
              <option value="notice">공지</option>
            </select>
          </li>
          <li>
            <input type="text" placeholder="제목을 입력하세요." name="title" maxlength="100" style="width: 100%" />
            <input type="hidden" name="nickname" value="${sessionMember}"/>
          </li>
        </ul>
      </div>
      <!-- 해시태그 관련 -->
      <input name="basic" placeholder="관련해시태그를 입력하세요" style="width: 80%" />
      <ul style="border-top: 1px solid #0000">
        <li>
          <textarea placeholder="내용을 입력하세요." name="content" style="width: 80%; height: 50vh"></textarea>
        </li>
        <li>
          <input type="submit" name="writing" value="글쓰기" />
        </li>
        <li id="modal_btn">
          <input type="button" name="preview" value="미리보기" onclick="inputValue()" />
        </li>
        <li>
          <!--쿠키-->
          <input type="button" name="temporaryStorage" value="임시저장" />
        </li>
        <li>
          <input type="button" name="home" value="목록으로" />
        </li>
        <li>
          <input type="file" accept=".jpg , .png , .gif " id="ff" name="fileValue" onchange="alert(this.value)" />
        </li>
      </ul>
     </form>
     
 	<script>
     	const boardValue = '${sessionScope.boardValue}';
    	const selectBoardValue = document.querySelectorAll('#boardValue option');
    	const selectBoardValueFree = document.querySelector('#boardValue option[value="free"]');
    	const basic = document.querySelector('input[name="basic"]');
    	selectBoardValue.forEach((element) => {
			if(boardValue == element.value){
				element.setAttribute('selected',true);
			}
    	})
    	if(boardValue == null){
    		selectBoardValueFree.setAttribute('selected',true);
		}
     </script>
  </body>
</html>