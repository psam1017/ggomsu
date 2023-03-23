/**
 * 작성자 : 박성민
 */

// start window.onload

const validationForm = document.getElementById("validationForm");
const nickname = document.getElementById("nickname");
const nicknameResult = document.getElementById("nicknameResult");
const terms = document.querySelectorAll(".agreedTermAt");
const validationSubmit = document.getElementById("validationSubmit");

let isNicknameValid = false;
let nicknameRegExp = /^[가-힣0-9]+$/;

nickname.addEventListener("keyup", function(){
	checkNickname(this.value);
});

nickname.addEventListener("focus", function(){
	checkNickname(this.value);
});

validationSubmit.addEventListener("click", function(e){
	e.preventDefault();
	formSubmit();
});

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

// 유효성 검사
function formSubmit(){

	if(!isNicknameValid){
		alert("닉네임을 확인해주세요.");
		nickname.focus();
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
	
	validationForm.submit();
}
