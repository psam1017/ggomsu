/**
 * 작성자 : 박성민
 */

// start window.onload

const validationForm = document.getElementById("validationForm");
const memberName = document.getElementById("name");
const nameResult = document.getElementById("nameResult");
const sex = document.querySelectorAll(".sex");
const birthDate = document.getElementById("birthDate");
const telecomValue = document.getElementById("telecomValue");
const eachContact = document.querySelectorAll(".eachContact");
const contact = document.getElementById("contact");
const contactResult = document.getElementById("contactResult");
const validationSubmit = document.getElementById("validationSubmit");

let isNameValid = false;
let isSexValid = false;
let isContactValid = false;
let nameRegExp = /^[가-힣]+$/;
let contactRegExp = /^[0-9]*$/;
// 비밀번호는 소문자, 숫자, 특수문자를 포함한 8자 이상

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
		nameResult.innerText = "한글 이름으로만 입력할 수 있습니다.";
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
	
	contactResult.innerText = "";
	isContactValid = true;
}

// 유효성 검사
function formSubmit(){

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
