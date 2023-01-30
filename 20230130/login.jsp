<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>LoginTest</title>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<style type="text/css">
	html, div, body, h3 {
		margin: 0;
		padding: 0;
	}
	h3 {
		display: inline-block;
		padding: 0.6em;
	}
</style>
</head>
<body>
	<div style="background-color: #15a191; width: 100%; height: 50px; text-align: center; color: white;">
		<h3>네이버 로그인</h3>
	</div>
	<br>
	<!-- 네이버 로그인 화면으로 이동 시키는 URL -->
	<!-- 네이버 로그인 화면에서 ID, PW를 올바르게 입력하면 callback 메소드 실행 요청 -->
	<div id="naver_id_login" style="text-align: center">
		<a href="${url}">
			<img width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png" />
		</a>
	</div>
	<br>
</body>
</html>