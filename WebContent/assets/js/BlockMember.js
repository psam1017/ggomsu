/**
 * 작성자 : 손하늘
 */

const updateBlock = document.getElementById("updateBlock");
const deleteBlock = document.getElementById("deleteBlock");

updateBlock.addEventListener("click", function(){
	location.href = "${pageContext.request.contextPath}/member/member-update-block-ok";
});

deleteBlock.addEventListener("click", function(){
	location.href = "${pageContext.request.contextPath}/member/member-delete-block-ok";
});