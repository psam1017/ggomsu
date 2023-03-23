const wikiConfirmButton = document.querySelectorAll(".wikiConfirmButton");
const wikiConfirmForm = document.querySelectorAll(".wikiConfirmForm");
const wikiViewButton = document.querySelectorAll(".wikiViewButton");
const reportWikiViewContainer = document.getElementById("reportWikiViewContainer");

if(wikiConfirmButton != null && wikiConfirmButton.length > 0){
	wikiConfirmButton.forEach((element, index) => {
	 	element.addEventListener("click", function(e){
	 		e.preventDefault();
	 		wikiConfirmForm[index].submit();
		});
	});
}

if(wikiViewButton != null && wikiViewButton.length > 0){
	wikiViewButton.forEach((element, index) => {
	 	element.addEventListener("click", function(e){
	 		e.preventDefault();
	 		wikiView(element.dataset.subject, element.dataset.rvs);
		});
	});
}

function wikiView(subject, rvs){
	
	let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/admin/wiki/view";

    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("subject=" + subject + "&rvs=" + rvs);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
        	let text = "";
        	
            if(json.status == "ok"){
            	reportWikiViewContainer.innerHTML = "";
            	text += '<a href="' + contextPath + '/wiki/view?subject=' + json.subject + '&rvs=' + json.rvs + '" class="btn btn-primary w-100 mb-2">자세히 보러가기</a><button id="closeButton" class="btn btn-warning w-100 mb-2">닫기</button><div class="card mb-4" style="height: 300px; overflow-x: hidden; overflow-y: auto"><div class="card-header" style="font-size: 0.8rem;"><span>작성자 : ' + json.writer + '</span><br><span>작성일 : ' + json.revisedAt + '</span><br><span>제목 : ' + json.subject + '(' + json.rvs + '번째)</span><br></div><div class="card-body">' + json.content + '</div></div>';
            	reportWikiViewContainer.innerHTML = text;
            	reportWikiViewContainer.style.height = "450px";
            	
            	const closeButton = document.getElementById("closeButton");
            	closeButton.addEventListener("click", function(){ reportWikiViewContainer.style.height = "0"; });
            }
            else if(json.status == "deleted"){
            	alert("이미 삭제된 버전입니다.");
            }
            else{
            	alert("존재하지 않는 버전입니다.");
            }
        }
	}
}