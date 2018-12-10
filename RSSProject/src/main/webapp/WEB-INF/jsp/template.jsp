<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head> 
    <tiles:importAttribute name="title"/> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title><!--<spring:message code="${title}"/>--> ${title}</title>  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href="<c:url value="/resources/css/styles2.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jscrip1.js" />"></script>
</head>  
<body>  
	<div class="template">
	        <div class="header"><tiles:insertAttribute name="header"/></div>  
	        <div class="main">
		        <div class="menu"><tiles:insertAttribute name="menu" /></div>  
		        <div class="body"><tiles:insertAttribute name="body" /></div> 
		       <div class="space"></div>
	        </div> 
	        <div class="footer"><tiles:insertAttribute name="footer" /></div>  
	</div>  
</body> 
</html>

