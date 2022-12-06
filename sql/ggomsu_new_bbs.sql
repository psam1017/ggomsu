
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
	boardValue		VARCHAR(10) NOT NULL,
    memberNickname	VARCHAR(10) NOT NULL,
    title			VARCHAR(100) NOT NULL,
    content			VARCHAR(20000) NOT NULL,
    viewCount		INT UNSIGNED NOT NULL DEFAULT 0,
    writtenAt		DATETIME NOT NULL DEFAULT NOW(),
    deletedAt		DATETIME NULL,
    CONSTRAINT FOREIGN KEY(boardValue)
		REFERENCES boards(value)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(memberNickname)
		REFERENCES memberProfile(nickname)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE attachment
(
	name			VARCHAR(100) NOT NULL PRIMARY KEY,
	articleIndex	INT UNSIGNED NOT NULL,
    CONSTRAINT FOREIGN KEY(articleIndex)
		REFERENCES articles(`index`)
			ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE comments
(
    `index`			INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    refIndex		INT UNSIGNED NULL,
    articleIndex	INT UNSIGNED NOT NULL,
    memberNickname	VARCHAR(10) NOT NULL,
    content			VARCHAR(1000) NOT NULL,
    writtenAt		DATETIME DEFAULT NOW(),
    deletedAt		DATETIME NULL,
	CONSTRAINT FOREIGN KEY(articleIndex)
		REFERENCES articles(`index`)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY(memberNickname)
		REFERENCES memberProfile(nickname)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(refIndex)
		REFERENCES comments(`index`)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE articleLike
(
	articleIndex	INT UNSIGNED NOT NULL,
	memberNickname	VARCHAR(10) NOT NULL,
    CONSTRAINT PRIMARY KEY(articleIndex, memberNickname),
    CONSTRAINT FOREIGN KEY(articleIndex)
		REFERENCES articles(`index`)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY(memberNickname)
		REFERENCES memberProfile(nickname)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE commentLike
(
	commentIndex INT UNSIGNED NOT NULL,
	memberNickname VARCHAR(10) NOT NULL,
    CONSTRAINT PRIMARY KEY(commentIndex, memberNickname),
    CONSTRAINT FOREIGN KEY(commentIndex)
		REFERENCES comments(`index`)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY(memberNickname)
		REFERENCES memberProfile(nickname)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
