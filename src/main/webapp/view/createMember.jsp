<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />     
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>CreateMember</title>
</head>
<body>
<form method="post"   action="${contextPath}/member/createMember.do">
<h1  style="text-align:center">ȸ������</h1>
<table  align="center">
    <tr>
       <td width="200"><p align="right">���̵�</td>
       <td width="400"><input type="text" name="id"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">��й�ȣ</td>
        <td width="400"><input type="password"  name="pwd"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">�̸�</td>
        <td width="400"><p><input type="text"  name="name"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">�̸���</td>
        <td width="400"><p><input type="text"  name="email"></td>
    </tr>
    <tr>
        <td width="200"><p>&nbsp;</p></td>
        <td width="400">
	       <input type="submit" value="�����ϱ�">
	       <input type="reset" value="�ٽ��Է�">
       </td>
    </tr>
</table>
</form>
</body>
</html>