<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
	<link href="https://fonts.googleapis.com/css?family=Sunflower:300" rel="stylesheet">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet" />
	<link  rel="stylesheet"  href="${pageContext.request.contextPath}/css/main-other.css" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/main-other.js"></script>
   
   <script>
	   $.getContextPath = function(){
	      return "${pageContext.request.contextPath}";
	   }
	   
	   $.getName = function(){
	      return "${authMember.person_name}";
	   }
   </script>
   
   <meta charset="utf-8">
   <title>동행</title>
   
      <!-- mobile specific metas
       ================================================== -->
   <meta name="viewport"
      content="width=device-width, initial-scale=1, maximum-scale=1">
   
      <!-- CSS
       ================================================== -->
   <link rel="stylesheet" href="css/base.css">
   <link rel="stylesheet" href="css/vendor.css">
   <link rel="stylesheet" href="css/main.css">
      <!-- script
       ================================================== -->
   <script src="${cPath }/js/modernizr.js"></script>
   <script src="${cPath }/js/pace.min.js"></script>
   
   <!-- favicons
       ================================================== -->
   <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
   <link rel="icon" href="favicon.ico" type="image/x-icon">
</head>


<body>
	<div style="border-bottom: 2px solid black;" id="header">
		<jsp:include page="/includee/main/header.jsp" />
	</div>

	<div class="pageheader-content row">
		<div class="col-full">

			<div class="featured">
				<div class="featured__column featured__column--big">
				<div id="myCarousel" class="carousel slide" data-ride="carousel"> 
	
	<!--페이지-->
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
	</ol>
	<!--페이지-->

	<div class="carousel-inner">
		<!--슬라이드1-->
		<div class="item active"> 
			<img src="${pageContext.request.contextPath}/images/pic2.png" alt="First slide" style="width:100%;" >
			<div class="container">
				<div class="carousel-caption">
				</div>
			</div>
		</div>
		<!--슬라이드1-->

		<!--슬라이드2-->
		<div class="item"> 
			<img src="${pageContext.request.contextPath}/images/pic1.png" style="width:100%; height:100%;" data-src="" alt="Second slide">
			<div class="container">
				<div class="carousel-caption">
				</div>
			</div>
		</div>
		<!--슬라이드2-->
		
		<!--슬라이드3-->
		<div class="item"> 
			<img src="${pageContext.request.contextPath}/images/pic3.png" style="width:100%; height:100%;" data-src="" alt="Third slide">
			<div class="container">
				<div class="carousel-caption">
				</div>
			</div>
		</div>
		<!--슬라이드3-->
	</div>
	
	<!--이전, 다음 버튼-->
	<a class="left carousel-control" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> 
	<a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a> 
</div>

					<!-- end entry -->
				</div>
				<!-- end featured__big -->
				<!-- end featured__big -->

				<div class="featured__column featured__column--small">
					<div class="entry"
						style="background-image: url('${pageContext.request.contextPath}/images/pic5.jpg');" />
					<div>
						<h1>
							<a href="#0" title=""></a>
						</h1>
					</div>
					<div class="entry__content">
						<span class="entry__category"><a href="#0">TRIP PLAN</a></span>
						<h1>
							<a href="#0" title="">누구나 쉽게 여행일정을 계획할 수 있습니다.</a>
						</h1>

						<div class="entry__info">
							<a href="#0" class="entry__profile-pic"> <img class="avatar"
								src="images/avatars/user-03.jpg" alt=""></a>

							<ul class="entry__meta">
							</ul>
						</div>
					</div>
					<!-- end entry__content -->

				</div>
				<!-- end entry -->
				
				<a href="<c:url value="air/showAir.do"/>">
					<div class="entry">
						<div id="myweatherInfoDiv" style="height: 100%;">
						
						</div>
					</div>
				</a>
			<!-- end entry -->
		</div>
		<!-- end featured__small -->
	</div>
	<!-- end featured -->
	</div>
	<!-- end col-full -->
	</div>
	<!-- end pageheader-content row -->
	<br>
	<br>
	<div id="body">
	
		<section class="s-content">
				<h1 style=" text-align:center; padding-bottom:15px; font-family: 'Do Hyeon', sans-serif;">인기 여행 패키지 상품</h1>
<!-- 				<h3 style="text-align:center; color: #a6a6a6;">인기있는 여행지 상품의 랭킹을 참고해 즐거운 여행을 계획해 보세요!</h3> -->

			<div class="row masonry-wrap">
				<div class="masonry">

					<div class="grid-sizer"></div>
					
					<article class="masonry__brick entry format-standard"
						data-aos="fade-up">
						<div class="entry__thumb">
							<a href="single-standard.html" class="entry__thumb-link"> <img
								src="${pageContext.request.contextPath}/images/pic6.png"
								srcset="${pageContext.request.contextPath}/images/pic6.png"
								alt=""></a>
						</div>

						<div class="entry__text">
							<div class="entry__header">
								<div class="entry__date">
									<a href="single-standard.html">2019.02.01~2019.02.05(3박4일)</a>
								</div>
								<h1 class="entry__title">
									<a href="single-standard.html">제주도 우성수 투어</a>
								</h1>
							</div>
							<div class="entry__excerpt">
								<p>낭만이 있는 제주도 우성수에서 3박 4일을 머문다면, 어디부터 가야할까?
									제주도 우성수에서 맞이하는...
									</p>
							</div>
							<div class="entry__meta">
<!-- 								<span class="entry__meta-links"> <a href="category.html">숙소○</a> -->
<!-- 									<a href="category.html">가이드○</a> -->
								</span>
							</div>
						</div>

					</article>
					<!-- end article -->

					<article class="masonry__brick entry format-standard"
						data-aos="fade-up">

						<div class="entry__thumb">
							<a href="single-standard.html" class="entry__thumb-link"> <img
								src="${pageContext.request.contextPath}/images/pic7.png"
								srcset="${pageContext.request.contextPath}/images/pic7.png"
								alt=""></a>
						</div>

						<div class="entry__text">
							<div class="entry__header">

								<div class="entry__date">
									<a href="single-standard.html">2019.01.29~2019.01.31(1박2일)</a>
								</div>
								<h1 class="entry__title">
									<a href="single-standard.html">대관령 양떼목장</a>
								</h1>

							</div>
							<div class="entry__excerpt">
								<p>아름답고 평화로운 은빛 설경으로의 초대!
								대관령 양떼목장과 정동진 & 안목 커피거리로 여러분들을 초대합니다!</p>
							</div>

							<div class="entry__meta">
<!-- 								<span class="entry__meta-links"> <a href="category.html">Design</a> -->
<!-- 									<a href="category.html">Photography</a> -->
<!-- 								</span> -->
							</div>
						</div>

					</article>
					<!-- end article -->

					<article class="masonry__brick entry format-standard"
						data-aos="fade-up">

						<div class="entry__thumb">
							<a href="single-standard.html" class="entry__thumb-link"> <img
								src="${pageContext.request.contextPath}/images/pic8.png"
								srcset="${pageContext.request.contextPath}/images/pic8.png"
								alt=""></a>
						</div>

						<div class="entry__text">
							<div class="entry__header">

								<div class="entry__date">
									<a href="single-standard.html">2019.02.11(당일치기)</a>
								</div>
								<h1 class="entry__title">
									<a href="single-standard.html">전주왕복버스 당일여행</a>
								</h1>

							</div>
							<div class="entry__excerpt">
								<p>전주 한옥마을 어떻게 놀아볼까? 여유럽고 자유롭게 전주한옥마을 관람하고 레일바이크 체험...</p>
							</div>
							<div class="entry__meta">
<!-- 								<span class="entry__meta-links"> <a href="category.html">Design</a> -->
<!-- 									<a href="category.html">Photography</a> -->
<!-- 								</span> -->
							</div>
						</div>
					</article>

					<article class="masonry__brick entry format-standard"
						data-aos="fade-up">

						<div class="entry__thumb">
							<a href="single-standard.html" class="entry__thumb-link"> <img
								src="${pageContext.request.contextPath}/images/pic9.png"
								srcset="${pageContext.request.contextPath}/images/pic9.png"
								alt=""></a>
						</div>

						<div class="entry__text">
							<div class="entry__header">

								<div class="entry__date">
									<a href="single-standard.html">2019.02.10(당일치기)</a>
								</div>
								<h1 class="entry__title">
									<a href="single-standard.html">강원 눈꽃열차 당일여행</a>
								</h1>

							</div>
							<div class="entry__excerpt">
								<p>태백~영월 환상선 눈꽃열차 당일 버스 여행! 차창 밖 화려하게 펼처진 설경을 감상할 수 있는 환상선 눈꽃열차...</p>
							</div>
							<div class="entry__meta">
<!-- 								<span class="entry__meta-links"> <a href="category.html">Design</a> -->
<!-- 									<a href="category.html">Photography</a> -->
<!-- 								</span> -->
							</div>
						</div>
					</article>

					<article class="masonry__brick entry format-standard"
						data-aos="fade-up">

						<div class="entry__thumb">
							<a href="single-standard.html" class="entry__thumb-link"> <img
								src="${pageContext.request.contextPath}/images/pic10.png"
								srcset="${pageContext.request.contextPath}/images/pic10.png"
								alt=""></a>
						</div>

						<div class="entry__text">
							<div class="entry__header">

								<div class="entry__date">
									<a href="single-standard.html">2019.02.05(당일치기)</a>
								</div>
								<h1 class="entry__title">
									<a href="single-standard.html">전라도 당일여행</a>
								</h1>

							</div>
							<div class="entry__excerpt">
								<p>서해의 보물섬! 선유도와 고군산 군도를 찾아서!선유도, 무녀도, 정자도, 대장도 여행...</p>
							</div>
							
							<div class="entry__meta">
<!-- 								<span class="entry__meta-links"> <a href="category.html">Design</a> -->
<!-- 									<a href="category.html">Photography</a> -->
<!-- 								</span> -->
							</div>
						</div>
					</article>		
					
					<article class="masonry__brick entry format-standard"
						data-aos="fade-up">

						<div class="entry__thumb">
							<a href="single-standard.html" class="entry__thumb-link"> <img
								src="${pageContext.request.contextPath}/images/pic11.png"
								srcset="${pageContext.request.contextPath}/images/pic11.png"
								alt=""></a>
						</div>

						<div class="entry__text">
							<div class="entry__header">

								<div class="entry__date">
									<a href="single-standard.html">2019.01.25~2019.01.26(무박2일)</a>
								</div>
								<h1 class="entry__title">
									<a href="single-standard.html">다이나믹 부산 여행</a>
								</h1>

							</div>
							<div class="entry__excerpt">
								<p>하루 만에 부산 매력 몰아보기! 해동용궁사, 해운대 외 3곳 서울 출발..</p>
							</div>
							<div class="entry__meta">
<!-- 								<span class="entry__meta-links"> <a href="category.html">Design</a> -->
<!-- 									<a href="category.html">Photography</a> -->
<!-- 								</span> -->
							</div>
						</div>
					</article>		
					
					<article class="masonry__brick entry format-standard"
						data-aos="fade-up">

						<div class="entry__thumb">
							<a href="single-standard.html" class="entry__thumb-link"> <img
								src="${pageContext.request.contextPath}/images/pic12.png"
								srcset="${pageContext.request.contextPath}/images/pic12.png"
								alt=""></a>
						</div>

						<div class="entry__text">
							<div class="entry__header">

								<div class="entry__date">
									<a href="single-standard.html">2019.02.22(당일치기)</a>
								</div>
								<h1 class="entry__title">
									<a href="single-standard.html">남해 해돋이 여행</a>
								</h1>

							</div>
							<div class="entry__excerpt">
								<p>서울/경기/인천 출발 ! 남해 보리암 해돋이 무박 버스여행...</p>
							</div>
							<div class="entry__meta">
<!-- 								<span class="entry__meta-links"> <a href="category.html">Design</a> -->
<!-- 									<a href="category.html">Photography</a> -->
<!-- 								</span> -->
							</div>
						</div>
					</article>		
					
					<article class="masonry__brick entry format-standard"
						data-aos="fade-up">

						<div class="entry__thumb">
							<a href="single-standard.html" class="entry__thumb-link"> <img
								src="${pageContext.request.contextPath}/images/pic13.png"
								srcset="${pageContext.request.contextPath}/images/pic13.png"
								alt=""></a>
						</div>

						<div class="entry__text">
							<div class="entry__header">

								<div class="entry__date">
									<a href="single-standard.html">2019.01.26(당일치기)</a>
								</div>
								<h1 class="entry__title">
									<a href="single-standard.html">강원 당일여행</a>
								</h1>

							</div>
							<div class="entry__excerpt">
								<p>겨울 힐링 척산온천휴양림 온천 시르즈 5탄 + 낙산사, 속초 먹거리 ...</p>
							</div>
							<div class="entry__meta">
<!-- 								<span class="entry__meta-links"> <a href="category.html">Design</a> -->
<!-- 									<a href="category.html">Photography</a> -->
<!-- 								</span> -->
							</div>
						</div>
					</article>																									
					<!-- end article -->

				</div>
				<!-- end masonry -->
			</div>
			<!-- end masonry-wrap -->

			<div class="row">
				<div class="col-full">
					<nav class="pgn">
						<button>더 많은 패키지상품 보러가기</button>
<!-- 						<ul> -->
<!-- 							<li><a class="pgn__prev" href="#0">Prev</a></li> -->
<!-- 							<li><a class="pgn__num" href="#0">더 많은 패키지 상품 보기</a></li> -->
<!-- 							<l
i><span class="pgn__num current">2</span></li> -->
<!-- 							<li><a class="pgn__num" href="#0">3</a></li> -->
<!-- 							<li><a class="pgn__num" href="#0">4</a></li> -->
<!-- 							<li><a class="pgn__num" href="#0">5</a></li> -->
<!-- 							<li><span class="pgn__num dots">…</span></li> -->
<!-- 							<li><a class="pgn__num" href="#0">8</a></li> -->
<!-- 							<li><a class="pgn__next" href="#0">Next</a></li> -->
<!-- 						</ul> -->
					</nav>
				</div>
			</div>

		</section>
		<!-- s-content -->


		<!-- s-extra
	    ================================================== -->
		<section class="s-content">
				<h1 style=" text-align:center; padding-bottom:15px; font-family: 'Do Hyeon', sans-serif;">베스트 여행 후기</h1>

			<div class="row masonry-wrap">
				<div class="masonry">
					<article class="masonry__brick entry format-standard"
						data-aos="fade-up">

						<div class="entry__thumb">
							<a href="single-standard.html" class="entry__thumb-link"> <img
								src="${pageContext.request.contextPath}/images/pic7.png"
								srcset="${pageContext.request.contextPath}/images/pic7.png"
								alt=""></a>
						</div>

						<div class="entry__text">
							<div class="entry__header">

								<div class="entry__date">
									<a href="single-standard.html">작성일 : 2019.01.21</a>
								</div>
								<h1 class="entry__title">
									<a href="single-standard.html">강원 당일여행 후기</a>
								</h1>

							</div>
							<div class="entry__excerpt">
								<p>겨울 힐링 척산온천휴양림 온천 시르즈 5탄 + 낙산사, 속초 먹거리 ...</p>
							</div>
							<div class="entry__meta">
								<span> 
								<img style="border-radius:300px; width:50px;height:50px; display:inline;" src="${pageContext.request.contextPath}/images/avatars/user-04.jpg" />
									<p style="display:inline; font-weight:bold">관리자(admin)</p>
								</span>
							</div>
						</div>
					</article>					
			</div>
			</div>
			<!-- end tags-wrap -->
		</section>
		<!-- end s-extra -->


		<!-- s-footer
	    ================================================== -->


		<!-- preloader
	    ================================================== -->
<!-- 		<div id="preloader"> -->
<!-- 			<div id="loader"> -->
<!-- 				<div class="line-scale"> -->
<!-- 					<div></div> -->
<!-- 					<div></div> -->
<!-- 					<div></div> -->
<!-- 					<div></div> -->
<!-- 					<div></div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->

	</div>

	<div id="footer">
		<jsp:include page="/includee/main/footer.jsp" />
	</div>

	<script src="${pageContext.request.contextPath}/js/plugins.js"></script>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
	<a id="cd-menu-trigger" href="#0"><div style="background-color: #0066ff;" class="open-button" id="settings-trigger"  onclick="openForm()"><i class="fas fa-comments "></i></div></a>
   	
<div class="chat-popup chat-panel panel panel-default"  id="myForm"  onclick="openForm()">
      <div  class="panel-heading">
         <i class="fa fa-comments fa-fw"></i> Chat
      </div>
      <!-- /.panel-heading -->
      <div class="panel-body">
         <ul class="chat" id="messageArea">

         </ul>
      </div>
      <!-- /.panel-body -->
      <div class="panel-footer">
         <div class="input-group">
            <input id="message" type="text" class="form-control input-sm"
               placeholder="Type your message here..." /> <span
               class="input-group-btn">
               <button style="border-radius:150px; border-color:#ffff66; background-color: #ffff66;" class="btn btn-warning btn-sm" id="sendBtn">Send</button>
            </span>
         </div>
		<a><i id="enterBtn" class="fas fa-comment-dots fa-2x"></i></a>
<!--          <input style="height:40px; border-color:#ffff66; background-color:#ffff66;" type="button" value="단톡방 입장" id="enterBtn" /> -->
		<a id="outBtn"><i class="fas fa-times-circle fa-2x"></i></a>
<!--          <input type="button" value="단톡방 퇴장" id="outBtn" /> <br> -->

      </div>
      <!-- /.panel-footer -->
   </div>
   
   <script>
      function openForm() {
        document.getElementById("myForm").style.display = "block";
      }
      
      function closeForm() {
        document.getElementById("myForm").style.display = "none";
      }
   </script>
</body>
</html>