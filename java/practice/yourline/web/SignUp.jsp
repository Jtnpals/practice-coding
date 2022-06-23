<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	<%request.setCharacterEncoding("euc-kr"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
<title>Your Line 회원가입</title>
<script type="text/javascript">
	// 입력해야 할 입력란이 뭔가 쓰여져있고 그게 조건을 충족하는지에 대한 여부를 기록하는 배열.
	var check = [false, false, false, false, false];
	// 체크해야할 입력 값들.
	var id = document.getElementById("id");
	var pw = document.getElementById("pw");
	var rePw = document.getElementById("rePw");
	var name = document.getElementById("name");
	var nickname = document.getElementById("nickname");
	var email = document.getElementById("email");
	var phone = document.getElementById("phone");
	
	function regularExCheck(re, e, msg){
        if(re.test(e.value)) return true;
        alert(msg);
        e.value="";
        return false;
	}
	function pwCheck(){
		if(!regularExCheck(/^[\w-]{4,}@[\w-]+(\.\w+){1,3}$/, pw, "알맞은 형식의 비밀번호를 입력하세요.\n( 8자리 이상...특수문자/문자/숫자")){
			return false;
		}
		if(document.userInfo.pw.value!=document.userInfo.rePw.value){
			alert("비밀번호를 동일하게 입력하세요.");
			return false;
		}
		return true;
	}
	function openConfirmid(inputid){
		if(inputid.id.value == ""){
			alert("중복확인에러: 아이디를 입력하세요.");
			return;
		}
		var ctxPath = "<%=request.getContextPath()%>";
		var url = ctxPath + "/ConfirmId.jsp?id=" + inputid.id.value;
		open(url,"confirm","toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=310,height=180");
	}
	//각 입력란의 조건을 검사하는 함수...
	function idConditionCheck(){
		if (document.userInfo.id.value && regularExCheck(/^[a-z][a-z\d]{3,11}$/, id, "잘못된 형식의 ID입니다."))
 		{
			check[0] = true;
		}else{
			check[0] = false;
			document.userInfo.id.value="";
		}
	}
	function nameConditionCheck(){
		if (document.userInfo.name.value && regularExCheck(/^[가-힝]{2,}$/, name, "한글만 입력하세요.(2글자 이상)")) 
		{
			check[1] = true;
		}else{
			check[1] = false;
			document.userInfo.name.value="";
		}
	}
	function nicknameConditionCheck(){
		if (document.userInfo.nickname.value) 
		{
			check[2] = true;
		}else{
			check[2] = false;
			document.userInfo.nickname.value="";
		}
	}
	function emailConditionCheck(){
		if (document.userInfo.email.value && regularExCheck(/^[\w-]{4,}@[\w-]+(\.\w+){1,3}$/, email, "이메일 형식이 잘못되었습니다."))
		{
			check[3] = true;
		}else{
			check[3] = false;
			document.userInfo.email.value="";
		}
	}
	function emailConditionCheck(){
		if (document.userInfo.phone.value && regularExCheck(/^\d{3}\d{3,4}\d{4}$/, phone, "핸드폰 번호를 입력하세요.\n예)01012341234")) 
		{
			check[4] = true;
		}else{
			check[4] = false;
			document.userInfo.phone.value="";
		}
	}
	
	function inputCondtionCheck(){
		for(var i=0; i<check.length; i++) {
		   if(check[i]==false){
			   document.getElementById("joinBtn").disabled = true;
			   break;
		   }else if(i==4 && check[i]==true){//모두 true이면 조건 완료한 상황이니까 가입하기 버튼 활성화시켜주자.
			   joinBtnActibation();
		   }else{
			   continue;//true면 다음꺼 확인
		   }
		}
	}
	
	function joinBtnActibation() {
		document.getElementById("joinBtn").disabled = false;
	}
</script>

</head>
<body>
	<div class="py-5 text-center" style="">
		<div class="container">
			<div class="row">
				<div class="mx-auto col-lg-6 col-10" style="">
					<h1>YOUR LINE</h1>
					<p class="mb-3">회원가입</p>
					<form class="text-left" method="POST" action="SignUp_process2.jsp"" onsubmit="return pwCheck()"
						name="userInfo">
						<div class="form-group">
							<label for="form16">이&nbsp&nbsp&nbsp름</label> <input type="text"
								name="name" class="form-control" id="name" onchange="nameConditionCheck()">
						</div>
						<div class="form-group">
							<label for="form16">닉네임</label> <input type="text"
								name="nickname" class="form-control" id="nickname" onchange="nicknameConditionCheck()">
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label for="form16">아이디</label> 
									<input type="text" name="id" class="form-control" id="id"" onchange="idConditionCheck()"> 
									<input type="button" name="confirm_id" value="중복확인"
										   OnClick="openConfirmid(this.form)"
										   class="pull-right btn btn-outline-success"
										   style="margin-top: 10px;" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12" style="">
								<div class="form-group">
									<label for="form16">핸드폰</label> <input type="text" name="phone"
										class="form-control" id="phone" onchange="phoneConditionCheck()">
								</div>
							</div>
						</div>
						<div class="form-group" style="">
							<label for="form18">이메일</label> <input type="email" name="email"
								class="form-control" id="email" onchange="emailConditionCheck()">
						</div>
						<div class="form-group">
							<label for="form16">성&nbsp&nbsp&nbsp별</label>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<input type="radio" name="sex" checked="checked" value="1"
											aria-label="Checkbox for following text input"><label
											class="form-check-label">남성</label>
									</div>
									<div class="input-group-text">
										<input type="radio" name="sex" value="2"
											aria-label="Checkbox for following text input"><label
											class="form-check-label">여성</label>
									</div>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="form19">비밀번호</label> <input type="password" id="pw"
										name="pw" class="form-control" id="pw">
								</div>
								<div class="form-group col-md-6">
									<label for="form20">비빌번호 확인</label> <input type="password" id="rePw"
										name="rePw" class="form-control" id="rePw">
								</div>
							</div>
							<input type="submit" value="가입하기" id="joinBtn"
								class="pull-right btn btn-outline-success"
								style="margin-top: 10px;" disabled='disabled'>
						</div>
					</form>
				</div>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
			integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
			crossorigin="anonymous" style=""></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
			integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
			crossorigin="anonymous" style=""></script>
	</div>
</body>

</html>