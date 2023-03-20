const articleConfirmButton = document.querySelectorAll(".articleConfirmButton");
const articleConfirmForm = document.querySelectorAll(".articleConfirmForm");
const articleViewButton = document.querySelectorAll(".articleViewButton");
const reportArticleViewContainer = document.getElementById("reportArticleViewContainer");

if(articleConfirmButton != null && articleConfirmButton.length > 0){
	articleConfirmButton.forEach((element, index) => {
	 	element.addEventListener("click", function(e){
	 		e.preventDefault();
	 		articleConfirmForm[index].submit();
		});
	});
}

if(articleViewButton != null && articleViewButton.length > 0){
	articleViewButton.forEach((element, index) => {
	 	element.addEventListener("click", function(e){
	 		e.preventDefault();
	 		articleView(element.dataset.value);
		});
	});
}

function articleView(articleIndex){
	
	let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/admin/article/view";

    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("articleIndex=" + articleIndex);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
        	let text = "";
        	
            if(json.status == "ok"){
            	reportArticleViewContainer.innerHTML = "";
            	text += '<a href="' + contextPath + '/article/view?articleIndex=' + json.articleIndex + '" class="btn btn-primary w-100 mb-2">자세히 보러가기</a><button id="closeButton" class="btn btn-warning w-100 mb-2">닫기</button><div class="card mb-4" style="height: 300px; overflow-x: hidden; overflow-y: auto"><div class="card-header" style="font-size: 0.8rem;"><span>작성자 : ' + json.nickname + '</span><br><span>작성일 : ' + json.writtenAt + '</span><br><span>제목 : ' + json.title + '(' + json.articleIndex + '번)</span><br></div><div class="card-body">' + json.content + '</div></div>';
            	reportArticleViewContainer.innerHTML = text;
            	reportArticleViewContainer.style.height = "450px";
            	
            	const closeButton = document.getElementById("closeButton");
            	closeButton.addEventListener("click", function(){ reportArticleViewContainer.style.height = "0"; });
            }
        }
	}
}