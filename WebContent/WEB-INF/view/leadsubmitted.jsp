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
	<link href='//fonts.googleapis.com/css?family=Open Sans' rel='stylesheet'>
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

			<h3 class="page-title">Successful</h3>

			<a class="successful" href="${pageContext.request.contextPath}/">Your Lucky Draw Form Has Been Submitted</a>	

			
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