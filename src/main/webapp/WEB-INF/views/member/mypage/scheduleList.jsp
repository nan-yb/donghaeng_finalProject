<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.0.1/fullcalendar.js"></script>
    <link  rel="stylesheet"  href="https://fullcalendar.io/js/fullcalendar-3.0.1/fullcalendar.min.css"></link>

<style type="text/css">
   #calendar {
    max-width: 900px;
    margin: 40px auto;
  }
</style>

<script>

function scheduleList(){
	$.ajax({
        url: '${pageContext.request.contextPath}/member/mypage/scheduleList.do',
        type: "POST",
        dataType : "json",
        data: {
        	mem_id : "${authMember.person_id}"
        },
        success : function(datas) {
        	if(datas){
        		$.each(datas,function(i, data){
		        	eventData = {
		        		id : data.myschedule_no,
		                title: data.myschedule_title,
		                start: data.myschedule_startdate,
		                end: data.myschedule_enddate
		            };
				   	$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
        		});
        	}
        },
     });
}


$(document).ready(function() {
	mem_id = $("input[name='mem_id']");
	myschedule_no = $("input[name='myschedule_no']");
	myschedule_content=	$("textarea[name='myschedule_content']");
	myschedule_startdate = $("input[name='myschedule_startdate']");
	myschedule_enddate= $("input[name='myschedule_enddate']");
	myschedule_title =$("input[name='myschedule_title']");
	modal_form = $("#modal_form");
	eventData = "";
	
	scheduleList();

	
    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month'
        },
        defaultDate: new Date(),
        navLinks: true, // can click day/week names to navigate views
        selectable: true,
        selectHelper: true,
        select: function(start, end) {
        	$('#modal').find('input').val('');
        	$('#modal').find('textarea').val('');
        	
        	mem_id.val('${authMember.person_id}');
        	myschedule_startdate.val(start.format());
        	myschedule_enddate.val(end.format());
        	$('#modal').modal('show');
        },
        eventClick: function(event, element) {
        	$('#remove_btn').remove();
        	$.ajax({
                url: '${pageContext.request.contextPath}/member/mypage/scheduleView.do',
                type: "POST",
                dataType : "json",
                data : {
                	myschedule_no : event.id
                },
                success : function(resp) {
                	mem_id.val(resp.mem_id);
                	myschedule_no.val(resp.myschedule_no);
                	myschedule_content.val(resp.myschedule_content);
                	myschedule_startdate.val(resp.myschedule_startdate);
                    myschedule_enddate.val(resp.myschedule_enddate);
                    myschedule_title.val(resp.myschedule_title);
                },
             });
        	$('#modal').modal('show');
			$('#modal_footer').append('<button type="button" id="remove_btn" class="btn btn-primary" onclick="remove_btnClick()" >일정 삭제</button>');
        	$('#modal_title').text('일정 조회');
        	$('#save-event').val('일정 수정');
        	
        	$('#calendar').fullCalendar('unselect');

               // Clear modal inputs
            $('#modal').find('input').val('');
        },
        editable: true,
        eventLimit: true, // allow "more" link when too many events
        eventDrop : function(event){
        	$.ajax({
    	        url: '${pageContext.request.contextPath}/member/mypage/calendarUpdate.do',
    	        type: "POST",
    	        dataType : "json",
    	        data: {
    	        	myschedule_no : event.id,
    	        	myschedule_startdate : event.start.format(),
    	        	myschedule_enddate : event.end.format()
    	        },
    	        success : function(datas) {
    			},
    	    });
        },
        eventResize : function(event){
        	$.ajax({
    	        url: '${pageContext.request.contextPath}/member/mypage/calendarUpdate.do',
    	        type: "POST",
    	        dataType : "json",
    	        data: {
    	        	myschedule_no : event.id,
    	        	myschedule_startdate : event.start.format(),
    	        	myschedule_enddate : event.end.format()
    	        },
    	        success : function(datas) {
    			},
    	    });
        }
    });

    $('#save-event').on('click', function() {
    	modal_form.submit();
    	
    	modal_form.ajaxForm({
    		dataType:'json',
    		success:function(resp){
    			console.log(resp);
    		},
    		error:function(resp){
    			alert(resp.status);
    		}
    	});
    	
    	
        $('#calendar').fullCalendar('unselect');

        // Clear modal inputs
        $('#modal').find('input').val('');

        // hide modal
        $('#modal').modal('hide');
    });
    
    
    
});

function remove_btnClick(){
	var action = modal_form.attr("action");
	modal_form.attr("action", "${pageContext.request.contextPath}/member/mypage/calendarDelete.do");
	modal_form.submit();
	modal_form.attr("action", action);
	modal_form[0].reset();
	$("#modalForm")[0].reset(); 			
	$('#modal').modal("hide");
	$('#remove_btn').remove();
}

</script>
<section class="s-content">
	<div class="row masonry-wrap">
		<div id='calendar'></div>
		<div id='datepicker'></div>
	</div>
</section>

 
<div class="modal fade" tabindex="-1" role="dialog" id="modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header" style="display: inline-block;">
                <h4 class="modal-title" style="float: left;" id="modal_title">일정 등록</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="float:right; margin-left: 400px;"><span aria-hidden="true">&times;</span></button>
            </div>
            
            <form action="${pageContext.request.contextPath}/member/mypage/scheduleInsert.do" id="modal_form" method="post">
	            <div class="modal-body">
	                <div class="row">
	                    <div class="col-xs-12">
	                        <label class="col-xs-4" for="title">일정 제목</label>
	                        <input type="hidden" name="myschedule_no" value="no" />
	                        <input type="hidden" name="mem_id" value="" />
	                        <input type="text" name="myschedule_title" id="title" />
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="col-xs-12">
	                        <label class="col-xs-4" for="starts-at">시작 날짜</label>
	                        <input type="text" name="myschedule_startdate" value="" style="width: 100px;" />
	                        
	                        <label class="col-xs-4" for="starts-at">종료 날짜</label>
	                        <input type="text" name="myschedule_enddate" value="" style="width: 100px;"/>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="col-xs-12">
	                        <label class="col-xs-4" for="ends-at">일정 상세 정보</label>
	                        <TEXTAREA  name="myschedule_content"></TEXTAREA>
	                    </div>
	                </div>
	            </div>
            </form>
            <div class="modal-footer" id="modal_footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="save-event" >일정 등록</button>
            </div>
        </div>
    </div>
</div>


