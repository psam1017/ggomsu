<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="author" content="손하늘" />
    <meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다."/>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<%--     <script src="${pageContext.request.contextPath}/assets/js/UpdateMyInfo.js" defer></script> --%>
    <title>프로필</title>
</head>
	<body>
	<jsp:include page="/app/fix/MyPageAside.jsp" />
	<main id="main">
	  <form id="updateMyProfileForm" name="updateMyProfileForm" action="${pageContext.request.contextPath}/member/member-update-my-profile-ok" method="post" enctype="multipart/form-data">
	      <legend>프로필</legend>
		      <ul>
		        <li>
		         	 프로필 이미지 <img id="profileImage" src="${member.getProfileImageUrl()}" alt="프로필 이미지">
		          <br>
		        	  변경할 프로필 이미지를 골라주세요. <input type="file" name="profileImageUrl" id="profileImageUrl" value="">
		        </li>
		        <li id="nicknameChanege">
		         	 닉네임 <input type="text" id="nickname" name="nickname" value="${member.getNickname()}"> 
		         	 <span id="nicknameResult"></span>
		         	 <button type="button" id="nicknameChanegeBtn">닉네임 변경</button>
		        </li>
		      </ul>
            <button type="submit">저장</button>
          </form>
        </main>
   	<script type="text/javascript" defer>
	   	const nicknameChanegeBtn = document.getElementById("nicknameChanegeBtn");
	   	const updateMyProfileForm = document.getElementById("updateMyProfileForm");
	   	nicknameChanegeBtn.addEventListener("click", function(){
	   		updateMyProfileForm.setAttribute("action","${pageContext.request.contextPath}/member/member-update-my-nickname-ok");
	   		updateMyProfileForm.setAttribute("enctype", "none");
	   	});
   	</script>
    </body>
</html>
