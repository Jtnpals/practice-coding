import sqlite3

conn = sqlite3.connect('./resource/database.db')
# 커서 바인당
c = conn.cursor()

c.execute("SELECT * FROM users")

# 커서위치가 변경
# 1개 row 선택
# print('one-> /n', c.fetchone())
# 지정 로우 선택
# print('two -> \n', c.fetchmany(size=2))

# 전체 row 선택
# print('all -/, \n', c.fetchall())

print()

# rows = c.fetchall()
# for row in rows:
#     print(row)

# for row in c.fetchall():
#     print(row)

# for row in c.execute('SELECT * FROM users ORDER BY id desc'):
#     print(row)

param1 = (3,)
c.execute('SELECT * FROM users WHERE id=?', param1)
print('param1', c.fetchone())
print('param1', c.fetchall())  # 데이터 없음

param2 = 4
c.execute('SELECT * FROM users WHERE id="%s"' % param2)
print('param2', c.fetchone())
print('param2', c.fetchall())  # 데이터 없음

c.execute('SELECT * FROM users WHERE id=:Id', {"Id": 4})
print('param3', c.fetchone())
print('param3', c.fetchall())  # 데이터 없음

param4 = (3, 4)
c.execute("SELECT * FROM users WHERE id IN(?,?)", param4)
print('param4', c.fetchall())

c.execute("SELECT * FROM users WHERE id IN('%d','%d')" % (3, 4))
print('param5', c.fetchall())

c.execute("SELECT * FROM users WHERE id=:id1 OR id=:id2", {"id1": 2, "id2": 3})
print('param6', c.fetchall())


# dump 출력
with conn:
    with open('./resource/dump.sql', 'w') as f:
        for line in conn.iterdump():
            f.write('%s\n' % line)
        print('complete')
