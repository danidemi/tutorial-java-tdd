<%@page import="com.danidemi.tutorial.tdd.web.controller.FlashMessageFilter"%>
<%@page import="com.danidemi.tutorial.tdd.web.view.FlashMessage"%>
<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,com.danidemi.tutorial.tdd.web.model.*" %>
<!DOCTYPE html>
<%
List<Actor> actors = (List<Actor>)request.getAttribute("actors");
FlashMessage flash = (FlashMessage)request.getAttribute(FlashMessageFilter.FLASH_ATTRIBUTE);

%>
<html>
	<head>
	
		<script type="text/javascript">
function closeFlash(){
	var flashDiv = document.getElementById("flash");
	flashDiv.parentElement.removeChild(flashDiv);
}
		</script>
	
		<title>Actor List</title>
		
		<style type="text/css">
		
			BODY {
				background-color: white;
				color: #111;
			}
		
			TABLE {
				border-width: 0px;
				margin-left: auto;
				margin-right: auto;
				margin-top: 2em;
			}
			
			THEAD {
				background-color: #004;
				color: #DDD;
			}
						
			TBODY TR:HOVER{
				background-color: #AAF;			
			}
			
			#header {
				text-align: center;
				border-bottom: 1px solid #004;
			}
			
			#form {
				margin-left: auto;
				margin-right: auto;
				margin: 2em;
				border:1px solid #004;
				padding: 2em;
			}
						
			#flash {
				border-width: 1px;
				border-style: double;
				color: white;
				text-align: center;
			}
			
			#closeFlash {
				float: right;
			}
			
			.flash-error {
				background-color: #A00;
			}
			
			.flash-warning {
				background-color: #AA0;
			}
			
			.flash-info {
				background-color: #0A0;
			}
		</style>
	</head>
	
	<body>
	
		<div id="header">
			Actor List
		</div>
		
		<%if(flash!=null) {%>
			<div id="flash" class="flash-<%=flash.getPriorityName()%>">
				<%= flash.getMessage() %>
				<div id="closeFlash">
					[<a href="#" onclick="closeFlash();return false;">X</a>]
				</div>
			</div>
		<%}%>
		
		<%if(actors!=null && !actors.isEmpty()){ %>
		
			<table>
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Birth Date</th>
						<th>Age</th>
						<td>&nbsp;</td>
					</tr>
				</thead>
				<tbody>
			
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
				
				</tbody>
			</table>
			
		<%}else{%>
			Still empty...
		<%}%>
		
		<div id="form">
			Add new actor
			<form action="<%=request.getServletContext().getContextPath()%>/new" method="POST">
				<label for="firstName">First Name</label>
				<input id="firstName" type="text" name="firstName"/>
				<br/>
				<label for="firstName">Last Name</label>
				<input id="lastName" type="text" name="lastName"/>
				<br/>
				<label for="firstName">Birth Date</label>
				<input id="birthDate" type="date" name="birthDate" />
				<br/>
				<input type="submit" value="Add" placeholder="yyyy-mm-dd" /> 
			</form>
		</div>
	</body>
	
</html>