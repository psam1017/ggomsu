# 작성자 : 박성민

INSERT INTO `psam1017`.`memberSign`
(`email`, `password`, `signAt`, `passwordAlertAt`)
VALUES
('psam1017@naver.com', 'password12!@', NOW(), (NOW() + INTERVAL 90 DAY)),
('test1234@google.com', 'password12!@', NOW(), (NOW() + INTERVAL 90 DAY)),
('admin@google.com', 'password12!@', NOW(), (NOW() + INTERVAL 90 DAY));

INSERT INTO `psam1017`.`memberInfo`
(`memberEmail`, `nickname`, `profileImageUrl`, `name`, `birthDate`, `sex`, `telecomValue`, `contact`, `zipcode`, `address`, `addressDetail`, `agreedTermAt`, `agreedMarketingAt`)
VALUES
('psam1017@naver.com', '박성민', 'psam-url', '박성민', '1996-10-17', 'M', 'CPK', '01077954671', '44332', '대구시 서구 케로로 10길 10', '20층(우동)', NOW(), NOW()),
('test1234@google.com', 'test1234', 'tester-url', '테스터', '1998-04-25', 'F', 'LG', '01012345678', '12378', '서울시 지구 여기로 2길 84', '3층 301호(카츠동)', NOW(), NULL),
('admin@google.com', 'admin', 'admin-url', '관리자', '2002-07-14', 'M', 'KT', '01744256982', '00154', '대구시 마구 통학로 21길 1', '우리집(금은동)', NOW(), NOW());

INSERT INTO `psam1017`.`memberProfile`
(`nickname`, `profileImageUrl`)
VALUES
('박성민', 'psam-url'),
('test1234', 'tester-url'),
('admin', 'admin-url');

# 프로그램에게 프로시저 권한 주기. 사용자 환경마다 다를 수 있으니 유의하세요.
-- SHOW GLOBAL VARIABLES LIKE 'LOG_BIN_TRUST_FUNCTION_CREATORS';
-- SET GLOBAL LOG_BIN_TRUST_FUNCTION_CREATORS = 1;

CALL dummyArticles();

INSERT INTO `psam1017`.`comments`
(`refIndex`, `articleIndex`, `memberNickname`, `content`)
VALUES
(1, 2, '박성민', '새로운 기념비적인 게시글입니다.'),
(2, 2, 'test1234', '과연 프로젝트 잘 될 수 있을까?'),
(3, 2, 'admin', '오늘 저녁은 돈까스입니다.'),
(1, 2, 'test1234', '말투 되게 아저씨같네.'),
(1, 2, '박성민', '왜 시비임?'),
(2, 2, 'admin', '과연이 아니라 어떻게 하면이라고 해주세요.');