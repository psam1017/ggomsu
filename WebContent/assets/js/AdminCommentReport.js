
// ajax로 신고글 가져오기
const con = document.getElementById("con");
var tr = document.querySelectorAll('.clickable-tr');
const commentIndex = document.getElementById("commentIndex");
const reportContent = document.getElementById("reportReason");
const commentContent = document.getElementById("commentContent");
const reportMember = document.getElementById("reportMember");
const reportedMember = document.getElementById("reportedMember");
const reportDate = document.getElementById("reportDate");
const deleteReason = document.getElementsByName("deleteReason");
  
    var nickname;
    var reportCommentIndex;
    var reportedNickname;
    
    tr.forEach(element => {
        element.addEventListener('click',function(){  
    	con.classList.remove('off')
    	reportCommentIndex = element.children[0].innerText;
    	nickname = element.children[1].innerText;
    	
    	
        let xhr = new XMLHttpRequest();
        let requestURL = contextPath + "/admin/admin-comment-report-get-ok";
        
        var obj =new Object();
        obj.nickname = nickname;
        obj.commentIndex = reportCommentIndex;
      	
        xhr.open("post", requestURL, true);
        xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
        var jsonData = JSON.stringify(obj);
        
        xhr.send(jsonData);
        
        xhr.onreadystatechange = function(){
            if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
            	let json = JSON.parse(xhr.responseText);
                if(json.reportStatus == "ok"){
                	commentIndex.innerText = json.commentIndex;
                	reportContent.innerText = json.reportContent;
                	commentContent.innerText = json.commentContent;
                	reportMember.innerText = json.reportMember;
                	reportedMember.innerText = json.reportedMember;
                	
                	reportedNickname = json.reportedMember;
                }
                else{
                	alert('이미 처리된 신고글 입니다.');
                }
            }
        }
    })
});
    
    
// 신고글 삭제 처리
const btnDelete = document.getElementById("reportDelete");
const btnPreserve = document.getElementById("reportPreserve");
    
btnDelete.addEventListener('click', function() {
	var msg = '';
	
	for(var i=0; i<deleteReason.length; i++){
	 if(deleteReason[i].checked){
		 msg += deleteReason[i].value + ", ";
	 }
	}
	if(msg == '') {
		alert('삭제 사유를 선택하세요!');
		return false;
	}
	 
	var isDelete = confirm('한번 삭제하면 되돌릴 수 없습니다.\n 정말로 삭제 하시겠습니까? ');
	if(isDelete){
		reportDeleteURL = contextPath + "/admin/admin-comment-report-ok?isDelete=on&commentIndex=" + reportCommentIndex + "&nickname=" + nickname + "&reportedNickname=" + reportedNickname + "&commentDeleteReason=" + msg ;
		location.href = reportDeleteURL;
	}else {
		alert('삭제취소');
	}
})

btnPreserve.addEventListener('click', function() {
	var isDelete = confirm('신고처리는 하고 .\n 정말로 삭제 하시겠습니까? ');
	if(isDelete){
		reportDeleteURL = contextPath + "/admin/admin-comment-report-ok?isDelete=off&commentIndex=" + reportCommentIndex + "&nickname=" + nickname + "&reportedNickname=" + reportedNickname + "&commentDeleteReason=" + msg ;
		location.href = reportDeleteURL;
	}else {
		alert('보존취소');
	}
})
    
    
    
    
    
    