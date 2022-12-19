var BtnRefCommentWrite = document.querySelectorAll('.refCommentWrite');
var displayOff = document.querySelectorAll('.off');

//답글달기 버튼 클릭시 각 요소 on/off
BtnRefCommentWrite.forEach((element,index, array) => {
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