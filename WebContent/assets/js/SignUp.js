/**
 * 작성자 : 박성민
 */

const signUpForm = document.getElementById("signUpForm");
const email = document.getElementById("email");
const emailResult = document.getElementById("emailResult");
const password = document.getElementById("password");
const passwordResult = document.getElementById("passwordResult");
const passwordCheck = document.getElementById("passwordCheck");
const passwordCheckResult = document.getElementById("passwordCheckResult");
const nickname = document.getElementById("nickname");
const nicknameResult = document.getElementById("nicknameResult");
const terms = document.querySelectorAll(".agreedTermAt");
const signUpSubmit = document.getElementById("signUpSubmit");

let isEmailValid = false;
let isPasswordValid = false;
let isPasswordCheckValid = false;
let isNicknameValid = false;
let isTermOk = false;
let emailRegExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
let passwordRegExp = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*?]).{8,}$/;;
// 비밀번호는 소문자, 숫자, 특수문자를 포함한 8자 이상

email.addEventListener("blur", function(){
    checkEmail(email.value);
});

password.addEventListener("keyup", function(){
	checkPassword(password.value);
});

passwordCheck.addEventListener("keyup", function(){
	checkPasswordCheck(password.value, passwordCheck.value);
});

nickname.addEventListener("blur", function(){
	checkNickname(nickname.value);
});

signUpSubmit.addEventListener("click", function(){
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
	
    let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/member/member-check-email-ok";

    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("email=" + emailValue);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
            if(json.emailStatus == "ok"){
            	emailResult.innerText = "이메일을 사용할 수 있습니다.";
            	isEmailValid = true;
            }
            else{
            	emailResult.innerText = "이미 사용 중인 이메일입니다.";
            }
        }
    }
}

function checkPassword(passwordValue){
	// 비밀번호는...
	// 필수항목이다.
	// 정규식을 만족해야 한다.
	// 같은 문자는 4번 이상 쓸 수 없다.
	// 공백을 포함해선 안 된다.
	// 한글을 쓸 수 없다.
	// 전송할 때(submit) 암호화하여 알 수 없게 한다. -> SSL

	isPasswordValid = false;

	if(passwordValue == ""){
		passwordResult.innerText = "비밀번호는 필수항목입니다.";
		return;
	}
	
	if(!passwordRegExp.test(passwordValue)){
		passwordResult.innerText = "비밀번호는 8자리 이상 소문자, 숫자, 특수문자를 포함해주세요.";
		return;
	}
	
	if(/(\w)\1\1\1/.test(passwordValue)){
		passwordResult.innerText = "비밀번호에 같은 문자를 4번 이상 사용할 수 없습니다.";
		return;
	}
	
	if(passwordValue.search(/\s/) != -1){
		passwordResult.innerText = "비밀번호는 공백 없이 입력해주세요.";
		return;
	}
	
	if(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/.test(passwordValue)){
		passwordResult.innerText = "비밀번호에 한글을 사용할 수 없습니다.";
		return;
	}
	
	passwordResult.innerText = "";
	isPasswordValid = true;
}

function checkPasswordCheck(passwordValue, passwordCheckValue){
	// 비밀번호 확인은...
	// 비밀번호가 유효해야 한다.
	// 비밀번호와 일치해야 한다.

	isPasswordCheckValid = false;

	if(!isPasswordValid){
		alert("비밀번호가 올바르지 않습니다.");
		password.focus();
		return;
	}
	else{
		if(passwordValue === passwordCheckValue){
			passwordCheckResult.innerText = "";
			isPasswordCheckValid = true;
		}
		else{
			passwordCheckResult.innerText = "비밀번호가 일치하지 않습니다.";
		}
	}
}

function checkNickname(nicknameValue){
	// 닉네임은...
	// 필수항목이다.
	// 2자 이상 10자 이하이어야 한다.
	// 숫자로 시작할 수 없다.
	// 공백을 포함해선 안 된다.
	// 기존 회원과 중복되어선 안 된다.

	isNicknameValid = false;

	if(nicknameValue == ""){
		nicknameResult.innerText = "닉네임은 필수항목입니다.";
		return;
	}
	
	if(nicknameValue.length < 2 || nicknameValue.length > 10){
		nicknameResult.innerText = "닉네임은 2자 이상 10자 이하로 입력해주세요.";
		return;
	}

	if(/[0-9]/.test(nicknameValue.charAt(0))){
		nicknameResult.innerText = "닉네임은 숫자로 시작할 수 없습니다.";
		return;
	}

	if(nicknameValue.search(/\s/) != -1){
		nicknameResult.innerText = "닉네임은 공백 없이 입력해주세요.";
		return;
	}

	let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/member/member-check-nickname-ok";

    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("nickname=" + nicknameValue);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
            if(json.nicknameStatus == "ok"){
            	nicknameResult.innerText = "닉네임을 사용할 수 있습니다.";
            	isNicknameValid = true;
            }
            else{
            	nicknameResult.innerText = "이미 사용 중인 닉네임입니다.";
            }
        }
    }
}

function formSubmit(){

	if(!isEmailValid){
		alert("이메일을 확인해주세요.");
		email.focus();
		return;
	}

	if(!isPasswordValid){
		alert("비밀번호를 확인해주세요.");
		password.focus();
		return;
	}

	if(!isPasswordCheckValid){
		alert("비밀번호가 일치하지 않습니다.");
		passwordCheck.focus();
		return;
	}

	if(!isNicknameValid){
		alert("닉네임을 확인해주세요.");
		nickname.focus();
		return;
	}

	isTermOk = true;
	terms.forEach(element => {
		if(element.checked == false){
			isTermOk = false;
			break;
		}
	});

	if(!isTermOk){
		alert("필수 이용약관에 동의해주십시오.");
		return;
	}

	// 비밀번호 암호화
	// 자문결과 front에서의 암호화는 SSL로 대체한다.
	// 필요한 경우 Base64를 사용할 수 있다.

	signUpForm.submit();
}
