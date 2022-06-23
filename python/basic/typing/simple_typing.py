import random
import time
import pygame
import sqlite3
import datetime

conn = sqlite3.connect('./resource/record.db', isolation_level=None)

cursor = conn.cursor()
cursor.execute(
    "CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT, cor_cnt INTEGER, record text, regdate text)")

words = []
n = 1
cor_cnt = 0  # 정답개수

# bs = pygame.mixer.Sound('bad.wav')

with open('./resource/word.txt', 'r') as f:
    for c in f:
        words.append(c.strip())

input("Press Enter Key")
start = time.time()
pygame.mixer.init()
while n <= 5:
    random.shuffle(words)
    q = random.choice(words)

    print()
    print(f"Q {n}")
    print(q)
    x = input()
    print()
    if str(q).strip() == str(x).strip():
        print("Pass")
        gs = pygame.mixer.Sound('./sound/good.wav')
        gs.play()
        cor_cnt += 1
    else:
        bs = pygame.mixer.Sound('./sound/bad.wav')
        bs.play()
        print("Wrong")
    n += 1

end = time.time()
et = end - start
et = format(et, '.3f')

if cor_cnt >= 3:
    print("합격")
else:
    # bs.play()
    print("불합격")

cursor.execute("INSERT INTO records('cor_cnt', 'record', 'regdate') VALUES (?,?,?)", (cor_cnt, et, datetime.datetime.now().strftime('%Y-%m-%d')))

print(et, cor_cnt)

if __name__ == '__main__':
    pass
