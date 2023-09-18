/**
 * 게시글 상세조회의 덧글 처리
 */

	function replyListMaker(resp) {
		if (resp.errors) {
			alert(resp.errors.message); 					
		} else { // 등록 성공
			var html = "";
			if(resp.dataList){
				$.each(resp.dataList, function(idx, reply){
					
                    html += "<div class='comment__avatar'>";
                    html += "<div class='comment_meminfo'>";                    
                    html += "<img class='avatar' src='"+$.getImage()+"'>";
                    html += "<cite class='comment_mem'>"+reply.travel_tip_mem_name+"("+reply.travel_tip_mem_id+")</cite>&nbsp;&nbsp;&nbsp;<time class='comment__time'>"+reply.travel_tip_reply_date+"</time>";
                    html += "</div>"	
                    html += "</div>"	
                    html += "</div>"
                    html += "<div class='comment__content'>";
                    html += "<div class='comment__info'>";
                    html += "<div class='comment__meta'>"; 
                    html += "</div>";
                    html += "</div>"; 
                    html += "<div class='comment__text'>";
                    html += "<pre class='comment_pre'>"+reply.travel_tip_reply_content;
                    html += "<p id='"+reply.travel_tip_mem_id+"_"+reply.travel_tip_reply_no+"'>";
        			if($.getAuthMember() == reply.travel_tip_mem_id){
        				html +="<span class='upnde_btn' data-toggle='modal'  onclick='replyDelfunc("+reply.travel_tip_reply_no+")' class='replyDelBtn'><i class='far fa-trash-alt'></i></span>"+ "&nbsp;&nbsp;&nbsp;" ;
        				html +="<span class='upnde_btn' data-toggle='modal'  onclick='replyUpfunc("+reply.travel_tip_reply_no+")' class='replyUpdBtn'><i class='far fa-edit'></i></span>" ;
        			}
        			"</p>";
            		html += "</pre>";
            		html += "</div>";
            		html += "</div>";
            		html += "<hr/>"        			
        			
//                    html += "<cite>"+reply.travel_tip_mem_id+"</cite>";
//                    html += "<div class='comment__meta'>";
//                    html += "<time class='comment__time'>"+reply.travel_tip_reply_date+"</time>";
//                    html += "</div>";
//                    html += "</div>";
//                    html += "<div class='comment__text'>";
//                    html += "<p id='"+reply.travel_tip_mem_id+"_"+reply.travel_tip_reply_no+"'><span data-toggle='modal'  data-target='#replyDeleteModal' class='replyDelBtn'>[삭제]</span></p>";
//                    html += "</div>";
//                    html += "</div>";
				});
			}else{
				html += "<li>댓글이 없습니다.</li>";
				html += "<hr/>"
			}
			pagingArea.html(resp.pagingHTML);	
			listBody.html(html);
			replyForm[0].reset();
		}
	}
	
	function pagingReply(page, travel_tip_no){
		$.ajax({
			url:$.getContextPath()+"/tipboard/replyList.do",
			data:{
				travel_tip_no:travel_tip_no,
				page:page
			},
			dataType:"json",
			success:replyListMaker,
			error:function(resp){
				console.log(resp.status);
			}
		});
	}
	

	
	
	$(function(){
		pagingArea = $("#pagingArea");
		listBody = $("#listBody");
 		replyForm = $("[name='replyForm']");
 		upModal = $("#replyUpdateModal");
 		
// 		travel_tip_reply_no = "";
// 		travel_tip_mem_id = "";
// 		listBody.on("click", "span" ,function(){
// 			var trId = $(this).closest("p").prop("id");
// 			travel_tip_reply_no = trId.substring(trId.indexOf("_")+1);
// 			travel_tip_mem_id = trId.substring(0,trId.indexOf("_"));
// 			upModal.find("#travel_tip_reply_no").val(travel_tip_reply_no);
// 			upModal.modal("show");
// 		});
 		
 		$("#modalUpdBtn").on("click", function(){
 			var action = replyForm.attr("action");
 			replyForm.attr("action", $.getContextPath()+"/tipboard/replyUpdate.do");
 			var travel_tip_reply_no = upModal.find("#travel_tip_reply_no").val();
 			var travel_tip_reply_content = upModal.find("#reply_content").val();
 			replyForm.find("[name='travel_tip_reply_no']").val(travel_tip_reply_no);
 			replyForm.find("[name='travel_tip_reply_content']").val(travel_tip_reply_content);
 			replyForm.submit();
 			replyForm.attr("action", action);
 			replyForm[0].reset();
 			$("#modalForm")[0].reset(); 			
 			upModal.modal("hide");
 		});
 		
 		replyForm.ajaxForm({
 			dataType:'json',
 			success:replyListMaker,
 			error:function(resp){
 				alert(resp.status);
 			}
 		});
	});