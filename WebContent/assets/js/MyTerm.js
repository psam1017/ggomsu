/**
 * 작성자 : 박성민
 */

// start window.onload

const agreedTermAt = document.getElementById("agreedTermAt");
const terms = document.querySelectorAll(".agreedTermAt");

terms.forEach(element => {
	element.addEventListener("click", function(){
		if(this.checked){
			terms.forEach(element => {
				element.checked = true;
			});
			agreedTermAt.value = "renew";
		}
		else{
			terms.forEach(element => {
				element.checked = false;
			});
			agreedTermAt.value = "";
		}
	});
});