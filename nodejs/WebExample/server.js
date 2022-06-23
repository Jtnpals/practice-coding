var http = require('http');
var fs = require('fs');

function send404Message(response) {
    response.writeHead(404, {
        "Content-Type": "text/plain"
    });
    response.write("404 ERROR... ");
    response.end();
}
// 200 Okay : 정상적인 요청
function onRequest(request, response) {
    if (request.method == 'GET' && request.url == '/') {
        response.writeHead(200, {"Content-Type": "text/html"});
        // 웹페이지 출력 
        fs.createReadStream("./index.html").pipe(response);
        // 같은 디렉토리에 있는 index.html를 response 함
    } else {
        // file이 존재 하지않을때, 
        send404Message(response);
    }
}
http.createServer(onRequest).listen(8000);
console.log('server created');