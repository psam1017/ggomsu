let input = document.querySelector("input[name=basic]");
new Tagify(input);

//미리보기 모달
const modal_wrap = document.querySelector(".modal_wrap");
const modal_background = document.querySelector(".modal_background");

//Show modal
document.querySelector("#modal_btn").addEventListener("click", () => {
  open();
});

//Hide modal
document.querySelector(".modal_close").addEventListener("click", () => {
  close();
});

//Hide modal
window.addEventListener("click", (e) => {
  e.target === modal_background ? close() : false;
});
function close() {
  modal_wrap.classList.remove("show-modal");
  modal_background.classList.remove("show-modal");
}
function open() {
  modal_wrap.classList.add("show-modal");
  modal_background.classList.add("show-modal");
}

//const previews = document.querySelector(".text"); //미리보기
//const form = document.writing; //form
//const inputs = form.querySelectorAll("input");
//console.log(inputs);
//function inputValue() {
//  let category = form.category.value; //카테고리
//  let title = form.title.value; //제목
//  let basic = form.basic.value; //해시태그
//  let mainText = form.mainText.value; //본문
//
//  previews.innerHTML = category;
//  previews.innerHTML = title;
//  previews.innerHTML = basic;
//}
