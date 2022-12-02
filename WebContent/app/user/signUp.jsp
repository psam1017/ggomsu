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
<body>
	<section id="signUp">
        <h1 id="signUpH">회원가입</h1>
        <form action="#" id="signUpForm" name="signUpForm" method="post">
            <ul id="signUpUl">
                <li class="signUpLi">이메일 <input type="text" name="email"></li>
                <li  class="signUpLi">비밀번호 <input type="password" name="password"></li>
                <li  class="signUpLi">비밀번호 확인 <input type="password" name="passwordCheck"></li>
                <li  class="signUpLi">닉네임 <input type="text" name="userNickname"></li>
                <li  class="signUpLi">이름 <input type="text" name="name"></li>
                <li  class="signUpLi">성별: 남성<input type="radio" name="sex" value="남성" checked>
                        	여성<input type="radio" name="sex" value="여성"></li>
                <li  class="signUpLi">생년월일 <input type="date" name="birthDate"></li>
                <li  class="signUpLi">통신사 <select name="telecomValue">
                    <option value="KT">KT</option>
                    <option value="SKT">SKT</option>
                    <option value="LGU+">LGU+</option>
                    <option value="MVNO">알뜰폰</option>
                </select></li>
                <li  class="signUpLi">전화번호 <input type="text" name="content" maxlangth ="11" size="11"></li>
                <li  class="signUpLi">우편번호 <input type="text" name="zipcode" class="postcodify_postcode5" maxlength="7" size="7">
                                                <button id="postcodify_search_button" onclick="postcodifyPopUp()">검색</button><br/>
                                    도로명주소 <input type="text" name="zipcode" class="postcodify_address" maxlength="30" size="30"/><br/>
                                    상세주소 <input type="text" name="zipcode" class="postcodify_details" maxlength="30" size="30"/></li>
                <li  class="signUpLi">주소 <input type="text" name="address"></li>
                <li  class="signUpLi">상세주소 <input type="text" name="addressDetail"></li>
                <li  class="signUpLi">약관 동의: 동의합니다<input type="radio" name="agreedTermAt" value="agree">
                    	동의하지 않습니다<input type="radio" name="agreedTermAt" value="disagree" checked></li><!--질문-->
                <li  class="signUpLi">마케팅 동의: 동의합니다<input type="radio" name="agreedMarketingAt" value="agree">
                    	동의하지 않습니다<input type="radio" name="agreedMarketingAt" value="disagree" checked></li>
                <li  class="signUpLi"><input type="button" value="회원가입" onclick="SignUp()"></li>
            </ul>
        </form>
    </section>
</body>
</html>