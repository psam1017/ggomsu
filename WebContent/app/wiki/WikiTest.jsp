<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>위키테스트</title>
</head>
<body>
	<form name="test" action="${pageContext.request.contextPath}/WikiTest" method="post">
		<textarea name="text" rows="6" cols="30" style="resize:none;" value=""></textarea>
		<input type="submit" value="전송">
	</form>
</body>
</html>