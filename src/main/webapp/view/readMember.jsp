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
<h1 style="text-align:center">ȸ�� ���� ����â</h1>
<form  method="post" action="${contextPath}/member/main">
 <table align="center" >
   <tr>
     <td width="200"><p align="right" >���̵�</td>
     <td width="400"><input type="text" value="${findMem.user_ID}" disabled></td>
     
   </tr>
 <tr>
     <td width="200"><p align="right" >��й�ȣ</td>
     <td width="400"><input type="text" value="${findMem.user_Pw}" disabled></td>
   </tr>
   <tr>
     <td width="200"><p align="right" >�̸�</td>
     <td width="400"><input type="text" value="${findMem.user_Name}" disabled></td>
   </tr>
   <tr>
     <td width="200"><p align="right" >�̸���</td>
     <td width="400"><input type="text" value="${findMem.user_Email}" disabled></td>
   </tr>
   <tr>
    <td width="200"><p>&nbsp;</p></td>
    <td width="400">
    	<input type="submit" value="��������" >
    </td>
   </tr>
 </table>
</form>
</body>
</html>