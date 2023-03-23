/**
 * 작성자 : 박성민
 */

const validationForm = document.getElementById("validationForm");
const nickname = document.getElementById("nickname");
const nicknameResult = document.getElementById("nicknameResult");
const validationSubmit = document.getElementById("validationSubmit");

let isNicknameValid = false;
let nicknameRegExp = /^[가-힣0-9]+$/;

checkNickname(nickname.value);

nickname.addEventListener("keyup", function(){
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

// 프로필 이미지 크기 검사, 삭제, 미리보기 추가
const profileImage = document.getElementById("profileImage");
const profileImageUrl = document.getElementById("profileImageUrl");
const profileDeleteButton = document.getElementById("profileDeleteButton");

let isChangeProfileImage = "false";

profileImageUrl.addEventListener("change", function(e){
	checkSize(this, e);
});

profileDeleteButton.addEventListener("click", function(e){
	e.preventDefault();
	deleteProfile();
});

// 이미지 크기 검사
function checkSize(image, event) {
	let isImageValid = true;
	
	if (image.files) {
		let file = image.files[0];
		let fileName = file.name;
		fileName =  fileName.substring(fileName.indexOf(".") + 1).toLowerCase();
		
		if(file.size > (1024 * 25)){
			alert("이미지 사이즈가 25KB를 넘습니다.");
			isImageValid = false;
		}
		if(fileName != "jpg" && fileName != "jpeg" &&  fileName != "gif" &&  fileName != "png"){
			alert("이미지는 jpg, jpeg, gif, png 형식만 등록할 수 있습니다.");
			isImageValid = false;
		}
		if(isImageValid){
			preview(event);
			isChangeProfileImage = "new";
		}
	}
}

// 업로드 이미지 미리보기
function preview(event) {
    let reader = new FileReader();

    reader.onload = function(event) {
      profileImage.src = event.target.result;
    };

    reader.readAsDataURL(event.target.files[0]);
}

// 프로필 이미지 삭제
function deleteProfile(){
	profileImage.src = contextPath + "/bs/assets/img/illustrations/profiles/default.png";
	profileImageUrl.value = null;
	isChangeProfileImage = "delete";
}

// 유효성 검사
function formSubmit(){

	if(!isNicknameValid){
		alert("닉네임을 확인해주세요.");
		nickname.focus();
		return;
	}

	validationForm.action += "?nickname=" + nickname.value + "&isChangeProfileImage=" + isChangeProfileImage;
	
	validationForm.submit();
}
