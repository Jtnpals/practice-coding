var Person = {}

Person['age'] = 18;
Person['name'] = 'tom';
Person.mobile = '010-000-1234';

//Person['name'] Person.name 두가지 방법
console.log(`나이 ${Person.age} 이름 ${Person['name']} 전화 ${Person.mobile}`);