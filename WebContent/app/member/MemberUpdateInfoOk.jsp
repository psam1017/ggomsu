<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="author" content="박성민" />
    <meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다."/>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <title>회원정보</title>
</head>
	<body>
	<jsp:include page="/app/fix/MyPageAside.jsp" />
	<main id="main">
	  <form id="updateMemberForm" action="${pageContext.request.contextPath}/member/member-update-info-ok" method="post">
	    <fieldset id="viewProfile">
	      <legend>프로필</legend>
		      <ul>
		        <li>
		          프로필 이미지 <img id="profileImage" src="${member.getProfileImageUrl()}" alt="프로필 이미지">
		          <br>
		          변경할 프로필 이미지를 골라주세요. <input type="file" name="profileImageUrl" id="profileImageUrl" value="">
		        </li>
		        <li id="nicknameChanege" class="">
		          닉네임 <c:out value="${member.getNickname()}"/> <button type="button">닉네임 변경</button>
		        </li>
		        <li id="nicknameCancel" class="off">
		          닉네임 <input type="text" id="nickname" name="nickname" value="${member.getNickname()}"> <button type="button">변경 취소</button>
		        </li>
		      </ul>
		    </fieldset>
		    <fieldset id="viewPersonal">
		      <legend>회원정보</legend>
		      <ul>
		        <li>이메일 <span><c:out value="${member.getEmail()}" /></span></li>
		        <li>비밀번호 변경
		          <span><input type="password" name="password" id="password"></span>
		          <span name="passwordResult" id="passwordResult">비밀번호는 필수항목입니다.</span>
		        </li>
		        <li>비밀번호 확인 
		          <span><input type="password" name="passwordCheck" id="passwordCheck"></span>
		          <span name="passwordCheckResult" id="passwordCheckResult"></span>
		        </li>
		        <li>이름<span><c:out value="${member.getName()}"/></span></li>
		        <li>성별<span><c:out value="${member.getSex()}"/></span></li>
		        <li>생년월일<span><c:out value="${member.getBrithDate()}"/></span></li>
		        <li>가입일<span><c:out value="${member.getCreatedAt()}"/></span></li>
  	          </ul>
            </fieldset>
            <fieldset id="viewPersonalOption">
              <legend>개인정보 변경</legend>
              <ul>
                <li>
                    통신사 
                    <select name="telecomValue" id="telecomValue">
                        <option value="KT">KT</option>
                        <option value="SKT">SKT</option>
                        <option value="LG">LGU+</option>
                        <option value="CPK">알뜰폰KT</option>
                        <option value="CPS">알뜰폰SKT</option>
                        <option value="CPL">알뜰폰LGU+</option>
                    </select>
                </li>
                <li>
                    전화번호 <input type="text" maxlength ="3" size="3" class="eachContact">
                    - <input type="text" maxlength ="4" size="4" class="eachContact">
                    - <input type="text" maxlength ="4" size="4" class="eachContact">
                    <input type="hidden" name="contact" id="contact" value="">
                </li>
                <li>
                    우편번호 <input type="text" name="zipcode" id="zipcode" class="postcodify_postcode5" maxlength="5" value="" disabled>
                             <button id="postcodify_search_button" type="button">검색</button>
                </li>
                <li>
                    도로명주소 <input type="text" name="address" id="address" class="postcodify_address" maxlength="100" value="" disabled>
                </li>
                <li>
                    상세주소 <input type="text" name="addressDetail" id="addressDetail" class="postcodify_details" maxlength="100" value="" placeholder="상세주소를 입력해주세요.">
                </li>
              </ul>
            </fieldset>
            <button type="submit" id="updateMemberSubmit">개인정보 수정</button>
          </form>
        </main>
    </body>
<script> const contextPath = "${pageContext.request.contextPath}"; </script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
</html>
