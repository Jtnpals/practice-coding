<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<link rel="stylesheet" type="text/css" href="./css/style.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script type="text/javascript">
	function checkValue() {
		inputForm = eval("document.loginInfo");
		if (!inputForm.id.value) {
			alert("아이디를 입력하세요");
			inputForm.id.focus();
			return false;
		}
		if (!inputForm.pw.value) {
			alert("비밀번호를 입력하세요");
			inputForm.password.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<div id="login-page" class="row">
		<div class="col s12 z-depth-4 card-panel">
			<form class="login-form" method="POST" action="Login_process2.jsp"
				name="loginInfo" onsubmit="return checkValue()">
				<div class="row">
					<div class="input-field col s12 center">
						<!-- <img src="images/login-logo.png" alt="" class="circle responsive-img valign profile-image-login"/> -->
						<p class="center login-form-text">LOGIN</p>
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<!-- <i class="mdi-social-person-outline prefix"></i> -->
						<i class="material-icons prefix">account_circle</i> <input
							id="username" name="id" type="text"
							style="background-image: url(&quot;data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR4nGP6zwAAAgcBApocMXEAAAAASUVORK5CYII=&quot;); cursor: auto;" />
						<label for="username" data-error="wrong" class="center-align"
							data-success="right">Username</label>
					</div>
				</div>

				<div class="row margin">
					<div class="input-field col s12">
						<!-- <i class="mdi-action-lock-outline prefix"></i> -->
						<i class="material-icons prefix">vpn_key</i> <input id="password"
							name="pw" type="password"
							style="background-image: url(&quot;data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR4nGP6zwAAAgcBApocMXEAAAAASUVORK5CYII=&quot;);" />
						<label for="password">Password</label>
					</div>
				</div>

				<!-- <div class="row">          
              <div class="input-field col s12 login-text">          		 
                  <input type="checkbox" id="test6" checked="checked" name="" />
                  <label for="test6" class="pointer-events">Remember me</label>
              </div>
            </div> -->

				<div class="row">
					<div class="input-field col s12">
						<button type="submit" class="btn waves-effect waves-light col s12">Login</button>
					</div>

					<!--  <div class="input-field col s12">
                <a href="index.html" class="btn waves-effect waves-light col s12 light-blue darken-4">FACEBOOK Login</a>
              </div>
            </div>-->

					<div class="row">
						<div class="input-field col s6 m6 l6">
							<p class="margin medium-small">
								<a href="SignUp.jsp">Register Now!</a>
							</p>
							<!--</div>
              <div class="input-field col s6 m6 l6">
                  <p class="margin right-align medium-small"><a href="page-forgot-password.html">Forgot password ?</a></p>
              </div>-->
						</div>
			</form>

		</div>
	</div>
	<%
		// 아이디, 비밀번호가 틀릴경우 화면에 메시지 표시
		// Login_process2.jsp에서 로그인 처리 결과에 따른 메시지를 보낸다.
		String msg = request.getParameter("msg");
		if (msg != null && msg.equals("-1")) {
			%>
			<script>
				alert("아이디를 확인해 주세요.");
			</script>
		<%
		} else if (msg != null && msg.equals("0")) {
			%>
			<script>
				alert("비밀번호를 확인해 주세요.");
			</script>
		<%
		}
	%>
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.min.js"></script>
	<script type="javascript" src="./js/script.js"></script>
</body>
</html>