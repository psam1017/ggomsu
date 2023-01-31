<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<aside class="position-absolute right-2 top-20 width-13 height-100 rounded-3">
    <div class="main-color-light rounded-3 w-100 height-65 list-group text-center padding-y-10">
    	<h5>최근 변경</h5>
        <div class="rounded-0 overflow-hidden">
        <c:forEach var="subject" items="${recentSubjects}">
            <a class="list-group-item main-color-light border-0 shadow-none main-navbar-action" href="${pageContext.request.contextPath}/wiki/wiki-view-ok?subject=${subject}"><c:out value="${subject}" /></a>
        </c:forEach>
        </div>
    </div>
    <div class="back-color-dark w-100 height-40 mt-3 rounded-3">
        <a href="#">광고(팀원 이력서)</a>
    </div>
</aside>