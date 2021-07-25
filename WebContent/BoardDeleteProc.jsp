<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>

<%
	String pass = request.getParameter("password");
	int no = Integer.parseInt(request.getParameter("no"));
	
	BoardDAO bdao = new BoardDAO();
	
	String password = bdao.getPass(no);
	
	if(pass.equals(password)){
		
		bdao.deleteBoard(no);
		response.sendRedirect("BoardList.jsp");
	}else{%>
		
<script type="text/javascript">
	alert("패스워드가 맞지 않습니다.");
	history.go(-1);
</script>		
		
	<%}%>

</body>
</html>