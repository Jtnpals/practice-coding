var Users = [{name:'tom', age:20}, {name:'mike', age:17}];
console.log(`unshift() 호출 전 배열 요소의 수 ${Users.length}`);

Users.unshift({name:'jake', age:13});
console.log(`unshift() 호출 후 배열 요소의 수 ${Users.length}`);

Users.shift();
console.log(`shift() 호출 후 배열 요소의 수 ${Users.length}`);