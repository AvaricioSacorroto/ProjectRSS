<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head></head>
<body>
   <h1>Login</h1>
  <form name='loginForm'
          action="<c:url value='j_spring_security_check' />" method='POST'>
<h1><c:out value="${error}"/> </h1>
      <table>
         <tr>
            <td>User:</td>
            <td><input type='text' name='name' value=''></td>
         </tr>
         <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
         </tr>
         <tr>
            <td><input  type="submit" name="action"  value="login" /></td>
         </tr>
      </table>
        <input type="hidden" name="${_csrf.parameterName}"
            value="${_csrf.token}" />
  </form>
  <p><a href="usuario">New user</a></p>
</body>
</html>