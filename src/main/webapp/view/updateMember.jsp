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
<title>Insert title here</title>
<style>
  .cls1 {
     font-size:40px;
     text-align:center;
   }
</style>
</head>
<body>
	<h1 class="cls1">ȸ�� ���� ����â</h1>
<form  method="post" action="${contextPath}/member/updateMember.do?id=${memInfo.id}">
 <table align="center" >
   <tr>
     <td width="200"><p align="right" >���̵�</td>
     <td width="400"><input   type="text" name="id" value="${memInfo.id}" disabled ></td>
     
   </tr>
 <tr>
     <td width="200"><p align="right" >��й�ȣ</td>
     <td width="400"><input   type="password" name="pwd" value="${memInfo.pwd}" >
     </td>
   </tr>
   <tr>
     <td width="200"><p align="right" >�̸�</td>
     <td width="400"><input   type="text" name="name" value="${memInfo.name}" ></td>
   </tr>
   <tr>
     <td width="200"><p align="right" >�̸���</td>
     <td width="400"><input   type="text" name="email"  value="${memInfo.email}" ></td>
   </tr>
   <tr align="center" >
    <td colspan="2" width="400"><input type="submit" value="�����ϱ�" >
       <input type="reset" value="�ٽ��Է�" > </td>
   </tr>
 </table>
</form>
</body>
</html>