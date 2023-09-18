<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<style type="text/css">
	#imageArea{
		float: right;
		width: 100px;
		height: 150px;
		border:1px solid black;
	}
	#imageArea>img{
		width: 100%;
		height: 100%;
	}
</style>
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
//                 document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
//                 document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            $('#divForm').find('[name="person_zip"]').val(data.zonecode);
            $('#divForm').find('[name="person_addr1"]').val(addr);
            // 커서를 상세주소 필드로 이동한다.
            $('#divForm').find('[name="person_addr2"]').focus();
        }
    }).open();
}

$(function(){
	var imageArea = $("#imageArea");
	
	$('[name="person_img"]').on("change", function(){
		var files = $(this).prop("files");
		if(!files) return;
		for(var idx=0; idx<files.length; idx++){
	//			console.log(files[idx]);
			var reader = new FileReader();
			reader.onloadend=function(event){
	//				console.log(event.target.result);
				var imgTag = new Image();
				imgTag.src = event.target.result;
				imageArea.html(imgTag);
			}
			reader.readAsDataURL(files[idx]);
		}
	});
	
});
	



	

<c:if test="${not empty message}">
alert("${message}");
</c:if>
</script>
<c:forEach items="${person.corList }" var="cor">
<c:if test="${authMember.person_id eq person.person_id }">
<section class="s-content">
	<div class="row masonry-wrap">
		<H3>PROFILE</H3>
		<div class = "content" id = "divForm">
			<form name = "updateForm" action="<c:url value ="/common/mypage/corUpdateInfo.do" />" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<th>ID</th>
							<td><input name="person_id"  type="text" value="${person.person_id }" 
								 readonly="readonly"/></td>
					</tr>

					<tr>
						<th>프로필사진</th>
						<td>
							<input type="file" name="person_img" accept="image/*" />
							<div id="imageArea">
							<c:if test="${not empty person.per_img }">
								<img src="data:image/*;base64,${person.per_imgToBase64 }">
							</c:if>
							</div>
						</td>
					</tr>

					<tr>
						<th>비밀번호</th>
							<td><input name="person_pass"  type="text" value="${person.person_pass }" /></td>

					</tr>

					<tr>
						<th>이름</th>
							<td>${person.person_name }</td>
					</tr>
					<tr>
						<th>우편번호</th>
							<td><input type="text" name="person_zip"
								value=${person.person_zip } /></td>
						<td><input type="button" value="우편번호 찾기"
							onclick="daumPostCode()" /></td>
					</tr>

					<tr>
						<th>주소</th>
							<td><input type="text" name="person_addr1"
								value=${person.person_addr1 } /></td>
							<td><input type="text" name="person_addr2"
								value=${person.person_addr2 } /></td>
					</tr>

					<tr>
						<th>전화번호</th>
							<td><input type="text" name="person_tel"
								value=${person.person_tel } /></td>
					</tr>
					
					<tr>
						<th>메일</th>
							<td><input type="text" name="person_mail"
								value=${person.person_mail } /></td>
					</tr>

					<tr>
						<th>승인여부</th>
						<td><c:if test="${'Y' eq cor.company_apply}">
						승인
						</c:if> 
						<c:if test="${'N' eq cor.company_apply}">
						미승인
						</c:if>
						</td>
					</tr>

					<tr>
						<th>사업자번호</th>
						<td>${cor.company_business_no}</td>
					</tr>

					<tr>
						<td colspan="2">
						<input type="submit" value="저장" />
						<input type="reset" value="취소" />
						<input type="button" value="뒤로가기" onclick="history.back();" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</section>
</c:if>
</c:forEach>