<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script>
	function ${pagingVO.funcName}(page){
		$("[name='searchForm']").find("[name='page']").val(page);
		$("[name='searchForm']").submit();
	}
	
	function makeList(data){
		var html = "";
		var corApplyList = data.dataList;
		if (corApplyList) {
			$.each(corApplyList, function(i, cor){
				html += "<tr><td>"+cor.company_id+"</td>";
				$.each(cor.personList, function(i, person){
					html += "<td>"+person.person_name+"</td>";
					html += "<td>"+person.person_mail+"</td>";
					html += "<td>"+person.person_tel+"</td>";
				});
				html += "<td>"+cor.company_business_no+"</td>";
				html += "<td>"+cor.company_apply+"</td>";
				html += "<td><input type='checkbox' name='apply' value='"+cor.company_id+"'></td></tr>";
			
			});
		} else {
			html += "<tr><td colspan='7'>게시글이 없음</td></tr>";
		}
		$('#navTag').html(data.pagingHTML);
		$('#bodyList').html(body);
	}
	

	$(function(){
		searchForm = $("[name='searchForm']");
		
		$(window).on("popstate", function(event){
			console.log(event);
			if (event.originalEvnet.state) {
				var pagingVO = event.originalEvnet.state;
				
			} else{
				location.reload();
			}
		});
		
		
		searchForm.ajaxForm({
			dataType: 'json',
			success: function(data){
				
				var pageNum = searchForm.find('[name="page"]').val();
				var queryString = searchForm.serialize();
				
				history.pushState(data, pageNum+"페이지", "?"+queryString);
				searchForm.find("[name='page']").val("");
			},
		});
		
		
		
		
	});
	
	function checkConfirm(){
// 		var company_id = [];
// 		if ($("input[name=apply]:checked") == null) {
// 			alert('기업회원을 선택해주세요');
// 		}
		
		
// 		$("input[name=apply]:checked").each(function() {
// 			var company_id = $(this).val();
// 			console.log(company_id);
// 			document.checkForm.apply.value;
// 			document.checkForm.submit();
// 			$.ajax({
// 				url: "${pageContext.request.contextPath}/admin/corApplyUpdate.do",
// 				data: {
// 					company_id : company_id
// 				},
// 				dataType: "json",
// 				success: function(resp){
// 					console.log(resp);
// 				},
// 				error:function(resp){
// 					console.log(resp.status);
// 				}
// 			});
// 			alert(company_id+'기업회원을 가입승인하였습니다.');
// 		});
	}
	
	$(document).ready(function(){
    //최상단 체크박스 클릭
	    $("#th_allCheck").click(function(){
	        //클릭되었으면
	        if($("#th_allCheck").prop("checked")){
	            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
	            $("input[name=apply]").prop("checked",true);
	            //클릭이 안되있으면
	        }else{
	            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
	            $("input[name=apply]").prop("checked",false);
	        }
	    })
	})
	
	

</script>




<div id="wrapper">
	<div id="page-wrapper">
	<form name="checkForm" action="${pageContext.request.contextPath}/admin/corApplyUpdate.do" >
		<table class="table table-bordered table-striped table-hover">
			<thead class="thead-dark">
				<tr>
					<th>여행사아이디</th>
					<th>여행사이름</th>
					<th>메일</th>
					<th>번호</th>
					<th>사업자번호</th>
					<th>가입승인여부</th>
					<th>승인<input id="th_allCheck" name="allCheck" type="checkbox"></th>
				</tr>
			</thead>
			<tbody id="bodyList">
				<c:choose>
					<c:when test="${not empty pagingVO.dataList }">
						<c:forEach items="${pagingVO.dataList }" var="cor">
							<tr>
								<td>${cor.company_id }</td>
								<c:forEach items="${cor.personList}" var="person">
									<td>${person.person_name }</td>
									<td>${person.person_mail}</td>
									<td>${person.person_tel }</td>
								</c:forEach>
								<td>${cor.company_business_no }</td>
								<td>${cor.company_apply }</td>
								<td><input type="checkbox" name="apply" value="${cor.company_id }"></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="7">게시글이 없음</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="7">
						<nav id="navTag">
						${pagingVO.pagingHTML }
						</nav>
						
					</td>
				</tr>
			</tfoot>
		</table>
		<input type="submit" value="승인"  class="btn btn-success">
		</form>
		<form name="searchForm" action="${pageContext.request.contextPath}/admin/corApplyList.do" class="form-inline justify-content-center">
			<input type="hidden" name="page">
			<select name="searchType" class="form-control">
				<option value="">검색조건</option>
				<option value="id">여행사아이디</option>
				<option value="name">여행사이름</option>
			</select>
			<input type="text" name="searchWord" value="${pagingVO.searchWord }" class="form-control"/>
			<input type="submit" value="검색" class="btn btn-success"/>
		</form>
		<script type="text/javascript">
			document.searchForm.searchType.value= "${pagingVO.searchType}";
		</script>
	</div>
</div>