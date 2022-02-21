<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Your Profile</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div id="page">
	<div id="indexWrapper2" style="position: fixed; top: 0px;">
		<a class="button button1" href="/home">Comic Wall</a>
		<a class="button button1" href="/collector/${collector.id}">My Profile</a>
		<a class="button button1" href="/logout">Logout</a>
	</div>
<br>
		<div id="profileWallTop1">
		<h1>-Edit Your <br> Profile-</h1>
		</div>
<br>
			<div id="editProfileInfo"><br>
				<form:form action="/collector/update/${editCollector.id}" method="post" modelAttribute="editedCollector">
				<div id="editProfileWrapper">
				<c:if test="${collectorLoggedIn==editCollector.id}">
			<h3>
				<form:label path="firstName">First Name:</form:label>
				<form:input path="firstName" size="38px" placeholder= "Can't be blank" value="${editCollector.firstName}"/>	
			</h3>
			<h3>
				<form:label path="lastName">Last Name:</form:label>
				<form:input path="lastName" size="40px" placeholder= "Can't be blank"  value="${editCollector.lastName}"/>
			</h3>
			<h3>
				<form:label path="email">Email:</form:label>
				<form:input path="email" size="50px" placeholder= "Can't be blank"  value="${editCollector.email}"/>
			</h3>
			<h3>
				<form:label path="state">State:</form:label>
				<form:input path="state" size="50px" placeholder= "Can't be blank"  value="${editCollector.state}"/>
			</h3>
			<h3>
				<form:label path="fav">Favorite Comic:</form:label>
				<form:input path="fav" size="26px" placeholder= "Can't be blank"  value="${editCollector.fav}"/>
			</h3>
			<h3>
				<form:label path="password"></form:label>
				<form:input type="hidden" path="password" value="${editCollector.password}"/>
			</h3>
			<h3>
				<form:label path="passwordConfirmation"></form:label>
				<form:input type="hidden" path="passwordConfirmation" value="${editCollector.password}"/>
			</h3>
				
			<button class="button button1" type="submit" value="Submit">Update</button>
			<a href="/home"><button class="button button1" value="Cancel">Cancel</button></a><br>
				<h3><form:errors path="fav"/><form:errors path="state"/><form:errors path="email"/><form:errors path="lastName"/><form:errors path="firstName"/></h3>	
				</c:if>
				</div><br><br>
			</form:form>
			</div>
	<br>
				<div id="indexFooter">
					<div id=wrapper10>
				<h5>CONTACT:</h5>
					<p>1803 W Golf Rd. <br>
					Mount Prospect IL 60056 <br>
					847-483-4789 <br>
					331-201-5128</p>
					</div>
				</div>
				<br>
</div>
</body>
</html>