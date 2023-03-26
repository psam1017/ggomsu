/**
 * 작성자 : 박성민
 */

// window.onload 시작
window.onload = function(){

const alarmListDeleteButton = document.getElementById("alarmListDeleteButton");
const alarmContainer = document.getElementById("alarmContainer");
const alarmCountContainer = document.getElementById("alarmCountContainer");

alarmListDeleteButton.addEventListener("click", function(e){
	e.preventDefault();
	alarmListDelete();
});

alarmListView();

function alarmListDelete(){
	
	let xhr = new XMLHttpRequest();
    let requestURL = contextPathOnHeader + "/alarm/list/delete";

    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send();

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
        	if(json.status == "ok"){
        		alert("모든 알람을 삭제했습니다.");
        		alarmContainer.innerHTML = '<div class="dropdown-notifications-item-content d-flex justify-content-center p-3 border-top"><div class="dropdown-notifications-item-content-text">업데이트된 알림이 없습니다.</div></div>';
        		alarmCountContainer.innerText = "0";
        	}
        	else{
        		alert("알람을 삭제하는 데 오류가 발생했습니다.\n관리자에게 문의해주세요.");
        	}
        }
    }
}

function alarmListView(){
	
	if(alarmFlag == "true"){
		let xhr = new XMLHttpRequest();
		let requestURL = contextPathOnHeader + "/alarm/list/view";
		
		xhr.open("post", requestURL, true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.send();
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
				let json = JSON.parse(xhr.responseText);
				showAlarmList(json.list);
			}
		}
	}
}


function showAlarmList(alarmList){
	
	let text = "";
	let alarmCount = 0;
	
	if(alarmList != null && alarmList.length != 0){
		alarmCount = alarmList.length;
		alarmList.forEach(alarm => {
			text += '<a class="dropdown-item dropdown-notifications-item" href="' + contextPathOnHeader + '/article/view?articleIndex=' + alarm.articleIndex + '"><div class="dropdown-notifications-item-content"><div class="dropdown-notifications-item-content-details">' + alarm.writtenAt + '</div><div class="dropdown-notifications-item-content-details">' + alarm.nickname + ' 님께서 댓글을 달았습니다.</div><div class="dropdown-notifications-item-content-text" style="width: 17rem; max-width: 17rem;">' + alarm.original + '</div><div class="dropdown-notifications-item-content-text" style="width: 20rem; max-width: 20rem;"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-right" viewBox="0 0 16 16"><path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"></path></svg><span>' + alarm.content + '</span></div></div></a>';
		});
		alarmCountContainer.innerText = alarmCount;
		alarmContainer.innerHTML = text;
	}
}



// window.onload 끝
}
