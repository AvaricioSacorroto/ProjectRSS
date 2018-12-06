<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 


	<img src="<c:url value="/resources/images/rssicon.png" />" id="icon"/>
	<div>
		<h6>
		<a href="?locale=en">Cambiar idioma a Inglés</a><br />
		<a href="?locale=es">Cambiar idioma a Español</a> 
		</h6>
	</div>
	<div>
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		    <form action="${logoutUrl}" method="post" id="logoutForm">
		      <input type="hidden" 
		        name="${_csrf.parameterName}"
		        value="${_csrf.token}" />
		        <input type="submit" value="logout">
		    </form>
    </div>

