print('hello')
print('''hello
world''')
print('2021', '04', sep='-', end='')
print('-21')

print('{} and {}'.format('a','b'))
print('{0} , {1} , {0}'.format('a','b'))
print('{a} , {b} , {b}'.format(a='a', b='b'))
print('{a: 5d} , {b: 3.2f}'.format(a=132, b=321.1234))

print('%s : stirng , %f : float' % ('foo', 3.14))
print('%5s : stirng , %1.1f : float' % ('foo', 3.14))

foo = 'foo'
bar = 'bar'
print(f'{foo} {bar}')

s1 = 'left'
print(f'|{s1:<10}|')
s2 = 'mid'
print(f'|{s2:^10}|')
s3 = 'right'
print(f'|{s3:>10}|')
# |left      |
# |   mid    |
# |     right|

num = 10
print(f'my age {{{num}}}, {{num}}')
# my age {10}, {num}

d = {'name': 'BlockDMask', 'gender': 'man', 'age': 100}
result = f'my name {d["name"]}, gender {d["gender"]}, age {d["age"]}'
print(result)

n = [100, 200, 300]
print(f'list : {n[0]}, {n[1]}, {n[2]}')
for v in n:
    print(f'list with for : {v}')
