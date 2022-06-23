var fs = require('fs');

//파일에 데이터를 씁니다.
fs.writeFile('./output.txt', 'Hello Word', function(err){
    if(err){
        console.log('Error : ' + err);
    }
    console.log('output.txt에 쓰기작업 완료');
});