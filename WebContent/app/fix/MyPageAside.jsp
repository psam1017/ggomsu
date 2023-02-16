<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<aside id="mainNav">
  	<ul>
    	<h2> 마이페이지 </h2>
    	<li><a href="${pageContext.request.contextPath}/member/member-view-my-info-ok?myPage=myProfile">프로필</a></li>
    	<li><a href="${pageContext.request.contextPath}/member/member-view-my-info-ok?myPage=myInfo">회원정보</a></li>
    	<li><a href="${pageContext.request.contextPath}/member/member-view-my-info-ok?myPage=myPrivacy">개인정보</a></li>
    	<li><a href="${pageContext.request.contextPath}/member/member-view-my-history-ok">최근활동</a></li>
    	<li><a href="#">스크랩</a></li>
    	<li><a href="${pageContext.request.contextPath}/member/member-view-alarm-flag-ok">알림</a></li>
    	<li><a href="${pageContext.request.contextPath}/member/member-view-term-ok">약관확인</a></li>
    	<li><a href="${pageContext.request.contextPath}/member/member-get-block-ok">차단목록</a></li>
    	<li><a href="${pageContext.request.contextPath}/member/member-logout-ok">로그아웃</a></li>
    	<li><a href="${pageContext.request.contextPath}/app/member/MemberWithdrawal.jsp">회원탈퇴</a></li>
  	</ul>
</aside>