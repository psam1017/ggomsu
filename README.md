# ggomsu

이 프로젝트는 코리아IT아카데미의 <스마트웹 응용 프로그래밍 개발자 양성 과정B> (2022-09-22 ~ 2023-03-27)에서 진행한 포트폴리오 프로젝트입니다.   
꼼수닷컴이라는 말은 "어떤 수를 써서든 완성시키기만 하면 된다"라는 마음가짐으로, 직접 부딪혀가며 만드는 프로젝트라는 의미에서 태어난 웹 사이트입니다.   

*프론트 페이지는 stratBootstrap의 템플릿을 사용하였으며, 사용약관에 의해 이를 사용한 페이지 소스는 공개하지 않고 있습니다.   

개발기간 : 22년 11월 7일 ~ 3월 23일.   

협력자 : 박성민(팀장), 김지혜, 손하늘, 이성호   

역할   
박성민 : 프로젝트 환경설정 및 배포, 스케줄 관리 리팩토링, DB 모델링, 기능구현(위키, 게시판, 회원가입 및 로그인 외 팀원 지원)   
손하늘 : 프론트 페이지 기능 구현, 기능구현(마이페이지, 신고)   
김지혜 : 프론트 페이지 디자인, 로고 및 아이콘 콘셉트, 기능구현(댓글, 알람)   
이성호 : DB 모델링 보조, 기능구현(게시판, 관리자)   

보다 상세한 내용은 꼼수닷컴 포트폴리오 메뉴(https://ggomsu.com/article/list?boardValue=portfolio)를 참고해주세요.   
또는 Github의 specification 항목을 참고해주세요.

### bug fix 23.03.26.

 **서버 기능 수정**
- 이메일 저장을 체크하지 않으면 쿠키가 삭제되지 않는 오류 수정
- 이메일 인증에 3번 이상 실패해도 이메일을 사용할 수 있던 문제 수정
- 개인정보를 수정할 때 자기 전화번호도 중복이라고 뜨는 문제 수정
- 알람을 받지 않는다고 설정해도 알람이 받아지던 문제 수정
- 위키를 수정할 때 발생하는 NullPointerException 제거

 **기능 추가**
- 권한 없이 마이페이지로 접근하면 에러페이지가 아니라 로그인 페이지로 리디렉트하도록 변경
- 게시글과 댓글 좋아요를 누르면 좋아요 수가 바로 변경되도록 수정
- 작성한 게시글에서 삭제된 게시글은 삭제되었음을 표시하고 이동할 수 없도록 변경
- 위키 Ver.1을 삭제할 경우 빈 내용의 Ver.2를 자동으로 생성
- 웹 하드 공간의 일부를 자동으로 삭제
- 포트폴리오 리스트에서는 list size를 20으로 늘려서 되도록 페이징되지 않도록 함.

 **화면 구현**
- 일부 누락된 전화번호 유효성 검사를 추가
- 메인 화면 포트폴리오 바로가기 이미지를 변경
- 게시글 상세보기에서 프로필 이미지 배치를 수정
- 작성한 게시글에서 프로필 이미지가 없을 때 기본 프로필 이미지가 나오지 않는 문제 수정
- 모바일 뷰포트에서 위키 제목이 가려지는 문제 수정

---

### bug fix 23.03.23.

 **서버 기능 수정**
- 업로드 경로를 웹 환경에 맞게 변경
- 게시글을 조회할 때 조회수가 바로 증가되지 않는 문제 수정

 **화면 구현**
- 포트폴리오 바로가기, 위키 바로가기에 빠진 경로 추가