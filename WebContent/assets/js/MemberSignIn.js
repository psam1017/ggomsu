const memberCapcha = document.querySelectorAll(".memberCapcha");

memberCapcha.forEach(element => {
	element.addEventListener("click", function(e){
		e.preventDefault();
		memberCapchaForm();
	});
})

function memberCapchaForm(){
  	let xhr = new XMLHttpRequest();
    let requestURL = contextPath + "/member/capcha";

    xhr.open("post", requestURL, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send();

    xhr.onreadystatechange = function(){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
        	let json = JSON.parse(xhr.responseText);
        	if(json.status == "success"){
	        	let imgSrc = document.getElementById("capchaImg");
	  	        imgSrc.setAttribute("src", contextPath + json.filename);
        	}
        	else{
        		alert("이미지 요청에 실패했습니다.\n관리자에게 문의해주세요.");
	        	}
	        }
	    }
	}
	
  const tabList = document.querySelectorAll('.tab_menu_list li a');
  const contents = document.querySelectorAll('.tab_menu_cont')
  let activeCont = ''; // 현재 활성화 된 컨텐츠 (기본:#tab1 활성화)
  for(var i = 0; i < tabList.length; i++){
    tabList[i].addEventListener('click', function(e){
  e.preventDefault();
  for(var j = 0; j < tabList.length; j++){
    tabList[j].classList.remove('active');
    contents[j].style.display = 'none';
  }
  this.classList.add('active');
  activeCont = this.getAttribute('data-item');
  document.querySelector(activeCont).style.display = 'block';
    });
  }