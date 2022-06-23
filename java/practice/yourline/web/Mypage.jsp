<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>

<head>
<style>
</style>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
</head>

<body
	style="background-color: #e9e9e9; background-position: top left; background-size: 100%;">
	<nav class="navbar navbar-expand-lg navbar-light">
		<div class="container">
			<a class="navbar-brand text-primary" href="#"> <i
				class="fa d-inline fa-lg fa-circle-o"></i> <b> BRAND</b>
			</a>
			<button class="navbar-toggler navbar-toggler-right border-0"
				type="button" data-toggle="collapse" data-target="#navbar5">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbar5">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="#">홈</a></li>
					<li class="nav-item"><a class="nav-link" href="#">결제</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">마이페이지</a></li>
				</ul>
				<a class="btn btn-outline-primary navbar-btn ml-md-2">로그인</a>
			</div>
		</div>
	</nav>

	<div class="content mx-auto"
		style="font-weight: bold; text-align: center; background-color: #ffffff; background-color: rgba(255, 255, 255, 0.5); width: 80%;">
		<div class="profileimg">
			<img
				class="img-fluid d-block pi-draggable mx-auto pt-4 rounded-circle"
				src="https://static.pingendo.com/img-placeholder-3.svg"
				width="200px" height="auto">
			<p class="text-center" style="">정 창훈</p>
		</div>
		<div class="py-5">
			<div class="container">
				<div class="row text-center" style="">
					<div class="col-md-6">
						<div class="table-responsive">
							<table class="table" name="userInfo">
								<tbody>
									<tr>
										<td>이름</td>
										<td></td>
									</tr>
									<tr>
										<td>닉네임</td>
										<td></td>
									</tr>
									<tr>
										<td>이메일</td>
										<td></td>
									</tr>
									<tr>
										<td>연락처</td>
										<td></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>

</html>