<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Comic-Carousel-Login</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div id="pageIndex">
<div id="indexWrapper1">
</div><br>
	<div id="login1">
		<form method="post" action="/login">
			<div id="wrapper7">		
			<h3>
				<label for="email">Email:</label>
				<input type="text" id="email" name="email" style="width: 250px;"/>
			</h3>
			<h3>
				<label for="password">Password:</label>
				<input type="password" id="password" name="password" style="width: 250px;"/>
				<br>
			</h3>
				<div id="wrapper8">
					<button class="button button1" type="submit" value="Login">Submit</button><br><h2><c:out value="${error}"/></h2>
				</div>
			</div>
		</form>
	</div>
	<div id="wrapper2">
	<div id="wrapper6">
<form:form action="/registration" method="post" modelAttribute="collector">
	<div id="wrapper5">
		<h3><form:label path="firstName">First Name:</form:label> 
		<form:input path="firstName" placeholder="Must Not Be Blank"/>
		</h3>
        <h3><form:label path="lastName">Last Name:</form:label> 
        <form:input path="lastName" placeholder="Must Not Be Blank"/>
        </h3>
        <h3><form:label path="email">Email:</form:label> 
        <form:input path="email" placeholder="Must Not Be Blank"/>
		</h3>
        <h3><form:label path="state">State:</form:label> 
        <form:input path="state" placeholder="Must Not Be Blank"/>
		</h3>
        <h3><form:label path="fav">Favorite Comic:</form:label> 
		<form:input path="fav" placeholder="Must Not Be Blank"/>
		</h3>
        <h3><form:label path="password">Password:</form:label> 
        <form:password path="password" placeholder="Password"/>
        </h3>
        <h3><form:label path="passwordConfirmation">Confirm:</form:label> 
        <form:password path="passwordConfirmation" placeholder="Password"/>
		</h3>
	</div>
	<div id="wrapper9">
    <button class="button button1" type="submit" value="Register">Register</button><br>
    <div id="errors">
    <form:errors path="firstName"/><form:errors path="lastName"/><form:errors path="email"/><form:errors path="state"/><form:errors path="fav"/><form:errors path="password"/><form:errors path="passwordConfirmation"/>
    </div>
    </div>
</form:form>
	</div>
	</div>
	<br><br>
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
	<br>
</div>
</body>
</html>