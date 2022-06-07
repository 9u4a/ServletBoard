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
<title>ReadMember</title>
</head>
<body>
<h1 style="text-align:center">회원 정보 수정창</h1>
<form  method="post" action="${contextPath}/member/main">
 <table align="center" >
   <tr>
     <td width="200"><p align="right" >아이디</td>
     <td width="400"><input type="text" value="${findMem.user_ID}" disabled></td>
     
   </tr>
 <tr>
     <td width="200"><p align="right" >비밀번호</td>
     <td width="400"><input type="text" value="${findMem.user_Pw}" disabled></td>
   </tr>
   <tr>
     <td width="200"><p align="right" >이름</td>
     <td width="400"><input type="text" value="${findMem.user_Name}" disabled></td>
   </tr>
   <tr>
     <td width="200"><p align="right" >이메일</td>
     <td width="400"><input type="text" value="${findMem.user_Email}" disabled></td>
   </tr>
   <tr>
    <td width="200"><p>&nbsp;</p></td>
    <td width="400">
    	<input type="submit" value="메인으로" >
    </td>
   </tr>
 </table>
</form>
</body>
</html>