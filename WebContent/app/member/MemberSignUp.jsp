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
    <title>회원가입 : 꼼수닷컴</title>
</head>
<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/assets/js/SignUp.js" defer></script>
<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
<body>
	<main id="signUp">
        <h1 id="signUpH">회원가입</h1>
        <form action="${pageContext.request.contextPath}" id="signUpForm" name="signUpForm" method="post">
        	<fieldset>
        	<legend>필수항목</legend>
            <ul id="signUpUl">
                <li>이메일 <input type="email" name="email" id= "email"></li><span id=emailResult></span>
                <li>비밀번호 <input type="password" name="password" id="password"></li><span id=passwordResult></span>
                <li>비밀번호 확인 <input type="password" id="passwordCheck"></li>
                <li>닉네임 <input type="text" name="nickname" id="nickname"></li><span id=nicknameResult></span>
            </fieldset>
            <fieldset>
            <legend>선택항목</legend>
                <li>이름 <input type="text" name="name" id="name"></li>
                <li>성별: 남성<input type="radio" name="sex" value="남성">
                        	여성<input type="radio" name="sex" value="여성"></li>
                <li>생년월일 <input type="date" name="birthDate" id="birthDate"></li>
                <li>통신사 <select name="telecomValue" id="telecomValue">
                    <option value="KT">KT</option>
                    <option value="SKT">SKT</option>
                    <option value="LG">LGU+</option>
                    <option value="CPK">알뜰폰KT</option>
                    <option value="CPS">알뜰폰SKT</option>
                    <option value="CPL">알뜰폰LGU+</option>
                </select></li>
                <li>전화번호 <input type="text" name="contact" maxlength ="3" size="3" id="contact1">
                 - <input type="text" name="contact" maxlength ="4" size="4" id="contact2">
                 	- <input type="text" name="contact" maxlength ="4" size="4" id="contact3"></li>
                <li>우편번호 <input type="text" name="zipcode" class="postcodify_postcode5" maxlength="5" size="5" id="zipcode">
                                                <button id="postcodify_search_button">검색</button><br/>
                                    도로명주소 <input type="text" name="address" id="address" class="postcodify_address" maxlength="100" size="100"/><br/>
                                    상세주소 <input type="text" name="addressDetail" id="addressDetail" class="postcodify_details" maxlength="100" size="100"/></li>
                <li>약관 동의: 동의합니다<input type="radio" name="agreedTermAt" value="agree"></li>
                <li>마케팅 동의: 동의합니다<input type="radio" name="agreedMarketingAt" value="agree"></li>
                <li>개인정보 처리방침동의: 동의합니다<input type="radio" name="agreed" value="agree"><li>
            </fieldset>
                <li><input type="button" value="회원가입" id="signUpSubmit"></li>
            </ul>
        </form>
    </main>
</body>
</html>