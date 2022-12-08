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
    <title>회원정보</title>
</head>
<body>
  <div id="mainWrap">
    <main id="main">
    <jsp:include page="../fix/myPageAside.jsp" />
      
      <section id="mainContent">
        <form action="" method="">
          <h2> 회원정보 </h2>
          <div id="content1">
            <div id="content1Child1">
              <p>이름</p>
              <input type="text" name="name" class="content1Ip"disabled>
              <p>닉네임</p>  
              <input type="text" name="userNickname" class="content1Ip" disabled>
              <p>E-mail</p>
              <input type="email" name="userEmail"class="content1Ip" disabled>
              <p>성별</p>
              <input type="text" name="sex" class="content1Ip" disabled>
              <p>생일</p>
              <input type="email" name="birthDate" class="content1Ip" disabled>
            </div>
            <div id="content1Child2">
              <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBDqY60zuKoKwtiS_KImuJki6FuJZUUZY3ag&usqp=CAU" alt="#">
              <div>
                <input type="file" name="profileImageUrl"/><br>
                <button>되돌리기</button>
              </div>
            </div> 
          </div>
            
            
          <div id="content2" >
            <div id="content2Child1">
              <div>
                <h3>마케팅 수신동의</h3>
                <p>꼼수에서 진행하는 다양한 이벤트, 정보성 뉴스레터 및 광고 수신자를 등록할 수 있습니다.</p>
                <a href="#"> 약관확인 바로가기 </a>
              </div>
              <div class="toggle_button">
                <input type="checkbox" id="toggle" name="agreedMarketingAt" hidden> 
                <label for="toggle" class="toggleSwitch">
                  <span class="toggleButton"></span>
                </label>
              </div>
            </div>
            
            <div id="content2Child2">
              <button>저장</button>
            </div>
          </div>
        </form>
      </section>
    </main>
  </div>
</body>
</html>