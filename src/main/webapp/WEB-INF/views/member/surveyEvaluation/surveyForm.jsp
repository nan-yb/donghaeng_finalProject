<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<style>
	.check {
		width: 20px;
 		height: 20px;
	}
.td {
	text-align: center;
}
</style>

<script type = "text/javascript">
	$(function(){
// 		$("#isSelect").click(function(){
// 			alert($('input:radio[name=${state.index}:checked]').val());
// 		})
		radioForm = $("#radioForm");
		
	});
	
// 	$(function(){
// 		var a = document.isSelect.td.val();
// 		alert(a);
// 	})
</script>

<section class="s-content">
	<div class="row masonry-wrap">
		<table>
			<c:if test="${not empty survey }">
			<thead>
				<tr>
					<th>항목</th>
					<td class="td">매우 불만족</td>
					<td class="td">불만족</td>
					<td class="td">보통</td>
					<td class="td">만족</td>
					<td class="td">매우 만족</td>
				</tr>
			</thead>
			<tbody>
			<form id="radioForm">
				<c:forEach items="${survey}" var="surv" varStatus="state">
					<tr id = "isSelect">
						<th>${surv.evaluation_content}</th>
							<td class = "td"><input type="radio" name="${state.index}" value="1"/></td>
							<td class = "td"><input type="radio" name="${state.index}" value="2"></td>
							<td class = "td"><input type="radio" name="${state.index}" value="3"></td>
							<td class = "td"><input type="radio" name="${state.index}" value="4"></td>
							<td class = "td"><input type="radio" name="${state.index}" value="5"></td>
					<tr>
				</c:forEach>
				
				<input type="submit" value="sd">
			</form>
			</tbody>
					
			</c:if>
		</table>
	</div>
</section>