CREATE DEFINER=`psam1017`@`localhost` PROCEDURE `dummyArticles`()
BEGIN
	DECLARE i INT DEFAULT 1;    
   		WHILE i <= 124 DO
		INSERT INTO articles(boardValue, memberNickname, title, content)
			VALUES('coding', '박성민', CONCAT('제목',i), CONCAT('내용',i));
		SET i = i + 1;
	END WHILE;
END