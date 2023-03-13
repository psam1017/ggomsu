/**
 * 작성자 : 박성민
 */

// start window.onload

const validationForm = document.getElementById("validationForm");
const email = document.getElementById("email");
const emailResult = document.getElementById("emailResult");
const validationSubmit = document.getElementById("validationSubmit");

let isEmailValid = false;
let emailRegExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

// 이메일 인증 추가
const sendMailButton = document.getElementById("sendMailButton");
let isAuthValid = false;

email.addEventListener("blur", function(){
    checkEmail(this.value);
});

email.addEventListener("change", function(){
	isAuthValid = false;
});

validationSubmit.addEventListener("click", function(e){
	e.preventDefault();
	formSubmit();
});

function checkEmail(emailValue){
	// 이메일은...
	// 필수항목이다.
	// 50자 이하이다.
	// 정규식을 만족해야 한다.
	// 기존 회원과 중복되어선 안 된다.

	isEmailValid = false;
	
	if(emailValue == ""){
		emailResult.innerText = "이메일은 필수항목입니다.";
		return;
	}
	
	if(emailValue.length > 50){
		emailResult.innerText = "이메일은 50자 이내로 입력해주세요.";
		return;
	}
	
	if(!emailRegExp.test(emailValue)){
		emailResult.innerText = "올바르지 않은 형식의 이메일입니다.";
		return;
	}
	
	emailResult.innerText = "";
	isEmailValid = true;
	return;
}

sendMailButton.addEventListener("click", function(e){
	e.preventDefault();
	if(!isAuthValid){
		sendMail(email.value);
	}
	else{
		alert("이미 인증에 성공했습니다.");
	}
});

function sendMail(emailValue){
	
	if(!isEmailValid){
		alert("이메일을 확인해주세요.");
		email.focus();
		return;
	}
	
	isAuthValid = false;
	
	let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/member/check/code";

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

// 유효성 검사
function formSubmit(){

	if(!isEmailValid){
		alert("이메일을 확인해주세요.");
		email.focus();
		return;
	}
	
	if(!isAuthValid){
		alert("이메일을 인증해주세요.");
		email.focus();
		return;
	}
	
	validationForm.submit();
}
