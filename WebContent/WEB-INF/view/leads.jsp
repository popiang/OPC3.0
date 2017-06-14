<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>

<html>

<head>
	<title>Current Leads</title>
	<link href='//fonts.googleapis.com/css?family=Advent Pro' rel='stylesheet'>
	<link href='//fonts.googleapis.com/css?family=Orbitron' rel='stylesheet'>
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

			<h1 class="title page-title">${event}</h1>
		
			<div class="leads-table-wrapper">
			<table id="leads-table" class="table">
		
				<tr>
					<th>Name</th>
					<th>HP Number</th>
					<th>Actions</th>
				</tr>
		
				<c:forEach var="lead" items="${leads}">
					<tr>
						<td>${lead.name}</td>
						<td>${lead.hpNumber}</td>
						<td>
							<a href="${pageContext.request.contextPath}/leadform?Id=${lead.id}">Edit</a>
							<a href="${pageContext.request.contextPath}/deletelead?Id=${lead.id}" onclick="return deleteFunction()">Delete</a>
						</td>
					</tr>
				</c:forEach>
		
			</table>
			</div>
			
			<c:choose>
			
				<c:when test="${action=='add'}">
					<div class="action-message"><h3>Lead has been added</h3></div>
				</c:when>
				<c:when test="${action=='edit'}">
					<div class="action-message"><h3>Lead has been edited</h3></div>
				</c:when>
				<c:when test="${action=='delete'}">
					<div class="action-message"><h3>Lead has been deleted</h3></div>
				</c:when>	
				
			</c:choose>
			
			<c:url var="logoutUrl" value="/logout" />
			<form id="form" action="${logoutUrl}" method="post">
				<input type="submit" value="Log Out">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			</form>			
		
		</div>
	
	</div>
	
	<footer>
		<p>Copyright &#169; <a href="http://www.ghhs.com.my">Golden Horse Health Sanctuary</a></p>
	</footer>
	

	<script type="text/javascript">
	
		function logout()
		{
			document.getElementById("form").submit();
		}
	
		function deleteFunction()
		{
			var del = confirm("Are you sure want to delete");
			
			if(del)
			{
				return true;
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