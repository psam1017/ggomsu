//댓글쓰기
const register = document.getElementById("register");
const cWrite = document.getElementById("cWrite");

register.addEventListener('click', function(){
	cWrite.submit();
})

//답글쓰기
const BtnRefCommentEnter = document.QuerySelecterAll(".BtnRefCommentEnter")
const content = document.getElementById("content")

BtnRefCommentEnter.addEventListener('click', function(){
	rcWrite.submit();
})