process.on('tick', function(count){
    console.log('tick 이벤트 발생함 : %s', count);
});

setTimeout(function(){
    console.log('2초후에 시스템 종료 시도함');

    process.emit('tick', '2');
}, 2000);
