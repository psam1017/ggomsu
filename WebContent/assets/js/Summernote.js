/**
 * 작성자 : 박성민
 * 필요한 contextPath, type은 Summernote.jsp에서 작성
 */
$(document).ready(function() {
	let path = "";
	
	const contentForm = document.getElementById("contentForm");
	const subject = document.getElementById("subject");
	const subjectResult = document.getElementById("subjectResult");
	const submitBtn = document.getElementById("submitBtn");
	
	let isExist = false;
	
	if(subject != null){
		subject.addEventListener("keyup", function(){
			checkSubject(contentForm.subject.value);
		});
	}
	
	submitBtn.addEventListener("click", function(e){
		e.preventDefault();
		contentFormSubmit();
	});
	
	// (1) content 전송 영역은 type에 따라 action과 content name, 콜백함수의 url이 달라진다.
	// type = [ boardWrite | boardUpdate | wikiWrite | wikiRevise ]
	changeAttrByType(type);
	
	// (2) summernote 내용 불러오기. 반드시 'summernote 초기화 이전에' 'script 안에서' 불러와야 함. 순서에 주의할 것.
	setContent(type);
	
	// (3) summernote 초기화
	$('.summernote').summernote({
		// 에디터 높이
		height: 150,
		// 에디터 한글 설정
		lang: "ko-KR",
		// 에디터에 커서 이동 (input창의 autofocus라고 생각하시면 됩니다.)
		focus : false,
		toolbar: [
			// 글꼴 설정
			['fontname', ['fontname']],
			// 글자 크기 설정
			['fontsize', ['fontsize']],
			// 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
			['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
			// 글자색
			['color', ['forecolor','color']],
			// 표만들기
			['table', ['table']],
			// 글머리 기호, 번호매기기, 문단정렬
			['para', ['ul', 'ol', 'paragraph']],
			// 줄간격
			['height', ['height']],
			// 그림첨부, 링크만들기, 동영상첨부
			['insert',['picture','link','video']],
			// 코드보기, 확대해서보기, 도움말
			['view', ['codeview','fullscreen', 'help']]
		],
		// 추가한 글꼴
		fontNames : ['Noto Sans KR', 'Noto Serif KR', 'Nanum Gothic', 'Nanum Myeongjo', 'Nanum Pen Script'],
		fontNamesIgnoreCheck : ['Noto Sans KR', 'Noto Serif KR', 'Nanum Gothic', 'Nanum Myeongjo', 'Nanum Pen Script'],
		// 추가한 폰트사이즈
		fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
		callbacks : { 
			// 여기 부분이 이미지를 첨부하는 부분
			onImageUpload : function(files, editor, welEditable) {
				for (var i = files.length - 1; i >= 0; i--) {
					uploadSummernoteImageFile(files[i], this);
				}
			},
			// 0315 추가. 길이 제한
			onChange : function(contents){
				if(type == "boardWrite" || type == "boardUpdate"){
					const maxlength = 10000;
					const contentLengthViewer = document.getElementById("contentLengthViewer");
					
					if(contentLengthViewer != null){
						contentLengthViewer.innerText = contents.length;
					}
					if(contents.length > maxlength)  {
						noteEditable.innerHTML = noteEditable.innerHTML.substr(0, maxlength - 100);
						alert("본문은 코드를 포함하여 9900자 내외로 제한됩니다.");
					}
				}
			}
		}
	});
	
	
	// 파일 전송 콜백함수
    function uploadSummernoteImageFile(file, el) {
		data = new FormData();
		data.append("file", file);
		$.ajax({
			data : data,
			type : "POST",
			url : contextPath + "/upload" + path,
			contentType : false,
			enctype : 'multipart/form-data',
			processData : false,
			dataType : "json",
			success : function(result) {
				$(el).summernote('editor.insertImage', result.url);
			},
			error : function(){
				alert("파일 업로드를 실패했습니다. 관리자에게 문의하세요.");
			}
		});
	}
	
	// action 초기화 함수
	function changeAttrByType(type){
		if(type === "boardWrite"){
			contentForm.action = contextPath + "/article/write/confirm";
			path = "/board";
		}
		else if(type === "boardUpdate"){
			contentForm.action = contextPath + "/article/update/confirm";
			path = "/board";
		}
		else if(type === "wikiWrite"){
			contentForm.action = contextPath + "/wiki/write/confirm";
			contentForm.title.name = "subject"; // 불필요한 명령. but 수정될 가능성이 높고 리소스 사용이 적으므로 보존
			contentForm.content.name = "contents";
			path = "/wiki";
		}
		else if(type === "wikiRevise"){
			contentForm.action = contextPath + "/wiki/revise/confirm";
			contentForm.title.name = "subject"; // 불필요한 명령. but 수정될 가능성이 높고 리소스 사용이 적으므로 보존
			contentForm.content.name = "contents";
			path = "/wiki";
		}
		else{
			alert("잘못된 접근입니다. 올바른 경로로 다시 시도해주십시오.\n");
			location.replace("/main");
			return false;
		}
	}
	
	// 수정 시 원문을 가져오는 함수
	function setContent(type){
		
		// 일단 textarea의 모든 내용을 비운다.
		contentForm.content.value = "";
		
		// 만약 수정한다면 이전 글 내용을 불러온다.
		if(type == 'boardUpdate' || type == 'wikiRevise'){
			contentForm.content.value = unescapeHtml(original.innerHTML);
		}
		
		original.innerHTML = "";
	}
	
	function unescapeHtml(str) {

		if (str == null) {
		 return "";
		}

		return str
		  .replace(/&amp;/g, '&')
		  .replace(/&lt;/g, '<')
		  .replace(/&gt;/g, '>')
		  .replace(/&quot;/g, '"')
		  .replace(/&#039;/g, "'")
		  .replace(/&#39;/g, "'");
	}
	
	// 0306 추가. 스타일 조정
	const noteEditable = document.querySelector(".note-editable");
	noteEditable.className += " bg-white";
	noteEditable.style.height = "350px";
	
	// 0316 추가. 빈 값 불허용
	function contentFormSubmit(){
		if(type == "boardWrite" || type == "boardUpdate"){
			let title = contentForm.title.value;
			let content = contentForm.content.value;
			
			if(title.replace(/ /g, "") == ""){
				alert("제목이 비어있습니다.");
				return;
			}
			
			if(content.replace(/ /g, "") == "" || content == "<p><br></p>"){
				alert("내용이 비어있습니다.");
				return;
			}
		}
		else if(type == "wikiWrite" || type == "wikiRevise"){
			const ip = document.getElementById("ip");
			let subject = contentForm.subject.value;
			let contents = contentForm.contents.value;
			
			if(subject.replace(/ /g, "") == ""){
				alert("제목이 비어있습니다.");
				return;
			}
			
			if(contents.replace(/ /g, "") == "" || contents == "<p><br></p>"){
				alert("내용이 비어있습니다.");
				return;
			}
			
			if(ip != null && ip.checked == false){
				alert("IP 수집에 동의해주십시오.");
				return;
			}
			
			if(type == "wikiWrite"){
				checkSubject(subject);
				
				if(isExist){
					alert("이미 생성된 주제입니다.\n내용을 복사하신 후 검색해보세요.");
					return;
				}
			}
		}
		contentForm.submit();
	}
	
	function checkSubject(subject){
		
		if(subjectResult == null){
			alert("오류가 발생했습니다.\n새로고침 후 다시 시도해주세요.");
			return;
		}
		
	    let xhr = new XMLHttpRequest();
	    let requestURL = contextPath + "/wiki/check/subject";

	    xhr.open("post", requestURL, true);
	    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	    xhr.send("subject=" + subject);

	    xhr.onreadystatechange = function(){
	        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
	        	let json = JSON.parse(xhr.responseText);
	            if(json.status == "exist"){
	            	subjectResult.innerText = "이미 생성된 주제입니다.";
	            	isExist = true;
	            }
	            else{
	            	subjectResult.innerText = "생성할 수 있는 주제입니다.";
	            	isExist = false;
	            }
	        }
	    }
	}
});