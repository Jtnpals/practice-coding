# raw string
a = 'asd2\n'
b = r'asd2\n'

print(a)
print(b)

import re

m = re.search(r'abc', '123abcdf')
m.start()
m.end()
m.group()
# 패턴이 없는경우 None 반환

n = re.search(r'\d\d', '112asdwq119') # 이어지는 두수가 있는기?
re.search(r'\d\d\w', 'dwq123f') # 이어지는 숫자 두개와 문자하나
re.search(r'..\w\w', '!@#!@123sad')

re.search(r'[cbm]at', 'cat') # 대괄호 안은 or조건
re.search((r'[0-4]haha', '7haha'))
re.search(r'[abc.^]', 'davv./////6^') # ^가 대괄호 맨 처음으로오면 not의 의미
re.search(r'[^abc]aron', 'aaron') # 검출안됨
re.search(r'[^abc]aron', '@aron') # 검출됨



