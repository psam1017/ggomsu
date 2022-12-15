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
    <meta name="author" content="테스트 파일" />
    <meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다." />
    <title>회원가입</title>
</head>
<body>
    <section id="signUp">
        <h1 id="signUpH">회원가입</h1>
        <form action="${pageContext.request.contextPath}/member/member-sign-up-ok" id="signUpForm" name="signUpForm" method="post">
            <ul id="signUpUl">
                <li class="signUpLi">
                    이메일 <input type="email" name="email" id="email" value="psam1017@naver.com">
                    <span id="emailResult" name="emailResult">이메일은 필수항목입니다.</span>
                </li>
                <li class="signUpLi">
                    비밀번호 <input type="password" name="password" id="password">
                    <span id="passwordResult" name="passwordResult">비밀번호는 필수항목입니다.</span>
                </li>
                <li class="signUpLi">
                    비밀번호 확인 <input type="password" id="passwordCheck">
                    <span id="passwordCheckResult" name="passwordCheckResult"></span>
                </li>
                <li class="signUpLi">
                    닉네임 <input type="text" name="nickname" id="nickname">
                    <span id="nicknameResult" name="nicknameResult">닉네임은 필수항목입니다.</span>
                </li>
                <li class="signUpLi">
                    이름 <input type="text" name="name">
                </li>
                <li class="signUpLi">
                    성별: 남성<input type="radio" name="sex" value="M">
                    여성<input type="radio" name="sex" value="F">
                </li>
                <li class="signUpLi">
                    생년월일 <input type="date" name="birthDate">
                </li>
                <li class="signUpLi">
                    통신사
                    <select name="telecomValue">
                        <option value="KT">KT</option>
                        <option value="SKT">SKT</option>
                        <option value="LG">LG</option>
                        <option value="CPK">알뜰폰 KT</option>
                        <option value="CPS">알뜰폰 SKT</option>
                        <option value="CPL">알뜰폰 LG</option>
                    </select>
                </li>
                <li class="signUpLi">
                    전화번호
                    <input type="text" name="contact" maxlength="11" size="11">
                </li>
                <li class="signUpLi">
                    우편번호
                    <input type="text" name="zipcode" class="postcodify_postcode5" maxlength="5" size="5">
                    <button id="postcodify_search_button">검색</button><br>
                    도로명주소
                    <input type="text" name="address" class="postcodify_address" maxlength="100" size="100"><br>
                    상세주소 <input type="text" name="addressDetail" class="postcodify_details" maxlength="100" size="100">
                </li>
                <li class="signUpLi">
                    이용약관 동의: 동의합니다<input type="checkbox" name="agreedTermAt" class="agreedTermAt">
                    개인정보처리방침: 동의합니다<input type="checkbox" name="agreedTermAt" class="agreedTermAt">
                </li>
                <li class="signUpLi">
                    마케팅 동의: 동의합니다<input type="checkbox" name="agreedMarketingAt">
                </li>
                <li class="signUpLi">
                    <input type="button" name="signUpSubmit" id="signUpSubmit" value="회원가입">
                </li>
            </ul>
        </form>
    </section>
</body>
<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/assets/js/SignUp.js" defer></script>
</html>
