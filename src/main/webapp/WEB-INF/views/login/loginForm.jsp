<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>login/loginForm.jsp</title>

<script type="text/javascript">
	console.log("1");
	console.log("${message}");
	<c:if test="${not empty message}">
		alert("${message}");
	<c:remove var="message" scope="session" />
	</c:if>
</script>

<link href="https://fonts.googleapis.com/css?family=Black+Han+Sans|Bowlby+One+SC|Do+Hyeon|Dokdo|Gaegu|Nanum+Pen+Script|Poor+Story|Sunflower:300" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
	<section class="container">
		<article class="half">
			<h1>동 행</h1>
			<div class="tabs">
				<span class="tab signin active"><a href="#signin">Sign in</a></span>
				 <span class="tab signup"><a href="${pageContext.request.contextPath}/index.jsp">HomePage</a></span>
			</div>
			<div class="content">
				<div class="signin-cont cont">
					<form action="<c:url value='/login/loginCheck.do' />" method="post">

						<input type="text" name="person_id"  value="${savedId }"
							class="inpt" required="required" placeholder="Your id"><label
							for="email">Your id</label> 
							
						<input type="password" name="person_pass" class="inpt" required="required" placeholder="Your password"><label for="password">Your
							password</label> 
						
						<input type="checkbox" name="idChecked" value="idSaved"
							${not empty savedId ? "checked":"" } /> <label for="remember">Remember
							id</label>
						
						<div class="submit-wrap">
							<input type="submit" value="Sign in" class="submit">
							<input onclick="location.href='<c:url value="/signup/member.do"/>';" type="button" value="Sign up" class="submit"><br>
							<input type="button" value="Naver" class="submit1">
							<input type="button" value="Google" class="submit2">
							<input type="button" value="kakao" class="submit3">
							<a href="${pageContext.request.contextPath}/login/findPw.do" class="more" style="display: inline; padding-right: 30px;">Forgot your password?</a>
							<a href="${pageContext.request.contextPath}/login/findId.do" class="more" style="display: inline;">Forgot your id?</a>
						</div>
						
						
					</form>
				</div>
			</div>
		</article>
		
		<div class="half bg">
			<div class="gallery-item">
				<div class="gallery-item-image">
					<img src="${pageContext.request.contextPath}/images/cycle.png">
				</div>

				<div class="gallery-item-description">
					<h3>Welcome to DongHaeng !</h3>
					<span>DongHaeng is a travel website.</span><br>
					<span>We support a pleasant journey.</span>
				</div>
			</div>
		</div>
	</section>
</body>
</html>


