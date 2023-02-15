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
    <meta name="author" content="김지혜, 박성민">
    <meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다.">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <title>회원가입</title>
</head>
<body>
	<main>
        <h2>회원가입</h2>
        <section>
            <form action="${pageContext.request.contextPath}/member/member-sign-up-ok" id="signUpForm" name="signUpForm" method="post">
                <fieldset>
                <legend>필수항목</legend>
	                <ul>
	                    <li>이메일 <input type="email" name="email" id= "email"><span id="emailResult"></span></li>
	                    <li>비밀번호 <input type="password" name="password" id="password"><span id="passwordResult"></span></li>
	                    <li>비밀번호 확인 <input type="password" id="passwordCheck"><span id="passwordCheckResult"></span></li>
	                    <li>닉네임 <input type="text" name="nickname" id="nickname"><span id="nicknameResult"></span></li>
	                    <li>이름 <input type="text" name="name" id="name"><span id="nameResult"></span></li>
	                    <li>
	                        성별: <span>남성<input type="radio" name="sex" class="sex" value="M"></span>
	                              <span>여성<input type="radio" name="sex" class="sex" value="F"></span>
	                    </li>
	                    <li>생년월일 <input type="date" name="birthDate" id="birthDate"></li>
	                    <li>
	                        통신사
	                        <select name="telecomValue" id="telecomValue">
	                        	<option value="NO" selected>통신사 선택</option>
	                            <option value="KT">KT</option>
	                            <option value="SKT">SKT</option>
	                            <option value="LG">LGU+</option>
	                            <option value="CPK">알뜰폰KT</option>
	                            <option value="CPS">알뜰폰SKT</option>
	                            <option value="CPL">알뜰폰LGU+</option>
	                        </select>
	                    </li>
	                    <li>
	                        전화번호 <input type="text" maxlength ="3" class="eachContact">
	                        - <input type="text" maxlength ="4" class="eachContact">
	                        - <input type="text" maxlength ="4" class="eachContact">
							<span id="contactResult"></span>
	                        <input type="hidden" name="contact" id="contact" value="">
	                    </li>
	                </ul>
                </fieldset>
                <fieldset>
                <legend>선택항목</legend>
	                <ul>
	                    <li>
	                        우편번호 <input type="text" name="zipcode" id="zipcode" class="postcodify_postcode5" maxlength="5" value="" readonly>
	                                 <button id="postcodify_search_button" type="button">검색</button>
	                    </li>
	                    <li>
	                        도로명주소 <input type="text" name="address" id="address" class="postcodify_address" maxlength="100" value="" readonly>
	                    </li>
	                    <li>
	                        상세주소 <input type="text" name="addressDetail" id="addressDetail" class="postcodify_details" maxlength="100" value="" placeholder="상세주소를 입력해주세요.">
	                    </li>
	                </ul>
                </fieldset>
                <fieldset>
                <legend>약관동의</legend>
	                <ul>
		                <li>개인정보 수입 및 이용약관에 동의합니다. (필수) <input type="checkbox" class="agreedTermAt"></li>
	                    <li>서비스 이용약관에 동의합니다. (필수) <input type="checkbox" class="agreedTermAt"></li>
	                    <li>마케팅 수신여부에 동의합니다. <input type="checkbox" name="agreedMarketingAt"></li>
	                </ul>
                </fieldset>
                <button type="button" name="signUpSubmit" id="signUpSubmit">회원가입</button>
            </form>
        </section>
    </main>
</body>
<script> const contextPath = "${pageContext.request.contextPath}"; </script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
<script src="${pageContext.request.contextPath}/assets/js/SignUp.js" defer></script>
</html>