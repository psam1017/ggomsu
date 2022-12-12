-- CREATE SCHEMA psam1017;
-- DROP SCHEMA psam1017;

# 작성자 : 박성민
# 22.12.07.
# 변경사항 : member 테이블 통합 / 컬럼명 통일
# 사유 : 정규화 / 컬럼명 구분에 따른 작업효율 저하
# 비고 : 제1정규형을 만족시키기 위해 소셜 로그인 key를 모두 분리하였습니다.

CREATE TABLE memberSex
(
	value VARCHAR(1) NOT NULL PRIMARY KEY,
    text VARCHAR(2) NOT NULL
);

INSERT INTO memberSex
VALUES	('M', "남자"),
		('F', "여자");

CREATE TABLE memberStatus
(
	value VARCHAR(3) NOT NULL PRIMARY KEY,
    text VARCHAR(50) NOT NULL
);

# 멤버의 권한 및 상태 코드 : ADM, MEM, DOR, SUS, DEL
# 활동 회원은 ADM 또는 MEM / 휴면 계정은 DOR / 사용 불가는 SUS 또는 DEL.
# 정지된 계정은 개인정보를 5년간 보관하며 그동안 가입 불가능
# DEL은 탈퇴만 하고 개인정보를 삭제 요청하지 않은 경우 1년간 보관(하는 데 큰 의미는 없다.)

# 테이블과 데이터의 규모가 작으므로 정상, 휴면, 탈퇴로 나누어서 회원 테이블을 생성
# 휴면 테이블은 언제든지 데이터를 바로 정상으로 되돌릴 수 있도록 정상 회원 테이블과 똑같이 만든다.
# 탈퇴 테이블은 email과 statusValue를 반드시 가지고, 약관에 따라 개인정보를 일정 기간 소지할 수 있다.
# 만약 회원이 요구한다면 DEL status로 두지 않고 데이터를 완전히 삭제한다.

INSERT INTO memberStatus
VALUES	("ADM", "관리자"),
		("MEM", "정상회원"),
		("DOR", "휴면계정"),
		("SUS", "관리자에 의해 정지"),
		("DEL", "자발적 탈퇴");

CREATE TABLE telecoms
(
	value VARCHAR(3) NOT NULL PRIMARY KEY,
    text VARCHAR(30) NOT NULL
);

INSERT INTO telecoms
VALUES	("KT", "케이티"),
		("SKT", "에스케이티"),
		("LG", "엘지"),
        ("CPK", "알뜰폰케이티"),
        ("CPS", "알뜰폰에스케이티"),
        ("CPL", "알뜰폰엘지");

CREATE TABLE members
(
	email				VARCHAR(50) 	NOT NULL	PRIMARY KEY,
	password			VARCHAR(128) 	NOT NULL,
	salt				VARCHAR(128)	NOT NULL,
    naverKey			VARCHAR(128)	NULL,
    kakaoKey			VARCHAR(128)	NULL,
    googleKey			VARCHAR(128)	NULL,
	signAt				DATETIME 		NOT NULL,
    passwordAlertAt		DATETIME 		NOT NULL,
    nickname			VARCHAR(10) 	NOT NULL 	UNIQUE,
	profileImageUrl		VARCHAR(128) 	NULL		UNIQUE,
	name				VARCHAR(5) 		NULL,
    birthDate			DATE 			NULL,
    sex					VARCHAR(1) 		NULL,
    telecomValue		VARCHAR(3) 		NULL,
    contact				VARCHAR(12) 	NULL 		UNIQUE,
	zipcode				VARCHAR(5)		NULL,
	address				VARCHAR(100)	NULL,
	addressDetail		VARCHAR(100)	NULL,
    agreedTermAt		DATETIME 		NOT NULL	DEFAULT NOW(),
    agreedMarketingAt	DATETIME 		NULL,
    createdAt			DATETIME 		NOT NULL 	DEFAULT NOW(),
    statusValue			VARCHAR(3) 		NOT NULL	DEFAULT "MEM",
    abuseCount			TINYINT			NOT NULL	DEFAULT 0,
	CONSTRAINT FOREIGN KEY(sex)
		REFERENCES memberSex(value)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(telecomValue)
		REFERENCES telecoms(value)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(statusValue)
		REFERENCES memberStatus(value)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE memberBlock
(
	nickname			VARCHAR(10) NOT NULL,
	blockedMember		VARCHAR(10) NOT NULL,
    CONSTRAINT PRIMARY KEY(nickname, blockedMember),
    CONSTRAINT FOREIGN KEY(nickname)
		REFERENCES members(nickname)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(blockedMember)
		REFERENCES members(nickname)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);
