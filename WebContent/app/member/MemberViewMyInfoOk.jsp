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
    <title>회원정보</title>
</head>

	<body>
	<jsp:include page="/app/fix/MyPageAside.jsp" />
	<main id="main">
	  <form id="viewMemberForm" name="viewMemberForm" action="${pageContext.request.contextPath}/member/member-update-info-ok" method="post" enctype="multipart/form-data">
	    <input type="hidden" value="email">
	    <fieldset id="viewProfile">
	      <legend>프로필</legend>
		      <ul>
		        <li>
		          프로필 이미지 <img id="profileImage" src="${member.getProfileImageUrl()}" alt="프로필 이미지">
		          <br>
		          변경할 프로필 이미지를 골라주세요. <input type="file" name="profileImageUrl" id="profileImageUrl" value="">
		        </li>
		        <li id="nicknameChanege" class="on">
		          닉네임 <input type="text" id="nickname" name="nickname" value="${member.getNickname()}" disabled="disabled"> <span id="nicknameResult"></span><button type="button" id="nicknameChanegeBtn">닉네임 변경</button>
		        </li>
		        <li id="nicknameCancel" class="off">
		          닉네임 <input type="text" id="nickname" name="nickname" value="${member.getNickname()}"> <button type="button" id="nicknameCancelBtn">변경 취소</button>
		        </li>
		      </ul>
		    </fieldset>
		    <fieldset id="viewPersonal">
		      <legend>회원정보</legend>
		      <ul>
		        <li>이메일 <span><c:out value="${member.getEmail()}" /></span></li>
		        <li>비밀번호 변경
		          <span><input type="password" name="password" id="password" value="${member.getPassword()}"></span>
		          <span name="passwordResult" id="passwordResult"></span>
		        </li>
		        <li>비밀번호 확인 
		          <span><input type="password" name="passwordCheck" id="passwordCheck"></span>
		          <span name="passwordCheckResult" id="passwordCheckResult"></span>
		        </li>
		        <li>이름<span><c:out value="${member.getName()}"/></span></li>
		        <c:set var="gender" value="${member.getSex()}" />
		        <li>성별<span><c:if test="${gender.equals('M')}"><c:out value="남자"/></c:if>
		        <c:if test="${gender.equals('F')}"><c:out value="여자"/></c:if></span></li>
		        <li>생년월일<span><c:out value="${member.getBirthDate()}"/></span></li>
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
                <script type="text/javascript" defer="defer">
	                function init(){
	        			setTelecomValue("${member.getTelecomValue()}");
	        		}
	        		
	        		function setTelecomValue(val){
	        			console.log("${member.getTelecomValue()}");
	        			var selectTelecom = document.getElementById("telecomValue");
	        			for (i = 0, j = selectTelecom.length; i < j; i++) {
	        				if(selectTelecom.options[i].value == val){
	        					selectTelecom.options[i].selected = true;
	        					break;
	        				}
	        			}
	        		}
	        		init();
                </script>
                <li>
                	<c:set var="contact1" value="${member.getContact().substring(0,3)}"/>
					<c:set var="contact2" value="${member.getContact().substring(3,7)}"/>
					<c:set var="contact3" value="${member.getContact().substring(7,11)}"/>
                    전화번호 <input type="text" maxlength ="3" size="3" class="eachContact" value="${contact1}" />
                    - <input type="text" maxlength ="4" size="4" class="eachContact" value="${contact2}">
                    - <input type="text" maxlength ="4" size="4" class="eachContact" value="${contact3}">
                    <input type="hidden" name="contact" id="contact" value="${member.getContact()}">
                    <span id="contactResult"></span>
                </li>
                <li>
                    우편번호 <input type="text" name="zipcode" id="zipcode" class="postcodify_postcode5" maxlength="5" value="${member.getZipcode()}" readonly>
                             <button id="postcodify_search_button" type="button">검색</button>
                </li>
                <li>
                    도로명주소 <input type="text" name="address" id="address" class="postcodify_address" maxlength="100" value="${member.getAddress()}" readonly>
                </li>
                <li>
                    상세주소 <input type="text" name="addressDetail" id="addressDetail" class="postcodify_details" maxlength="100" value="${member.getAddressDetail()}" placeholder="상세주소를 입력해주세요.">
                </li>
              </ul>
            </fieldset>
            <button type="submit">수정</button>
            <button type="button" name="updateMemberSubmit" id="updateMemberSubmit">개인정보 수정</button>
          </form>
        </main>
    </body>
<script> const contextPath = "${pageContext.request.contextPath}"; </script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
</html>
