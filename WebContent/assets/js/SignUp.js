/**
 * 작성자 : 박성민
 */

const email = document.getElementById("email");
const result = document.getElementById("emailCheckResult");
const signUpForm = document.getElementById("signUpForm");
const terms = document.querySelectorAll(".term");
const signUpSubmit = document.getElementById("signUpSubmit");

let isTermOk = false;
let isValid = false;
let regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

function checkEmail(emailValue){
	isValid = false;
	
	// 이메일 유효성 검사
	if(emailValue == ""){
		result.innerText = "이메일을 입력해주세요.";
		return;
	}
	
	if(emailValue.length > 50){
		result.innerText = "이메일은 50자 이내로 입력해주세요.";
		return;
	}
	
	if(!regExp.test(emailValue)){
		result.innerText = "올바르지 않은 이메일입니다.";
		return;
	}
	
	// 이메일 중복 검사
    let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/member/member-check-email-ok";

    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("email=" + emailValue);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
            if(json.status == "ok"){
            	result.innerText = "이메일을 사용할 수 있습니다.";
            	isValid = true;
            }
            else{
            	result.innerText = "이미 사용 중인 이메일입니다.";
            }
        }
    }
}

email.addEventListener("blur", function(){
    checkEmail(email.value);
});

function formSubmit(){
	
	if(!isValid){
		alert("이메일을 확인해주세요.");
		email.focus();
		return;
	}
	
	isTermOk = true;
	terms.forEach(element => {
		console.log(element.checked);
		if(element.checked == false){
			isTermOk = false;
			return;
		}
	});

	if(!isTermOk){
		alert("필수 이용약관에 동의해주십시오.");
		return;
	}

	signUpForm.submit();
}

signUpSubmit.addEventListener("click", function(){
	formSubmit();
});