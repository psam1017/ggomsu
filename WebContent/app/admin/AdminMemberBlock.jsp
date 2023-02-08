<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	          <li class="nav-item">
	            <a class="nav-link" href="${pageContext.request.contextPath}/admin/admin-member-block">
	              <span data-feather="shopping-cart" class="align-text-bottom"></span>
	              유저 관리
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
	            <caption>멤버 목록</caption>
	            <thead>
	              <tr>
	                <th scope="col">이름</th>
	                <th scope="col">닉네임</th>
	                <th scope="col">신고횟수</th>
	                <th scope="col">상태</th>
	                <th scope="col">상태변경</th>
	              </tr>
	            </thead>
	            <tbody>
	            <c:choose>
	                <c:when test="${members != null and fn:length(members) > 0}">
	                    <c:forEach var="member" items="${members}">
	                        <tr class="clickable-tr">
	                            <th scope="row"><c:out value="${member.getName()}"/></th>
                             	<td><c:out value="${member.getNickname()}"/></td>
                             	<td><c:out value="${member.getAbuseCount()}"/></td>
                             	<td>
                             		<c:set var="memberStatus" value="${member.getStatusValue()}"/>
                   					<form action="${pageContext.request.contextPath}/admin/admin-member-status-update-ok" method="post" class="memberStatus">
		                             	<input type="hidden" name="memberNickname" value="${member.getNickname()}">
		                             	<select name="memberStatus" class="form-select selectMemberStatus">
		                             		<option value="ADM" <c:if test="${'ADM' eq memberStatus}">selected</c:if> >관리자</option>
										    <option value="MEM" <c:if test="${'MEM' eq member.getStatusValue()}">selected</c:if>>정상회원</option>
										    <option value="SUS" <c:if test="${'SUS' eq member.getStatusValue()}">selected</c:if>>관리자에 의해 정지</option>
										    <option value="TMP" <c:if test="${'TMP' eq member.getStatusValue()}">selected</c:if>>비회원</option>
										    <option value="DEL" <c:if test="${'DEL' eq member.getStatusValue()}">selected</c:if>>자발적 탈퇴</option>
										    <option value="DOR" <c:if test="${'DOR' eq member.getStatusValue()}">selected</c:if>>휴면 계정</option>
										    <option value="EML" <c:if test="${'EML' eq member.getStatusValue()}">selected</c:if>>이메일 인증 대기</option>
		                             	</select>
	                             	</form>
                             	</td>
		                    	<td><a class="btn btn-secondary updateMemberStatus">적용</a></td>
	                   		</tr>
	                    </c:forEach>
	                </c:when>
	                <c:otherwise>
	                    <tr>
	                        <td colspan="5"> 멤버가 없습니다. </td>
	                    </tr>
	                </c:otherwise>
	              </c:choose>
	              </tbody>
	          </table>
	          </div>
	    </main>
	  </div>
	</div>
    <%-- <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script> --%>
	<script src="${pageContext.request.contextPath}/assets/js/AdminMemberBlock.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script>
	<script>
        const contextPath = "${pageContext.request.contextPath}";
        const updateMemberStatus = document.querySelectorAll(".updateMemberStatus");
        const memberStatunsFrom = document.querySelectorAll(".memberStatus");
        
        updateMemberStatus.forEach((element,index) => {
        	
        	element.preventDefault;
        	element.addEventListener("click",function() {
        		memberStatunsFrom[index].submit();
        	})
        })
    </script>
  </body>
</html>
