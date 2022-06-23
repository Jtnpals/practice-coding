//외장모듈 설치해줘야함 npm install nconf
var nconf = require('nconf');
nconf.env();

console.log('USER 환경변수의 값 : %s', nconf.get('USER'));