<%@page import="model.BoardBean"%>
<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<%
	int no = Integer.parseInt(request.getParameter("no").trim());

	BoardDAO bdao = new BoardDAO();

	BoardBean bean = bdao.getOneBoard(no);
	
%>

<center>
	
	<h2> 게시글 수정하기</h2>
	<form action="BoardUpdateProc.jsp" method="post">
	<table width="600" border="1" bgcolor="skyblue">
	 <tr height="40">
	 	<td align="center" width="120"> 글번호</td>
	 	<td align="center" width="180"><%=bean.getNo() %></td>
	 	<td align="center" width="120">조회수 </td>
	 	<td align="center" width="180"><%=bean.getReadcount() %></td>
 	</tr>
 	<tr height="40">
	 	<td align="center" width="120"> 작성자</td>
	 	<td align="center" width="180"><%=bean.getWriter() %></td>
	 	<td align="center" width="120">작성일 </td>
	 	<td align="center" width="180"><%=bean.getDate() %></td>
 	</tr>
 	<tr height="40">
	 	<td align="center" width="120"> 이메일</td>
	 	<td align="center" colspan="3"><%=bean.getEmail() %></td>
 	</tr>
 	<tr height="40">
	 	<td align="center" width="120">제목 </td>
	 	<td align="center" colspan="3"><%=bean.getSubject() %></td>	 	
 	</tr>
 	<tr height="40">
	 	<td align="center" width="150"> 글내용 </td>
			<td width="450"> <textarea rows="10" cols="50" name="content"></textarea></td>
 	</tr>
 	<tr height="40">
 		<td align="center" colspan="4">
 		<input type="submit" value="수정하기"/>
 		<input type="hidden" name="no" value="<%=bean.getNo()%>"/>
 		</form>
 		<input type="button" value="뒤로가기" onclick="history.go(-1)"/>
	
	</table>
	
</center>

</body>
</html>