/**
 * 작성자 : 박성민
 */

// start window.onload

const validationForm = document.getElementById("validationForm");
const email = document.getElementById("email");
const sendMailButton = document.getElementById("sendMailButton");
const memberKey = document.getElementById("memberKey");

let isAuthValid = false;
let path = sendMailButton.href;

sendMailButton.addEventListener("click", function(e){
	e.preventDefault();
	if(!isAuthValid){
		sendMail(email.value);
	}
	else{
		let isSendAgain = confirm("이미 인증을 요청했습니다.\n다시 요청하시겠습니까?");
		if(isSendAgain){
			sendMail(email.value);
		}
	}
});

function sendMail(emailValue){
	
	isAuthValid = false;
	
	let xhr = new XMLHttpRequest();
    let requestURL = path;

    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("email=" + emailValue);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
            if(json.status == "success"){
            	alert("인증번호를 전송했습니다.");
            	isAuthValid = true;
            }
            else if(json.status == "fail-encoding"){
            	alert("이메일 전송에 실패했습니다.\n언어를 확인해주세요.");
            }
            else if(json.status == "fail-address"){
            	alert("해당 주소는 존재하지 않습니다.\n주소를 확인해주세요.");
            }
            else if(json.status == "fail-transport"){
            	alert("이메일 전송에 실패했습니다.\n관리자에게 문의해주세요.");
            }
        }
    }
}
