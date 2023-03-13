/**
 * 작성자 : 박성민
 */

// start window.onload

const validationForm = document.getElementById("validationForm");
const password = document.getElementById("password");
const passwordResult = document.getElementById("passwordResult");
const passwordCheck = document.getElementById("passwordCheck");
const passwordCheckResult = document.getElementById("passwordCheckResult");
const validationSubmit = document.getElementById("validationSubmit");

let isPasswordValid = false;
let isPasswordCheckValid = false;
let passwordRegExp = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*?]).{8,}$/;
// 비밀번호는 소문자, 숫자, 특수문자를 포함한 8자 이상

password.addEventListener("keyup", function(){
	checkPassword(this.value);
});

passwordCheck.addEventListener("keyup", function(){
	checkPasswordCheck(password.value, passwordCheck.value);
});

validationSubmit.addEventListener("click", function(e){
	e.preventDefault();
	formSubmit();
});

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
		passwordResult.innerText = "비밀번호는 8자리 이상 소문자, 숫자, 특수문자(!@#$%^&*?)를 포함해주세요.";
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

// 유효성 검사
function formSubmit(){

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
	// 비밀번호 암호화
	// 자문결과 front에서의 암호화는 SSL로 대체한다.
	// 필요한 경우 Base64를 사용할 수 있다.
	// 진짜 암호화는 JAVA에서 PBKDF2로 수행한다.

	validationForm.submit();
}
