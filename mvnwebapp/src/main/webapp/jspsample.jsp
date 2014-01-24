<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>JSP Sample</title>
</head>
<body>
<%@include file="/includes/heading.inc"%><%-- this is a static include --%>
<h2>Hello JSP!</h2>
	<p>Message: ${message} </p>
	We the peeps.
	<p> c:out <c:out value="${message}" />
	
	<ul style="list-style-type:none; color:green;">
	  <li> pageContext.request.contextPath=${pageContext.request.contextPath} </li>
	  <li> pageContext.request.requestURL=${pageContext.request.requestURL} </li>
	  <li> pageContext.request.serverName=${pageContext.request.serverName} </li>
	</ul>
	<!-- TESTING -->
	<hr/>
<c:set var="myvar" value="ABCD" />
	<p>JSTL variable + text: <c:out value="${myvar} is alphabetical." /> </p>
</body>
</html>
