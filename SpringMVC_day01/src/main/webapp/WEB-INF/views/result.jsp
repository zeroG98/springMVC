<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
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
	function getFile(fileName) {
		location.href ="<c:url value='/download.do'/>?fileName="+ fileName; 
	}
</script>

</head>
<body>
<div class="container mt-5 d-flex justify-content-center">
	<div class="w-50 border rounded px-5 py-5 shadow"">
		  <h3 class="text-center">파일 업로드 확인</h3>	
		  <div class="alert alert-success mt-3 py-2"> 파일 업로드 <strong>성공!!</strong></div>
		  	  
	  <div class="d-flex mt-5">
		  <div class="form-group col-md-6 pl-0">
		    <label for="id" style="display:block">아이디</label>
		    <input type="text" class="form-control" id="id" name="id" value="${map.id}" disabled/>
		  </div>
		  <div class="form-group col-md-6 pr-0">
		    <label for="name">이름</label>
		    <input type="text" class="form-control" id="name" name="name" value="${map.name}" disabled/>
		  </div>
	  </div>
	  <div>
	  	<table class="table table-borderless">
	  		<c:forEach var="fName" items="${map.fileList}"> 	
		  		<tr>
		  			<td>${fName}</td>
		  			<td><a class="btn btn-sm btn-outline-info px-2" href="javascript:getFile('${fName}')">다운로드 <i class="far fa-save"></i></a></td>
		  		</tr>
	  		</c:forEach>
	  		
	  		<tr>
	  			<td colspan="2">
	  				<a class="btn btn-primary" href="<c:url value='/form.do'/>">다시 업로드하기</a>
	  			</td>
	  		</tr>
	  	</table>
	  </div>
	</div>
</div> <!-- .container -->

</body>
</html>