
const commentConfirmButton = document.querySelectorAll(".commentConfirmButton");
const commentConfirmForm = document.querySelectorAll(".commentConfirmForm");
const commentViewButton = document.querySelectorAll(".commentViewButton");
const reportCommentViewContainer = document.getElementById("reportCommentViewContainer");

if(commentConfirmButton != null && commentConfirmButton.length > 0){
 commentConfirmButton.forEach((element, index) => {
	 	element.addEventListener("click", function(e){
	 		e.preventDefault();
	 		commentConfirmForm[index].submit();
		});
	});
}

if(commentViewButton != null && commentViewButton.length > 0){
 commentViewButton.forEach((element, index) => {
	 	element.addEventListener("click", function(e){
	 		e.preventDefault();
	 		commentView(element.dataset.value);
		});
	});
}

function commentView(commentIndex){
	
	let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/admin/comment/view";

    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("commentIndex=" + commentIndex);

xhr.onreadystatechange = function(){
    if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
    	let json = JSON.parse(xhr.responseText);
    	let text = "";
	
    if(json.status == "ok"){
  	reportCommentViewContainer.innerHTML = "";
    	text += '<a href="' + contextPath + '/article/view?articleIndex=' + json.articleIndex + '" class="btn btn-primary w-100 mb-2">자세히 보러가기</a><button id="closeButton" class="btn btn-warning w-100 mb-2">닫기</button><div class="card mb-4" style="height: 250px; overflow-x: hidden; overflow-y: auto"><div class="card-header" style="font-size: 0.8rem;"><span>작성자 : ' + json.nickname + '(' + json.commentIndex + '번)</span><br><span>작성일 : ' + json.writtenAt + '</span></div><div class="card-body">' + json.content + '</div></div>';
    	reportCommentViewContainer.innerHTML = text;
    	reportCommentViewContainer.style.height = "390px";
    	
    	const closeButton = document.getElementById("closeButton");
    	closeButton.addEventListener("click", function(){ reportCommentViewContainer.style.height = "0"; });
            }
        }
	}
}