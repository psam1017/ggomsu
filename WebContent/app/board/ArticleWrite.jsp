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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/writing.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/lib/dist/tagify.css" />
    <script src="${pageContext.request.contextPath}/assets/js/writing.js" defer></script>
    <script src="${pageContext.request.contextPath}/assets/lib/dist/tagify.js"></script>
</head>
  <body>
    <div class="modal_background"></div>
    <div class="modal_wrap">
      <button class="modal_close">X</button>
      <div class="text">미리보기 모달</div>
    </div>
    <h3>게시판 글쓰기</h3>
    <form action="" method="get" name="writing">
      <div class="title">
        <ul>
          <li>
            <select name="category">
              <option value="자유" selected>자유</option>
              <option value="코딩">코딩</option>
              <option value="게임">게임</option>
              <option value="공지">공지</option>
            </select>
          </li>
          <li>
            <input type="text" placeholder="제목을 입력하세요." name="title" maxlength="100" style="width: 100%" />
          </li>
        </ul>
      </div>
      <!-- 해시태그 관련 -->
      <input name="basic" placeholder="관련해시태그를 입력하세요" style="width: 80%" />
      <ul style="border-top: 1px solid #0000">
        <li>
          <textarea placeholder="내용을 입력하세요." name="mainText" style="width: 80%; height: 50vh"></textarea>
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
      </ul>
    </form>
    <!-- 첨부파일 : 일시적으로 이미지만 업로드 가능하도록 -->
    <form name="file" action="" method="post" enctype="multipart/form-data">
      <!-- onchange 확인 후 삭제 -->
      <input type="file" accept=".jpg , .png , .gif " name="fileValue" multiple onchange="alert(this.value)" />
    </form>
  </body>
  <script> let contextPath = "${pageContext.request.contextPath}"; console.log(contextPath);</script>
</html>