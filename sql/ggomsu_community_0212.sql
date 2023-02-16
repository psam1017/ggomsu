# 작성자 : 박성민
# 컨벤션 스타일 : 카멜 표기법 사용 <- 컬럼명 불일치로 인한 작업효율 저하

DROP SCHEMA IF EXISTS psam1017;
CREATE SCHEMA psam1017;
USE psam1017;
ALTER SCHEMA psam1017 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

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
	signAt				DATETIME 		NOT NULL	DEFAULT NOW(),
    passwordAlertAt		DATETIME 		NULL,
    nickname			VARCHAR(10) 	NOT NULL 	UNIQUE,
	profileImageUrl		VARCHAR(128) 	NULL		UNIQUE,
	name				VARCHAR(5) 	NULL,
    birthDate			DATE 			NULL,
    sex					VARCHAR(1) 	NULL,
    telecomValue		VARCHAR(3) 	NULL,
    contact				VARCHAR(12) 	NULL	UNIQUE,
	zipcode				VARCHAR(5)		NULL,
	address				VARCHAR(100)	NULL,
	addressDetail		VARCHAR(100)	NULL,
    agreedTermAt		DATETIME 		NOT NULL	DEFAULT NOW(),
    agreedMarketingAt	DATETIME 		NULL,
    createdAt			DATETIME 		NOT NULL 	DEFAULT NOW(),
    statusValue			VARCHAR(3) 	NOT NULL	DEFAULT "MEM",
    abuseCount			TINYINT		NOT NULL	DEFAULT 0,
    articleAlarmFlag	BOOLEAN		NOT NULL	DEFAULT 1,
    commentAlarmFlag	BOOLEAN		NOT NULL	DEFAULT 1,
    darkModeFlag		BOOLEAN		NOT NULL	DEFAULT 0,
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

CREATE TABLE memberSNS
(
	email	VARCHAR(50) NOT NULL,
    snsKey	VARCHAR(128) NOT NULL,
    CONSTRAINT PRIMARY KEY(email, snsKey),
    CONSTRAINT FOREIGN KEY(email)
		REFERENCES members(email)
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

CREATE TABLE boards
(
	boardValue VARCHAR(10) NOT NULL PRIMARY KEY,
    text VARCHAR(50) NOT NULL
);

INSERT INTO boards
VALUES	("notice", "공지사항"),
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
    commentIndex		INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
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
	nickname			VARCHAR(10)	NOT NULL,
    articleIndex		INT UNSIGNED	NOT NULL,
    articleReportReason	VARCHAR(50)	NOT NULL,
    articleDeleteReason	VARCHAR(50)	NULL,
	CONSTRAINT PRIMARY KEY(nickname, articleIndex),
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
	nickname			VARCHAR(10)	NOT NULL,
    commentIndex		INT UNSIGNED	NOT NULL,
    commentReportReason	VARCHAR(50)	NOT NULL,
	commentDeleteReason	VARCHAR(50)	NULL,
	CONSTRAINT PRIMARY KEY(nickname, commentIndex),
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
('psam1017@naver.com', 'psam1017', '', NOW(), (NOW() + INTERVAL 90 DAY), 'psam-nick', 'psam-url', '박성민', '1996-10-17', 'M', 'CPK', '01077954671', '44332', '대구시 서구 여기로 10길 10', '20층(여기동)', NOW(), NOW()),
('test1234@google.com', 'test1234', '', NOW(), (NOW() + INTERVAL 90 DAY), 'test-nick', 'test-url', '테스터', '1998-04-25', 'F', 'LG', '01012345678', '12378', '서울시 중구 저기로 2길 84', '3층 301호(저기동)', NOW(), NULL),
('admin@google.com', 'admin', '', NOW(), (NOW() + INTERVAL 90 DAY), 'admin-nick', 'admin-url', '관리자', '2002-07-14', 'M', 'KT', '01744256982', '00154', '대전시 남구 거기로 21길 1', '우리집(거기동)', NOW(), NULL),
('noname@ggomsu.com', 'noname', '', NOW(), (NOW() + INTERVAL 90 DAY), 'noname', '', '', '2000-01-01', 'M', 'LG', '', '', '', '', NOW(), NULL);

INSERT INTO articles(boardValue, nickname, title, content)
VALUES('coding', 'psam-nick', '더미제목', '더미내용');

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
SET viewCount = 200
WHERE articleIndex = 254;

UPDATE articles
SET viewCount = 181
WHERE articleIndex = 252;

INSERT INTO tags
VALUES
(250, 'JAVA'),
(250, 'Spring'),
(251, 'Java'),
(251, 'Python'),
(253, 'Java'),
(253, 'C++');

INSERT INTO comments(refIndex, articleIndex, nickname, content)
VALUES
(1, 255, 'psam-nick', '새로운 기념비적인 게시글입니다.'),
(2, 255, 'test-nick', '과연 프로젝트 잘 될 수 있을까?'),
(3, 255, 'admin-nick', '오늘 저녁은 돈까스입니다.'),
(1, 255, 'test-nick', '말투 되게 아저씨같네.'),
(1, 255, 'psam-nick', '왜 시비임?'),
(2, 255, 'admin-nick', '과연이 아니라 어떻게 하면이라고 해주세요.');

INSERT INTO articleLike
VALUES
(254, 'test-nick'),
(254, 'psam-nick'),
(253, 'psam-nick'),
(252, 'psam-nick'),
(251, 'psam-nick');

INSERT INTO commentLike
VALUES
(1, 'psam-nick'),
(1, 'admin-nick'),
(6, 'admin-nick'),
(6, 'psam-nick'),
(2, 'test-nick');

/*
-- 프로시저
CREATE DEFINER=`psam1017`@`localhost` PROCEDURE `dummyArticles`()
BEGIN
	DECLARE i INT DEFAULT 1;    
   		WHILE i <= 124 DO
		INSERT INTO articles(boardValue, nickname, title, content, viewCount)
			VALUES('coding', 'psam-nick', CONCAT('제목',i), CONCAT('내용',i), (150 - i));
		SET i = i + 1;
	END WHILE;
END

SHOW GLOBAL VARIABLES LIKE 'LOG_BIN_TRUST_FUNCTION_CREATORS'; # 프로시저 확인
SET GLOBAL LOG_BIN_TRUST_FUNCTION_CREATORS = 1; # 프로시저 권한 부여
CALL dummyArticles(); # 프로시저 실행
*/