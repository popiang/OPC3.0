<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>

<html>

<head>
	<title>Create User Page</title>
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

			<h2 class="page-title">Create Admin</h2>

			<fm:form onsubmit="return canSubmit()" action="${pageContext.request.contextPath}/createadmin" method="post" commandName="user">
			
				<table id="create-user-form-table">
				
					<tr>
						<td class="form-label">Username</td>
						<td>
							<fm:input cssClass="create-user-field" path="username" /><br>
							<fm:errors path="username" cssClass="error"></fm:errors>
						</td>
					</tr>
					
					<tr>
						<td class="form-label">Password</td>
						<td>
							<fm:input cssClass="create-user-field" onblur="checkPasswordSize()"  id="password" type="password" path="password" /><br>
							<fm:errors path="password" cssClass="error"></fm:errors>
						</td>
					</tr>
					
					<tr>
						<td class="form-label">Confirm Password</td>
						<td>
							<input class="create-user-field"  onkeyup="checkPassword()" id="confirm-password" name="confirm-password" type="password"><br>
							<label class="error" id="check-password-message"></label>
						</td>
					</tr>
					
					<tr>
						<td></td>
						<td>
							<fm:hidden path="authority"/>
							<fm:hidden path="enabled"/>
							<fm:hidden path="event"/>
							<input class="create-user-field"  type="submit" value="Create Admin" >
						</td>
					</tr>				
						
				</table>
			
			</fm:form>
			
		</div>
	
	</div>
	
	<footer>
		<p>Copyright &#169; <a href="http://www.ghhs.com.my">Golden Horse Health Sanctuary</a></p>
	</footer>
	
	<script type="text/javascript">
		var passwordPassed = false;	
	
		function checkPasswordSize()
		{
			var password = document.getElementById('password').value;
			var message = document.getElementById('check-password-message');
			message.innerHTML = "";
			
			if(password.length < 8)
			{
				message.innerHTML = "Password is too short";
				message.style.color = "red";
			}
		}
		
		function checkPassword()
		{
			var password = document.getElementById('password').value;
			var confirmPassword = document.getElementById('confirm-password').value;
			var message = document.getElementById('check-password-message');
			
			
			if(password.length >= 8 && confirmPassword.length >= 8)
			{
				if(password == confirmPassword)
				{
					message.innerHTML = "Passwords matched";
					message.style.color = "green";
					passwordPassed = true;
				}
				else
				{
					message.innerHTML = "Passwords not matched";
					message.style.color = "red";
					passwordPassed = false;
				}
			}
		}
		
		function canSubmit()
		{
			if(passwordPassed)
			{
				return true;
			}
			else
			{
				alert("Invalid or not matched passwords!!");
				return false;
			}
		}
		
	</script>
	
</body>

</html>