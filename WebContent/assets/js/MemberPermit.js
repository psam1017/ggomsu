/**
 * 작성자 : 박성민
 */

// start window.onload

const validationForm = document.getElementById("validationForm");
const password = document.getElementById("password");
const passwordResult = document.getElementById("passwordResult");
const passwordCheck = document.getElementById("passwordCheck");
const passwordCheckResult = document.getElementById("passwordCheckResult");
const memberName = document.getElementById("name");
const nameResult = document.getElementById("nameResult");
const sex = document.querySelectorAll(".sex");
const birthDate = document.getElementById("birthDate");
const telecomValue = document.getElementById("telecomValue");
const eachContact = document.querySelectorAll(".eachContact");
const contact = document.getElementById("contact");
const contactResult = document.getElementById("contactResult");
const validationSubmit = document.getElementById("validationSubmit");

let isPasswordValid = false;
let isPasswordCheckValid = false;
let isNameValid = false;
let isSexValid = false;
let isContactValid = false;
let isTermOk = false;
let passwordRegExp = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*?]).{8,}$/;
let nameRegExp = /^[가-힣]+$/;
let contactRegExp = /^[0-9]*$/;
// 비밀번호는 소문자, 숫자, 특수문자를 포함한 8자 이상

password.addEventListener("keyup", function(){
	checkPassword(this.value);
});

passwordCheck.addEventListener("keyup", function(){
	checkPasswordCheck(password.value, passwordCheck.value);
});

memberName.addEventListener("keyup", function(){
	checkName(this.value);
});

eachContact.forEach(element => {
	element.addEventListener("keyup", function(){
		this.value = contactOnlyNumber(this.value);
		concatContact();
		checkContact(contact.value);
	});
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
	
	validationForm.submit();
}
