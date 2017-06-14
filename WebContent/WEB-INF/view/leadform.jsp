<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>

<html>

<head>
	<title>OPC Form</title>
	<link href='//fonts.googleapis.com/css?family=Advent Pro' rel='stylesheet'>
	<link href='//fonts.googleapis.com/css?family=Orbitron' rel='stylesheet'>
	<link href='//fonts.googleapis.com/css?family=Open Sans' rel='stylesheet'>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
</head>

<body onload="document.f.name.focus()">
	
	<header>
		<div id="logo-container">
			<a id="logo1"  href="http://www.ghhs.com.my"><img alt="GHHS" src="${pageContext.request.contextPath}/static/images/ghhs_logo.png"></a>
			<h1 id="logo2"><a href="${pageContext.request.contextPath}/">VISIT &#38; WIN CONTEST</a></h1>
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

			<h1 class="page-title">${event}</h1>
		
			<div class="lead-form-big-wrapper" id="lead-form-big-wrapper">
		
			<fm:form name="f" id="lead-form" cssClass="lead-form" action="${pageContext.request.contextPath}/newlead" method="post" commandName="lead">
				
				<table class="lead-form-table">
				
					<tr class="title-row">
						<td colspan="2">
							<h3 class="lead-form-title">Lucky Draw Form</h3>		
						</td>
					</tr>
		
					<tr class="second-row">
						<td class="label">Name</td>
						<td>
							<fm:input name="name" path="name" type="text" cssClass="lead-form-field"/><br>
							<fm:errors path="name" cssClass="error lead-form-error"></fm:errors>
						</td>
					</tr>
		
					<tr>
						<td class="label">IC Number</td>
						<td><fm:input path="IC" type="text" cssClass="lead-form-field"/></td>
					</tr>
		
					<tr>
						<td class="label">H/P Number</td>
						<td>
							<fm:input path="hpNumber" type="text" cssClass="lead-form-field"/><br>
							<fm:errors path="hpNumber" cssClass="error lead-form-error"></fm:errors>
						</td>
					</tr>
		
					<tr>
						<td class="label">Marital Status</td>
						<td>
							<label class="rb-label">Yes</label><fm:radiobutton cssClass="radio-button" path="maritalStatus" value="True"/>
							<label class="rb-label">No</label><fm:radiobutton cssClass="radio-button" path="maritalStatus" value="False" checked="checked"/>
						</td>
					</tr>

					<tr>
						<td class="label">Email</td>
						<td><fm:input path="email" type="text" cssClass="lead-form-field"/></td>
					</tr>
		
					<tr>
						<td class="label">Occupation</td>
						<td><fm:input path="occupation" type="text" cssClass="lead-form-field"/></td>
					</tr>
		
					<tr>
						<td class="label">Household Income</td>
						<td><fm:input path="householdIncome" type="text" cssClass="lead-form-field"/></td>
					</tr>
		
					<tr>
						<td class="label">Travel often</td>
						<td>
							<label class="rb-label">Yes</label><fm:radiobutton cssClass="radio-button" path="oftenTravel" value="True"/>
							<label class="rb-label">No</label><fm:radiobutton cssClass="radio-button" path="oftenTravel" value="False"/>
						</td>
					</tr>
					
					<tr>
						<td class="label">Local or Oversea</td>
						<td>
							<label class="rb-label">Local</label><fm:radiobutton cssClass="radio-button" path="localOrOversea" value="Local"/>
							<label class="rb-label">Oversea</label><fm:radiobutton cssClass="radio-button" path="localOrOversea" value="Oversea" checked="checked"/>
						</td>
					</tr>

					<tr>
						<td class="label">Preferred Payment</td>
						<td>
							<fm:select cssClass="payment-dropdown" path="preferedPayment">
								<fm:option value="Cash" label="Cash" />
								<fm:option value="Credit Card" label="Credit Card" />
								<fm:option value="Master" label="Master" />
								<fm:option value="Visa" label="Visa" />
								<fm:option value="Amex" label="Amex" />
							</fm:select>
						</td>
					</tr>
															
					<c:choose>
						<c:when test="${lead.id < 1}">
							<tr>
								<td class="last-row" colspan="2">
									<fm:hidden path="date"/>
									<fm:hidden path="tmCode"/>
									<fm:hidden path="eventName"/>
									<button class="lead-form-button" type="button" onclick="hideForm();">Proceed</button>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="last-row" colspan="2">
									<fm:hidden path="Id" />
									<fm:hidden path="date"/>
									<fm:hidden path="tmCode"/>
									<fm:hidden path="eventName"/>
									<input class="lead-form-button" name="save_edit" type="submit" value="Save Edit">
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
					
				</table>
				
			</fm:form>
			
			</div>
			
			<div id="pdpa-wrapper"  style="display:none" >
			
				<h3>DATA PROTECTION AND PRIVACY NOTICE</h3><br>
				
				<p>Golden Horse Palace Berhad respects and views your pesonal
				 data and privacy information provided by you to Country Heights
				 Group very seriously. This Notice is issuded pursuant to the 
				 requirements of the Personal Data Protection Act 2010. </p><br>
				 
				 <h4>A. SOURCE OF PERSONAL DATA</h4><br>
				 
				 <p>We collect, use and handle the personal data supplied by
				 individuals and from the following sources (not exhaustive):</p>
				 
				 <ol>
				 	<li>From any forms (whether electronic or printed) submitted
				 	by you during any promotional or marketing events of our 
				 	products and/or services; and/or</li>
				 	<li>Third Party, Private Sources and cookies</li>
				 </ol><br>
				 
				 <h4>B. PURPOSE OF COLLECTING PERSONAL DATA</h4><br>
				 
				 <p>We will process the personal data collected and used by 
				 us for, inter alia, the following purposes:</p>
				 
				 <ol>
				 	<li>Delivery of notices, services, products and marketing and 
				 	promotion of such services or products, whether present or future, 
				 	to you; and/or</li>
				 	<li>For contact purposes; and/or</li>
				 	<li>Research purposes including but not limited to historical 
				 	and statistical record keeping purposes; and/or</li>
				 	<li>Providing customer with regular communications relating to the 
				 	services provided by us; and/or</li>
				 	<li>Providing you with services, products or benefits offered by 
				 	Country Heights Group, its associated companies or its affiliates 
				 	or by selected third parties that we think may be of interest to you; and/or</li>
				 	<li>For considering and to process applications, updating customer's 
				 	records, for fraus and crime prevention, for purposes requred by law 
				 	or regulation, for purposes of enforcing legal rights and for internal
				 	operations; and/or</li>
				 	<li>For purposes relating thereto</li>
				 </ol><br>
				 
				 <h4>C. DISCLOSURE</h4><br>
				 
				 <p>Data held by us will be kept confidential but it may be provided or 
				 disclosed to the following parties (within or outside Malaysia):</p>
				 
				 <ol>
				 	<li>Our agents and service providers (including those located overseas) 
				 	providing services relating to the purposes for which the personal data 
				 	is collected; and/or</li>
				 	<li>Companies within Country Heights Group; and/or</li>
				 	<li>Our business partners and affiliates that provide related services 
				 	or products in connection with our business; and/or</li>
				 	<li>Any other person under a duty of confidentiality agreement or 
				 	understanding with us; and/or</li>
				 	<li>An person acting on behalf or nominatedl and/or</li>
				 	<li>Third party reward, lovalty, co-branding and privileges programme, 
				 	any guarantor or security provider.</li>
				 </ol><br>
				 
				 <h4>D. SECURITY AND DATA PROTECTION</h4><br>
				 
				 <p>We assure you that your personal data will be treated as confidential 
				 and we will implement the apporopriated steps or procedures in 
				 accordance with the laws, rules and regulations to secure and protect 
				 your personal data and prevent unauthorized and unlawful use, 
				 loss, accidental access or disclosure, alteration or destruction of 
				 your personal data.</p><br>
				 
				 <h4>E. DATA RETENTION</h4><br>
				 
				 <p>The personal data collected will be kept so long as necessary to 
				 fulfill the purpose stated in Part B above after which we will take 
				 all reasonable steps to ensure that all personal data is destroyed 
				 or permanently deleted if it is no longer necessary unless its 
				 retention is required to satisfy legal, regulatory or accounting 
				 requirements or to protect the interest of Country Heights Group</p><br>
				 
				 <h4>F. TRANSFER OF DATA</h4><br>
				 
				 <p>In the course of our business we may require or need to transfer 
				 your personal data out of Malaysia</p><br>
				 
				 <h4>G. RIGHT OF ACCESS TO PERSONAL DATA</h4><br>
				 
				 <p>Under the Personal Data Protection Act 2010, you have the right of 
				 access to your personal data held by us and you may request for 
				 correction of the personal data that is inaccurate, incomplete, misleading 
				 or not up to date</p><br>
				 
				 <h4>H. MISCELLANEOUS</h4><br>
				 
				 <p>Golden Horse Palace Berhad reserves the right to amend this Notice 
				 at any time and will place notice of such amendment on their website 
				 and or other mode's viewed suitable. The individuals continuing usage 
				 of our facilities is deemed consent for Golden Horse Palace Berhad to 
				 collect, process and store the data in accordance with the above.</p>
				 
				 <p>If you have any questions relating to this Notice or otherwise 
				 relating to misuse or suspected misuse of your personal data, you may 
				 send your request in writing to the following address:</p><br>
				 
				 <h4>GOLDEN HORSE PALACE BERHAD</h4><br>
				 
				 <p>LG Floor, East Wing,, Jalan Kuda Emas, Mines Wellness City, 43300 
				 Seri Kembangan, Selangor, Malaysia
				 Fax: 603-8945 3320 | Tel: 603-8941 1888</p><br>
				 
				 <div class="submit-button-wrapper">
				 	<button class="lead-form-button" id="submit-form" onclick="submitForm();">Agree &#38; Submit Participation</button>
				 </div>
			
			</div>
			
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
	
		function hideForm()
		{
			document.getElementById('lead-form-big-wrapper').style.display = 'none';
			document.getElementById('pdpa-wrapper').style.display = 'block';
		}
		
		function submitForm()
		{
			document.getElementById('lead-form').submit();
		}
	
	</script>

</body>

</html>