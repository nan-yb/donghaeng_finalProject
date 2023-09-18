<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<link href="https://fonts.googleapis.com/css?family=Black+Han+Sans|Bowlby+One+SC|Do+Hyeon|Dokdo|Gaegu|Nanum+Pen+Script|Poor+Story|Sunflower:300" rel="stylesheet">
<html lang="kr">
   <head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
   <title>회원가입 폼</title>
   
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/signup.css">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>

<script>
	function daumPostCode(){
		new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
//                     document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
//                     document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $('#divForm').find('[name="person_zip"]').val(data.zonecode);
                $('#divForm').find('[name="person_addr1"]').val(addr);
                // 커서를 상세주소 필드로 이동한다.
                $('#divForm').find('[name="person_addr2"]').focus();
            }
        }).open();
    }
	
	function daumPostCode2(){
		new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
//                     document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
//                     document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $('#divForm2').find('[name="person_zip"]').val(data.zonecode);
                $('#divForm2').find('[name="person_addr1"]').val(addr);
                // 커서를 상세주소 필드로 이동한다.
                $('#divForm2').find('[name="person_addr2"]').focus();
            }
        }).open();
    }
	
	var CodeNum = Math.floor(Math.random() * 999999)+ 100000;
	
	function joinCode(){
		var email = $("#divForm").find("[name='person_mail']").val();
		
		if (email == "") {
			alert("메일을 입력해주세요")
			return false;
		} 
		
		
		
		$.ajax({
			url: "${pageContext.request.contextPath}/mail/mailAuth.do",
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
	
	
	function joinCode2(){
		var email = $("#divForm2").find("[name='person_mail']").val();
		
		if (email == "") {
			alert("메일을 입력해주세요")
			return false;
		} 
		
		
		
		$.ajax({
			url: "${pageContext.request.contextPath}/mail/mailAuth.do",
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
	
	function codeConfirm(){
		var code = $("#divForm").find("[name='code']").val();
		
		console.log(CodeNum);
		console.log(code);
		if (code == CodeNum) {
			alert('인증되었습니다.');
			$('#submitAct').attr('disabled', false);
			
		} else {
			alert('인증번호가 틀렸습니다.');
		}
	}
	
	function codeConfirm2(){
		var code = $("#divForm2").find("[name='code']").val();
		
		console.log(CodeNum);
		console.log(code);
		if (code == CodeNum) {
			alert('인증되었습니다.');
			$('#submitAct2').attr('disabled', false);
		} else {
			alert('인증번호가 틀렸습니다.');
		}
	}
	
	$(function(){
		mem_rgno1 = $('[name="mem_rgno1"]');
		mem_rgno2 = $('[name="mem_rgno2"]');
		
		$("#regnoCheckBtn").on('click',function(){
			var test = mem_rgno1.val()+mem_rgno2.val();
			console.log(test);
			console.log(isValidJuminNo(test));
		});
	})
	
	function isValidJuminNo(socialRegNumber) {

		  socialRegNumber=String(socialRegNumber); // 문자로 변환

		  if(!socialRegNumber || socialRegNumber.length!=13) {
		    return '주민등록번호 입력이 잘못되었습니다.';
		  }

		  // 숫자가 아닌 것을 입력한 경우
		  if (isNaN(socialRegNumber)) {
		    return '주민등록번호 입력이 잘못되었습니다.';
		  }

		  var jumin1 = socialRegNumber.substr(0,6)
		  , jumin2 = socialRegNumber.substr(6,7)
		  , genda  = jumin2.substr(0,1)        // 성별 1~4
		  , formalYear = (genda < 3) ? '19' : '20' // 연도 계산 - 1 또는 2: 1900년대, 3 또는 4: 2000년대
		  , yyyy     = formalYear + jumin1.substr(0,2)
		  , MM     = jumin1.substr(2,2)
		  , dd     = jumin1.substr(4,2);

		  // 성별부분이 1 ~ 4 가 아닌 경우
		  if (genda < 1 || genda > 4) {
		    return '주민등록번호 뒷자리를 다시 입력하세요.';
		  }


		  // 날짜 유효성 검사
		  if (isValidDate(yyyy, MM, dd) == false) {
		    return '주민등록번호 앞자리를 다시 입력하세요.';
		  }

		  // Check Digit 검사
		  if (!isSSN(jumin1, jumin2)) {
		    return '입력한 주민등록번호를 검토한 후, 다시 입력하세요.';
		  }
		  return '';
		}

		function isValidDate(yyyy, MM, dd) {
		  var oDate = new Date();
		  oDate.setFullYear(yyyy);
		  oDate.setMonth(MM - 1);
		  oDate.setDate(dd);

		  if (oDate.getFullYear() != yyyy
		      || oDate.getMonth() + 1 != MM
		      || oDate.getDate() != dd) {

		    return false;
		  }

		  return true;
		}


		function isSSN(jumin1, jumin2) {
		  var n = 2;
		  var sum = 0;
		  for (var i = 0; i < jumin1.length; i++){
		    sum += parseInt(jumin1.substr(i, 1)) * n++;
		  }
		  for (var i = 0; i < jumin2.length - 1; i++) {
		    sum += parseInt(jumin2.substr(i, 1)) * n++;
		    if (n == 10){
		      n = 2;
		    }
		  }

		  var checkSum = 11 - sum % 11;

		  if (checkSum == 11){
		    checkSum = 1;
		  }

		  if (checkSum == 10){
		    checkSum = 0;
		  }

		  if (checkSum != parseInt(jumin2.substr(6, 1))){
		    return false;
		  }

		  return true;
		}


		
// 	$('#submitAct').on('click',function(){
// 		console.log('123');
// 		if ($('#submitAct').attr('disabled', true)) {
// 			$('#submitAct').attr('onclick', alert('메일을 인증해주세요'));
// 		}
// 	});

// 	if ($('#submitAct').attr('disabled', true)) {
// 		alert('메일을 인증해주세요');
// 	}
	
	function busiConfirm(){
		
		
		var person_id 		= $('[name="person_id"]');
		var person_pass 	= $('[name="person_pass"]');
		var person_mail 	= $('[name="person_mail"]');
		var code 			= $('[name="code"]');
		var person_tel 		= $('[name="person_tel"]');
		var person_zip	 	= $('[name="person_zip"]');
		var person_addr1 	= $('[name="person_addr1"]');
		var person_addr2 	= $('[name="person_addr2"]');
		
		
		var business_no = $("#divForm2").find("[name='company_business_no']").val();
		// bizID는 숫자만 10자리로 해서 문자열로 넘긴다. 
		
// 		357-19-00494 사업자번호 입력해
		var checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1); 
		var tmpBizID, i, chkSum=0, c2, remander; 
		business_no = business_no.replace(/-/gi, '');
		console.log(business_no);
		for (i=0; i<=7; i++) chkSum += checkID[i] * business_no.charAt(i); 
		c2 = "0" + (checkID[8] * business_no.charAt(8)); 
		c2 = c2.substring(c2.length - 2, c2.length); 
		chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1)); 
		remander = (10 - (chkSum % 10)) % 10 ; 
		if (Math.floor(business_no.charAt(9)) == remander){
			person_id.attr('disabled', false); 
			person_pass.attr('disabled', false); 
			person_mail.attr('disabled', false); 
			code.attr('disabled', false); 
			person_tel.attr('disabled', false); 
			person_zip.attr('disabled', false); 
			person_addr1.attr('disabled', false); 
			person_addr2.attr('disabled', false); 
			
			alert('사업자번호를 확인했습니다.');
			return true ; // OK! 
		}
		alert('사업자번호를 정확히 입력해주세요');
		return false; 
	}
	
	function companyIdCheck(){
		company_id = $('#company_id').val();
		$.ajax({
			url: "${pageContext.request.contextPath}/signup/memIdCheck.do",
			method: "post",
			data:{
				mem_id: company_id
			},
			dataType:"json",
			success: function(resp){
				if(resp == true){
					alert("인증에 성공하였습니다.");
				}else{
					alert("중복된 아이디입니다. 다시 입력해주세요");
				}
			},
			error:function(resp){
				console.log(resp.status);
			}
		});
	}
	function memIdCheck(){
		member_id = $('#member_id').val();
		$.ajax({
			url: "${pageContext.request.contextPath}/signup/memIdCheck.do",
			method: "post",
			data:{
				mem_id: member_id
			},
			dataType:"json",
			success: function(resp){
				if(resp == true){
					alert("인증에 성공하였습니다.");
				}else{
					alert("중복된 아이디입니다. 다시 입력해주세요");
				}
			},
			error:function(resp){
				console.log(resp.status);
			}
		});
	}
	
</script>

</head>
<body>
<form></form>

<section class="container">
          <article class="half">
                 <h1>동 행</h1>
                 <div class="tabs">
                        <span class="tab signin active"><a href="#signin">Member</a></span>
                        <span class="tab signup"><a href="#signup">Corporation</a></span>
                 </div>
                 
                 <div class="content">
                    <div class="signin-cont cont" id="divForm">
                       		
                       <form action="<c:url value ="/signup/member.do" />" method="post" >
                            <input type="text" name="person_name"  class="inpt" required="required" placeholder="이름">
                            
                            <input type="text" class="inpt"  name="mem_rgno1" id="unum1" size="12" maxlength="6" placeholder=""></input>
           					<input type="password" class="inpt" name="mem_rgno2" id="unum2" size="12" maxlength="7" placeholder=""></input>
                            <input type="button" id="regnoCheckBtn" value="실명 인증"  ">
                       
                            <input type="text" id="member_id"  name="person_id"  class="inpt" required="required" placeholder="아이디">
                            <input type="button" value="아이디 인증" onclick="memIdCheck()">
                           
                            <input type="password" name="person_pass"  class="inpt" required="required" placeholder="비밀번호">
                            <input type="email" name="person_mail"  class="inpt" required="required" placeholder="이메일">
                            <input type="button" value="메일인증" onclick="joinCode()">
                            <input type="text" name="code" class="inpt" required="required" placeholder="인증번호입력">
                            <input type="button" value="인증확인" onclick="codeConfirm()">
                            <input type="text" name="person_tel"  class="inpt" required="required" placeholder="핸드폰번호">
                            <input type="button" value="우편번호 찾기" onclick="daumPostCode()">
                            <input type="text" name="person_zip"  class="inpt" required="required" placeholder="우편번호">
                            <input type="text" name="person_addr1"  class="inpt" required="required" placeholder="주소">
                            <input type="text" name="person_addr2"  class="inpt" required="required" placeholder="상세주소">
                            
		                    <div class="submit-wrap">
		                      <input type="submit" value="Sign up" class="submit" id="submitAct" disabled="disabled">
		                    </div>
                      </form>
                </div>
                    

                <div class="signup-cont cont" id="divForm2">
               		<form action="<c:url value ="/signup/member.do" />" method="post">
                            <input type="text" name="person_name"  class="inpt" required="required" placeholder="이름">
                            <input type="text" name="company_business_no"  class="inpt" required="required" placeholder="사업자번호 ex : xxx-xx-xxxxx" >
                            <input type="button" value="사업자확인"  onclick="busiConfirm()"> 
                              
                            <input type="text" id="company_id" name="person_id"  class="inpt" required="required" placeholder="아이디" disabled="disabled">
                            <input type="button" value="아이디 인증" onclick="companyIdCheck()">
                            <input type="password" name="person_pass"  class="inpt" required="required" placeholder="비밀번호" disabled="disabled">
                            <input type="email" name="person_mail"  class="inpt" required="required" placeholder="이메일" disabled="disabled">
                            <input type="button" value="메일인증" onclick="joinCode2()">
                            <input type="text" name="code" class="inpt" required="required" placeholder="인증번호입력" disabled="disabled">
                            <input type="button" value="인증확인" onclick="codeConfirm2()">
                            <input type="text" name="person_tel"  class="inpt" required="required" placeholder="핸드폰번호" disabled="disabled">
                            <input type="button" value="우편번호 찾기" onclick="daumPostCode2()">
                            <input type="text" name="person_zip"  class="inpt" required="required" placeholder="우편번호" disabled="disabled">
                            <input type="text" name="person_addr1"  class="inpt" required="required" placeholder="주소" disabled="disabled">
                            <input type="text" name="person_addr2"  class="inpt" required="required" placeholder="상세주소" disabled="disabled">
		                   
		                    <div class="submit-wrap">
		                      <input type="submit" value="Sign up" disabled="disabled" class="submit" id="submitAct2">
		                    </div>
                      </form>
            	</div>
             </div>
          </article>
          
          
          <div class="half bg"></div>
   </section>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> 
<script type="text/javascript">
$('.tabs .tab').click(function(){
    if ($(this).hasClass('signin')) {
        $('.tabs .tab').removeClass('active');
        $(this).addClass('active');
        $('.cont').hide();
        $('.signin-cont').show();
    } 
    if ($(this).hasClass('signup')) {
        $('.tabs .tab').removeClass('active');
        $(this).addClass('active');
        $('.cont').hide();
        $('.signup-cont').show();
    }
});
$('.container .bg').mousemove(function(e){
    var amountMovedX = (e.pageX * -1 / 30);
    var amountMovedY = (e.pageY * -1 / 9);
    $(this).css('background-position', amountMovedX + 'px ' + amountMovedY + 'px');
});
</script>
</body>
</html>