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

<c:choose>
   <c:when test='${msg=="createMember" }'>
      <script>
         window.onload=function(){
            alert("ȸ�����ԿϷ�.");
         }
      </script>
   </c:when>
   <c:when test='${msg=="updateMember" }'>
      <script>
        window.onload=function(){
          alert("�����Ϸ�.");
        }
      </script>
   </c:when>
   <c:when test= '${msg=="deleteMember" }'>
      <script>
         window.onload=function(){
            alert("�����Ϸ�.");
        } 
      </script>
	</c:when>
	<c:when test= '${msg=="loginSuccess" }'>
      <script>
         window.onload=function(){
            alert("�α��οϷ�.");
        } 
      </script>
	</c:when>
	<c:when test= '${msg=="loginFalse" }'>
      <script>
         window.onload=function(){
            alert("�α��ν���.");
        } 
      </script>
	</c:when>
	<c:when test='${msg=="loginNow" }'>
      <script>
         window.onload=function(){
            alert("�̹� �α��� �Ǿ����ϴ�.");
         }
      </script>
   </c:when>
   <c:when test='${msg=="logout" }'>
      <script>
         window.onload=function(){
            alert("�α׾ƿ� �Ǿ����ϴ�.");
         }
      </script>
   </c:when>
   <c:when test='${msg=="notUser" }'>
      <script>
         window.onload=function(){
            alert("�α��� �� �̿밡���մϴ�.");
         }
      </script>
   </c:when>
</c:choose>

<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
 <a href="${contextPath}/member/loginForm.do"><p>�α���</p></a></br>  
 <a href="${contextPath}/member/createMemberForm.do"><p>ȸ������</p></a></br>
 <a href="${contextPath}/member/logout.do"><p>�α׾ƿ�</p></a></br> 
 <a href="${contextPath}/member/readMember.do"><p>������</p></a></br> 
 <a href="${contextPath}/member/updateMemberForm.do"><p>ȸ������</p></a></br>
 <a href="${contextPath}/member/deleteMember.do"><p>ȸ������</p></a></br>
</body>
</html>
