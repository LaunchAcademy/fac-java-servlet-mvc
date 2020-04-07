<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.launchacademy.startupIdeas.Idea" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Ideas</title>
  </head>
  <body>
    <h1>Startup Ideas</h1>
    <ul>
      <c:forEach var="idea" items="${requestScope.ideas}">
        <li>
          <a href="/ideas?id=<c:out value="${idea.id}"/>">
            <c:out value="${idea.title}"/>
          </a>
         </li>
      </c:forEach>
    </ul>
  </body>
</html>