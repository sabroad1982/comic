<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main Wall</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div id="page"><br>
	<div id="indexWrapper2" style="position: fixed; top: 0px;">
		<a class="button button1" href="/home">Comic Wall</a>
		<a class="button button1" href="/collector/${collector.id}">My Profile</a>
		<a class="button button1" href="/logout">Logout</a>
	</div>
			<div id="wrapperWallShare">
			<form:form action="/share/create" method="post" modelAttribute="newShare">
				<h1>
					<form:label path="Share"></form:label><br>
					<form:input path="Share" placeholder="CAN'T BE BLANK" style="width: 900px; height: 100px; text-align: center;"/>
					<form:input type="hidden" value="${collectorId}" path="collector"/>
				</h1>
			<div id="errorsShare">
			<form:errors path="Share"/><br><br><br>
			<p><button class="button button1" type="submit" value="Submit">Post To The Wall</button></p><br>
			</div>
			</form:form><br>
				<br>
			</div>
			<br>
					<div id="wrapperWallShowShare">
							<table class="styled-table">
								<thead>
								      <tr>
								        <th><h2>Post ID:</h2></th>
								        <th><h2>Proflie:</h2></th>
								        <th><h2>Posts:</h2></th>
								        <th><h2># likes:</h2></th>
				     				 </tr>
				    			</thead>
								<tbody>
									<c:forEach items="${shares}" var="share">
									
										<tr class="active-row">
											<td><a class="button button1" href="/${collector.id}/share/${share.id }"><c:out value="${share.id}"/></a></td>
											<td><a class="button button1" href="/collector/${share.collector.id}"><c:out value="${share.collector.firstName}"/></a></td>
											<td><h3><c:out value="${share.share}"/></h3></td>
											<td>
												<c:choose><c:when test="${share.seens.contains(collector)}">				
															<a class="button button1" href="/shares/unseen/${share.id}">Undo</a>
														  </c:when>
														  <c:otherwise>
															<a class="button button1" href="/shares/seen/${share.id}">Like</a>
														  </c:otherwise>
												</c:choose><c:out value="${share.seens.size()}"/>
											</td>
										</tr>
									</c:forEach>		
								</tbody>
							</table>
							<br>
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
	</div><br>
	</div>	
</body>
</html>