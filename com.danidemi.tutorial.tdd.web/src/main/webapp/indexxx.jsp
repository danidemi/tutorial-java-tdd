<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,com.danidemi.tutorial.tdd.web.model.*" %>
<%
List<Actor> actors = (List<Actor>)request.getAttribute("actors"); 
%>
<html>
	<head>
		<title>Actor List</title>
	</head>
	
	<body>
		Actor List
		
		<%if(actors!=null){ %>
			<%for(Actor actor: actors){%>
				<div>
				<%= actor.getFirstName() %>
				<%= actor.getLastName() %>
				<%= actor.getBirthDate() %>
				
				<input type="button" value="-"/>
				</div>
			<%}%>
		<%}else{%>
			Still empty...
		<%}%>
		
		<div>
			<form action="">
				<label for="firstName">First Name</label>
				<input id="firstName" type="text" name="firstName"/>
				<label for="firstName">Last Name</label>
				<input id="lastName" type="text" name="lastName"/>
				<label for="firstName">Birth Date</label>
				<input id="birthDate" type="date" name="birthDate" />
				<input type="button">Add</input> 
			</form>
		</div>
	</body>
	
</html>