/**
 * 작성자 : 박성민
 */

// start window.onload

const validationForm = document.getElementById("validationForm");
const telecomValue = document.getElementById("telecomValue");
const eachContact = document.querySelectorAll(".eachContact");
const contact = document.getElementById("contact");
const contactResult = document.getElementById("contactResult");
const validationSubmit = document.getElementById("validationSubmit");

let isContactValid = false;
let contactRegExp = /^[0-9]*$/;

concatContact();
checkContact(contact.value);

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
