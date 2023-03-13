/**
 * 작성자 : 박성민
 */

// start window.onload

const validationForm = document.getElementById("validationForm");
const terms = document.querySelectorAll(".agreedTermAt");
const validationSubmit = document.getElementById("validationSubmit");

let isTermOk = false;

validationSubmit.addEventListener("click", function(e){
	e.preventDefault();
	formSubmit();
});

// 유효성 검사
function formSubmit(){

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
