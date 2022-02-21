<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!-- 
	pageContext는 내장객체 
	- request, session, application... 내장 객체의 속성을 제어
-->

<c:set var="ctx" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div style="width:400px; padding:30px; border:1px solid #ddd; border-radius:5px; margin: 50px auto">
<!-- <form action="/WEB_day06/memberInsert.do" method="post"> -->
<form action="${ctx}/memberInsert.do" method="post">
<table class="table table-borderless">
<thead>
  <tr>
  	<th colspan="2" class="text-center"><b>회원등록</b></th>
  </tr>
  <tr>
    <td>아이디</td>
    <td><input class="form-control" type="text" name="id"></td>
  </tr>
</thead>
<tbody>
  <tr>
    <td>비밀번호</td>
    <td><input class="form-control" type="password" name="pw"></td>
  </tr>
  <tr>
    <td>이름</td>
    <td><input class="form-control" type="text" name="name"></td>
  </tr>
  <tr>
    <td>나이</td>
    <td><input class="form-control" type="text" name="age"></td>
  </tr>
  <tr>
    <td>이메일</td>
    <td><input class="form-control" type="text" name="email"></td>
  </tr>
  <tr>
    <td>전화번호</td>
    <td><input class="form-control" type="text" name="tel"></td>
  </tr>
  <tr>
    <td colspan="2" class="text-center">
    	<input type="submit" value="등록" class="btn btn-primary mr-2"/>
    	<input type="reset" value="취소" class="btn btn-info"/>
    </td>
  </tr>
</tbody>
</table>
</form>
</div>
</body>
</html>