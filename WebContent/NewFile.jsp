<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a class="move" href="${ pageContext.request.contextPath }/NewFile.jsp">이동</a>
	<%= request.getAttribute("userid") %>
	<%= request.getAttribute("name") %>
</body>
<script>
	const move = document.querySelector(".move");
	move.addEventListener("click", function(e){
		e.preventDefault();
		
		const form = document.createElement('form');
		
	    form.setAttribute('method', 'POST');
	    form.setAttribute('action', '${pageContext.request.contextPath}/NewFile?name=psam');
	    
	    let obj;
	    obj = document.createElement('input');
	    obj.setAttribute('type', 'hidden');
	    obj.setAttribute('name', 'userid');
	    obj.setAttribute('value', 'id');
	    form.appendChild(obj);
	    
	    /* 쿼리 파라미터가 우선된다. 이 값은 전달해도 받을 수 없다.
	    obj = document.createElement('input');
	    obj.setAttribute('type', 'text');
	    obj.setAttribute('name', 'name');
	    obj.setAttribute('value', 'name');
	    form.appendChild(obj);
	    */
	    
	    document.body.appendChild(form);
	    console.log(form)
		form.submit();
	});
</script>

</html>