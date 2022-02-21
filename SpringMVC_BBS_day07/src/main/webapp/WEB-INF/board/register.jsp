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
<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>            
<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js'></script>   
<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js'></script>
</head>
<body>
<div class="container mt-5 d-flex justify-content-center">
	<div class="w-50 border rounded px-5 py-5 shadow"">
		<form method="post" action="<c:url value='register.do'/>">		
		  <h3 class="text-center">글쓰기</h3>		  
		  <div class="form-group">
		    <label for="subject" style="display:block">제목</label>
		    <input type="text" class="form-control" id="subject" name="subject" placeholder="제목을 입력하세요"/>
		  </div>
		  <div class="form-group">
		    <label for="contents">내용</label>
		    <textarea class="form-control" id="contents" name="contents" rows="3"></textarea>
		  </div>
		  <div class="form-group">
		    <label for="writer">작성자</label>
			<input type="text" class="form-control" id="writer" name="writer" placeholder="작성자를 입력하세요"/>
		  </div>
		  <div class="form-group">
		  	<button type="submit" class="btn btn-primary form-control">등록</button>
		  </div>
		</form>
	</div>
</div> <!-- .container -->

</body>
</html>