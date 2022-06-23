var Users = [{name:'tom', age:20}, {name:'mike', age:17}];

Users.push({name:'jake', age:22});

console.log(`사용자 수 ${Users.length}`);
for(var i = 0; i < Users.length; i++){
    console.log("배열 요소 #" + i + ' : %s', Users[i].name);
}

//forEach 구문 사용 item, index 순서 중요
Users.forEach(function(item, index){
    console.log(`배열 요소 #${index} 이름 ${item.name}`);
});
