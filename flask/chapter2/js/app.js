let num = 20;
console.log(num);

num = 2;
console.log(num);

let num2;

console.log(num2);

num2 = num;

console.log(num2);
console.log(num, num2);

let a = 7;
let b = 2;

a + b; // 9
a - b; // 5
a * b; // 14
a / b; // 3.5
a % b; // 1 (나머지)

a + 3 * b; // 13 (여러 연산을 한 줄에 할 경우 사칙연산의 순서를 따른다.)

let word1 = "jungle";
let word2 = "coding";

let mac = "McDonald's";
let sentence = 'He said, "Hello!"';

let myname = "jungle";

myname.toUpperCase(); // JUNGLE

let myemail = "test@gmail.com";

let result = myemail.split("@"); // ['test','gmail.com'] (뒤에 배울 '리스트'라는 자료형이다)

result[0]; // test (리스트의 첫번째 요소)
result[1]; // gmail.com (리스트의 두 번째 요소

let result2 = result[1].split("."); // ['gmail','com']

result2[0]; // gmail -> 우리가 알고 싶었던 것
result2[1]; // com

// 한 줄로 쓸 수도 있다.
myemail.split("@")[1].split(".")[0];

console.log(`안녕하세요 ${myemail}, ${result2}`);
