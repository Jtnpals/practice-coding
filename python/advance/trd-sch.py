# 파일명과 scheduls, threading  같은 이름으로 하면 적용안됨

import schedule
import time
import threading

'''
def job():
    print("Do Job...!!!")


schedule.every(10).minutes.do(job)
schedule.every().hour.do(job)
schedule.every().day.at("10:30").do(job)
schedule.every(5).to(10).minutes.do(job)
schedule.every().monday.do(job)
schedule.every().wednesday.at("13:15").do(job)
schedule.every().minute.at(":17").do(job)
schedule.every(1).second.do(job)

while True:
    schedule.run_pending()
    time.sleep(1)
'''


# 함수 정의, 함수 내부에 threading 정의
def printhello():
    print("Hello!")

    # threading을 정의한다. 5초마다 반복 수행함.
    threading.Timer(5, printhello).start()


# printhello
printhello()
