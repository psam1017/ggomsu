<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="손하늘">
<meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다.">
<title>차단목록</title>
<script src="${pageContext.request.contextPath}/assets/js/BlockMember.js" defer></script>
</head>
<body>
<jsp:include page="/app/fix/MyPageAside.jsp" />
	<form name="memberBlock" id="memberBlock" method="post" >
		<h3>차단할 사용자</h3>
        <label>닉네임<input type="text" name="blockedMember" placeholder="차단 할 사용자 아이디를 입력해주세요"></label>
        <button id="updateBlock">추가</button>
	    <hr>
	    <h3>차단 리스트</h3>
<!-- 	    <p><input type="checkbox" name="allcheck" id="allCheck" class="checkBox">전체선택</p> -->
		<ul>
			<c:forEach var="blockList" items="${blockList}">
		 		<li><input type="radio" name="blockList" class="rowCheck checkBox" value="${blockList.getBlockedMember()}"> ${blockList.getBlockedMember()} </li>
			</c:forEach>
		</ul>
		<hr>
	    <h3>차단해제 할 사용자</h3>
	    <button id="deleteBlock">차단해제</button>
  	</form>
</body>
</html>