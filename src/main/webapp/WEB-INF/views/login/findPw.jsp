<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>PW찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script>
	var tempPw = Math.floor(Math.random() * 99999999)+ 10000000;

	function sendCode(){
		
		$("#findForm").find("[name='person_pass']").val(tempPw);
		$("#findForm").submit();
		
		var email = $("#content").find("[name='person_mail']").val();
		
		$.ajax({
			url: "${pageContext.request.contextPath}/mail/findPwMail.do",
			method: "Post",
			data:{
				email: email,
				CodeNum: tempPw
			},
			dataType:"json",
			success: function(resp){
				console.log(resp);
			},
			error:function(resp){
				console.log(resp.status);
			}
		});
		alert('임시비밀번호를 발송하였습니다.');
	}

// 	function codeEnter(){
// 		var codeNo = $("#content").find("[name='codeNo']").val();
// 		if (codeNo == CodeNum) {
// 			$("#findForm").submit();
			
// 			alert('고객님의 ID는 ${person_id } 입니다.');
// 		} else  {
// 			alert('인증번호를 제대로 입력해주세요.');
// 		}
		
// 	}
	
</script>
</head>
<body>
	<section class="container">
		<article class="half">
			<form id="findForm" action="<c:url value='/login/findPwding.do' />" method="post">
			<h1>PW찾기</h1>
			<div class="tabs">
				<span class="tab "><a href="${pageContext.request.contextPath}/login/findId.do">ID찾기</a></span>
				<span class="tab active"><a href="#findPw">PW찾기</a></span>
				<span class="tab"><a href="${pageContext.request.contextPath}/index.jsp">HomePage</a></span>
			</div>
			<div class="content" id="content">
				<div class="findPw-cont cont">
						<input type="hidden" name="person_pass" >
						ID <input type="text" name="person_id" class="inpt" required="required" ><br>
						메일 <input type="text" name="person_mail" class="inpt" required="required" >
				</div>
			</div>
			<div class="submit-wrap">
				<input type="button" class="submit" value="확인" onclick="sendCode()">
			</div>
			</form>
		</article>
	</section>
</body>
</html>