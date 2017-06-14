<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
	<title>Login Page</title>
	<link href='//fonts.googleapis.com/css?family=Advent Pro' rel='stylesheet'>
	<link href='//fonts.googleapis.com/css?family=Orbitron' rel='stylesheet'>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
</head>

<body onload='document.f.username.focus();'>

	<header>
		<div id="logo-container">
			<a id="logo1"  href="http://www.ghhs.com.my"><img alt="GHHS" src="${pageContext.request.contextPath}/static/images/ghhs_logo.png"></a>
			<h1 id="logo2"><a href="${pageContext.request.contextPath}/">OPC LEAD CAPTURER</a></h1>
		</div>
	</header>

	<div id="big-wrapper">

		<div id="login-form-wrapper">
	
			<h3 class="page-title" id="login-head">Welcome</h3>
		
			<form name='f' action='${pageContext.request.contextPath}/login'
				method='POST'>
		
				<table class="login-form-table">

					<tr>
						<td><input class="login-field" type='text' name='username' placeholder="Username"></td>
					</tr>
					<tr>
						<td><input class="login-field" type='password' name='password' placeholder="Password" /></td>
					</tr>
					<tr>
						<td colspan='2'>
							<input class="login-button" name="submit" type="submit" value="Login" />
						</td>
					</tr>
					
				</table>
		
				<c:if test="${param.error!=null}">	
					<p class="error" id="login-error" >Incorrect username or password</p>
				</c:if>
		
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
		
			</form>
			
			<c:if test="${param.loggedout==true}">
				<p class="action-message" id="action-message">You've successfully logged out</p>	
			</c:if>
		
		</div>
	
	</div>
	
	<footer>
		<p>Copyright &#169; <a href="http://www.ghhs.com.my">Golden Horse Health Sanctuary</a></p>
	</footer>

	<script type="text/javascript">

		function closeDiv()
		{
			var actionMessage = document.getElementById("action-message");
			var loginError = document.getElementById("login-error");
			
			if(actionMessage != null)
			{
				actionMessage.style.display = "none";
			}
			
			if(loginError != null)
			{
				loginError.style.display = "none";
			}
		}
		
		window.setTimeout(closeDiv, 3000);
	
	</script>

</body>

</html>