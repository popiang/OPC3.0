<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>

<html>

<head>
	<title>Admin Page</title>
	<link href='//fonts.googleapis.com/css?family=Orbitron' rel='stylesheet'>
	<link href='//fonts.googleapis.com/css?family=Advent Pro' rel='stylesheet'>	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
</head>

<body>

	<header>
		<div id="logo-container">
			<a id="logo1"  href="http://www.ghhs.com.my"><img alt="GHHS" src="${pageContext.request.contextPath}/static/images/ghhs_logo.png"></a>
			<h1 id="logo2"><a href="${pageContext.request.contextPath}/">OPC LEAD CAPTURER</a></h1>
		</div>
	</header>

	<div class="big-wrapper">
	
		<div class="main-body">
		
			<sec:authorize access="isAuthenticated()">
				<div class="home-logout-link">
					<p><a class="home" href="${pageContext.request.contextPath}/">Home</a></p>
					<p><a class="logout" href="#" onclick="logout()">Logout</a></p>
				</div>
			</sec:authorize>

			<h2 class="page-title">Admin Page</h2>
		
			<table class="table" id="users-table">
		
				<tr>
					<th>Username</th>
					<th>Authority</th>
					<th>Event</th>
					<th>No Of Leads</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="user" items="${users}">
					<tr>
		
						<td>${user.username}</td>
						<td>${user.authority}</td>
						<td>${user.event}</td>
						<td style="text-align: center;"><span id="${user.username}">-</span></td>
						<td>
							<a class="action-button" href="${pageContext.request.contextPath}/edituser?id=${user.id}">Edit</a>
							<c:if test="${user.authority == 'ROLE_USER'}">
								<a class="action-button" href="${pageContext.request.contextPath}/deleteuser?id=${user.id}" onclick="return checkDelete()">Delete</a>
								<a class="action-button" href="${pageContext.request.contextPath}/exportleads?event=${user.event}">Export</a>
							</c:if>
						</td>
		
					</tr>
				</c:forEach>
		
			</table>
			
			<a id="create-user-button" href="${pageContext.request.contextPath}/createuserform">Create New User</a>
			
			<c:choose>
			
				<c:when test="${action=='adduser'}">
					<div class="action-message"><h3>User has been added</h3></div>	
				</c:when>
				<c:when test="${action=='edituser'}">
					<div class="action-message"><h3>User has been edited</h3></div>
				</c:when>
				<c:when test="${action=='deleteuser'}">
					<div class="action-message"><h3>User has been deleted</h3></div>
				</c:when>
				<c:when test="${action=='export'}">
					<div class="action-message"><h3>Leads has been exported</h3></div>
				</c:when>
			
			</c:choose>
			
			<c:url var="logoutUrl" value="/logout" />
			<form id="form" action="${logoutUrl}" method="post">
				<input type="submit" value="Log Out">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			</form>
		
		</div>
	
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.2.1.js"></script>
	
	<footer>
		<p>Copyright &#169; <a href="http://www.ghhs.com.my">Golden Horse Health Sanctuary</a></p>
	</footer>
	
	<script type="text/javascript">
	
		function updateNoOfLeads(data)
		{
			for(var key in data)
			{
				$("#" + key).text(data[key]);				
			}
		}
	
		function updatePage()
		{
			$.getJSON("${pageContext.request.contextPath}/noofleads", updateNoOfLeads);
		}
	
		function onLoad()
		{
			updatePage();
			window.setInterval(updatePage, 5000);
		}
	
		$(document).ready(onLoad);
	
		function logout()
		{
			document.getElementById("form").submit();
		}
	
		function checkDelete()
		{
			var result = confirm("Are you sure want to delete the user?");
			
			if(result)
			{
				var doubleConfirm = confirm("All leads will also be deleted. Are you sure want to proceed?")
				
				if(doubleConfirm)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		
		function closeDiv()
		{
			var x = document.getElementsByClassName("action-message"); 
			for(var i = 0; i < 4; ++i )
			{
				x[i].style.display = "none";	
			}
		}
		
		window.setTimeout(closeDiv, 3000);
	
	</script>
	
</body>

</html>



