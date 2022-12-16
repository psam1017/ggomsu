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
    <meta name="author" content="박성민">
    <meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다.">
    <title>비밀번호 확인</title>
</head>
<body>
    <main id="main">
      <section id="checkPassword">
        <h2>비밀번호 확인</h2>
        <form id="passwdForm" action="${pageContext.request.contextPath}/member/member-check-password" method="post">
            <span>비밀번호</span>
            <input type="password" name="password">
            <button>확인</button>
        </form>
      </section>
    </main>
</body>
</html>