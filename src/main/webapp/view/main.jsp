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
            alert("회원가입완료.");
         }
      </script>
   </c:when>
   <c:when test='${msg=="updateMember" }'>
      <script>
        window.onload=function(){
          alert("수정완료.");
        }
      </script>
   </c:when>
   <c:when test= '${msg=="deleteMember" }'>
      <script>
         window.onload=function(){
            alert("삭제완료.");
        } 
      </script>
	</c:when>
	<c:when test= '${msg=="loginSuccess" }'>
      <script>
         window.onload=function(){
            alert("로그인완료.");
        } 
      </script>
	</c:when>
	<c:when test= '${msg=="loginFalse" }'>
      <script>
         window.onload=function(){
            alert("로그인실패.");
        } 
      </script>
	</c:when>
	<c:when test='${msg=="loginNow" }'>
      <script>
         window.onload=function(){
            alert("이미 로그인 되었습니다.");
         }
      </script>
   </c:when>
   <c:when test='${msg=="logout" }'>
      <script>
         window.onload=function(){
            alert("로그아웃 되었습니다.");
         }
      </script>
   </c:when>
   <c:when test='${msg=="notUser" }'>
      <script>
         window.onload=function(){
            alert("로그인 후 이용가능합니다.");
         }
      </script>
   </c:when>
</c:choose>

<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
 <a href="${contextPath}/member/loginForm.do"><p>로그인</p></a></br>  
 <a href="${contextPath}/member/createMemberForm.do"><p>회원가입</p></a></br>
 <a href="${contextPath}/member/logout.do"><p>로그아웃</p></a></br> 
 <a href="${contextPath}/member/readMember.do"><p>내정보</p></a></br> 
 <a href="${contextPath}/member/updateMemberForm.do"><p>회원수정</p></a></br>
 <a href="${contextPath}/member/deleteMember.do"><p>회원삭제</p></a></br>
</body>
</html>
