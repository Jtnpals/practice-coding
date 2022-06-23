<%@page contentType="text/html;charset=utf-8" pageEncoding="euc-kr"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <style>
    @import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
    .hf{ font-family:"Nanum Gothic";}
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">		
    <script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script>
    window.onload = function(){
        $(document).ready(function(){
            $('#modal_ADD').click(function(e){
            	var email = $('#email').val();
            	var passwd = $('#passwd').val();
            	if( email == 'apple' && passwd == '1234' ){
					$('#alert').text('성공했습니다');
            	} 
            });
            
            $('#modal_OK').click( function(e){
                $('#abcd').modal('hide');
            });            
        });
    };
    </script>		
</head>
<body>
<div class="container">
    <button data-toggle="modal" data-target="#abcd"
        class="btn btn-default">Click</button>
        
    <div id="abcd" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
					<div id="alert" class="hf alert alert-dark" role="alert">
  						입력해 주시와요 
					</div>                
					<div class="form-group">
						<label class="hf" for="email">이메일</label>
						<input id="email" class="hf form-control" type="text"/>
					</div>
					<div class="form-group">
						<label class="hf" for="passwd">암호</label>
						<input id="passwd" class="hf form-control" type="password"/>
					</div>
                </div>
                <div class="modal-footer">
                    <button id="modal_ADD"
                        class="btn btn-default">Add</button>
                    <button id="modal_OK" 
                    	class="btn btn-default">Close</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>