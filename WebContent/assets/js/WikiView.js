/**
 * 작성자 : 박성민
 */

// window.onload 시작
window.onload = function(){

const wikiReportForm = document.getElementById("wikiReportForm");
const wikiReportButton = document.getElementById("articleReportButton");

wikiReportButton.addEventListener("click", function(e){
	e.preventDefault();
	if(confirm("정말로 신고하시겠습니까?")){
		reportWikiConfirm(wikiReportForm);
	}
});

function reportWikiConfirm(wikiReportForm){
	
	let subject = wikiReportForm.subject.value;
	let rvs = wikiReportForm.rvs.value;
	let wikiReportReason = wikiReportForm.wikiReportReason.value;
	let allowAddr = wikiReportForm.allowAddr;
	
	if(allowAddr != null && allowAddr.checked == false){
		alert("IP 수집에 동의해주십시오.");
		return;
	}
	
    let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/report/wiki/confirm";

    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("subject=" + subject + "&rvs=" + rvs + "&wikiReportReason=" + wikiReportReason);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
        	if(json.status == "success"){
        		alert("신고가 접수되었습니다.\n감사합니다.");
        	}
        	else{
        		alert("신고가 접수되지 않았습니다.\n관리자에게 문의해주세요.");
        	}
        }
    }
}

// window.onload 끝
}
