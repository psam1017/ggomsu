# 작성자 : 박성민

INSERT INTO members
(email, password, salt, signAt, passwordAlertAt, nickname, profileImageUrl, name, birthDate, sex, telecomValue, contact, zipcode, address, addressDetail, agreedTermAt, agreedMarketingAt)
VALUES
('psam1017@naver.com', 'psam1017', '', NOW(), (NOW() + INTERVAL 90 DAY), 'psam-nick', 'psam-url', '박성민', '1996-10-17', 'M', 'CPK', '01077954671', '44332', '대구시 서구 케로로 10길 10', '20층(우동)', NOW(), NOW()),
('test1234@google.com', 'test1234', '', NOW(), (NOW() + INTERVAL 90 DAY), 'test-nick', 'test-url', '테스터', '1998-04-25', 'F', 'LG', '01012345678', '12378', '서울시 지구 여기로 2길 84', '3층 301호(카츠동)', NOW(), NULL),
('admin@google.com', 'admin', '', NOW(), (NOW() + INTERVAL 90 DAY), 'admin-nick', 'admin-url', '관리자', '2002-07-14', 'M', 'KT', '01744256982', '00154', '대구시 마구 통학로 21길 1', '우리집(금은동)', NOW(), NULL);

# 프로그램에게 프로시저 권한 주기. 사용자 환경마다 다를 수 있으니 유의하세요.
-- SHOW GLOBAL VARIABLES LIKE 'LOG_BIN_TRUST_FUNCTION_CREATORS';
SET GLOBAL LOG_BIN_TRUST_FUNCTION_CREATORS = 1;

CALL dummyArticles();

UPDATE articles
SET viewCount = 200
WHERE articleIndex = 17;

UPDATE articles
SET viewCount = 181
WHERE articleIndex = 18;

INSERT INTO tags
VALUES
(17, 'JAVA'),
(17, 'Spring'),
(18, 'Java'),
(19, 'Python'),
(19, 'Java'),
(19, 'C++');

INSERT INTO comments(refIndex, articleIndex, nickname, content)
VALUES
(1, 2, 'psam-nick', '새로운 기념비적인 게시글입니다.'),
(2, 2, 'test-nick', '과연 프로젝트 잘 될 수 있을까?'),
(3, 2, 'admin-nick', '오늘 저녁은 돈까스입니다.'),
(1, 2, 'test-nick', '말투 되게 아저씨같네.'),
(1, 2, 'psam-nick', '왜 시비임?'),
(2, 2, 'admin-nick', '과연이 아니라 어떻게 하면이라고 해주세요.');

INSERT INTO articleLike
VALUES
('55', 'test-nick'),
('55', 'psam-nick'),
('66', 'psam-nick'),
('77', 'psam-nick'),
('88', 'psam-nick');

INSERT INTO commentLike
VALUES
(1, 'psam-nick'),
(1, 'admin-nick'),
(6, 'admin-nick'),
(6, 'psam-nick'),
(2, 'test-nick');