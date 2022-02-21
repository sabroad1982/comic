<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deal Wall</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div id="page"><br>
	<div id="indexWrapper2" style="position: fixed; top: 0px;">
		<a class="button button1" href="/home">Comic Wall</a>
		<a class="button button1" href="/collector/${collector.id}">My Profile</a>
		<a class="button button1" href="/logout">Logout</a>
	</div>
		<div id="wrapperWallDeal">
			<form:form action="/${share.id}/deal/create" method="post" modelAttribute="newDeal">
				<div id="wrapperSaid"><br>
									<h3>Post-<c:out value="${share.id}"/>- What <c:out value="${share.collector.firstName}"/> <c:out value="${share.collector.lastName}"/> Said:</h3><h3><c:out value="${share.share}"/>
									<c:if test="${share.collector.id==collectorLoggedIn}"><br>
									<a class="button button1" href="/share/delete/${share.id}">Delete</a>
									</c:if></h3>
				</div>
					<div id="wrapperTalk">
						<h2>
							<form:label path="Deal"></form:label><br>
							<form:errors path="Deal"/>
							<form:input path="Deal" placeholder="CAN'T BE BLANK/CAN'T BE OVER 500 CHARATERS LONG" style="width: 600px; height: 76px; border: 5px double black; text-align:center;"/>
						</h2>
						<form:input type="hidden" value="${collectorId}" path="collector"/>
						<form:input type="hidden" value="${shareId}" path="share"/>
						
						<br><br><br>
						<button class="button button1" type="submit" value="Submit">Post Deal</button>
					</div>
		</form:form>
		<br>
		</div>
<br>
		<div id="wrapperWallShowDeal">
		<table class="styled-table">
			<thead>
						<tr>
					        <th><h2>Collector ID & Email:</h2></th>
					        <th><h2>Posters Profile:</h2></th>
					        <th><h2>Comments:</h2></th>		        
	     				</tr>
			<tbody>
				<c:forEach items="${deals}" var="deal">
					<tr class="active-row">
					<c:if test= "${share.id==deal.share.id }">
								<td><h1><c:out value="${deal.collector.id}"/></h1><h3><c:out value="${deal.collector.email }"/></h3></td>
							<td><a class="button button1" href="/collector/${deal.collector.id}"><c:out value="${deal.collector.firstName}"/></a></td>
												<td><h3><c:out value="${deal.collector.firstName }"/> <c:out value="${deal.collector.lastName }"/>'s Deal Comments:<br><c:out value="${deal.deal }"/></h3>
															<c:if test="${deal.collector.id==collectorLoggedIn}">
																<a class="button button1" href="/deal/delete/${collector.id }/${share.id}/${deal.id }">Delete</a>
															</c:if>
					</c:if>	
				</c:forEach>		
			</tbody>
		</table>
		</div>
	<br>
			<div id="wrapperShareViews">	
									<p>Number of Comic Collectors who have liked this Post: <c:out value="${share.seens.size()}"/></p>	
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
</div>
</body>
</html>