<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.javaex.dao.GuestDao"%>
<%@ page import="com.javaex.vo.GuestVo"%>

<%
	request.setCharacterEncoding("UTF-8");
GuestDao guestDao = new GuestDao();

List<GuestVo> gList = (List<GuestVo>) request.getAttribute("GList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/guestbook2/gc" method="get">
		<table border="1" width="800px">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value=""></td>
				<td>비밀번호</td>
				<td><input type="password" name="password" value=""></td>
			</tr>

			<tr>
				<td colspan="4"><textarea name="content"></textarea></td>
			</tr>

			<tr>
				<td colspan="4">
					<button type="submit">확인</button> 
					<input type="hidden" name="action" value="insert">
				</td>
			</tr>

		</table>
	</form>
	<br>

	<%
		for (int i = 0; i < gList.size(); i++) {
	%>

	<table border="1" width="800px">

		<tr>
			<td width="150px"><%=gList.get(i).getNo()%><input type="hidden"
				name="no" value="<%=gList.get(i).getNo()%>"></td>
			<td width="200px"><%=gList.get(i).getName()%></td>
			<td width="300px"><%=gList.get(i).getRegDate()%></td>
			<td width="150px"><a
				href="./gc?action=dform&no=<%=gList.get(i).getNo()%>">삭제</a></td>

		</tr>
		<tr>
			<td colspan="4"><%=gList.get(i).getContent()%></td>
		</tr>
	</table>

	<br>

	<%
		}
	%>

</body>
</html>