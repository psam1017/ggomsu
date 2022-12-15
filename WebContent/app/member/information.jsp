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
        <title>회원정보</title>
    </head>
    <body>
        <jsp:include page="../fix/myPageAside.jsp" />

        <main id="infomationMain">
          <form name="memberInformation" id="updateMembersForm" action="${pageContext.request.contextPath}/member/member-update-info-ok" method="post">
            <fieldset id="viewProfile">
              <legend>프로필</legend>
              <ul>
                <li>
                  프로필 이미지 <img id="memberInfoProfileImageUrl" src="${member.getProfileImageUrl}" alt="프로필 이미지">
                  <br>
                  변경할 프로필 이미지를 골라주세요. <input type="file" name="profileImageUrl" value="">
                </li>
                <li>닉네임 <c:out value="${member.getNickname()}"/></li>
              </ul>
            </fieldset>
            <fieldset id="viewPersonal">
              <legend>회원정보</legend>
              <ul>
                <li>이메일 <span><c:out value="${member.getEmail()}" /></span></li>
                <li>비밀번호 변경
                  <span><input type="password" name="password" id="memberInfoPassword"></span>
                  <span name="passwordResult" id="passwordResult">비밀번호는 필수항목입니다.</span>
                </li>
                <li>비밀번호 확인 
                  <span><input type="password" name="passwordCheck" id="memberInfoPasswordCheck"></span>
                  <span name="passwordCheckResult" id="passwordCheckResult"></span>
                </li>
                <li>이름<span><c:out value="${member.getName()}"/></span></li>
                <li>성별<span><c:out value="${member.getSex()}"/></span></li>
                <li>생일<span><c:out value="${member.getBrithDate()}"/></span></li>
                <li>가입일<span><c:out value="${member.getCreatedAt()}"/></span></li>
              </ul>
            </fieldset>
            <fieldset id="viewPersonalOption">
              <legend>개인정보 변경</legend>
              <ul>
                <li>
                  전화번호
                  <select name="telecomValue" id="memberInfoTelecomValue">
                    <option value="KT">KT</option>
                    <option value="SKT">SKT</option>
                    <option value="LG">LG</option>
                    <option value="CPK">알뜰폰(KT)</option>
                    <option value="CPS">알뜰폰(SKT)</option>
                    <option value="CPL">알뜰폰(LG)</option>
                  </select>
                  <input type="text" name="contact1" id="memberInfoContact1"> - 
                  <input type="text" name="contact2" id="memberInfoContact2"> - 
                  <input type="text" name="contact3" id="memberInfoContact3">
                </li>
                <li>우편번호 
                  <input type="text" name="zipcode" class="postcodify_postcode5" maxlength="5" size="5" id="zipcode" value="${member.getZipcode()}">
                  <button id="postcodify_search_button">검색</button>
                  <br>
                  도로명주소 <input type="text" name="address" id="memberInfoAddress" class="postcodify_address" maxlength="100" size="100" value="${member.getAddress()}">
                  <br>
                  상세주소 <input type="text" name="addressDetail" id="memberInfoAddressDetail" class="postcodify_details" maxlength="100" size="100" value="${member.getAddressDetail()}">
                </li>
              </ul>
            </fieldset>
            <input type="button" value="개인정보 수정" id="updateMemberSubmit">
          </form>
        </main>
    </body>
</html>
