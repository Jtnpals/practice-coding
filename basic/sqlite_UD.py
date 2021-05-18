import sqlite3

conn = sqlite3.connect('./resource/database.db')

# cursor
c = conn.cursor()

# update
# c.execute("UPDATE users SET username = ? WHERE id = ?", ('niceman', 2))

# c.execute("UPDATE users SET username = ? WHERE id = ?", ('niceman', 2))

# gm = 'goodman'
# num = 3
# cmd = f"UPDATE users SET username = '{gm}' WHERE id = {num}"
# print("UPDATE users SET username = %s WHERE id = %d" % ('niceman', 2))
# c.execute(cmd)

for user in c.execute("SELECT * FROM users"):
    print(user)

# c.execute("DELETE FROM users WHERE id = ?", (2,))
# conn.commit()
#
# for user in c.execute("SELECT * FROM users"):
#     print(user)

# 테이블 전체 삭재
print(conn.execute("DELETE FROM users").rowcount, "row")
conn.commit()

conn.close()
