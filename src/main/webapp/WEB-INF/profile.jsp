<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Your Profile</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div id="page"><br>
<div id="indexWrapper2" style="position: fixed; top: 0px;">
	<a class="button button1" href="/home">Comic Wall</a>
	
	<a class="button button1" href="/collector/user">My Profile</a>
	
	<a class="button button1" href="/logout">Logout</a>
</div>
	<div id="profileWallTop1">
<h1><c:out value="${collector.firstName}"/>'s <br>
 Profile</h1>
</div><br>


<div id="viewProfileInfo"><br>
	<div id="profileInfo">
<h3>Name: <c:out value="${collector.firstName}"/> <c:out value="${collector.lastName}"/></h3>
<h3>Email: <c:out value="${collector.email}"/></h3>
<h3>Location: <c:out value="${collector.state}"/></h3>
<h3>Favorite Comic: <c:out value="${collector.fav}"/></h3>
	<c:if test="${collector.id==collectorLoggedIn}">
	<a class="button button1" href="/collector/edit/${collector.id}"> Edit Your Info </a>
	</c:if>
	<br>
	

<c:forEach items="${shares}" var="share">
				
						<c:if test="${collector.id==share.collector.id}">
						<h3>Post ID: <c:out value="${share.id}"/></h3>
						<h3>Comments Made:"<c:out value="${share.share}"/>" </h3>
						<h3><c:out value="${share.seens.size()}"/> Collector Has Seen This Share</h3><br>
						</c:if>	
								
</c:forEach>
	


	<c:forEach items="${seens }" var="seen">
		<c:out value="${share.seen.size()}"/>
	</c:forEach>

	</div><br>
		</div>
	<div id="indexFooter">
		<div id=wrapper10>
	<h5>CONTACT:</h5>
		<p>1803 W Golf Rd. <br>
		Mount Prospect IL 60056 <br>
		847-483-4789 <br>
		331-201-5128</p>
		</div>
	</div><br>
	</div>
</body>
</html>