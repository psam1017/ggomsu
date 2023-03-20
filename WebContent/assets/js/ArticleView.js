/**
 * 작성자 : 박성민
 */

// window.onload 시작
window.onload = function(){

const articleLikeButton = document.querySelectorAll(".articleLikeButton");
const articleLikePath = document.querySelectorAll(".articleLikePath");
const articleReportReason = document.getElementById("articleReportReason");
const articleReportButton = document.getElementById("articleReportButton");
const commentWriteForm = document.getElementById("commentWriteForm");
const refCommentContent = document.getElementById("refCommentContent");
const commentContent = document.getElementById("commentContent");
const commentResetButton = document.getElementById("commentResetButton");
const commentWriteButton = document.getElementById("commentWriteButton");
const commentContainer = document.getElementById("commentContainer");

articleLikeButton.forEach(element => {
	element.addEventListener("click", function(e){
		e.preventDefault();
		articleLike(articleIndex);
	});
});

if(articleReportButton != null){
	articleReportButton.addEventListener("click", function(e){
		e.preventDefault();
		if(confirm("정말로 신고하시겠습니까?")){
			reportArticleConfirm(articleIndex, articleReportReason.value);
		}
	});
}

commentResetButton.addEventListener("click", function(e){
	e.preventDefault();
	commentWriteForm.refIndex.value = "0";
	refCommentContent.innerText = "";
});

commentWriteButton.addEventListener("click", function(e){
	e.preventDefault();
	commentWriteConfirm(articleIndex);
});

commentList(articleIndex);

function articleLike(articleIndex){
	
    let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/article/like";

    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("articleIndex=" + articleIndex);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
        	if(json.status == "do"){
        		articleLikePath.forEach(element => {
        			element.style = "fill:#ff69b4; fill-rule:nonzero;";
        		});
        	}
        	else if(json.status == "cancel"){
        		articleLikePath.forEach(element => {
        			element.style = "none;";
        		});
        	}
        	else if(json.status == "tmp"){
        		alert("비회원을 추천할 수 없습니다.\n로그인 후 이용해주세요.");
        	}
        	else{
        		alert("추천에 실패했습니다.\n관리자에게 문의해주세요.");
        	}
        }
    }
}

function reportArticleConfirm(articleIndex, articleReportReason){
	
    let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/report/article/confirm";

    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("articleIndex=" + articleIndex + "&articleReportReason=" + articleReportReason);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
        	if(json.status == "ok"){
        		alert("신고가 접수되었습니다.\n감사합니다.");
        	}
        	else if(json.status == "tmp"){
        		alert("비회원은 신고가 불가능합니다.\n로그인 후 이용해주세요.");
        	}
        	else{
        		alert("신고가 접수되지 않았습니다.\n관리자에게 문의해주세요.");
        	}
        }
    }
}

function commentList(articleIndex){
	
    let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/comment/list";

    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("articleIndex=" + articleIndex);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
        	showCommentList(json);
        }
    }
}

function commentWriteConfirm(articleIndex){
	
    let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/comment/write/confirm";
    
    let refIndex = commentWriteForm.refIndex.value;
    let content = commentWriteForm.content.value;
    
    if(content.replace(/ /g, "") == ""){
    	alert("댓글 내용이 비어있습니다.");
    	return;
    }
    
    // 쿼리 스트링에 길게 적어도 상관 없나? -> 출처 : TCP SCHOOL
    // ... 또한, POST 방식의 HTTP 요청에 의한 데이터는 쿼리 문자열과는 별도로 전송됩니다.
    // 따라서 데이터의 길이에 대한 제한도 없으며, GET 방식보다 보안성이 높습니다.
    
    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("refIndex=" + refIndex + "&articleIndex=" + articleIndex + "&content=" + content);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
        	if(json.status == "ok"){
        		alert("댓글을 등록했습니다.");
        		
        		// 댓글 폼 되돌리기
        		refCommentContent.innerText = "";
        		commentWriteForm.refIndex.value = "0";
        		commentContent.value = "";
        		
        		// 댓글 다시 불러오기
        		commentContainer.innerText = "";
        		commentList(articleIndex);
        	}
        	else if(json.status == "tmp"){
        		alert("비회원은 댓글을 등록할 수 없습니다.\n로그인 후 이용해주세요.");
        	}
        	else{
        		alert("댓글이 등록되지 않았습니다.\n관리자에게 문의하세요.");
        	}
        }
    }
}

function commentLike(commentIndex, commentLikePath){
	
    let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/comment/like";
    
    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("commentIndex=" + commentIndex);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
        	if(json.status == "do"){
        		commentLikePath.style = "fill:#ff69b4; fill-rule:nonzero;";
        	}
        	else if(json.status == "cancel"){
        		commentLikePath.style = "none;";
        	}
        	else if(json.status == "tmp"){
        		alert("비회원을 추천할 수 없습니다.\n로그인 후 이용해주세요.");
        	}
        	else{
        		alert("추천에 실패했습니다.\n관리자에게 문의해주세요.");
        	}
        }
    }
}

function commentDeleteConfirm(commentIndex){
	
    let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/comment/delete/confirm";
    
    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("commentIndex=" + commentIndex);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
        	if(json.status == "ok"){
        		alert("댓글을 삭제했습니다.");
        		
        		// 댓글 다시 불러오기
        		commentContainer.innerText = "";
        		commentList(articleIndex);
        	}
        	else if(json.status == "tmp"){
        		alert("비회원은 사용할 수 없는 기능입니다.");
        	}
        	else{
        		alert("본인의 댓글이 아니면 삭제할 수 없습니다.");
        	}
        }
    }
}

function reportCommentConfirm(commentIndex){
	
    let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/report/comment/confirm";
    
    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("commentIndex=" + commentIndex);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
        	if(json.status == "ok"){
        		alert("신고가 접수되었습니다.\n감사합니다.");
        	}
        	else if(json.status == "tmp"){
        		alert("비회원은 신고가 불가능합니다.\n로그인 후 이용해주세요.");
        	}
        	else{
        		alert("신고가 접수되지 않았습니다.\n관리자에게 문의해주세요.");
        	}
        }
    }
}

function showCommentList(commentList){
	
	let text = "";
	
	if(commentList != null && commentList.length != 0){
		commentList.forEach(comment => {
			let profileImageUrl = comment.profileImageUrl != null ? comment.profileImageUrl : "/bs/assets/img/illustrations/profiles/default.png";
			let isMyComment = comment.nickname == sessionNickname;
			let isRefComment = comment.commentIndex != comment.refIndex;
			let isLikedStyle = comment.isLiked ? 'style="fill:#ff69b4; fill-rule:nonzero;"' : '';
			let isDeleted = comment.deletedAt != null;
			
			if(isDeleted){
				comment.content = "삭제된 댓글입니다.";
			}
			
			// 댓글인가 대댓글인가
			// 내 댓글인가 남 댓글인가
			// 삭제된 댓글인가
			if(!isRefComment){
				if(isMyComment){
					if(isDeleted){
						text += '<div class="row py-1 border border-dark-subtle" style="background-color: #dee2e6;"><div class="col-12"><img class="img-fluid" src=' + profileImageUrl + ' style="width:24px; height: 24px;"><span class="ms-1"><small>' + comment.nickname + '</small></span><span class="ms-1 pt-1"><a href="#!" data-value="' + comment.commentIndex + '" class="commentLikeButton text-decoration-none d-inline-block me-1" style="width: 18px; height: 18px;"><svg style="color: #69707a; width: 18px; height: 18px; margin-top: 4px;" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-heart"><path class="commentLikePath" ' + isLikedStyle + ' d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path></svg></a><span><small>' + comment.commentLikeCount + '</small></span></span></div><div class="mt-1"><span class="text-break commentContent small">' + comment.content + '</span></div><div class="mt-1"><span style="font-size: 12px;"><small>' + comment.writtenAt + '</small></span><span class="ms-1"><a class="btn btn-secondary px-2 py-1 refCommentButton" data-value="' + comment.commentIndex +'" href="#commentContent" style="margin-bottom: 2.5px;"><svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" fill="currentColor" class="bi bi-arrow-return-right" viewbox="0 0 14 14"><path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/></svg><span class="ms-1">답글달기</span></a></span></div></div>';
					}
					else{
						text += '<div class="row py-1 border border-dark-subtle"><div class="col-12"><img class="img-fluid" src=' + profileImageUrl + ' style="width:24px; height: 24px;"><span class="ms-1"><small>' + comment.nickname + '</small></span><span class="ms-1 pt-1"><a href="#!" data-value="' + comment.commentIndex + '" class="commentLikeButton text-decoration-none d-inline-block me-1" style="width: 18px; height: 18px;"><svg style="color: #69707a; width: 18px; height: 18px; margin-top: 4px;" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-heart"><path class="commentLikePath" ' + isLikedStyle + ' d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path></svg></a><span><small>' + comment.commentLikeCount + '</small></span></span></div><div class="mt-1"><span class="text-break commentContent">' + comment.content + '</span></div><div class="mt-1"><span style="font-size: 12px;"><small>' + comment.writtenAt + '</small></span><span class="ms-1"><a class="btn btn-secondary px-2 py-1 refCommentButton" data-value="' + comment.commentIndex +'" href="#commentContent" style="margin-bottom: 2.5px;"><svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" fill="currentColor" class="bi bi-arrow-return-right" viewbox="0 0 14 14"><path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/></svg><span class="ms-1">답글달기</span></a><button class="btn btn-warning px-2 py-1 ms-1 commentDeleteButton" data-value="' + comment.commentIndex + '" style="margin-bottom: 2.5px;"><span>삭제</span><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-trash-2 ms-1"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line></svg></button></span></div></div>';
					}
				}
				else{
					if(isDeleted){
						text += '<div class="row py-1 border border-dark-subtle" style="background-color: #dee2e6;"><div class="col-12"><img class="img-fluid" src=' + profileImageUrl + ' style="width:24px; height: 24px;"><span class="ms-1"><small>' + comment.nickname + '</small></span><span class="ms-1 pt-1"><a href="#!" data-value="' + comment.commentIndex + '" class="commentLikeButton text-decoration-none d-inline-block me-1" style="width: 18px; height: 18px;"><svg style="color: #69707a; width: 18px; height: 18px; margin-top: 4px;" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-heart"><path class="commentLikePath" ' + isLikedStyle + ' d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path></svg></a><span><small>' + comment.commentLikeCount + '</small></span></span></div><div class="mt-1"><span class="text-break commentContent small">' + comment.content + '</span></div><div class="mt-1"><span style="font-size: 12px;"><small>' + comment.writtenAt + '</small></span><span class="ms-1"><a class="btn btn-secondary px-2 py-1 refCommentButton" data-value="' + comment.commentIndex +'" href="#commentContent" style="margin-bottom: 2.5px;"><svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" fill="currentColor" class="bi bi-arrow-return-right" viewbox="0 0 14 14"><path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/></svg><span class="ms-1">답글달기</span></a></span></div></div>';
					}
					else{
						text += '<div class="row py-1 border border-dark-subtle"><div class="col-12"><img class="img-fluid" src=' + profileImageUrl + ' style="width:24px; height: 24px;"><span class="ms-1"><small>' + comment.nickname + '</small></span><span class="ms-1 pt-1"><a href="#!" data-value="' + comment.commentIndex + '" class="commentLikeButton text-decoration-none d-inline-block me-1" style="width: 18px; height: 18px;"><svg style="color: #69707a; width: 18px; height: 18px; margin-top: 4px;" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-heart"><path class="commentLikePath" ' + isLikedStyle + ' d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path></svg></a><span><small>' + comment.commentLikeCount + '</small></span></span></div><div class="mt-1"><span class="text-break commentContent">' + comment.content + '</span></div><div class="mt-1"><span style="font-size: 12px;"><small>' + comment.writtenAt + '</small></span><span class="ms-1"><a class="btn btn-secondary px-2 py-1 refCommentButton" data-value="' + comment.commentIndex +'" href="#commentContent" style="margin-bottom: 2.5px;"><svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" fill="currentColor" class="bi bi-arrow-return-right" viewbox="0 0 14 14"><path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/></svg><span class="ms-1">답글달기</span></a><button class="btn btn-danger px-2 py-1 ms-1 commentReportButton" data-value="' + comment.commentIndex + '" style="margin-bottom: 2.5px;"><span>신고</span><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-edit ms-1"><path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path><path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path></svg></button></span></div></div>';
					}
				}
			}
			else{
				if(isMyComment){
					if(isDeleted){
						text += '<div class="row py-1 d-flex flex-nowrap border border-dark-subtle" style="background-color: #dee2e6;"><div class="flex-shrink-1 px-1"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-right" viewbox="0 0 16 16"><path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/></svg></div><div class="flex-grow-1" style="padding-left: 0;"><img class="img-fluid" src="' + profileImageUrl + '" style="width:24px; height: 24px;"><span class="ms-1"><small>' + comment.nickname + '</small></span><span class="ms-1 pt-1"><a href="#!" data-value="' + comment.commentIndex + '" class="commentLikeButton text-decoration-none d-inline-block me-1" style="width: 18px; height: 18px;"><svg style="color: #69707a; width: 18px; height: 18px; margin-top: 4px;" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-heart"><path class="commentLikePath" ' + isLikedStyle + ' d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path></svg></a><span><small>' + comment.commentLikeCount + '</small></span></span><div class="mt-1"><span class="text-break small">' + comment.content + '</span></div><div class="mt-1"><span style="font-size: 12px;"><small>' + comment.writtenAt + '</small></span><span class="ms-1"></span></div></div></div>';
					}
					else{
						text += '<div class="row py-1 d-flex flex-nowrap border border-dark-subtle" style="background-color: #cfe2ff;"><div class="flex-shrink-1 px-1"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-right" viewbox="0 0 16 16"><path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/></svg></div><div class="flex-grow-1" style="padding-left: 0;"><img class="img-fluid" src="' + profileImageUrl + '" style="width:24px; height: 24px;"><span class="ms-1"><small>' + comment.nickname + '</small></span><span class="ms-1 pt-1"><a href="#!" data-value="' + comment.commentIndex + '" class="commentLikeButton text-decoration-none d-inline-block me-1" style="width: 18px; height: 18px;"><svg style="color: #69707a; width: 18px; height: 18px; margin-top: 4px;" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-heart"><path class="commentLikePath" ' + isLikedStyle + ' d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path></svg></a><span><small>' + comment.commentLikeCount + '</small></span></span><div class="mt-1"><span class="text-break">' + comment.content + '</span></div><div class="mt-1"><span style="font-size: 12px;"><small>' + comment.writtenAt + '</small></span><span class="ms-1"><button class="btn btn-warning px-2 py-1 commentDeleteButton" data-value="' + comment.commentIndex + '" style="margin-bottom: 2.5px;"><span>삭제</span><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-trash-2 ms-1"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line></svg></button></span></div></div></div>';
					}

				}
				else{
					if(isDeleted){
						text += '<div class="row py-1 d-flex flex-nowrap border border-dark-subtle" style="background-color: #dee2e6;"><div class="flex-shrink-1 px-1"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-right" viewbox="0 0 16 16"><path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/></svg></div><div class="flex-grow-1" style="padding-left: 0;"><img class="img-fluid" src="' + profileImageUrl + '" style="width:24px; height: 24px;"><span class="ms-1"><small>' + comment.nickname + '</small></span><span class="ms-1 pt-1"><a href="#!" data-value="' + comment.commentIndex + '" class="commentLikeButton text-decoration-none d-inline-block me-1" style="width: 18px; height: 18px;"><svg style="color: #69707a; width: 18px; height: 18px; margin-top: 4px;" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-heart"><path class="commentLikePath" ' + isLikedStyle + ' d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path></svg></a><span><small>' + comment.commentLikeCount + '</small></span></span><div class="mt-1"><span class="text-break small">' + comment.content + '</span></div><div class="mt-1"><span style="font-size: 12px;"><small>' + comment.writtenAt + '</small></span><span class="ms-1"></span></div></div></div>';
					}
					else{
						text += '<div class="row py-1 d-flex flex-nowrap border border-dark-subtle" style="background-color: #cfe2ff;"><div class="flex-shrink-1 px-1"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-right" viewbox="0 0 16 16"><path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/></svg></div><div class="flex-grow-1" style="padding-left: 0;"><img class="img-fluid" src="' + profileImageUrl + '" style="width:24px; height: 24px;"><span class="ms-1"><small>' + comment.nickname + '</small></span><span class="ms-1 pt-1"><a href="#!" data-value="' + comment.commentIndex + '" class="commentLikeButton text-decoration-none d-inline-block me-1" style="width: 18px; height: 18px;"><svg style="color: #69707a; width: 18px; height: 18px; margin-top: 4px;" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-heart"><path class="commentLikePath" ' + isLikedStyle + ' d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path></svg></a><span><small>' + comment.commentLikeCount + '</small></span></span><div class="mt-1"><span class="text-break">' + comment.content + '</span></div><div class="mt-1"><span style="font-size: 12px;"><small>' + comment.writtenAt + '</small></span><span class="ms-1"><button class="btn btn-danger px-2 py-1 commentReportButton" data-value="' + comment.commentIndex + '" style="margin-bottom: 2.5px;"><span>신고</span><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-edit ms-1"><path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path><path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path></svg></button></span></div></div></div>';
					}
				}
			}
		});
	}
	else{
		text = '<p class="d-flex justify-content-center pt-2">댓글이 아직 없습니다.</p>';
	}
	commentContainer.innerHTML += text;
	
	// 답글(대댓글)달기 버튼 이벤트 생성
	let refCommentButton = document.querySelectorAll(".refCommentButton");
	let originalCommentContent = document.querySelectorAll(".commentContent");
	
	if(refCommentButton != null){
		refCommentButton.forEach((element, index) => {
			element.addEventListener("click", function(e){
				e.preventDefault();
				commentWriteForm.refIndex.value = refCommentButton[index].dataset.value;
				refCommentContent.innerText = originalCommentContent[index].innerText;
				refCommentContent.scrollTo();
				commentContent.focus();
			});
		});
	}
	
	// 댓글 삭제 버튼 이벤트 생성
	let commentDeleteButton = document.querySelectorAll(".commentDeleteButton"); 
	
	if(commentDeleteButton != null){
		commentDeleteButton.forEach((element) => {
			element.addEventListener("click", function(e){
				e.preventDefault();
				if(confirm("정말로 삭제하시겠습니까?")){
					commentDeleteConfirm(element.dataset.value);
				}
			});
		});
	}
	
	// 댓글 신고 버튼 이벤트 생성
	let commentReportButton = document.querySelectorAll(".commentReportButton"); 
	
	if(commentReportButton != null){
		commentReportButton.forEach((element) => {
			element.addEventListener("click", function(e){
				e.preventDefault();
				if(confirm("정말로 신고하시겠습니까?")){
					reportCommentConfirm(element.dataset.value);
				}
			});
		});
	}
	
	// 댓글 좋아요 버튼 이벤트 생성
	
	let commentLikeButton = document.querySelectorAll(".commentLikeButton");
	let commentLikePath = document.querySelectorAll(".commentLikePath");
	
	if(commentLikeButton != null){
		commentLikeButton.forEach((element, index) => {
			element.addEventListener("click", function(e){
				e.preventDefault();
				commentLike(element.dataset.value, commentLikePath[index]);
			});
		});
	}
}



// window.onload 끝
}
