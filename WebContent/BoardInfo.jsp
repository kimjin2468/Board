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
	<h2> 게시글 보기</h2>
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
	 	<td align="center" width="120">글 내용 </td>
	 	<td align="center" colspan="3"><%=bean.getContent() %></td>	 	
 	</tr>
 	<tr height="40">
 		<td align="center" colspan="4">
 		<input type="button" value="답글쓰기" onclick="location.href='BoardReWriteForm.jsp?no=<%=bean.getNo()%>&ref=<%=bean.getRef()%>&re_step=<%=bean.getRe_step()%>&re_level=<%=bean.getRe_level()%>'">
 		<input type="button" value="수정하기" onclick="location.href='BoardUpdateForm.jsp?no=<%=bean.getNo()%>'">
 		<input type="button" value="삭제하기" onclick="location.href='BoardDeleteForm.jsp?no=<%=bean.getNo()%>'">
 		<input type="button" value="목록보기" onclick="location.href='BoardList.jsp'">
	
	</table>
	
</center>

</body>
</html>