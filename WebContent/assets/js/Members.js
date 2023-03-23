
const membersForm = document.querySelectorAll(".membersForm");
const membersConfirmButton = document.querySelectorAll(".membersConfirmButton");

membersConfirmButton.forEach((element, index) => {
	element.addEventListener("click", function (e) {
		e.preventDefault();
		membersForm[index].submit();
	});
});

function filter() {
	const memberRow = document.querySelectorAll(".memberRow");
	const nickname = document.querySelectorAll(".nickname");
	let search = document.getElementById("search").value;
	
	for (let i = 0; i < memberRow.length; i++) {
		if (nickname[i].innerText.indexOf(search) > -1) {
			memberRow[i].style.display = "revert";
		} else {
			memberRow[i].style.display = "none";
		}
	}
}
