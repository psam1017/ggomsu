SET GLOBAL event_scheduler = ON;

DROP PROCEDURE IF EXISTS insertComment; # 박성민
DROP PROCEDURE IF EXISTS insertRefComment; # 박성민
DROP PROCEDURE IF EXISTS reviseWikiInfo; # 박성민
DROP PROCEDURE IF EXISTS deleteAlarmListByNickname; # 박성민
DROP PROCEDURE IF EXISTS checkPasswordRenew; # 박성민
DROP PROCEDURE IF EXISTS checkTermExpired; # 박성민
DROP EVENT IF EXISTS updateDormant; # 손하늘
DROP EVENT IF EXISTS deleteOldAlarm; # 박성민
DROP EVENT IF EXISTS deleteWithdrawn; # 박성민

######프로시저######
# 댓글 삽입
DELIMITER $$
CREATE DEFINER=`psam1017`@`localhost` PROCEDURE `insertComment`(
	myArticleIndex INT UNSIGNED, 
    myNickname VARCHAR(10), 
    myContent VARCHAR(10000))
BEGIN
	INSERT INTO comments(articleIndex, nickname, content)
	VALUES(myArticleIndex, myNickname, myContent);

	SET @value = (SELECT MAX(commentIndex) FROM comments);

	UPDATE comments
	SET refIndex = @value
	WHERE commentIndex = @value;

	SELECT * FROM comments WHERE commentIndex = @value;
END $$
DELIMITER ;

# 대댓글 삽입
DELIMITER $$
CREATE DEFINER=`psam1017`@`localhost` PROCEDURE `insertRefComment`(
	myRefIndex INT UNSIGNED,
	myArticleIndex INT UNSIGNED, 
    myNickname VARCHAR(10), 
    myContent VARCHAR(10000))
BEGIN
	INSERT INTO comments(refIndex, articleIndex, nickname, content)
	VALUES(myRefIndex, myArticleIndex, myNickname, myContent);

	SET @value = (SELECT MAX(commentIndex) FROM comments);

	SELECT * FROM comments WHERE commentIndex = @value;
END $$
DELIMITER ;

# 위키 목록 삽입
DELIMITER $$
CREATE DEFINER=`psam1017`@`localhost` PROCEDURE `reviseWikiInfo`(
	inSubject VARCHAR(100), 
    inNickname VARCHAR(10), 
    inIp VARCHAR(128))
BEGIN
	SET @newRVS = (SELECT MAX(rvs) + 1 FROM wikiInfo WHERE subject = inSubject);
    INSERT INTO wikiInfo
    VALUES(inSubject, @newRVS, inNickname, inIp, NOW(), NULL);
    SELECT @newRVS;
END $$
DELIMITER ;

# 알람 일괄 삭제
DELIMITER $$
CREATE DEFINER=`psam1017`@`localhost` PROCEDURE `deleteAlarmListByNickname`(myNickname VARCHAR(10))
BEGIN
	DELETE FROM articleAlarm
	WHERE nickname = myNickname;
	DELETE FROM commentAlarm
	WHERE nickname = myNickname;
END $$
DELIMITER ;

# 비밀번호 변경일자 조회
DELIMITER $$
CREATE DEFINER=`psam1017`@`localhost` PROCEDURE `checkPasswordRenew`(memberEmail VARCHAR(50))
BEGIN
	SET @value = (SELECT passwordAlertAt FROM members WHERE email = memberEmail);
	SELECT TIMESTAMPDIFF(DAY, @value, NOW());
END $$
DELIMITER ;

# 이용약관 동의일자 조회
DELIMITER $$
CREATE DEFINER=`psam1017`@`localhost` PROCEDURE `checkTermExpired`(memberEmail VARCHAR(50))
BEGIN
	SET @value = (SELECT DATE_ADD(agreedTermAt, INTERVAL 1 YEAR) FROM members WHERE email = memberEmail);
	SELECT TIMESTAMPDIFF(DAY, @value, NOW());
END $$
DELIMITER ;





######이벤트######
# check dormant
CREATE EVENT IF NOT EXISTS `updateDormant` 
ON SCHEDULE EVERY '1' DAY STARTS '2023-02-01 03:00:00'
DO
  UPDATE members 
  SET statusValue = 'DOR' 
  WHERE 0 < TIMESTAMPDIFF(YEAR, signAt, NOW()); 

# delete alarm
CREATE EVENT IF NOT EXISTS `deleteOldAlarm` 
ON SCHEDULE EVERY '1' DAY STARTS '2023-02-01 03:10:00'
DO
  DELETE FROM articleAlarm
  WHERE TIMESTAMPDIFF(DAY, articleAlarmAt, NOW()) > 21;
  DELETE FROM commentAlarm
  WHERE TIMESTAMPDIFF(DAY, commentAlarmAt, NOW()) > 21; 
  
# delete withdrawn
CREATE EVENT IF NOT EXISTS `deleteWithdrawn` 
ON SCHEDULE EVERY '1' DAY STARTS '2023-02-01 03:20:00'
DO
  DELETE FROM members
  WHERE TIMESTAMPDIFF(DAY, signAt, NOW()) > 90
  AND statusValue = 'DEL';




/*
-- 사용하지 않는 더미데이터 프로시저
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