<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link rel="stylesheet" href='${pageContext.request.contextPath }/css/crew/main_crew.css'>
<script >


	$.getContextPath = function(){
		return "${pageContext.request.contextPath}"
	}

	$(function(){
		myCrewSelect();
		
	});
	
	
	function myCrewSelect(){
		
		var jbheader = $("#jb-header");
		var html = "";
	
		$.ajax({
			url:$.getContextPath()+"/crew/crewMain.do",
			data:{
				mem_id: "${authMember.person_id}"
			},
			dataType:"json",
			success:function(resp){
				if(resp.length > 0){
					html += "	<div class='crewContent'>                                                                                                           ";
					html += "	<div class='roundDiv'>                                                                                                              ";
					html += "		<div class='round' >                                                                                                            ";
					html += "			<a href='${pageContext.request.contextPath}/crew/crewHome.do?what="+resp[0].crew_no+"'><img alt='' src='data:image/*;base64,"+resp[0].crew_img+"'></a>    ";
					html += "		</div>                                                                                                                          ";
					html += "	</div>                                                                                                                              ";
					html += "		<div style='text-align: center'>                                                                                                ";
					html += "			<a href='${pageContext.request.contextPath}/crew/crewHome.do?what="+resp[0].crew_no+"'>"+resp[0].crew_name+"</a>                                                                  ";
					html += "		</div>                                                                                                                          ";
					html += "	</div>  																															";					
				}else{
					html += "<a href='<c:url value="/crew/crewInsert.do"/>'>내 크루 만들기</a>";
				}
				
				jbheader.html(html);
			},
			error:function(resp){
				console.log(resp);
			}
		});
		
		var tbody = $('#tbody');
		var tfoot = $('#tfoot');
		var tbodychild = '';
		$.ajax({
			url:$.getContextPath()+"/crew/crewMainList.do",			
			dataType:"json",
			success:function(resp){
				console.log();
				var crewList = resp.dataList;
				if(crewList){
					$.each(crewList, function(idx, el){
						tbodychild += '<tr>';
						tbodychild += '<th>이미지</th>';
						tbodychild += '<th>'+el.crew_name+'</th>';
						tbodychild += '</tr>';						
					})					
				}else{
					tbodychild += '<tr>';
					tbodychild += '<td colspan="2">생성된 크루가 없습니다.</td>';
					tbodychild += '</tr>';
				}
				tbody.append(tbodychild);
				tfoot.append(resp.pagingHTML);
				
				$('#tbody tr th').click(function(){
					alert($(this).text());
				});
			},
			error:function(resp){
				console.log(resp);
			}
		});
		
	}
	
</script>

<section class = "s-content">
	<div class="row masonry-wrap">
		${crew}
		<div id="jb-container">
			<div>
				
				<h1>내 크루</h1>				
				
			</div>
			<div id="jb-header">
				
			</div>
			
			<div>
				<h1>크루 소개 게시판</h1>
			</div>			
			<div id="jb-content">
				<table class = "table-bordered table-striped table-hover">
					<thead class="thead-dark">
						<tr>			
							<th>이미지</th>
							<th>크루명</th>
						</tr>
					</thead>
					<tbody id="tbody">
					</tbody>
					<tfoot id="tfoot">
					</tfoot>
				</table>
			</div>
		</div>
		
		<!-- Modal -->
<div class="modal fade" id="replyDeleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      	<form onsubmit="return false;" id="modalForm">
      		<input type="hidden" id="bo_no" value="${board.bo_no }"/>
      		<input type="text" id="rep_no" />
        	비밀번호 : <input type="text" id="rep_pass" />
        </form>	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
        <button type="button" class="btn btn-primary" id="modalBtn">삭제</button>
      </div>
    </div>
  </div>
</div>
	</div>
</section>