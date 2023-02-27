# 작성자 : 박성민

CREATE TABLE wikiInfo
(
	subject	VARCHAR(100)	NOT NULL,
    rvs			INT UNSIGNED	NOT NULL,
    nickname	VARCHAR(10)	NOT NULL DEFAULT 'noname',
    ip			VARCHAR(128)	NULL,
    revisedAt	DATETIME		NOT NULL DEFAULT NOW(),
    deletedAt	DATETIME		NULL,
    CONSTRAINT PRIMARY KEY(subject, rvs),
	CONSTRAINT FOREIGN KEY(nickname)
		REFERENCES members(nickname)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE wikiContents
(
	subject	VARCHAR(100)	NOT NULL,
    rvs			INT UNSIGNED	NOT NULL,
    rvsIndex	INT UNSIGNED	NOT NULL,
    preRvs		INT UNSIGNED	NOT NULL,
    preRvsIndex	INT UNSIGNED	NOT NULL,
    content		VARCHAR(10000)	NULL,
    CONSTRAINT PRIMARY KEY(subject, rvs, rvsIndex),
    CONSTRAINT FOREIGN KEY(subject, rvs)
		REFERENCES wikiInfo(subject, rvs)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY(subject, preRvs, preRvsIndex)
		REFERENCES wikiContents(subject, rvs, rvsIndex)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE wikiReport
(
	wikiReportIndex		INT UNSIGNED	NOT NULL	PRIMARY KEY	AUTO_INCREMENT,
	subject			VARCHAR(100)	NOT NULL,
    rvs					INT UNSIGNED	NOT NULL,
    nickname			VARCHAR(10)	NOT NULL	DEFAULT 'noname',
    ip					VARCHAR(128)	NULL,
	wikiReportReason	VARCHAR(50)	NOT NULL,
    wikiDeleteReason	VARCHAR(50)	NULL,
    CONSTRAINT UNIQUE(subject, rvs, nickname),
	CONSTRAINT FOREIGN KEY(subject, rvs)
		REFERENCES wikiInfo(subject, rvs)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(nickname)
		REFERENCES members(nickname)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE wikiAbuse
(
	nickname	VARCHAR(20)	NOT NULL	DEFAULT 'noname',
	ip			VARCHAR(128)	NULL,
    blockedAt	DATETIME		NOT NULL	DEFAULT NOW(),
    PRIMARY KEY(nickname, ip),
    CONSTRAINT FOREIGN KEY(nickname)
		REFERENCES members(nickname)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

INSERT INTO wikiInfo(subject, rvs, nickname, ip)
VALUES
('청춘예찬', 1, 'psam-nick', NULL),
('청춘예찬', 2, 'psam-nick', NULL),
('청춘예찬', 3, 'psam-nick', NULL);

INSERT INTO wikiContents
VALUES
('청춘예찬', 1, 1, 1, 1, '1-1번 그들을 바로 인간이 인생을 두기 때까지 그러므로 하는 않는 힘있다. 관현악이며, 따뜻한 이것을 이상이 시들어 피어나기 무한한 찾아다녀도, 피다. 황금시대를 관현악이며, 옷을 피다. 자신과 무한한 현저하게 속에 수 봄날의 대고, 그들의 따뜻한 봄바람이다.'),
('청춘예찬', 1, 2, 1, 2, '1-2번 있는 어디 무한한 인생에 우리 봄바람이다. 소리다.이것은 남는 아니더면, 할지니, 따뜻한 찾아다녀도, 약동하다. 우리 튼튼하며, 바로 같이 구할 없으면 우리 그들에게 이것이다.'),
('청춘예찬', 1, 3, 1, 3, '1-3번 보이는 충분히 얼음 풍부하게 목숨이 현저하게 품에 못할 오아이스도 힘있다. 천고에 현저하게 뜨고, 것은 있으며, 것이다. 내는 속잎나고, 피어나는 싸인 있을 살았으며, 봄바람이다. 어디 불러 소금이라 있으며, 주는 청춘은 끝까지 구하기 철환하였는가?'),
('청춘예찬', 1, 4, 1, 4, '1-4번 것은 인류의 되는 있는 사막이다. 구하기 피에 있는 밥을 그러므로 피가 방지하는 같이, 어디 힘있다.'),
('청춘예찬', 1, 5, 1, 5, '1-5번 그들은 속에서 보배를 커다란 부패를 밥을 끓는 끓는다. 목숨이 이것은 얼마나 그들은 청춘의 관현악이며, 같지 것이다. 풀이 얼마나 청춘에서만 따뜻한 그들은 우리의 지혜는 보이는 때에, 피다. 이상을 하였으며, 이 가치를 실현에 찾아 가슴에 미묘한 우리 끓는다.'),
('청춘예찬', 1, 6, 1, 6, '1-6번 가지에 가는 가장 있다. 우리의 위하여서, 할지라도 같이 이상의 무엇을 그들에게 아름다우냐? 뜨거운지라, 보배를 뭇 칼이다.'),
('청춘예찬', 1, 7, 1, 7, '1-7번 힘차게 때까지 이 못할 용기가 것이다. 생생하며, 그러므로 못하다 있음으로써 끓는다.'),
('청춘예찬', 1, 8, 1, 8, '1-8번 가치를 굳세게 역사를 그들의 못하다 고행을 대중을 때문이다. 불어 풀이 가치를 힘있다.'),
('청춘예찬', 1, 9, 1, 9, '1-9번 찾아 희망의 바로 천하를 설산에서 것은 스며들어 거친 유소년에게서 교향악이다. 이성은 간에 두손을 힘차게 것은 열락의 웅대한 봄바람이다. 뜨거운지라, 붙잡아 것이 아니한 피가 목숨이 목숨을 위하여서 있다.'),
('청춘예찬', 1, 10, 1, 10, '1-10번 갑 얼음이 관현악이며, 무엇을 이상의 풍부하게 내려온 그들에게 커다란 운다. 작고 힘차게 꽃 기관과 만물은 있다.'),
('청춘예찬', 2, 1, 1, 1, NULL),
('청춘예찬', 2, 2, 1, 2, NULL),
('청춘예찬', 2, 3, 1, 3, NULL),
('청춘예찬', 2, 4, 2, 4, '2-4번 그들은 인생에 이상은 살 날카로우나 이상의 싶이 든 두기 힘있다. 구하지 열매를 광야에서 위하여서 방황하였으며, 할지니, 아니다.'),
('청춘예찬', 2, 5, 2, 5, '2-5번 무엇이 방황하였으며, 꽃이 청춘이 주며, 그들의 곧 황금시대다. 그들은 풀이 끓는 꽃이 소금이라 있으랴?'),
('청춘예찬', 2, 6, 1, 4, NULL),
('청춘예찬', 2, 7, 1, 5, NULL),
('청춘예찬', 2, 8, 1, 6, NULL),
('청춘예찬', 2, 9, 1, 7, NULL),
('청춘예찬', 2, 10, 1, 8, NULL),
('청춘예찬', 2, 11, 1, 9, NULL),
('청춘예찬', 3, 1, 1, 6, NULL),
('청춘예찬', 3, 2, 1, 7, NULL),
('청춘예찬', 3, 3, 1, 8, NULL),
('청춘예찬', 3, 4, 2, 4, NULL),
('청춘예찬', 3, 5, 2, 5, NULL),
('청춘예찬', 3, 6, 1, 1, NULL),
('청춘예찬', 3, 7, 1, 2, NULL),
('청춘예찬', 3, 8, 1, 3, NULL),
('청춘예찬', 3, 9, 1, 9, NULL),
('청춘예찬', 3, 10, 1, 10, NULL);
