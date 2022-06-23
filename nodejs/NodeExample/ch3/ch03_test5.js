var Person = {}

Person['age'] = 18;
Person['name'] = 'tom';
Person.add = function(a, b){
    return a + b;
};

//Person['name'] Person.name 두가지 방법
console.log(`나이 ${Person.age} 이름 ${Person['name']} 10 + 10 = ${Person.add(10, 10)}`);