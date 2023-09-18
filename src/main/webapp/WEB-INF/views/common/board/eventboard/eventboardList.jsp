<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/eventboard/eventboardView.do" var="eventList">
	<c:param name="bo_no">1</c:param>
</c:url>

<section class="s-content">
	<div class="row masonry-wrap">
		<table class="table">
			<thead>
				<tr>
					<th><abbr title="Position">Pos</abbr></th>
					<th>Team</th>
					<th><abbr title="Played">Pld</abbr></th>
					<th><abbr title="Won">W</abbr></th>
					<th><abbr title="Drawn">D</abbr></th>
					<th><abbr title="Lost">L</abbr></th>
					<th><abbr title="Goals for">GF</abbr></th>
					<th><abbr title="Goals against">GA</abbr></th>
					<th><abbr title="Goal difference">GD</abbr></th>
					<th><abbr title="Points">Pts</abbr></th>
					<th>Qualification or relegation</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>1</th>
					<td><a href="${eventList}">Leicester City</a> <strong>(C)</strong>
					</td>
					<td>38</td>
					<td>23</td>
					<td>12</td>
					<td>3</td>
					<td>68</td>
					<td>36</td>
					<td>+32</td>
					<td>81</td>
					<td>Qualification for the <a href="<c:url value=""/>">Champions
							League group stage</a></td>
				</tr>
	
				<tr class="is-selected">
	
	
					<th>2</th>
					<td><a
						href="https://en.wikipedia.org/wiki/Manchester_City_F.C."
						title="Manchester City F.C.">Manchester City</a></td>
					<td>38</td>
					<td>19</td>
					<td>9</td>
					<td>10</td>
					<td>71</td>
					<td>41</td>
					<td>+30</td>
					<td>66</td>
					<td>Qualification for the <a
						href="https://en.wikipedia.org/wiki/2016%E2%80%9317_UEFA_Champions_League#Play-off_round"
						title="2016–17 UEFA Champions League">Champions League play-off
							round</a></td>
				</tr>
			</tbody>
		</table>
		
		<a href='<c:url value="/eventboard/eventboardInsert.do"/>'>글쓰기 버튼</a>
	</div>
</section>