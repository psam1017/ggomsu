# 작성자 : 박성민
# 컨벤션 스타일 : 카멜 표기법 사용 <- 컬럼명 불일치로 인한 작업효율 저하

DROP SCHEMA IF EXISTS psam1017;
CREATE SCHEMA psam1017;
USE psam1017;
ALTER SCHEMA psam1017 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci; # 인코딩
SET GLOBAL LOG_BIN_TRUST_FUNCTION_CREATORS = 1; # 프로시저 권한 부여

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

# 테이블과 데이터의 규모가 작으므로 status를 column으로 가지는 회원 테이블을 생성
# 만약 규모가 크다면 정상, 휴면, 탈퇴로 테이블을 나누어서 3개 이상으로 생성
# 휴면 테이블은 언제든지 데이터를 바로 정상으로 되돌릴 수 있도록 정상 회원 테이블과 똑같이 만든다.
# 탈퇴 테이블은 email과 statusValue를 반드시 가지고, 약관에 따라 개인정보를 일정 기간 소지할 수 있다.
# 만약 회원이 요구한다면 DEL status로 두지 않고 데이터를 완전히 삭제한다.

# 추가사항 : TMP - 비회원으로서 사이트 이용

INSERT INTO memberStatus
VALUES	("ADM", "관리자"),
		("MEM", "정상회원"),
		("SNS", "SNS회원"),
		("TMP", "비회원"),
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
	password			VARCHAR(128) 	NULL,
	salt				VARCHAR(128)	NULL,
	signAt				DATETIME 		NULL		DEFAULT NOW(),
    passwordAlertAt		DATETIME 		NULL		DEFAULT NOW(),
    nickname			VARCHAR(10) 	NULL		UNIQUE,
	profileImageUrl		VARCHAR(128) 	NULL		UNIQUE,
	name				VARCHAR(5) 		NULL,
    birthDate			DATE 			NULL,
    sex					VARCHAR(1) 		NULL,
    telecomValue		VARCHAR(3) 		NULL,
    contact				VARCHAR(12) 	NULL		UNIQUE,
	zipcode				VARCHAR(5)		NULL,
	address				VARCHAR(100)	NULL,
	addressDetail		VARCHAR(100)	NULL,
    agreedTermAt		DATETIME 		NOT NULL	DEFAULT NOW(),
    agreedMarketingAt	DATETIME 		NULL,
    createdAt			DATETIME 		NOT NULL 	DEFAULT NOW(),
    statusValue			VARCHAR(3) 		NOT NULL	DEFAULT 'MEM',
    abuseCount			TINYINT			NOT NULL	DEFAULT 0,
    alarmFlag			BOOLEAN			NOT NULL	DEFAULT 1,
    darkModeFlag		BOOLEAN			NOT NULL	DEFAULT 0,
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

CREATE TABLE snsType
(
	value VARCHAR(20) NOT NULL PRIMARY KEY,
    text VARCHAR(20) NOT NULL
);

INSERT INTO snsType
VALUES	("naver", "네이버");

CREATE TABLE memberSns
(
	email		VARCHAR(50) NOT NULL,
    snsKey		VARCHAR(128) NOT NULL,
    `type`		VARCHAR(20) NOT NULL,
    CONSTRAINT PRIMARY KEY(email, snsKey),
    CONSTRAINT FOREIGN KEY(email)
		REFERENCES members(email)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY(`type`)
		REFERENCES snsType(value)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE memberBlind
(
	nickname		VARCHAR(10) NOT NULL,
	blindMember		VARCHAR(10) NOT NULL,
    CONSTRAINT PRIMARY KEY(nickname, blindMember),
    CONSTRAINT FOREIGN KEY(nickname)
		REFERENCES members(nickname)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(blindMember)
		REFERENCES members(nickname)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE boards
(
	boardValue VARCHAR(10) NOT NULL PRIMARY KEY,
    text VARCHAR(50) NOT NULL
);

INSERT INTO boards
VALUES	("portfolio", "포트폴리오"),
		("free", "자유꼼수판"),
		("coding", "코딩꼼수판"),
        ("game", "게임꼼수판");

# 임의의 게시판의 게시글 번호는 index를 참조하지 않고 게시글 리스트 페이지 수를 연산하여 출력.
# (ex) 1 페이지 = 1~10, 2페이지 = 11~20, ...

CREATE TABLE articles
(
    articleIndex		INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	boardValue			VARCHAR(10) NOT NULL,
    nickname			VARCHAR(10) NOT NULL,
    title				VARCHAR(100) NOT NULL,
    content				VARCHAR(10000) NOT NULL,
    viewCount			INT UNSIGNED NOT NULL DEFAULT 0,
    writtenAt			DATETIME NOT NULL DEFAULT NOW(),
    updatedAt			DATETIME NULL,
    deletedAt			DATETIME NULL,
    articleDeleteReason	VARCHAR(50) NULL,
    CONSTRAINT FOREIGN KEY(boardValue)
		REFERENCES boards(boardValue)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(nickname)
		REFERENCES members(nickname)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE tags
(
	articleIndex	INT UNSIGNED NOT NULL,
	tagValue		VARCHAR(50) NOT NULL,
    CONSTRAINT PRIMARY KEY(articleIndex, tagValue),
	CONSTRAINT FOREIGN KEY(articleIndex)
	REFERENCES articles(articleIndex)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

/* summernote를 사용하며 본문에 경로를 직접 삽입하므로 attachment Table 필요 없음.
CREATE TABLE attachment
(
	attachmentName	VARCHAR(128) NOT NULL PRIMARY KEY,
	articleIndex	INT UNSIGNED NOT NULL,
    CONSTRAINT FOREIGN KEY(articleIndex)
		REFERENCES articles(articleIndex)
			ON DELETE CASCADE
            ON UPDATE CASCADE
);
*/

CREATE TABLE comments
(
    commentIndex		INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    refIndex			INT UNSIGNED NULL,
    articleIndex		INT UNSIGNED NOT NULL,
    nickname			VARCHAR(10) NOT NULL,
    content				VARCHAR(1000) NOT NULL,
    writtenAt			DATETIME DEFAULT NOW(),
    deletedAt			DATETIME NULL,
    commentDeleteReason	VARCHAR(50) NULL,
	CONSTRAINT FOREIGN KEY(articleIndex)
		REFERENCES articles(articleIndex)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY(nickname)
		REFERENCES members(nickname)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(refIndex)
		REFERENCES comments(commentIndex)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE articleLike
(
	articleIndex	INT UNSIGNED NOT NULL,
	nickname		VARCHAR(10) NOT NULL,
    CONSTRAINT PRIMARY KEY(articleIndex, nickname),
    CONSTRAINT FOREIGN KEY(articleIndex)
		REFERENCES articles(articleIndex)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY(nickname)
		REFERENCES members(nickname)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE commentLike
(
	commentIndex INT UNSIGNED NOT NULL,
	nickname VARCHAR(10) NOT NULL,
    CONSTRAINT PRIMARY KEY(commentIndex, nickname),
    CONSTRAINT FOREIGN KEY(commentIndex)
		REFERENCES comments(commentIndex)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY(nickname)
		REFERENCES members(nickname)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE articleReport
(
	articleReportIndex	INT UNSIGNED	NOT NULL	PRIMARY KEY	AUTO_INCREMENT,
	nickname			VARCHAR(10)	NOT NULL,
    articleIndex		INT UNSIGNED	NOT NULL,
    articleReportReason	VARCHAR(50)	NOT NULL,
    articleDeleteReason	VARCHAR(50)	NULL,
	CONSTRAINT UNIQUE(nickname, articleIndex),
    CONSTRAINT FOREIGN KEY(nickname)
		REFERENCES members(nickname)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(articleIndex)
		REFERENCES articles(articleIndex)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE commentReport
(
	commentReportIndex	INT UNSIGNED	NOT NULL	PRIMARY KEY	AUTO_INCREMENT,
	nickname			VARCHAR(10)	NOT NULL,
    commentIndex		INT UNSIGNED	NOT NULL,
	commentDeleteReason	VARCHAR(50)	NULL,
	CONSTRAINT UNIQUE(nickname, commentIndex),
    CONSTRAINT FOREIGN KEY(nickname)
		REFERENCES members(nickname)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(commentIndex)
		REFERENCES comments(commentIndex)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE articleAlarm
(
    articleIndex	INT UNSIGNED	NOT NULL PRIMARY KEY,
    commentIndex	INT UNSIGNED	NOT NULL,
    nickname		VARCHAR(10)	NOT NULL,
    articleAlarmAt	DATETIME		NOT NULL DEFAULT NOW(),
	CONSTRAINT FOREIGN KEY(articleIndex)
		REFERENCES articles(articleIndex)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(commentIndex)
		REFERENCES comments(commentIndex)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(nickname)
		REFERENCES members(nickname)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE commentAlarm
(
    refIndex		INT UNSIGNED	NOT NULL PRIMARY KEY,
    commentIndex	INT UNSIGNED	NOT NULL,
    nickname		VARCHAR(10)	NOT NULL,
    commentAlarmAt	DATETIME		NOT NULL DEFAULT NOW(),
	CONSTRAINT FOREIGN KEY(commentIndex)
		REFERENCES comments(commentIndex)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(refIndex)
		REFERENCES comments(refIndex)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(nickname)
		REFERENCES members(nickname)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

# 더미데이터
INSERT INTO members
(email, password, salt, signAt, passwordAlertAt, nickname, profileImageUrl, name, birthDate, sex, telecomValue, contact, zipcode, address, addressDetail, agreedTermAt, agreedMarketingAt)
VALUES
('dummy@ggomsu.com', '더미', '', NOW(), (NOW() + INTERVAL 90000 DAY), '더미', NULL, NULL, NULL, NULL, NULL, NULL, '', '', '', NOW(), NULL),
('dummy2@ggomsu.com', '더미2', '', NOW(), (NOW() + INTERVAL 90000 DAY), '더미2', NULL, NULL, NULL, NULL, NULL, NULL, '', '', '', NOW(), NULL),
('noname@ggomsu.com', 'noname', '', NOW(), (NOW() + INTERVAL 90000 DAY), 'noname', NULL, NULL, NULL, NULL, NULL, NULL, '', '', '', NOW(), NULL);

UPDATE members
SET statusValue = "TMP"
WHERE nickname = "noname";

INSERT INTO articles(boardValue, nickname, title, content)
VALUES	('coding', '더미', '코딩 더미 제목', '<p>이 게시글은 페이징 테스트를 위한 더미 게시글입니다.</p>'),
		('game', '더미', '게임 더미 제목', '<p>이 게시글은 페이징 테스트를 위한 더미 게시글입니다.</p>'),
		('free', '더미', '자유 더미 제목', '<p>이 게시글은 페이징 테스트를 위한 더미 게시글입니다.</p>');

INSERT INTO articles(boardValue, nickname, title, content)
SELECT boardValue, nickname, title, content FROM articles;
INSERT INTO articles(boardValue, nickname, title, content)
SELECT boardValue, nickname, title, content FROM articles;
INSERT INTO articles(boardValue, nickname, title, content)
SELECT boardValue, nickname, title, content FROM articles;
INSERT INTO articles(boardValue, nickname, title, content)
SELECT boardValue, nickname, title, content FROM articles;
INSERT INTO articles(boardValue, nickname, title, content)
SELECT boardValue, nickname, title, content FROM articles;
INSERT INTO articles(boardValue, nickname, title, content)
SELECT boardValue, nickname, title, content FROM articles;
INSERT INTO articles(boardValue, nickname, title, content)
SELECT boardValue, nickname, title, content FROM articles;
INSERT INTO articles(boardValue, nickname, title, content)
SELECT boardValue, nickname, title, content FROM articles;

UPDATE articles
SET viewCount = 99999
WHERE articleIndex = 886;
UPDATE articles
SET viewCount = 250
WHERE articleIndex = 883;
UPDATE articles
SET viewCount = 2400
WHERE articleIndex = 880;
UPDATE articles
SET viewCount = 22
WHERE articleIndex = 877;
UPDATE articles
SET viewCount = 3000
WHERE articleIndex = 874;

INSERT INTO tags
VALUES
(886, 'JAVA'),
(886, 'Spring'),
(883, 'Java'),
(883, 'Python'),
(880, 'Java'),
(880, 'Python'),
(877, 'Java'),
(877, 'C++'),
(874, 'Python'),
(874, 'Java');

INSERT INTO comments(refIndex, articleIndex, nickname, content)
VALUES
(1, 886, '더미', '더미 댓글입니다.'),
(2, 886, '더미2', '더미 댓글입니다.'),
(3, 886, '더미', '더미 댓글입니다.'),
(1, 886, '더미2', '더미 댓글입니다.');
INSERT INTO comments(refIndex, articleIndex, nickname, content)
VALUES
(1, 886, '더미', '더미 댓글입니다.'),
(2, 886, '더미2', '더미 댓글입니다.'),
(7, 886, '더미', '더미 댓글입니다.');
INSERT INTO comments(refIndex, articleIndex, nickname, content)
VALUES
(8, 886, '더미2', '더미 댓글입니다.'),
(9, 886, '더미', '더미 댓글입니다.'),
(9, 886, '더미', '더미 댓글입니다.'),
(9, 886, '더미2', '더미 댓글입니다.');

INSERT INTO articleLike
VALUES
(886, '더미'),
(886, '더미2');

INSERT INTO commentLike
VALUES
(1, '더미'),
(1, '더미2'),
(6, '더미'),
(6, '더미2'),
(2, '더미2');

UPDATE articles
SET title = '더미 댓글이 있는 더미 게시글입니다.'
WHERE articleIndex = 886;
