var Users = [{name:'tom', age:20}, {name:'mike', age:17}];

var add = function(a, b){
    return a + b;
}

Users.push(add);

console.log(`배열 요소의 수 ${Users.length}`);
console.log(`세번째 요소 함수 실행 ${Users[2](10, 10)}`);