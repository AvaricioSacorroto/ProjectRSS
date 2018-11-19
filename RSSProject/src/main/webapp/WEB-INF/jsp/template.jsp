<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head> 
    <tiles:importAttribute name="title"/> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title><!--<spring:message code="${title}"/>--> ${title}</title>  
<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jscrip.js" />"></script>
</head>  
<body>  
        <div><tiles:insertAttribute name="header"/></div>  
        <div style="float:left;padding:10px;width:15%;"><tiles:insertAttribute name="menu" /></div>  
        <div style="float:left;padding:10px;width:80%;border-left:1px solid pink;">  
        <tiles:insertAttribute name="body" /></div>  
        <div style="clear:both"><tiles:insertAttribute name="footer" /></div>  
  
</body> 
</html>

