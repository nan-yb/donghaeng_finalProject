<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
    
    
  <!-- pageheader
    ================================================== -->
    <section style="border-bottom: 2px solid #8c8c8c;" class="s-pageheader s-pageheader--home">
    
        <header class="header">
            <div class="header__content row">

                <div class="header__logo">
                    <a  class="logo" href="<c:url value="/"/>">
                        <img style="width: 150px; height: 100px;" src="${pageContext.request.contextPath}/images/new.png" alt="Homepage">
                    </a>
                </div> <!-- end header__logo -->
             
<!--                 로그인 -->
                <ul class="header__login">
                    <li>
                     <c:if test="${empty authMember}">
<%--                               <button style="background:url('images/login.png')" value="회원" onclick="location.href='${pageContext.request.contextPath}/login/loginCheck.do'"></button> --%>
                           <a onclick="location.href='${pageContext.request.contextPath}/login/loginCheck.do'">
                              <img style="width: 30px; height: 30px; cursor:pointer;" src="${pageContext.request.contextPath}/images/login.png" />
                           </a>
                        
                         </c:if>
                         
                         <c:if test="${not empty authMember}">
                            <c:if test="${authMember.person_type eq 1}">
                            </c:if>
<%--                               <a href="<c:url value="/common/mypage/viewInfo.do"/>">${authMember.person_name}</a><span style="font-size: 15px; color:black;">님</span>  --%>
                            <c:url var="myPage" value="/common/mypage/viewInfo.do">
                            	<c:param name="who">
                            		${authMember.person_id}
                            	</c:param>
                            </c:url>
                            
                    <ul class="header__nav">                     
                        <li class="has-children">
                            <a href="${myPage}">${authMember.person_name}님</a><span style="font-size: 15px; color:black;"></span>
                            <ul class="sub-menu">
                               <li><a><i class="fas fa-coins">&nbsp;마일리지&nbsp;${member.mem_mileage}점</i></a></li>
                               <li><a href="${myPage}">마이페이지</a></li>
                               <li><a href="<c:url value="/login/logout.do"/>">로그아웃</a></li>
                            </ul>
                        </li>             
					</ul> 
                         </c:if>
                  </li>
                  
                  <li>    
                    </li>
                </ul> 
                
                            
<!--                 기업 회원 -->
                <ul class="header__social">
                    <li>
                       <c:if test="${not empty authMember}">
                         <c:if test="${authMember.person_type eq 2}">
<%--                              <button style="background-image:url('images/company.png'); " value="기업" onclick="location.href='${pageContext.request.contextPath}/cor/mainPage.do'"></button> --%>
                            <a onclick="location.href='${pageContext.request.contextPath}/cor/mainPage.do'">
                              <img style="width: 40px; height: 40px; border: 3px solid white; border-radius: 50px; cursor:pointer; " src="${pageContext.request.contextPath}/images/company.png" />
                           </a>                           
                           </c:if>
                         </c:if>
                    </li>
                    
                    <li>
                          <c:if test="${not empty authMember}">
                         <c:if test="${authMember.person_type eq 3}">
<%--                               <button  style="background-color: blue;" value="관리자" onclick="location.href='${pageContext.request.contextPath}/admin/mainPage.do'"></button> --%>
                            <a onclick="location.href='${pageContext.request.contextPath}/admin/mainPage.do'">
                              <img style="width: 40px; height: 40px; border: 3px solid white; border-radius: 50px; cursor:pointer; " src="${pageContext.request.contextPath}/images/admin.png" />
                           </a>  
                           </c:if>
                         </c:if>
                    </li>
                    
                   
                </ul> <!-- end header__social -->
                
                
                <a class="header__search-trigger" href="#0"></a>

                <div class="header__search">

                    <form role="search" method="get" class="header__search-form" action="#">
                        <label>
                            <span class="hide-content">Search for:</span>
                            <input type="search" class="search-field" placeholder="Type Keywords" value="" name="s" title="Search for:" autocomplete="off">
                        </label>
                        <input type="submit" class="search-submit" value="Search">
                    </form>
        
                    <a href="#0" title="Close Search" class="header__overlay-close">Close</a>

                </div>  <!-- end header__search -->

                <a class="header__toggle-menu" href="#0" title="Menu"><span>Menu</span></a>

                <nav class="header__nav-wrap">

                    <h2 class="header__nav-heading h6">Site Navigation</h2>

                    <ul class="header__nav">
                        <li class="current"><a href="<c:url value="/"/>" title="">Home</a></li>
                        <li class="has-children">
                            <a href="#0" title="">여행상품</a>
                            <ul class="sub-menu" style="width: 150px;" >
<!--                             <li class="subli" style="text-align: center; font-weight: bold; font-size:13px;"><a>여행상품</a></li> -->
                           			 <li class="subli"><img style="width: 150px; height:140px;" src="${pageContext.request.contextPath}/images/jw/pi1.png"> </li>
                                <li><a href="<c:url value="/common/package/pakageProductForm.do"/>">패키지상품</a></li>
                          		<li><a href="<c:url value="/common/product/freeProduct.do"/>">자유상품</a></li>
                            </ul>
                        </li>
                        
                        <li><a href="<c:url value = "/crew/crewMain.do"/>">크루</a></li>
                    
                          <li class="has-children">
                           <a href="#" title="">커뮤니티</a>
                             <ul class="sub-menu" style="width: 150px; ">
                             	   <li><img style="width: 150px; height:122px; " src="${pageContext.request.contextPath}/images/jw/pi2.png"> </li>
                                   <li><a href="<c:url value="/tipboard/tipboardRetrieve.do"/>">여행팁 게시판</a></li>
                                   <li><a href="<c:url value="/lodging/lodgingRetrieve.do"/>">숙박정보 게시판</a></li>                            
                                   <li><a href="<c:url value="/festival/festivalRetrieve.do"/>">축제정보 게시판</a></li>
                                   <li><a href="<c:url value="/postboard/postboardRetrieve.do"/>">후기게시판</a></li>
                                   <li><a href=" <c:url value="/freeboard/freeboardRetrieve.do"/>">자유게시판</a></li>
                             </ul>
                        </li>
                        
                        <li><a href="<c:url value="/eventboard/eventboardRetrieve.do"/>" title="">Event</a></li>
                        <li><a href="<c:url value="/customerService/customerService.do"/>" title="">고객센터</a></li>
<!--                         <li class="has-children"> -->
<%--                            <a href="<c:url value="/customerService/customerService.do"/>" title="">고객센터</a></li> --%>
<!--                              <ul class="sub-menu" style="width: 150px; "> -->
<%--                              	   <li><img style="width: 150px; height:128px;" src="${pageContext.request.contextPath}/images/jw/pic3.jpg"></li> --%>
<%--                                    <li><a href=" <c:url value="/noticeboard/noticeboardRetrieve.do"/>">공지게시판</a></li> --%>
<%--                                    <li><a href="<c:url value="/suggest/suggestList.do"/>" title="">건의사항게시판</a></li> --%>
<%--                                    <li><a href="<c:url value="/customer/goodVoiceRetrieve.do"/>" title="">고객의 소리</a></li> --%>
<%--                                    <li><a href="<c:url value="/qnaboard/qnaboardList.do" />">QNA게시판</a></li> --%>
<%--                                    <li><a href="<c:url value="/member/surveyEvaluationList.do" />">설문조사</a></li> --%>
<!--                              </ul> -->
                       
                        
<!--                         /suggest/suggestList.do -->
<!--                     </ul> end header__nav -->
                    
<!--                     <a href="#0" title="Close Menu" class="header__overlay-close close-mobile-menu">Close</a> -->
                </nav>
            </div> 
        </header> 
    </section> 
    