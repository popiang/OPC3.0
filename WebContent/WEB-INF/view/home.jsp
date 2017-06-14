<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fm" %>

<!DOCTYPE html>

<html>

<head>
	<title>Main Page</title>
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
	
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<h1 id="welcome-head" class="page-title">Welcome Admin</h1>
				<a id="enter-admin-button" href="${pageContext.request.contextPath}/adminpage">Enter Admin Page</a>
			</sec:authorize>	
			
			<sec:authorize access="hasRole('ROLE_USER')">
			
				<h3 class="page-title">Event: ${event}</h3>

				<div class="user-home-wrapper">
			
				<a href="${pageContext.request.contextPath}/allleads">View All Leads</a>
			
				<fm:form id="user-home-form" method="post" action="${pageContext.request.contextPath}/leadform" commandName="lead">
		
					<a href="#" onclick="document.getElementById('user-home-form').submit()">Add New Lead</a>
		
					<fm:select cssClass="create-lead-control dropdown" path="tmCode">
					
						<fm:option cssClass="dropdown" value="J7264" label="J7264" />
						<fm:option cssClass="dropdown" value="A8220" label="A8220" />
						<fm:option cssClass="dropdown" value="W2002" label="W2002" />
						<fm:option cssClass="dropdown" value="J4505" label="J4505" />
					
					</fm:select>
									
				</fm:form>
				
				</div>
				
			</sec:authorize>
			
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
		
	</script>
	
</body>

</html>