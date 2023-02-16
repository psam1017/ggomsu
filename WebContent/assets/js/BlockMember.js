/**
 * 작성자 : 손하늘
 */

const memberBlock = document.getElementById("memberBlock");
const updateBlock = document.getElementById("updateBlock");
const deleteBlock = document.getElementById("deleteBlock");
//const contextPath = "${pageContext.request.contextPath}";
const contextPath = "/ggomsu";
//전체 체크박스
const checkboxes = document.querySelectorAll('.checkBox');
//select all 체크박스
const selectAll = document.querySelector('#allCheck');
// 선택된 체크박스
const checked = document.querySelectorAll('input[name="blockList"]:checked');

//체크박스 전체 선택 이벤트
//selectAll.addEventListener("click", function(){
//	selectAlls(this);
//});
//
//function selectAlls(selectAll)  {
//	  const checkboxes = document.getElementsByName('blockList');
//	  checkboxes.forEach((checkbox) => {
//	    checkbox.checked = selectAll.checked
//	  })
//	  for(let i = 0; i < checkboxes.length; i++){
//			let checkSelect = checkboxes[i];
//			checkSelect.addEventListener("click", function(){
//				checkSelectAll();
//			});
//		};
//}
//function checkSelectAll()  {
//	  if(checkboxes.length === checked.length)  {
//	    selectAll.checked = true;
//	  }else {
//	    selectAll.checked = false;
//	  }
//}

//블랙리스트 추가 이벤트
updateBlock.addEventListener("click", function(){
	memberBlock.setAttribute('action', contextPath + "/member/member-update-block-ok")
	updateBlock.submit();
});

//블랙리스트 삭제 이벤트
deleteBlock.addEventListener("click", function(){
	memberBlock.setAttribute('action', contextPath + "/member/member-delete-block-ok");
	deleteBlock.submit();
})

