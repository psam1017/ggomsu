
-- CREATE SCHEMA psam1017;
-- DROP SCHEMA psam1017;

# 작성자 : 박성민
# 전화번호는 js로 유효성 검사 실시
# 차단유저는 로그인할 때 정보를 한 번에 불러와서 세션에 저장하기

CREATE TABLE user_sex
(
	value VARCHAR(1) NOT NULL PRIMARY KEY,
    text VARCHAR(2) NOT NULL
);

INSERT INTO user_sex
VALUES	('M', "남자"),
		('F', "여자");

CREATE TABLE user_status
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
# 탈퇴 테이블은 email과 status_value를 반드시 가지고, 약관에 따라 개인정보를 일정 기간 소지할 수 있다.
# 만약 회원이 요구한다면 DEL status로 두지 않고 데이터를 완전히 삭제한다.

INSERT INTO user_status
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
        ("CP", "알뜰폰");

CREATE TABLE user_sign
(
	email				VARCHAR(50) 	NOT NULL	PRIMARY KEY,
    password			VARCHAR(128) 	NOT NULL,
    sign_at				DATETIME 		NOT NULL,
    password_alert_at	DATETIME 		NOT NULL
);

CREATE TABLE user_info
(
	user_email			VARCHAR(50) 	NOT NULL	PRIMARY KEY,
    nickname			VARCHAR(10) 	NOT NULL 	UNIQUE,
	profile_image_url	VARCHAR(128) 	NULL		UNIQUE,
	name				VARCHAR(5) 	NULL,
    birth_date			DATE 			NULL,
    sex					VARCHAR(1) 	NULL,
    telecom_value		VARCHAR(3) 	NULL,
    contact				VARCHAR(12) 	NULL 		UNIQUE,
	zipcode				VARCHAR(5)		NULL,
	address				VARCHAR(100)	NULL,
	address_detail		VARCHAR(100)	NULL,
    agreed_term_at		DATETIME 		NOT NULL,
    agreed_marketing_at	DATETIME 		NULL,
    created_at			DATETIME 		NOT NULL 	DEFAULT NOW(),
    status_value		VARCHAR(3) 	NOT NULL	DEFAULT "MEM",
    abuse_count			TINYINT		NOT NULL	DEFAULT 0,
	CONSTRAINT FOREIGN KEY(user_email)
		REFERENCES user_sign(email)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(sex)
		REFERENCES user_sex(value)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(telecom_value)
		REFERENCES telecoms(value)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(status_value)
		REFERENCES user_status(value)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE user_profile
(
    nickname			VARCHAR(10) 	NOT NULL PRIMARY KEY,
	profile_image_url	VARCHAR(128) 	NULL,
    CONSTRAINT FOREIGN KEY(nickname)
		REFERENCES user_info(nickname)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(profile_image_url)
		REFERENCES user_info(profile_image_url)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE user_social
(
	user_email	VARCHAR(50) 	NOT NULL,
	unique_id	VARCHAR(100)	NOT NULL,
	CONSTRAINT PRIMARY KEY(user_email, unique_id),
	CONSTRAINT FOREIGN KEY(user_email)
		REFERENCES user_sign(email)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE user_block
(
	user_nickname	VARCHAR(10) NOT NULL,
	blocked_user		VARCHAR(10) NOT NULL,
    CONSTRAINT PRIMARY KEY(user_nickname, blocked_user),
    CONSTRAINT FOREIGN KEY(user_nickname)
		REFERENCES user_profile(nickname)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(blocked_user)
		REFERENCES user_profile(nickname)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);