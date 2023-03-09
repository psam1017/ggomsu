/**
 * 작성자 : 박성민
 */

const validationForm = document.getElementById("validationForm");
const nickname = document.getElementById("nickname");
const nicknameResult = document.getElementById("nicknameResult");
const validationSubmit = document.getElementById("validationSubmit");

let isNicknameValid = false;
let nicknameRegExp = /^[가-힣0-9]+$/;

nickname.addEventListener("blur", function(){
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

// 프로필 이미지 크기 검사 추가

const profileImageUrl = document.getElementById("profileImageUrl");

let isProfileImageUrlValid = false;

profileImageUrl.addEventListener("change", function(){
	checkSize(this);
});

function checkSize(image) {
	if (image.files) {
		if(image.files[0].size > (160 * 160 * 3)){
			alert("파일 사이즈가 25KB를 넘습니다.");
			image.value = null;
			isProfileImageUrlValid = false;
		}
		else{
			isProfileImageUrlValid = true;
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

	if(!isProfileImageUrlValid){
		alert("프로필 이미지 유형과 크기를 확인해주세요.");
		return;
	}

	validationForm.submit();
}
