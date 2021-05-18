BEGIN TRANSACTION;
CREATE TABLE users(id INTEGER PRIMARY KEY, username text, email text, phone text, website text, regdate text);
INSERT INTO "users" VALUES(1,'kim','kim@naver.com','010-0000-0000','kim.com','2021-05-18 16:32:21');
INSERT INTO "users" VALUES(2,'park','park@naver.com','010-0001-1111','park.com','2021-05-18 16:35:54');
INSERT INTO "users" VALUES(3,'lee','lee@naver.com','010-0002-2222','lee.com','2021-05-18 16:40:08');
INSERT INTO "users" VALUES(4,'cho','cho@naver.com','010-0003-3322','cho.com','2021-05-18 16:40:08');
COMMIT;
