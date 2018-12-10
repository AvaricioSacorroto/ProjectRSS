<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form method="POST" id="rssForm" modelAttribute="urlForm" action="rrs">
    <table>
            <tr>
               <td>URL:</td>
               <td><form:input type="text" path = "url" /><form:errors path="url" /></td>
               <form:input type="hidden" value="${currentUser}" path="id"/>
            </tr>
   </table>    
     <input type = "submit" name="action" value = "add"/>
 <h4></h4>

</form:form>


    



    <c:forEach items="${feeds}" var="feed">
    <div class="feed">
    
	        <img class="image"  src="${feed.getImageUrl()}">
            <h3><s:message code="titulo"/>:${feed.getTitle()}</h3>
		    <!--<p>Languaje:${feed.getLanguaje()}</p>-->
		    <p><s:message code="homepage"/>:${feed.getLink()}</p>
		    <p>ImageTitle:${feed.getImageTitle()}</p>
		    
            <form:form method="POST" id="rssForm" modelAttribute="urlForm" action="rrs">
             <form:input type="hidden" value="${currentUser}" path="id"/>
             <form:input type="hidden" value="${feed.getUrl()}" path="url"/>
            <input type = "submit" name="action" value = "delete"/>
		    </form:form>
	 </div> 	  
	    
	 
		   
			    <div class="entries">
				<c:forEach items="${feed.getEntries()}" var="entrie">
					<div class="entrie">
					    <p><s:message code="titulo"/>:${entrie.getTitle()}</p>
					    <div>${entrie.getDescriptionValue()}</div>
					    <p><a href="${entrie.getLink()}">Verlo en la web</a></p>
				    </div>
				</c:forEach>
				</div>
		    
		    
	
	<div class="close"><h1>Close</h1></div>  
    </c:forEach>


</body>
</html>