<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,com.danidemi.tutorial.tdd.web.model.*" %>
<!DOCTYPE html>
<%
List<Actor> actors = (List<Actor>)request.getAttribute("actors"); 
%>
<html>
	<head>
		<title>Actor List</title>
		<style type="text/css">
			TABLE {
				border-width: 0px;
				background-color: #111;
			}
			
			TD {
				background-color: #DDD;
				color: #111;
			}
			
			TH {
				background-color: #111;
				color: #DDD;
			}
		</style>
	</head>
	
	<body>
		Actor List
		
		<%if(actors!=null && !actors.isEmpty()){ %>
		
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Birth Date</th>
					<th>Age</th>
					<td>&nbsp;</td>
				</tr>
			
				<%for(Actor actor: actors){%>
					<tr>
						<td><%= actor.getFirstName() %></td>
						<td><%= actor.getLastName() %></td>
						<td><%= actor.getBirthDate() %></td>
						<td><%= actor.getAgeInYears(new Date()) %></td>
						<td>
							<form action="<%=request.getServletContext().getContextPath()%>/delete" method="POST">
								<input type="hidden" name="id" value="<%= actor.getId() %>" />
								<input type="submit" value="del"/>
							</form>
						</td>
					</tr>
				<%}%>
			</table>
			
		<%}else{%>
			Still empty...
		<%}%>
		
		<div>
			<form action="<%=request.getServletContext().getContextPath()%>/new" method="POST">
				<label for="firstName">First Name</label>
				<input id="firstName" type="text" name="firstName"/>
				<label for="firstName">Last Name</label>
				<input id="lastName" type="text" name="lastName"/>
				<label for="firstName">Birth Date</label>
				<input id="birthDate" type="date" name="birthDate" />
				<input type="submit" value="Add" placeholder="yyyy-mm-dd" /> 
			</form>
		</div>
	</body>
	
</html>