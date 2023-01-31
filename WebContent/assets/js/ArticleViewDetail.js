var BtnRefCommentWrite = document.querySelectorAll('.refCommentWrite');
var displayOff = document.querySelectorAll('.off');

//답글달기 버튼 클릭시 각 요소 on/off
BtnRefCommentWrite.forEach((element) => {
    element.addEventListener('click', () => {
    	var oneRefComment = document.querySelectorAll('.oneRefComment');
		for(i = 0; i < oneRefComment.length; i++){
            if(!oneRefComment[i].classList.contains('off')){
                oneRefComment[i].classList.add('off');
            }  
        } // 모든 답글달기 off
        var thisParent = element.parentElement;
        var SelectedOneRefComment = thisParent.nextElementSibling;
        if(SelectedOneRefComment.classList.contains('off')){
            SelectedOneRefComment.classList.remove('off');
        } // 선택된 답글달기 on
    })
});

// 답글달기 취소
var BtnRefCommentCancel = document.querySelectorAll('.BtnRefCommentCancel');

BtnRefCommentCancel.forEach((element) => {
    element.addEventListener('click',function(){
    	liRefComment = element.parentElement.parentElement;
    	liRefComment.classList.add('off');
    })
})

// 게시글 좋아요
articleLikeImg.addEventListener("click", function(){
	let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/board/article-like-check-ok";
    
    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("nickname=" + nickname + "&articleIndex=" + articleIndex);
    
    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
            if(json.goodStatus == "ok"){
            	articleLikeImg.classList.remove("ok");
            	articleLikeImg.classList.add("not-ok");
            } else{
            	articleLikeImg.classList.remove("not-ok");
            	articleLikeImg.classList.add("ok");
            }
        } else if(xhr.status != 200) {
        	alert('게시글 좋아요를 실패했습니다. 조금 이따가 다시 시도해 주세요.')
        }
    }
   
})

