import sqlite3
import datetime

now = datetime.datetime.now()
print(now)

nowDatetime = now.strftime('%Y-%m-%d %H:%M:%S')
print(nowDatetime)
print('sqlite version :', sqlite3.version)
print('sqlite engine version: ', sqlite3.sqlite_version)

# DB생성 Auto commit(rollback)
conn = sqlite3.connect('./resource/database.db', isolation_level=None)

# cursor
c = conn.cursor()
print('cursor type : ', type(c))

# 테이블 생성
c.execute(
    "CREATE TABLE IF NOT EXISTS users(id INTEGER PRIMARY KEY, username text, email text, phone text, website text, regdate text)")

# 데이터 삽입
# c.execute("INSERT INTO users VALUES(1, 'kim', 'kim@naver.com', '010-0000-0000', 'kim.com', ? )", (nowDatetime,))
# c.execute("INSERT INTO users(id, username, email, phone, website, regdate) VALUES (?,?,?,?,?,?)",\
#           (2, 'park', 'park@naver.com', '010-0001-1111', 'park.com', nowDatetime))

# many 삽입(튜플, 리스트)
userList = (
    (3, 'lee', 'lee@naver.com', '010-0002-2222', 'lee.com', nowDatetime),
    (4, 'cho', 'cho@naver.com', '010-0003-3322', 'cho.com', nowDatetime),
)

# c.executemany("INSERT INTO users(id, username, email, phone, website, regdate) VALUES (?,?,?,?,?,?)", userList)

# 테이블 삭제
# conn.execute("DELETE FROM users")
# print("user db delete :", conn.execute("DELETE FROM users").rowcount) # 지워진 행수 반환

# isolation_level = None 일경우 오토커밋  None이 아닐경우 conn.commit() 작성해줘야함
# conn.rollback()

conn.close()
