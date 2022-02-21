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
	let num= 1;
	function fileAppend(){
		$("#div-file").append("<br>"+"<input type='file' name='file"+num+"'/>");
		num++;
	}
</script>

</head>
<body>
<div class="container mt-5 d-flex justify-content-center">
	<div class="w-50 border rounded px-5 py-5 shadow"">
		<form method="post" name="uploadForm" action="<c:url value='/upload.do'/>" enctype="multipart/form-data">		
		  <h3 class="text-center">파일 업로드</h3>		  
		  <div class="form-group">
		    <label for="id" style="display:block">아이디</label>
		    <input type="text" class="form-control" id="id" name="id" placeholder="아이디를 입력하세요"/>
		  </div>
		  <div class="form-group">
		    <label for="name">이름</label>
		    <input type="text" class="form-control" id="name" name="name" placeholder="이름을 입력하세요"/>
		  </div>
		  <div class="form-group">
		    <label for="myFile">업로드 파일 추가하기</label>
		    <input type="button" class="form-control btn btn-light col-md-5" value="파일추가" onclick="fileAppend()"/>
		    <br><div id="div-file"></div>
		  </div>
		  <div class="form-group">
		  	<button type="submit" class="btn btn-primary form-control">파일 업로드</button>
		  </div>
		</form>
	</div>
</div> <!-- .container -->

</body>
</html>