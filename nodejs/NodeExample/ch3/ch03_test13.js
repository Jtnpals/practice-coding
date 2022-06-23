var Users = [{name:'tom', age:20}, {name:'mike', age:17}, {name:'jake', age:13}];
console.log(`delete 호출 전 배열 요소의 수 ${Users.length}`);

delete Users[1];
console.log('delete 키워드 호출 후');
console.dir(Users);
console.log(Users);
//delete는 배열 안에 요소만 삭제하고 공간은 남겨둠
console.log(`delete 호출 전 배열 요소의 수 ${Users.length}`);

Users.splice(1, 0, {name: 'fin', age: 14});
console.dir(Users);

Users.splice(2, 1);
console.dir(Users);
