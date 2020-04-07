<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.launchacademy.startupideas.Idea" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="idea" value="${requestScope.idea}" scope="request" />
<!DOCTYPE html>
<html>
  <head>
    <title><c:out value="${idea.title}" /></title>
  </head>
  <body>
    <h1><c:out value="${idea.title}" /></h1>
    <p><c:out value="${idea.description}" /></p>
  </body>
</html>