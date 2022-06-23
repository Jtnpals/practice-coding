var Person = {}

Person['age'] = 18;
Person['name'] = 'tom';

var oper = function(a, b){
    return a + b;
};

Person['add'] = oper;

console.log(`나이 ${Person.age} 이름 ${Person['name']} 10 + 10 = ${Person.add(10, 10)}`);