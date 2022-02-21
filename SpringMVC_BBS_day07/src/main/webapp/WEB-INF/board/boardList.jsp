<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>                                  
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>            
<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js'></script>   
<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js'></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#writeBtn").click(() => {
			location.href="<c:url value='/register.do'/>";
		})
		
		/* 페이징 */
		var moveForm = $("#moveForm");		
		$(".page-item a").on("click", function(e){
			e.preventDefault();
			
			moveForm.find("input[name='viewPage']").val($(this).attr("href"));
			moveForm.submit();
		});
		
		/* 검색  */
		var searchForm = $("#searchForm");
		
		$("#searchBtn").on("click", function(e){
			if(!searchForm.find("input[name='keyWord']").val()){
				alert("키워드를 입력하세요!!");
				$("#keyWord").focus();
				return false;
			}	
			/* alert(searchForm.find("input[name='keyWord']").val()); */
			searchForm.submit();
		});
		
	});
</script>
         
</head>
<body>
<div class="container mt-5">
<h2>스프링 게시판 </h2>
	<form action="list.do" method="get" id="moveForm">
		<input type="hidden" name="viewPage" value="${viewPage}"/>
		<input type="hidden" name="totalCnt" value="${totalCnt}"/>
		<input type="hidden" name="searchType" value="${vo.searchType}"/>
		<input type="hidden" name="keyWord" value="${keyWord}"/>
	</form>


	<form id="searchForm" method="post" action="list.do">
		<div class="d-flex justify-content-end">
			<div class="d-flex col-md-5 justify-content-end px-0">
				<select class="custom-select col-md-4 mr-2" name="searchType">
				  <option value=""
				  	<c:out value="${vo.searchType == null ? 'selected':''}"/>>선택</option>
				  <option value="S"
				  	<c:out value="${vo.searchType eq 'S' ? 'selected':''}"/>>제목</option>
				  <option value="C"
				  	<c:out value="${vo.searchType eq 'C' ? 'selected':''}"/>>내용</option>
				  <option value="W"
				  	<c:out value="${vo.searchType eq 'W' ? 'selected':''}"/>>글쓴이</option>
				</select>	    
			    <input type="text" class="form-control col-md-6" id="keyWord" name="keyWord" 
			    placeholder="Search" style="border-radius:4px 0 0 4px" value="${vo.keyWord}">
			  	<button id="searchBtn" class="btn btn-info" style="width:40px; border-radius:0 4px 4px 0;"><i class="fa fa-search"></i></button>
		  	</div>
	  	</div>
  	</form>
	<table class="table">
	<div>Total : ${totalCnt}</div>
		<thead class="thead-light">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>조회수</th>
				<th>글쓴이</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="bidCnt" value="${rowNumber}"/>
			<%-- <c:set var="bidCnt" value="1"/> --%>
				<c:forEach var="bList" items="${list}">
					<tr>
						<%-- <td>${vo.bid}</td> --%>
						<td><c:out value="${bidCnt}"/></td>
						<td><a href="<c:url value='/view.do?bid=${bList.bid}&viewPage=${viewPage}&searchType=${vo.searchType}&keyWord=${vo.keyWord}&totalCnt=${totalCnt}'/>">${bList.subject}</td>
						<td>${bList.hit}</td>
						<td>${bList.writer}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${bList.regDate}"/></td>
					</tr>
				<c:set var="bidCnt" value="${bidCnt - 1}"/>
			</c:forEach>
			<tr>
				<td colspan="5" class="text-center"><button class="btn btn-primary" id="writeBtn">글쓰기</button></td>
			</tr>
		</tbody>
		
	</table>
	</div>

<!-- ############# Page navigation ############# -->
	<ul class="pagination justify-content-center">
	
	  <li class="page-item ${prevPage <=0 ? 'disabled': ''}">
	    <a class="page-link" href="${prevPage}">이전</a>
	  </li>
  
	  <c:forEach var="i" begin="${blockStart}" end="${blockEnd}">	  	
	  	<li class="page-item ${viewPage == i ? 'active':''}">
	  		<a class="page-link" href="${i}">${i}</a>
	  	</li>
	  </c:forEach>
	  		  
	  <li class="page-item ${ blockEnd >= totalPage ? 'disabled':''}">
	    <a class="page-link" href="${nextPage}">다음</a>
	  </li>
	</ul>
</div>	
</body>
</html>