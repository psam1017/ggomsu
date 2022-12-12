<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <form action="${pageContext.request.contextPath}/member/member-get-block-ok" id="signUpForm" name="signUpForm" method="post">
  	<ul>
  	<% 
  		
  	%>
  	</ul>
        <label>닉네임<input type="text" name="nickname" placeholder="차단 할 사용자 아이디를 입력해주세요"></label>
        <input type="submit" value="추가">
   </form>
</body>
</html>