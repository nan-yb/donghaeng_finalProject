<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>ID찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script>
	var CodeNum = Math.floor(Math.random() * 999999)+ 100000;

	function sendCode(){
		var email = $("#content").find("[name='person_mail']").val();
		
		$.ajax({
			url: "${pageContext.request.contextPath}/mail/findMail.do",
			method: "Post",
			data:{
				email: email,
				CodeNum: CodeNum
			},
			dataType:"json",
			success: function(resp){
				console.log(resp);
			},
			error:function(resp){
				console.log(resp.status);
			}
		});
		alert('인증번호를 발송하였습니다.');
	}

	function codeEnter(){
		var codeNo = $("#content").find("[name='codeNo']").val();
		if (codeNo == CodeNum) {
			$("#findForm").submit();
			
			alert('고객님의 ID는 ${person_id } 입니다.');
		} else  {
			alert('인증번호를 제대로 입력해주세요.');
		}
		
	}
	
</script>
</head>
<body>
	<section class="container">
		<article class="half">
			
			<form id="findForm" action="<c:url value='/login/findIding.do' />" method="post">
			<h1>ID찾기</h1>
			<div class="tabs">
				<span class="tab active"><a href="#findId">ID찾기</a></span>
				<span class="tab"><a href="${pageContext.request.contextPath}/login/findPw.do">PW찾기</a></span>
				<span class="tab"><a href="${pageContext.request.contextPath}/index.jsp">HomePage</a></span>
			</div>
			<div class="content" id="content">
				<div class="findId-cont cont">
						이름 <input type="text" name="person_name" class="inpt" required="required" ><br>
						메일 <input type="text" name="person_mail" class="inpt" required="required" >
						<input type="button" name="certify" value="인증번호 받기" onclick="sendCode()"><br>
						인증번호<input type="text" name="codeNo" class="inpt" required="required">
				</div>
			</div>
			<div class="submit-wrap">
				<input type="button" class="submit" value="확인" onclick="codeEnter()">
			</div>
			</form>
		</article>
	</section>
</body>
</html>