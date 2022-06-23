var path = require('path');

//디렉토리 이름 합치기
var directories = ["users", "mike", "docs"];
var docsDirectories = directories.join(path.sep);
console.log('문서 디렉토리 : %s', docsDirectories);

//디렉토리 이름과 파일 이름 합치기
var curPath = path.join('Users/mike', 'notepad.exe');
console.log('파일 패스 : %s', curPath);

//패스에서 디렉토리, 파일이름, 확장자 구별하기
// var filename = "C:\\User\\mike\\notepad.exe"; 윈도우에서는 구별자 \\ 맥에서는 //사용
var filename = "C://User//mike//notepad.exe";
var dirname = path.dirname(filename);
var basename = path.basename(filename);
var extname = path.extname(filename);

console.log('디렉토리 : %s, 파일이름 : %s, 확장자 : %s', dirname, basename, extname);