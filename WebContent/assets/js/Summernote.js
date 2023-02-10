/**
 * 작성자 : 박성민
 * 필요한 contextPath, type은 Summernote.jsp에서 작성
 */
$(document).ready(function() {
	let path = "";
	
	const contentForm = document.getElementById("contentForm");
	const original = document.getElementById("original");
	
	// (1) content 전송 영역은 type에 따라 action과 content name, 콜백함수의 url이 달라진다.
	// type = [ board-write | board-update | wiki-write | wiki-revise ]
	decideActionByType(type);
	
	// (2) summernote 내용 불러오기. 반드시 'summernote 초기화 이전에' 'script 안에서' 불러와야 함. 순서에 주의할 것.
	getOriginalContent();
	
	// (3) summernote 초기화
	$('.summernote').summernote({
		// 에디터 높이
		height: 150,
		// 에디터 한글 설정
		lang: "ko-KR",
		// 에디터에 커서 이동 (input창의 autofocus라고 생각하시면 됩니다.)
		focus : true,
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
		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','궁서','굴림체','굴림','돋음체','바탕체'],
		// 추가한 폰트사이즈
		fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
		callbacks : { //여기 부분이 이미지를 첨부하는 부분
			onImageUpload : function(files, editor, welEditable) {
				for (var i = files.length - 1; i >= 0; i--) {
					uploadSummernoteImageFile(files[i], this);
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
	function decideActionByType(type){
		if(type === "boardWrite"){
			contentForm.action = contextPath + "/board/article-write-ok";
			path = "/board";
		}
		else if(type === "boardUpdate"){
			contentForm.action = contextPath + "/board/article-update-ok";
			path = "/board";
		}
		else if(type === "wikiWrite"){
			contentForm.action = contextPath + "/wiki/write-ok";
			contentForm.content.name = "contents";
			path = "/wiki";
		}
		else if(type === "wikiRevise"){
			contentForm.action = contextPath + "/wiki/revise-ok";
			contentForm.content.name = "contents";
			path = "/wiki";
		}
		else{
			alert("잘못된 접근입니다. 올바른 경로로 다시 시도해주십시오.\ntype : " + type);
			location.replace("/ggomsu");
			return false;
		}
	}
	
	// 수정 시 원문을 가져오는 함수
	function getOriginalContent(){
		
		let innerContent = original.innerHTML;
		$(".summernote").html(innerContent);
		original.innerHTML = "";
	}
});