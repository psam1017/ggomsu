
# 작성자 : 박성민

CREATE TABLE boards
(
	value VARCHAR(10) NOT NULL PRIMARY KEY,
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
    `index`			INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	board_value		VARCHAR(10) NOT NULL,
    user_nickname	VARCHAR(10) NOT NULL,
    title			VARCHAR(100) NOT NULL,
    content			VARCHAR(20000) NOT NULL,
    view_count		INT UNSIGNED NOT NULL DEFAULT 0,
    written_at		DATETIME NOT NULL DEFAULT NOW(),
    deleted_at		DATETIME NULL,
    CONSTRAINT FOREIGN KEY(board_value)
		REFERENCES boards(value)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(user_nickname)
		REFERENCES user_profile(nickname)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE attachment
(
	file_name VARCHAR(100) NOT NULL PRIMARY KEY,
	article_index INT UNSIGNED NOT NULL,
	file_name_original VARCHAR(100) NOT NULL,
    CONSTRAINT FOREIGN KEY(article_index)
		REFERENCES articles(`index`)
			ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE comments
(
    `index`			INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    re_index		INT UNSIGNED NULL,
    article_index	INT UNSIGNED NOT NULL,
    user_nickname	VARCHAR(10) NOT NULL,
    content			VARCHAR(1000) NOT NULL,
    written_at		DATETIME DEFAULT NOW(),
    deleted_at		DATETIME NULL,
	CONSTRAINT FOREIGN KEY(article_index)
		REFERENCES articles(`index`)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY(user_nickname)
		REFERENCES user_profile(nickname)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(re_index)
		REFERENCES comments(`index`)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE article_like
(
	article_index INT UNSIGNED NOT NULL,
	user_nickname VARCHAR(10) NOT NULL,
    CONSTRAINT PRIMARY KEY(article_index, user_nickname),
    CONSTRAINT FOREIGN KEY(article_index)
		REFERENCES articles(`index`)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY(user_nickname)
		REFERENCES user_profile(nickname)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE comment_like
(
	comment_index INT UNSIGNED NOT NULL,
	user_nickname VARCHAR(10) NOT NULL,
    CONSTRAINT PRIMARY KEY(comment_index, user_nickname),
    CONSTRAINT FOREIGN KEY(comment_index)
		REFERENCES comments(`index`)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY(user_nickname)
		REFERENCES user_profile(nickname)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
