<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!doctype html>
<html lang="kr">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="이성호">
    <meta name="generator" content="Hugo 0.104.2">
    <title>관리자 - 게시글 신고</title>

	<link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }
    </style>

    
    <!-- Custom styles for this template -->
    
  </head>
  <body>
    
	<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
	  <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6" href="#">GGOMSU</a>
	  <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <input class="form-control form-control-dark w-100 rounded-0 border-0" type="text" placeholder="Search" aria-label="Search">
	  <div class="navbar-nav">
	    <div class="nav-item text-nowrap">
	      <a class="nav-link px-3" href="#">Sign out</a>
	    </div>
	  </div>
	</header>

	<div class="container-fluid">
	  <div class="row">
	    <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
	      <div class="position-sticky pt-3 sidebar-sticky">
	        <ul class="nav flex-column">
	          <li class="nav-item">
	            <a class="nav-link active" aria-current="page" href="#">
	              <span data-feather="home" class="align-text-bottom"></span>
	              관리자 정보
	            </a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="${pageContext.request.contextPath}/admin/admin-article-report-ok">
	              <span data-feather="file" class="align-text-bottom"></span>
	              게시글 관리
	            </a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="${pageContext.request.contextPath}/admin/admin-comment-report-ok">
	              <span data-feather="shopping-cart" class="align-text-bottom"></span>
	              댓글 관리
	            </a>
	          </li>
	        </ul>
	
	        <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted text-uppercase">
	          <span>Saved reports</span>
	          <a class="link-secondary" href="#" aria-label="Add a new report">
	            <span data-feather="plus-circle" class="align-text-bottom"></span>
	          </a>
	        </h6>
	        <ul class="nav flex-column mb-2">
	          <li class="nav-item">
	            <a class="nav-link" href="#">
	              <span data-feather="file-text" class="align-text-bottom"></span>
	              Current month
	            </a>
	          </li>
	        </ul>
	      </div>
	    </nav>
	
	    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
	        <div class="table-responsive" style="width:100%; height:40vh; overflow:auto">
	          <table class="table">
	            <caption>댓글 신고 목록</caption>
	            <thead>
	              <tr>
	                <th scope="col">commentIndex</th>
	                <th scope="col">nickname</th>
	                <th scope="col">reason</th>
	                <th scope="col">Handle</th>
	              </tr>
	            </thead>
	            <tbody>
	            <c:choose>
	                <c:when test="${commentReportList != null and fn:length(commentReportList) > 0}">
	                    <c:forEach var="commentReport" items="${commentReportList}">
	                    	<c:if test="${commentReport.getCommentDeleteCheck() == 0}">
		                        <tr class="clickable-tr">
		                            <th scope="row"><c:out value="${commentReport.getCommentIndex()}"/></th>
		                            <td><c:out value="${commentReport.getNickname()}"/></td>
				                     <td><c:out value="${commentReport.getCommentReportReason()}"/></td>
				                     <td>
				                      	<a class="btn btn-primary" href="#">
				                            	상세보기
				                        </a>
		                      		</td>
		                   		</tr>
	                   		</c:if>
	                    </c:forEach>
	                </c:when>
	                <c:otherwise>
	                    <tr>
	                        <td colspan="5"> 신고된 게시글이 없습니다. </td>
	                    </tr>
	                </c:otherwise>
	              </c:choose>
	              </tbody>
	          </table>
	          </div>
	    	<div  class="table-responsive table-" >
	          	<table id="con" class="table thead-dark">
	          		<tr>
		                <th width="20%" scope="col">분류</th>
		                <th scope="col" >내용</th>
	               </tr>
	          		<tr>
	          			<th scope="row">댓글 번호</th>
	          			<td id="commentIndex"> </td>
	          		</tr>
	          		<tr>
	          			<th scope="row">신고내용</th>
	          			<td id="reportReason"> </td>
	          		</tr>
	          		<tr>
	          			<th scope="row">댓글 내용</th>
	          			<td id="commentContent"> </td>
	          		</tr>
	          		<tr>
	          			<th scope="row">신고자</th>
	          			<td id="reportMember"> </td>
	          		</tr>
	          		<tr>
	          			<th scope="row">신고받은사람</th>
	          			<td id="reportedMember"> </td>
	          		</tr>
	          		<tr>
	          			<td><button id="reportPreserve" class="btn btn-primary">보존</button></td>
	          			<td>
	          				<button id="reportDelete" class="btn btn-danger">삭제</button><br>
	          				<input type="radio" name="deleteReason" value="광고성 게시물">음란성/선정성<br>
							<input type="radio" name="deleteReason" value="부적절한 언어 포함">광고/홍보성<br>
							<input type="radio" name="deleteReason" value="사용자간 분란 조장">혐오감 유발<br>
							<input type="radio" name="deleteReason" value="개인정보 침해">개인정보 침해<br>
							<input type="radio" name="deleteReason" value="지나친 욕설">지나친 욕설<br>
							<input type="radio" name="deleteReason" value="저작권 위반">저작권 위반<br>
							<input type="radio" name="deleteReason" value="허위정보/조작/오보">허위정보/조작/오보<br>
							<input type="radio" name="deleteReason" value="지나친 추천 유도">지나친 추천 유도<br>
							<input type="radio" name="deleteReason" value="기타사유">기타사유<br>
	          			</td>
	          		</tr>
	    		</table>
	    	</div>
	    </main>
	  </div>
	</div>

    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/AdminCommentReport.js"></script>
	
    <script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script>
    
	<script>
        const contextPath = "${pageContext.request.contextPath}";
        
    </script>
  </body>
</html>
