//댓글쓰기
const register = document.getElementById("register");
const commentWriteForm = document.getElementById("commentWriteForm");

register.addEventListener('click', function(){
	commentWriteForm.submit();
})

//답글쓰기
const BtnRefCommentEnter = document.querySelectorAll(".BtnRefCommentEnter")
const content = document.getElementById("content")
const refCommentWriteForm = document.getElementById("refCommentWriteForm")

BtnRefCommentEnter.forEach((element, index, array) => {
	element.addEventListener('click', ()=>{
		refCommentWriteForm.submit();
	})
});

//댓글삭제
const commentDeleteBtn = document.querySelectorAll(".commentDeleteBtn")
const commentDeleteForm = document.querySelectorAll(".commentDeleteForm")

commentDeleteBtn.forEach((element, index, array) => {
	element.addEventListener('click', ()=>{
		commentDeleteForm.submit();
	})
});

