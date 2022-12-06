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
    <title>비밀번호 확인</title>
</head>
<body>
  <div id="mainWrap">
    <main id="main">
      <%@ include file="${pageContext.request.contextPath}/app/fix/aside.jsp/" %>

      <section id="mainContent">
        <h2>비밀번호 확인</h2>
        <!-- 폼태그 작성필요 -->
        <form id="passwdForm" action="" method="post">
          <div>
            <p>비밀번호 입력</p>
            <input type="password" name="password">
            <button>확인</button>
          </div>
        </form>
      </section>
    </main>
  </div>
</body>
</html>