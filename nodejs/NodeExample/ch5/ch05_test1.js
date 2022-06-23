var http = require('http');

//웹 서버 객체를 만듭니다.
var server = http.createServer();

//웹 서버를 시작하여 192.168.0.5 IP와 3000번 포트에서 대기하도록 설정합니다.
//window -> ipconfig , mac -> ifconfig 로 IP주소 확인해서 알맞게 설정하기
var host = '192.168.0.5';
var port = 3000;
server.listen(port, function(){
    console.log(`웹 서버가 시작되었습니다. ${port} ${host}`);
});