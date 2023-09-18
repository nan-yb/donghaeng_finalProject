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
                    html += "<div class='comment_img'>";
                    html += "<img class='avatar' src='"+$.getImage()+"'>";
//                    html += "</div>";
//                    html += "<div class='commemt_mem'>";
                    html += "<cite class='comment_mem'>"+reply.board_reply_mem_name+"("+reply.board_reply_mem_id+")</cite>&nbsp;&nbsp;&nbsp;<time class='comment__time'>"+reply.board_reply_date+"</time>";
                    html += "</div>";
                    html += "</div>";
                    html += "</div>";
                    html += "<div class='comment__content'>";
                    html += "<div class='comment__info'>";
//                    html += "<cite>"+reply.board_reply_mem_id+"</cite>";
                    html += "<div class='comment__meta'>";
//                    html += "<time class='comment__time'>"+reply.board_reply_date+"</time>";
                    html += "</div>";
                    html += "</div>";
                    html += "<div class='comment__text'>";
                    html += "<pre class='comment_pre'>"+reply.board_reply_content;
                    html += "<p id='"+reply.board_reply_mem_id+"_"+reply.board_reply_no+"'>";
                    		if($.getAuthMember() == reply.board_reply_mem_id){
                    			html +="<span class='upnde_btn' data-toggle='modal'  onclick='replyDelete("+reply.board_reply_no+")' class='replyDelBtn'><i class='far fa-trash-alt'></i></span>"+ "&nbsp;&nbsp;&nbsp;" ;
                    			html +="<span class='upnde_btn' data-toggle='modal'  onclick='replyUpdate("+reply.board_reply_no+")' class='replyUpdBtn'><i class='far fa-edit'></i></span>" ;
                    		}
                    		"</p>";
                    html += "</pre>";
                    html += "</div>";
                    html += "</div>";
                    html += "<hr/>"
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
	
	function pagingReply(page, board_no){
		$.ajax({
			url:$.getContextPath()+"/freeboard/replyList.do",
			data:{
				board_no:board_no,
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
// 		review_reply_no = "";
// 		review_reply_mem_id = "";
// 		listBody.on("click", "span" ,function(){
// 			var trId = $(this).closest("p").prop("id");
// 			board_reply_no = trId.substring(trId.indexOf("_")+1);
// 			board_reply_mem_id = trId.substring(0,trId.indexOf("_"));
// 			delModal.find("#board_reply_no").val(board_reply_no);
// 			delModal.modal("show");
// 		});
 		
 		$("#modalUpdBtn").on("click", function(){
 			var action = replyForm.attr("action");
 			replyForm.attr("action", $.getContextPath()+"/freeboard/replyUpdate.do");
 			var reply_no = upModal.find("#board_reply_no").val();
 			var reply_content = upModal.find("#reply_content").val();
 			replyForm.find("[name='board_reply_no']").val(reply_no);
 			replyForm.find("[name='board_reply_content']").val(reply_content);
 			replyForm.submit();
 			replyForm.attr("action", action);
 			replyForm[0].reset();
 			$("#modalForm")[0].reset(); 
 			upModal.modal("hide");
 		});
 		
// 		$("#modalUpdBtn").on("click", function(){
// 			var action = replyForm.attr("action");
// 			var content = replyForm.find("[name='board_reply_content'").val();
// 			
// 			replyForm.attr("action", $.getContextPath()+"/freeboard/replyUpdate.do");
// 			var board_reply_pass = delModal.find("#board_reply_pass").val();
// 			replyForm.find("[name='board_reply_no']").val(board_reply_no);
// 			replyForm.find("[name='board_reply_pass']").val(board_reply_pass);
// 			replyForm.find("[name='board_reply_mem_id']").val(board_reply_mem_id);
// 			replyForm.submit();
// 			replyForm.attr("action", action);
// 			replyForm[0].reset();
// 			updModal.modal("hide");
// 		});
 		
 		replyForm.ajaxForm({
 			dataType:'json',
 			success:replyListMaker,
 			error:function(resp){
 				alert(resp.status);
 			}
 		});

	});
	
	function replyUpdate(reply_no){
		if (confirm("댓글을 수정하시겠습니까?") == true) {
			upModal.find("#board_reply_no").val(reply_no);
			upModal.modal("show");
		} else {
			return;
		}
	}
	
	function replyDelete(reply_no){
		var result = confirm("삭제하시려면 확인을 눌러주세요.");
		if(result){
			var action = replyForm.attr("action");
			replyForm.attr("action", $.getContextPath()+"/freeboard/replyDelete.do");
			replyForm.find("[name='board_reply_no']").val(reply_no);
 			replyForm.submit();
 			replyForm.attr("action", action);
 			replyForm[0].reset();
		} else {
			return;
		}
	}