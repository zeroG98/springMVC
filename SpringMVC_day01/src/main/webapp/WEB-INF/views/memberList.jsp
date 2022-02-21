<%@page import="java.util.ArrayList"%>
<%@page import="mr.web.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<c:set var="ctx" value="${pageContext.request.contextPath}"/> 
        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>                                  
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>
<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>            
<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js'></script>   
<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js'></script>         
</head>
<body>
<script type="text/javascript">
	function deleteFunc(num){
		location.href="${ctx}/memberDelete.do?num="+num;
	}
	
	function showList(){
		$.ajax({
			url:"<c:url value='/memberAjaxList.do'/>",
			type: "get",
			dataType: "json",
			success: resultList,
			error: function() {alert("error");}
		});
	}
	
	function resultList(data){
		console.log(data);
		alert(data);
		
		let html="<table class='table'>";
		html+="<thead class='thead-light'>";
		html+="<tr>            ";
	    html+="<th>번호</th>   ";
	    html+="<th>아이디</th> ";
	    html+="<th>비밀번호</th>";
	    html+="<th>이름</th>   ";
	    html+="<th>나이</th>   ";
	    html+="<th>이메일</th> ";
	    html+="<th>전화번호</th>";
	    html+="</tr>            ";
		html+="</thead>";
		html+="<tbody>";
		$.each(data, function(index, obj){
		    html+="<tr>            ";
			html+="<td>"+obj.num+"</td>";			
			html+="<td>"+obj.id+"</td>";	
			html+="<td>"+obj.pw+"</td>";	
			html+="<td>"+obj.name+"</td>";	
			html+="<td>"+obj.age+"</td>";	
			html+="<td>"+obj.email+"</td>";	
			html+="<td>"+obj.tel+"</td>";
		    html+="</tr>            ";
		});
		
		html+="</tbody>";
		html+="</table>";
		
		$("#ajax-list").html(html);
		
	}
	
	
</script>
<div class="container">
<h2>회원리스트</h2>
<table class='table'>
	<thead class="thead-light">
	  <tr> 
	    <th>번호</th>    
	    <th>아이디</th> 
	    <th>비밀번호</th>
	    <th>이름</th>   
	    <th>나이</th>  
	    <th>이메일</th> 
	    <th>전화번호</th>
	    <th>삭제</th>
	  </tr>         
	</thead>
	<tbody>
	<c:forEach var="dto" items="${memberList}">
	  <tr>           
	    <td>${dto.num}</td>        
	    <td><a href="${ctx}/memberInfo.do?num=${dto.num}">${dto.id}</a></td>
	    <td>${dto.pw}</td>        
	    <td>${dto.name}</td>      
	    <td>${dto.age}</td>       
	    <td>${dto.email}</td>     
	    <td>${dto.tel}</td>
	    <td><input type="button" class="btn btn-danger" value="삭제" onclick="deleteFunc(${dto.num})"></td>
	  </tr> 
	</c:forEach>  
		<tr>
			<td colspan="8" class="text-center">
				<input type="button" value="회원등록" class="btn btn-primary" onclick="location.href='${ctx}/memberJoin.do'"/>
			</td>
		</tr>
		<tr>
			<td colspan="8" class="text-center">
				<input type="button" value="Ajax리스트 보기" class="btn btn-primary" onclick="showList()"/>
			</td>
		</tr>
		<tr>
			<td colspan="8" class="text-center" id="ajax-list">
			</td>
		</tr>           
	</tbody>
</table>
</div>	
</body>
</html>