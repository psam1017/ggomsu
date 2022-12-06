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
    <meta name="author" content="배규">
    <meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다.">
    <title>개인정보 변경</title>
</head>
<body>
  <div id="mainWrap">
    <main id="main"> 
      <%@ include file = "../fix/aside.jsp/" %>
      
      <section id="mainContent">
        <h2>개인정보 변경</h2>
        <form action="" method="post">
          <div id="personalDataPage">
            <div>
              <h3>비밀번호</h3>
              <div>
                <input type="password">
                <button>수정</button>
              </div>
            </div>

            <div>
              <p>닉네임</p>  
              <input type="text"  name="userNickname">
              <button>변경</button>
              <h3>이메일</h3>
              <input type="email">
              <button>이메일 인증</button>
            </div>

            <div>
              <h3>연락처</h3>
              <select name="telecomValue">
                <option value="SKT">SKT</option>
                <option value="KT">KT</option>
                <option value="LG">LG</option>
                <option value="알뜰폰">알뜰폰</option>
              </select>
              <select name="contact" style="width:50px;">
                <option value="011">011</option>
                <option value="010">010</option>
              </select>
              - <input type="text" maxlength="4" size="4" name="contact"> -
              <input type="text" maxlength="4" size="4" name="contact"> 
              <button>변경</button>              
            </div>

            <div>
              <h3>주소</h3>
              <input name="zipcode" size="10" maxlength="7" readonly>
                    <input type="button" value="우편번호 검색" style="cursor:pointer;"><br/>
                    <input name="address" size="70" maxlength="70" readonly><br>
                    <input name="addressDetail" size="70" maxlength="70">
            </div>

            <div>
              <h3>소셜계정연동</h3>
              <div>
              	<p>네이버</p>
	            <input type="button">
	          </div>
	          <div>
              	<p>카카오</p>
	            <input type="button">
	          </div>
	          <div>
              	<p>구글</p>
	            <input type="button">
	          </div>
	          </div>
            </div>

            <div>
              <h3>회원탈퇴</h3>
              <div>
                <div>
                  <p> 회원 탈퇴일로부터 계정과 닉네임을 포함한 계정 정보(아이디/이메일/닉네임)는
                    <a href="#">개인정보 보호방침</a> 에 따라 60일간 보관(잠김)되며, 60일 경과된 후에는 모든 
			                    개인 정보는 완전히 삭제되며 더 이상 복구할 수 없게 됩니다.작성된  게시물은 삭제되지 않으며, 익명처리 후 꼼수 로 소유권이 귀속됩니다.
		          </p>
                </div>
              </div>
              <div>
                <input type="checkbox" name="deletedAt">
                <label>계정 삭제에 관한 링크를 읽고 동의합니다.</label>
                <button>버튼</button>
              </div>
            </div>
          </div>
        </form>
      </section>  
    </main>
  </div>
</body>
</html>