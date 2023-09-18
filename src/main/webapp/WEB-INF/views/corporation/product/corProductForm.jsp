<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script>
	$(function(){

		$("#package_start_date").flatpickr({
			minDate : new Date()
		});
		$("#package_end_date").flatpickr({
			minDate : new Date()
		});
		
		var imageArea = $("#imageArea");
		
		$('[name="package_image"]').on("change", function(){
			var files = $(this).prop("files");
			if(!files) return;
			for(var idx=0; idx<files.length; idx++){
// 				console.log(files[idx]);
				var reader = new FileReader();
				reader.onloadend=function(event){
// 					console.log(event.target.result);
					var imgTag = new Image();
					imgTag.src = event.target.result;
					imageArea.html(imgTag);
				}
				reader.readAsDataURL(files[idx]);
			}
		});
		
// 		$.each(category,function(idx,temp){
// 		      category.append($('<option>',{
// 		         value : temp.package_category_no,
// 		         text : temp.package_category_name
// 		      }));
// 		   });
// 		$.each(car,function(idx,temp){
// 		      car.append($('<option>',{
// 		         value : temp.car_no,
// 		         text : temp.car_name
// 		      }));
// 		   });
		
	});
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>	
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">패키지 상품 등록</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
		
						
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<form modelAttribute="packageVO" id="packageForm" method="post" enctype="multipart/form-data">
								<input type="hidden" name="company_id" id="company_id" value="${authMember.person_id}">
								<div class="form-group">
									<label>패키지 대표이미지 설정</label>
									<div id="imageArea" >
									<div class="roundDiv">
									<div class="round" >
										<img name="pack_img" src="data:image/*;base64,${crew.crew_imgToBase64}" />
									</div>
									</div>
									</div>
									<div>
										<input type="file" id="package_image" name="package_image" accept="image/*" />
									</div>
								</div>
								<div class="form-group">
									<label>패키지 명을 입력하세요</label> 
									<input id="package_name" name="package_name" class="form-control" placeholder="Package Name">
								</div>
								<div class="form-group">
									<label>패키지 카테고리</label> 
									<select name="package_category_no" id="package_category_no" class="form-control">
									<c:choose>
										<c:when test="${not empty category}">
											<c:forEach items="${category }" var="cate">
											<option value="${cate.package_category_no}">${cate.package_category_name}</option>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr>
												<td colspan="7">패키지 카테고리가 없습니다.</td>
											</tr>
										</c:otherwise>
									</c:choose>
									</select>
								</div>
								<div class="form-group">
									<label>패키지 차량</label> 
									<select name="car_no" id="car_no" class="form-control">
									<c:choose>
										<c:when test="${not empty car}">
											<c:forEach items="${car }" var="car">
											<option value="${car.car_no}">${car.car_name}</option>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr>
												<td colspan="7">현재 이용가능한 차량이 없습니다.</td>
											</tr>
										</c:otherwise>
									</c:choose>
									</select>
								</div>
								<div class="form-group">
									<label>패키지 최소 정원</label> <input id="package_mincount" name="package_mincount"
										class="form-control" placeholder="Package Min Person">
								</div>
								<div class="form-group">
									<label>패키지 최대 정원</label> <input id="package_maxcount" name="package_maxcount"
										class="form-control" placeholder="Package Max Person">
								</div>
								<div class="form-group">
									<label>패키지 가격</label> <input id="package_price" name="package_price"
										class="form-control" placeholder="Package Price">
								</div>
								<div class="form-group">
									<label>패키지 여행 출발일</label> <input type="text" id="package_start_date" name="package_start_date"
										class="form-control" placeholder="Package Start Date">
								</div>
								<div class="form-group">
									<label>패키지 여행 도착일</label> <input type="text" id="package_end_date" name="package_end_date"
										class="form-control" placeholder="Package End Date">
								</div>
								<div class="form-group">
									<label>패키지 소개</label>
									<TEXTAREA id="package_content" name="package_content" rows="" cols=""></TEXTAREA>
								</div>
								<button type="submit" class="btn btn-default">등록</button>
								<button type="reset" class="btn btn-default">취소</button>
							</form>
						</div>
						<!-- /.col-lg-6 (nested) -->
						</div>
					<!-- /.row (nested) -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->


<script type="text/javascript">
		var reviewForm = $("#packageForm");

		CKEDITOR.replace('package_content', {

		      extraAllowedContent: 'h3{clear};h2{line-height};h2 h3{margin-left,margin-top}',

		      // Adding drag and drop image upload.
		      extraPlugins: 'uploadimage',
		      uploadUrl: '${pageContext.request.contextPath}/postboard/uploadImage.do',

		      // Configure your file manager integration. This example uses CKFinder 3 for PHP.
		      filebrowserImageUploadUrl: '${pageContext.request.contextPath}/postboard/uploadImage.do',

		      height: 560,

		      removeDialogTabs: 'image:advanced;link:advanced'
	    });
	</script>