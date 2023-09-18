<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script>
	$(function(){
		var imageArea = $("#imageArea");
		
		$('[name="crew_image"]').on("change", function(){
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
	});
</script>

<style>
	.hrTag{
		border: 1px solid black;
	}
	#imageArea{
		width: 150px;
		height: 150px;
	}
	img{
		width: 100%; height: 100%;
	}
	.round {
		display :inline-block;
	    margin: auto;
		border-radius: 50%;
		overflow: hidden;
		width: 150px;
		height: 150px;
		border : 3px solid black;
	}
	.round img {
		margin: 0 auto;
		text-align: center;
		display: block;
		min-width: 100%;
		min-height: 100%;
	}
	
</style>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if> 

<section class = "s-content">
	<div class="row masonry-wrap">
		
		<div>
			크루 만들기 			
		</div>
		
		<hr class="hrTag"/>
	
		<form:form modelAttribute="crew" id="crewForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="mem_id" value="${authMember.person_id}" />
			<table class = "table-bordered table-striped table-hover">
				
				<tr>
					<td>크루명</td>
					<td><input type="text" name="crew_name"/> </td>
				</tr>
				
				<tr>
					<td>소개글</td>
					<td>	
						<TEXTAREA name="crew_content" rows="" cols=""></TEXTAREA>
					 </td>
				</tr>
				
				<tr>
					<td>크루 프로필 이미지</td>
					<td>
						
						<div id="imageArea" >
							<div class="roundDiv">
								<div class="round" >
									<img name="crew_img" src="data:image/*;base64,${crew.crew_imgToBase64}" />
								</div>
							</div>
						</div>
						
						<div>
							<input type="file" name="crew_image" accept="image/*" />
						</div>
					</td>
				</tr>
				
				<tr>
					<td>크루정원</td>
					<td><input name="crew_limit" type="number"/></td>
				</tr>
				
			</table>
			
			<div style="text-align: center"> 
				<input type="submit" value="등록">
			</div>
		</form:form>
		
	</div>
</section>


<script type="text/javascript">
		var reviewForm = $("#crewForm");

		CKEDITOR.replace('crew_content', {

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