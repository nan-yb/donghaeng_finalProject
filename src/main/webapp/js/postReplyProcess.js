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
            		console.log(reply);
                    html += "<div class='comment__avatar'>";
                    html += "<div class='comment_meminfo'>";
                    html += "<img class='avatar' src='"+$.getImage()+"'>";
                    html += "<cite class='comment_mem'>"+reply.review_reply_mem_name+"("+reply.review_reply_mem_id+")</cite>&nbsp;&nbsp;&nbsp;<time class='comment__time'>"+reply.review_reply_date+"</time>";
                    html += "</div>"   
                    html += "</div>"   
                    html += "</div>"   
                    html += "<div class='comment__content'>";
                    html += "<div class='comment__info'>";
                    html += "<div class='comment__meta'>";
//                    html += "<time class='comment__time'>"+reply.review_reply_date+"</time>";
                    html += "</div>";
                    html += "</div>";
                    html += "<div class='comment__text'>";
                    html += "<pre class='comment_pre'>"+reply.review_reply_content;
                    html += "<p id='"+reply.review_reply_mem_id+"_"+reply.review_reply_no+"'>";
                     if($.getAuthMember() == reply.review_reply_mem_id){
                        html +="<span class='upnde_btn' data-toggle='modal'  onclick='replyDelfunc("+reply.review_reply_no+")' class='replyDelBtn'><i class='far fa-trash-alt'></i></span>"+ "&nbsp;&nbsp;&nbsp;" ;
                        html +="<span class='upnde_btn' onclick='replyUpfunc("+reply.review_reply_no+")'  data-target='#replyUpdateModal' class='replyUpdBtn'><i class='far fa-edit'></i></span>" ;
                     }
                     "</p>";
                  html += "</pre>";
                  html += "</div>";
                  html += "</div>";
                  html += "<hr/>"
//                    html += "<p id='"+reply.review_reply_mem_id+"_"+reply.review_reply_no+"'><span data-toggle='modal'  data-target='#replyDeleteModal' class='replyDelBtn'>[삭제]</span></p>";
//                    html += "</div>";
//                    html += "</div>";
            });
         }else{
            html += "<li>댓글이 없습니다.</li>";
         }
         pagingArea.html(resp.pagingHTML);   
         listBody.html(html);
         replyForm[0].reset();
      }
   }
   
   function pagingReply(page, review_no){
      $.ajax({
         url:$.getContextPath()+"/postboard/replyList.do",
         data:{
            review_no:review_no,
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
      
//      review_reply_no = "";
//      review_reply_mem_id = "";
//       
//       
//       listBody.on("click", "span" ,function(){
//          var trId = $(this).closest("p").prop("id");
//          review_reply_no = trId.substring(trId.indexOf("_")+1);
//          review_reply_mem_id = trId.substring(0,trId.indexOf("_"));
//          delModal.find("#review_reply_no").val(review_reply_no);
//          delModal.modal("show");
//       });
       
       $("#modalUpdBtn").on("click", function(){
          var action = replyForm.attr("action");
          replyForm.attr("action", $.getContextPath()+"/postboard/replyUpdate.do");
          var review_reply_no = upModal.find("#review_reply_no").val();
          var reply_content = upModal.find("#reply_content").val();
          replyForm.find("[name='review_reply_no']").val(review_reply_no);
          replyForm.find("[name='review_reply_content']").val(reply_content);
          replyForm.submit();
          replyForm.attr("action", action);
          replyForm[0].reset();
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