
const password = document.getElementById("password");
const passwordResult = document.getElementById("passwordResult");
const passwordCheck = document.getElementById("passwordCheck");
const passwordCheckResult = document.getElementById("passwordCheckResult");
const nickname = document.getElementById("nickname");
const nicknameResult = document.getElementById("nicknameResult");
const nicknameChanegeBtn = document.getElementById("nicknameChanegeBtn");
const nicknameCancelBtn = document.getElementById("nicknameCancelBtn");
const telecomValue = document.getElementById("telecomValue");
const eachContact = document.querySelectorAll(".eachContact");
const contact = document.getElementById("contact");
const contactResult = document.getElementById("contactResult");
const terms = document.querySelectorAll(".agreedTermAt");

const on = document.querySelector('.on > #nickname');
const off = document.querySelector('.off > #nickname');

let isPasswordValid = false;
let isPasswordCheckValid = false;
let isNicknameValid = false;
let isContactValid = false;
let isTermOk = false;
let passwordRegExp = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*?]).{8,}$/;
let nicknameRegExp = /^[가-힣0-9]+$/;
let contactRegExp = /^[0-9]*$/;
// 비밀번호는 소문자, 숫자, 특수문자를 포함한 8자 이상

password.addEventListener("keyup", function(){
	checkPassword(this.value);
});

passwordCheck.addEventListener("keyup", function(){
	checkPasswordCheck(password.value, passwordCheck.value);
});

nicknameChanegeBtn.addEventListener("click", function(){
	nicknameChanege(this.value)
});

nicknameCancelBtn.addEventListener("click", function(){
	nicknameCancel(this.value)
});

nickname.addEventListener("blur", function(){
	checkNickname(this.value);
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

updateMemberSubmit.addEventListener("click", function(){
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

function nicknameChanege(){
	nickname.disabled = false;
	off.setAttribute('name','none')
}

function nicknameCancel(){
	on.setAttribute('value', off.value)
	off.setAttribute('name','nickname')
	nickname.disabled = true;
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
    let requestURL = contextPath + "/member/member-check-contact-ok";

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

	if(!isNicknameValid){
		alert("닉네임을 확인해주세요.");
		nickname.focus();
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
			return;
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

	viewMemberForm.submit();
}
