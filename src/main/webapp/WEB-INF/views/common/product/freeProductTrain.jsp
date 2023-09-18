<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
$(function(){
	
   
	
	
   dataListArrive = [];
   dataListDept = [];
   
   trainTypeSelect = $("#trainTypeSelect");
   searchBtn = ("#searchBtn");
   Modal = $("#Modal");
   arriveSelect = $("#arriveSelect");
   deptSelect = $("#deptSelect");
   arriveStationSelect = $("#arriveStationSelect");
   deptStationSelect = $("#deptStationSelect");
   depplandtime = $("#depplandtime");
   arrplandtime = $("#arrplandtime");
   adultcharge = $("#adultcharge");
   traingradename = $("#traingradename");
   tBodyList = $("#tBodyList");
   
   searchForm= $("#searchForm");
	   
   TrainOptionGenerate();
   
   
   $('#deptSelect option[value="${reservationInfo.selectDept}"]').attr('selected','selected');
   $('#arriveSelect option[value="${reservationInfo.selectArr}"]').attr('selected','selected');
   
   
   
   $("#deptSelect").change(function(){
	   var params = $(this).val();
	   var pattern = "<option value='%V'>%T</option>";
	   $.ajax({
           url : "${pageContext.request.contextPath}/traffic/trainInfo.do",
           method : 'get',
           dataType : "json",
           data : {
               cityCode : params,
            },
           success : function(resp) {
        	   var list = resp.response.body.items.item;
        	   var options = pattern.replace("%V", "").replace("%T", "출발역 선택");
				$.each(list, function(idx, temp){
				options += pattern.replace("%V", temp.nodeid)
								  .replace("%T", temp.nodename);
				});
				deptStationSelect.html(options);
           },
           
           error : function(resp) {
              console.log(resp.status);
           }
        });
   });
   
   deptSelect.trigger("change");
   
   $("#arriveSelect").change(function(){
	   var params = $(this).val();
	   var pattern = "<option value='%V'>%T</option>";
	   $.ajax({
           url : "${pageContext.request.contextPath}/traffic/trainInfo.do",
           method : 'get',
           dataType : "json",
           data : {
               cityCode : params,
            },
           success : function(resp) {
        	   var list = resp.response.body.items.item;
        	   var options = pattern.replace("%V", "").replace("%T", "도착역 선택");
				$.each(list, function(idx, temp){
				options += pattern.replace("%V", temp.nodeid)
								  .replace("%T", temp.nodename);
				});
				arriveStationSelect.html(options);
           },
           
           error : function(resp) {
              console.log(resp.status);
           }
        });
   });
   arriveSelect.trigger("change");
   
   $("#searchBtn").on("click", function(){
	      event.preventDefault();
	      
	      dept = deptStationSelect.val();
	      arr = arriveStationSelect.val();
	      trainType = trainTypeSelect.val();
	      
	      startDate = $("#startDate").val();
	      var search = {
	    		  depPlaceId : dept,
	    		  arrPlaceId : arr,
	    		  depPlandTime : startDate,
	    		  trainType : trainType
	      };
	      getTrainStation(search);
	   });
   
   $("#tBodyList").delegate("tr" ,"click", function(){
	   $("#depplandtime").val($(this).find("td:nth-child(2)").text());
	   $("#arrplandtime").val($(this).find("td:nth-child(4)").text());
	   $("#adultcharge").val($(this).find("td:nth-child(5)").text());
	   $("#traingradename").val($(this).find("td:nth-child(6)").text());
	   dep = depplandtime.val();
	   arrt = arrplandtime.val();
	   charge = adultcharge.val();
	   grade = traingradename.val();
	   per = $("#person").val();
	   person_id2 = "${authMember.person_id}";
	   search2 = {
	    		  depPlaceId : dept,
	    		  arrPlaceId : arr,
	    		  depPlandTime : startDate,
	    		  depplandtime : dep,
	    		  arrplandtime : arrt,
	    		  adultcharge : charge,
	    		  traingradename : grade,
	    		  person : per,
	    		  person_id : person_id2
	   }
	   Modal.modal("show");
   });
   
   $("#modalBtn").on("click", function(){
	    insertTrainStation(search2);
		Modal.modal("hide");
	});
	   
});
function insertTrainStation(object) {
	            $.ajax({
	               url : "${pageContext.request.contextPath}/traffic/trainInsert.do",
	               method : 'post',
	               dataType : "json",
	               data : {
	            	  depPlaceId : object.depPlaceId,
	 	    		  arrPlaceId : object.arrPlaceId,
	 	    		  depPlandTime : object.depPlandTime,
	 	    		  depplandtime : object.depplandtime,
	 	    		  arrplandtime : object.arrplandtime,
	 	    		  adultcharge : object.adultcharge,
	 	    		  traingradename : object.traingradename,
	 	    		  person : object.person,
	 	    		 mem_id : object.person_id
	               },
	               
	               success : function(resp) {
	            	   
	               },
	               
	               error : function(resp) {
	               }
	               
	            });
	         }
function replaceAll(str, searchStr, replaceStr) {
    return str.split(searchStr).join(replaceStr);
}

function getTrainStation(object) {
	a = replaceAll(object.depPlandTime , "-" ,"");
	      $.ajax({
	         url : "${pageContext.request.contextPath}/traffic/trainInfo.do",
	         method : 'post',
	         dataType : "json",
	         data : {
	      	  depPlaceId : object.depPlaceId,
	 	 		  arrPlaceId : object.arrPlaceId,
	 	 		  depPlandTime : a , 
	 	 		  trainType  : object.trainType
	         },
	         
	         success : function(resp) {
	      	   
	      	   if(resp.response.body.items == ""){
	      		   console.log("정보없음");
	      		   return;
	      	   };
	      	   
	      	   var depthtml = "";
	      	   var list = resp.response.body.items.item;
	      	   $.each(list,function(idx,tmp){
	      		   var a = tmp.arrplandtime;
	      		   var year = a.toString().substr(0,4);
	      		   var month = a.toString().substr(4,2);
	      		   var day = a.toString().substr(6,2);
	      		   var si = a.toString().substr(8,2);
	      		   var bun = a.toString().substr(10,2);
	      		   
	      		   var b = tmp.depplandtime;
	      		   var year2 = b.toString().substr(0,4);
	      		   var month2 = b.toString().substr(4,2);
	      		   var day2 = b.toString().substr(6,2);
	      		   var si2 = b.toString().substr(8,2);
	      		   var bun2 = b.toString().substr(10,2);
	      		         depthtml += "<tr>";
	      		         depthtml += "<td>"+tmp.depplacename+"</td>";
	      		         depthtml += "<td>"+year2+"-"+month2+"-"+day2+"  "+si2+":"+bun2+"</td>";
	      		         depthtml += "<td>"+tmp.arrplacename+"</td>";
	      		         depthtml += "<td>"+year+"-"+month+"-"+day+"  "+si+":"+bun+"</td>";
	      		         depthtml += "<td>"+tmp.adultcharge*$("#person").val()+"</td>";
	      		         depthtml += "<td>"+tmp.traingradename+"</td>";
	      		         depthtml += "<td>"+$("#person").val()+" 명</td>";
	      		         depthtml += "</tr>";
	      		   });
	      		   tBodyList.html(depthtml);
	         },
	         
	         error : function(resp) {
	            console.log(resp.status);
	         }
	      });
}
	         
function TrainOptionGenerate(){
	 $.ajax({
         url : "${pageContext.request.contextPath}/traffic/trainTypeInfo.do",
         method : 'get',
         dataType : "json",
         success : function(resp){
        	 $.each(resp.response.body.items.item,function(idx,temp){
        		 trainTypeSelect.append($('<option>',{
        	         value : temp.vehiclekndid,
        	         text : temp.vehiclekndnm
        	      }));
        	   });
         },
         error : function(resp){
        	 console.log(resp);
         }
	 });
   
   var trainCity =[
      {cityname:"서울특별시" , citycode:"11"},
      {cityname:"세종특별시" , citycode:"12"},
      {cityname:"부산광역시" , citycode:"21"},
      {cityname:"대구광역시" , citycode:"22"},
      {cityname:"인천광역시" , citycode:"23"},
      {cityname:"광주광역시" , citycode:"24"},
      {cityname:"대전광역시" , citycode:"25"},
      {cityname:"울산광역시" , citycode:"26"}
   ];
   
   $.each(trainCity,function(idx,temp){
      arriveSelect.append($('<option>',{
         value : temp.citycode,
         text : temp.cityname
      }));
   });
   
   $.each(trainCity,function(idx,temp){
      deptSelect.append($('<option>',{
         value : temp.citycode,
         text : temp.cityname
      }));
   });
}
</script>

<style>
#person{
   width: 5%;
   height: 20%;
   display: inline;
}

.deptSelect {
   width: 47%;
   height: 20%;
   display: inline;
}
.arriveSelect {
   width: 47%;
   height: 20%;
   display: inline;
}
.personSelect {
   width: 15%;
   height: 20%;
   display: inline;
}
table.type09 {
   border-collapse: collapse;
   text-align: left;
   line-height: 1.5;
}

table.type09 thead th {
   padding: 10px;
   font-weight: bold;
   vertical-align: top;
   color: #369;
   border-bottom: 3px solid #036;
}

table.type09 tbody th {
   width: 150px;
   padding: 10px;
   font-weight: bold;
   vertical-align: top;
   border-bottom: 1px solid #ccc;
   background: #f3f6f7;
}

table.type09 td {
   width: 350px;
   padding: 10px;
   vertical-align: top;
   border-bottom: 1px solid #ccc;
}
</style>
<section class="s-content">
   <div class="row masonry-wrap">
      
      ${reservationInfo }
      
      <form id="searchForm"  method="post" action='<c:url value="/traffic/trainInsert.do"/>'>
         <div style="height: 250px;" >
            <div style="margin :auto; width: 85%; height : 15%; background-color: #7787c7; border: 1px solid ; border-radius: 15px 15px 0px 0px;">
               <div style="color: white; float: right; margin-right: 15px;">
               </div>
            </div>
            
            <div style="padding: 10px; margin : auto; width: 85%; height: 75%; background-color: #ffffff; border: 1px solid; border-radius: 0px 0px 15px 15px;">
               <div style="margin : auto; text-align:center; width: 90%;  display: inline-block;">
                  
                  <select class="deptSelect" id="deptSelect">
   
                  </select> 
                  
                  <select class="deptSelect" id="deptStationSelect">
   
                  </select> 
                  
                  <select class="arriveSelect" id="arriveSelect">
   
                  </select>
                  
                  <select class="arriveSelect" id="arriveStationSelect">
   
                  </select>
               </div>
               
               <c:set var="totalPerson" value="${reservationInfo.adult + reservationInfo.children }"/>
               
               
               <div style="margin : auto; text-align:center; width: 90%;  display: inline-block;">
             	  기차 타입  : <select class="personSelect" id="trainTypeSelect" ></select>
             	  예약 인원  : <input type="text" id="person" value="${totalPerson}" disabled="disabled">
                  <input type="text" id="startDate" value="${reservationInfo.startDate}" disabled="disabled">
                  <input type="hidden" id="arrplandtime">
                  <input type="hidden" id="depplandtime">
                  <input type="hidden" id="adultcharge">
                  <input type="hidden" id="traingradename">
            	  <input type="submit" id="searchBtn" value="검색"  style="height: 45px; width: 20%;">
               </div>
            </div>
         </div>
      </form>
      
      <div style="display: inline-block; text-align: center; width: 100%; margin-left: 110px;">
         <div style="overflow : auto; padding: 10px; margin : auto; width: 80%; float:left; height: 75%; background-color: #ffffff; border: 1px solid; border-radius: 0px 0px 15px 15px;">
            <table class="type09" style="float: left; width: 100%">
               <thead>
                  <tr>
                     <th>출발지</th>
                     <th>출발시간</th>
                     <th>도착지</th>
                     <th>도착시간</th>
                     <th>가격</th>
                     <th>종류</th>
                     <th>예약인원</th>
                  </tr>
               </thead>
               
               <tbody id="tBodyList">
                  
               </tbody>
            </table>
         </div>
      
   </div>
   </div>
</section>

<div class="modal fade" id="Modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
          <span aria-hidden="true">정말 예약 하시겠습니까? </span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="modalBtn">확인</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>