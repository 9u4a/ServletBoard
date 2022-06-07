<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>CreateMember</title>
</head>
<body>
<form method="post"   action="${contextPath}/member/login.do">
<h1  style="text-align:center">로그인</h1>
<table  align="center">
    <tr>
       <td width="200"><p align="right">아이디</td>
       <td width="400"><input type="text" name="id"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">비밀번호</td>
        <td width="400"><input type="password"  name="pwd"></td>
    </tr>
    <tr>
        <td width="200"><p>&nbsp;</p></td>
        <td width="400">
	       <input type="submit" value="로그인">
	       <input type="reset" value="다시입력">
       </td>
    </tr>
</table>
</form>
</body>
</html>