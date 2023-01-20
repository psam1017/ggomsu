<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<aside class="position-absolute width-13 height-100 left-2 top-20 d-flex flex-column">
    <div class="main-color-light rounded-3 w-100 height-65 list-group text-center padding-y-10">
        <div class="rounded-0 overflow-hidden">
            <a class="list-group-item main-color-light border-0 shadow-none main-navbar-action" href="${pageContext.request.contextPath}/board/article-get-list-ok?boardValue=notice">공지사항</a>
            <a class="list-group-item main-color-light border-0 shadow-none main-navbar-action" href="${pageContext.request.contextPath}/board/article-get-list-ok?boardValue=coding">코딩게시판</a>
            <a class="list-group-item main-color-light border-0 shadow-none main-navbar-action" href="${pageContext.request.contextPath}/board/article-get-list-ok?boardValue=game">게임게시판</a>
            <a class="list-group-item main-color-light border-0 shadow-none main-navbar-action" href="${pageContext.request.contextPath}/board/article-get-list-ok?boardValue=free">자유게시판</a>
        </div>
    </div>
    <div class="back-color-dark w-100 height-40 mt-3 rounded-3">
        <a href="#">광고(팀원 이력서)</a>
    </div>
</aside>