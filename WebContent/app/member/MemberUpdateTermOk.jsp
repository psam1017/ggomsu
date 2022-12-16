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
    <meta name="description" content="이 세상의 모든 꼼수를 다루는 꼼수닷컴입니다." />
    <title>약관확인</title>
</head>
<body>
    <jsp:include page="../fix/myPageAside.jsp" />
    <main id="main">
        <section id="terms">
            <div>
                <h2>개인정보 수집 및 이용 약관</h2>
                <p>동의일자 : <c:out value="${member.getAgreedTermAt()}"/></p>
                <p><span>유효기간</span></p>
                <textarea name="" id="" cols="30" rows="10">
                    약관 내용 서술
                </textarea>
            </div>
            <div>
                <h2>서비스 이용약관</h2>
                <p>동의일자 : <c:out value="${member.getAgreedTermAt()}"/></p>
                <p><span>유효기간</span></p>
                <textarea name="" id="" cols="30" rows="10">
                    약관 내용 서술
                </textarea>
            </div>
            <div>
                <h2>마케팅 수신동의</h2>
                <p>동의일자 : <c:out value="${member.getAgreedMarketingAt()}">동의하지 않았습니다.</c:out></p>
                <p><span>유효기간</span></p>
                <textarea name="" id="" cols="30" rows="10">
                    약관 내용 서술
                </textarea>
                <form action="${pageContext.request.contextPath}/member/member-update-term-ok">
                    <input type="checkbox" name="agreedMarketingAt" id="agreedMarketingAt">
                    <input type="submit" value="수신동의 변경">
                </form>
            </div>
        </section>
    </main>
</body>
</html>
