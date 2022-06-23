var Users = [{name:'tom', age:20}, {name:'mike', age:17}];
console.log(`push() 호출 전 배열 요소의 수 ${Users.length}`);

Users.push({name:'jake', age:13});
console.log(`push() 호출 후 배열 요소의 수 ${Users.length}`);

Users.pop();
console.log(`pop() 호출 후 배열 요소의 수 ${Users.length}`);