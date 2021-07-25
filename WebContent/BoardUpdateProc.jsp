<%@page import="model.BoardBean"%>
<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<%
	request.setCharacterEncoding("utf-8");

%>

<jsp:useBean id="boardbean" class="model.BoardBean">
	<jsp:setProperty name="boardbean" property="*" />
</jsp:useBean>

<%
	BoardDAO bdao = new BoardDAO();

	String pass = bdao.getPass(boardbean.getNo());
	
	if(pass.equals(boardbean.getPassword())){
		bdao.updateBoard(boardbean);
		response.sendRedirect("BoardList.jsp");	
	}else{
%>
	<script type="text/javascript">
		alert("패스워드가 일치하지 않습니다.")
		history.go(-1);
	</script>		
	<%}%>


</body>
</html>