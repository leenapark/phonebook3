<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<form action="/phonebook3/phone/modify" method="">
			이름(name):<input type="text" name="name" value="${pList.name }"><br>
			핸드폰(hp):<input type="text" name="hp" value="${pList.hp }"><br>
			회사(company):<input type="text" name="company" value="${pList.company }"><br>
			
			<!-- 아래 두 영역은 히든 처리로 노출하지 않는 부분 test 때만 text로 두고 확인한다 -->
			id: <input type="text" name="id" value="${pList.personId }">		
			<button type="submit">등록</button>
		</form>
	
	
</body>
</html>