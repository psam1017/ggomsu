
# 작성자 : 박성민

CREATE DEFINER=`psam1017`@`localhost` PROCEDURE `dummy_articles`()
BEGIN
	DECLARE i INT DEFAULT 1;    
   		WHILE i <= 124 DO
		INSERT INTO articles(board_value, user_nickname, title, content)
			VALUES('coding', '박성민', CONCAT('제목',i), CONCAT('내용',i));
		SET i = i + 1;
	END WHILE;
END