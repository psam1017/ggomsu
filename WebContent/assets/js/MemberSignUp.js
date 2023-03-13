/**
 * 작성자 : 박성민
 */

// start window.onload

const validationForm = document.getElementById("validationForm");
const email = document.getElementById("email");
const emailResult = document.getElementById("emailResult");
const password = document.getElementById("password");
const passwordResult = document.getElementById("passwordResult");
const passwordCheck = document.getElementById("passwordCheck");
const passwordCheckResult = document.getElementById("passwordCheckResult");
const nickname = document.getElementById("nickname");
const nicknameResult = document.getElementById("nicknameResult");
const memberName = document.getElementById("name");
const nameResult = document.getElementById("nameResult");
const sex = document.querySelectorAll(".sex");
const birthDate = document.getElementById("birthDate");
const telecomValue = document.getElementById("telecomValue");
const eachContact = document.querySelectorAll(".eachContact");
const contact = document.getElementById("contact");
const contactResult = document.getElementById("contactResult");
const terms = document.querySelectorAll(".agreedTermAt");
const validationSubmit = document.getElementById("validationSubmit");

let isEmailValid = false;
let isPasswordValid = false;
let isPasswordCheckValid = false;
let isNicknameValid = false;
let isNameValid = false;
let isSexValid = false;
let isContactValid = false;
let isTermOk = false;
let emailRegExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
let passwordRegExp = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*?]).{8,}$/;
let nicknameRegExp = /^[가-힣0-9]+$/;
let nameRegExp = /^[가-힣]+$/;
let contactRegExp = /^[0-9]*$/;
// 비밀번호는 소문자, 숫자, 특수문자를 포함한 8자 이상

// 0304 이메일 인증 추가
const sendMailButton = document.getElementById("sendMailButton");
const checkAuthButton = document.getElementById("checkAuthButton");
const memberKey = document.getElementById("memberKey");

let isAuthValid = false;

email.addEventListener("blur", function(){
    checkEmail(this.value);
});

password.addEventListener("keyup", function(){
	checkPassword(this.value);
});

passwordCheck.addEventListener("keyup", function(){
	checkPasswordCheck(password.value, passwordCheck.value);
});

nickname.addEventListener("blur", function(){
	checkNickname(this.value);
});

memberName.addEventListener("blur", function(){
	checkName(this.value);
});

eachContact.forEach(element => {
	element.addEventListener("keyup", function(){
		this.value = contactOnlyNumber(this.value);
	});
});

eachContact[2].addEventListener("blur", function(){
	concatContact();
	checkContact(contact.value);
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
	
    let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/member/check/email";

    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("email=" + emailValue);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText); // out.print(json.toJSONString())의 값을 받아온다.
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

function checkNickname(nicknameValue){
	// 닉네임은...
	// 필수항목이다.
	// 2자 이상 10자 이하이어야 한다.
	// 한글과 숫자만 사용할 수 있다.
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
	
	if(!nicknameRegExp.test(nicknameValue)){
		nicknameResult.innerText = "한글과 숫자만 사용할 수 있습니다.";
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
    let requestURL = contextPath + "/member/check/nickname";

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

function checkName(nameValue){
	// 이름은...
	// 필수항목이다.
	// 한국식 이름으로만 사용할 수 있다.

	isNameValid = false;
	
	if(nameValue < 2 || nameValue > 5){
		nameResult.innerText = "이름은 2자 이상 5자 이내로 입력해주세요.";
		return;
	}
	
	if(!nameRegExp.test(nameValue)){
		nameResult.innerText = "한글 이름으로만 가입할 수 있습니다.";
		return;
	}
	
	nameResult.innerText = "";
	isNameValid = true;
}

// 전화번호를 이어붙인다.
function concatContact(){
	contact.value = eachContact[0].value.concat(eachContact[1].value).concat(eachContact[2].value);
}

// 사용자가 전화번호에 숫자 이외의 값을 집어넣으려고 하면 그 값을 삭제한다.
function contactOnlyNumber(contactValue){

	if(!contactRegExp.test(contactValue)){
		return contactValue.substring(0, contactValue.length - 1);
	}
	else{
		return contactValue;
	}
}

// 전화번호의 유효성을 검사한다.
function checkContact(contactValue){

	isContactValid = false;

	if(!(contact.value.length == 10 || contact.value.length == 11)){
		contactResult.innerText = "전화번호 길이가 유효하지 않습니다.";
		return;
	}

	let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/member/check/contact";

    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("contact=" + contactValue);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
            if(json.contactStatus == "ok"){
            	contactResult.innerText = "전화번호를 사용할 수 있습니다.";
            	isContactValid = true;
            }
            else{
            	contactResult.innerText = "이미 사용 중인 전화번호입니다.";
            }
        }
    }
}

// 0304 이메일 인증 기능 추가

sendMailButton.addEventListener("click", function(e){
	e.preventDefault();
	if(!isAuthValid){
		sendMail(email.value);
	}
	else{
		alert("이미 인증에 성공했습니다.");
	}
});

checkAuthButton.addEventListener("click", function(e){
	e.preventDefault();
	if(!isAuthValid){
		checkAuth(memberKey.value);
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
            	alert("인증번호를 전송했습니다.\n3회 안에 인증에 성공해주세요.");
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

function checkAuth(memberKeyValue){
	
	isAuthValid = false;
	
	let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/member/check/auth";

    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("memberKey=" + memberKeyValue);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
            if(json.auth == "ok"){
            	alert("이메일 인증에 성공했습니다.");
            	isAuthValid = true;
            }
            else if(json.auth == "not-ok" && json.authFailCount < 3){
            	alert("이메일 인증에 실패했습니다.\n" + json.authFailCount + "번 실패했습니다.");
            }
            else if(json.auth == "not-ok" && json.authFailCount >= 3){
            	alert(json.authFailCount + "번 실패했습니다. 해당 이메일은 사용하실 수 없습니다.");
            }
            else if(json.auth == "fail"){
            	alert("해당 주소는 사용할 수 없습니다.\n다른 주소를 사용해주세요.");
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
	
	if(!isNameValid){
		alert("이름을 확인해주세요.");
		memberName.focus();
		return;
	}
	
	// 하나라도 값이 있다면 OK
	isSexValid = false;

	sex.forEach(element => {
		if(element.checked == true){
			isSexValid = true;
		}
	});

	if(!isSexValid){
		alert("성별을 선택해주세요.");
		sex[0].focus();
		return;
	}
	
	if(birthDate.value == ""){
		alert("생일을 입력해주세요.");
		birthDate.focus();
		return;
	}
	
	if(telecomValue.value == "NO"){
		alert("통신사를 선택해주세요.");
		telecomValue.focus();
		return;
	}
	
	if(!isContactValid){
		alert("전화번호를 확인해주세요.");
		eachContact[2].focus();
		return;
	}
	
	// 모두 값이 있어야 OK
	let isTermOk = true;
	
	terms.forEach(element => {
		if(element.checked == false){
			isTermOk = false;
		}
	});
	
	if(!isTermOk){
		alert("필수 이용약관에 동의해주십시오.");
		return;
	}
	
	// 비밀번호 암호화
	// 자문결과 front에서의 암호화는 SSL로 대체한다.
	// 필요한 경우 Base64를 사용할 수 있다.
	// 진짜 암호화는 JAVA에서 PBKDF2로 수행한다.

	validationForm.submit();
}
