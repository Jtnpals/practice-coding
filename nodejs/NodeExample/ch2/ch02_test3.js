console.dir(process.env);

console.log('os 환경변수의 값 : ' + process.env['OS']);

//mac의 경우 OS값이 기본 환경변수로 지정되어있지 않아서 export OS=mac 지정해줘야함
//환경변수 삭제는 unset OS