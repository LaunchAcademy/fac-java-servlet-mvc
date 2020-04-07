# Java Servlets and MVC

---

## Model View Controller (MVC)

- **Models** - _business logic_. We should relegate as much data manipulation and computation to these classes as possible.
- **Controllers** - _traffic logic_. Responsible for receiving the HTTP Request and determining what to do with it. In order to do its work, controllers almost always have to serve as the intermediary between models and views.
- **Views** - _presentation logic_ - responsible for presenting the user with relevant information.

---

## MVC in Primitive Java

- **Models** - Hibernate / JPA Entity
- **Controllers** - Servlets
- **Views** - Java Server Pages (JSP)

There are more robust frameworks to manage this, but we want to give you one piece at a time. 

We will learn Spring Boot later, which improves and simplifies this configuration

---

## MVC is a Design Pattern

- Gives developers guidelines on how to approach a common software challenge
- Provides consistency from application to application and from stack to stack
- Think of it like Open Source for Architecture - a guide of commonly known designs to common Computer Science challenges

---

## Our Sample Application

- We're building an app that keeps track of startup ideas
- Our primary model is `Idea`
- We can seed data with the `IdeaSeeder`
- We can retrieve a single idea by navigating to `/idea?id=<id>`
- We can retrieve a list of ideas by navigating to `/ideas`

---

## Enacting Our Sample Application

1. All traffic gets directed to a servlet (controller)
2. The servlet will retrieve the data
3. The servlet will assign the data to the request context
4. The servlet will render a JSP

---

## But first, in true Java form, some setup!

- Observe the presence of Maven Wrapper for easy running of maven tasks
- Observe the dependencies in `pom.xml`
- Notice the presence of both `META-INF` and `WEB-INF` folders
- There's a migration for an `ideas` table
- Observe the `Idea` entity
- Let's run the `IdeaSeeder` - is it re-runnable as is?

---

## Launch the App

```no-highlight
createdb java-startup-ideas
./mvnw flyway:migrate
./mvnw tomcat7:run
```

Note: Tomcat will likely show you some very nasty error messages. Ignore them

---

## IdeaController

_It's too big for a slide, let's pull up the code_

- All the request handling starts here
- It asks the model for data
- It informs the view (JSP) what data it found

---

## Idea

- You already know this stuff!
- We're now just using it in the context of a web application
- It _informs_ the controller with data

Arguably, we should have more logic in the model, but more on this later

---

## `/views/ideas/show.jsp`

_It's too big for a slide, let's pull up the code_

- This is the presentation layer
- Presents the client with HTML based on controller details
- We favor the use of JSTL for readability

---

## Packages: a Digression

- Java developers like everything neat and tidy
- It is important for us to **namespace** our classes to avoid collisions with third party devs
- We cannot use classes in the global namespace in JSP

---

## Defining a package

- In Intellij, a package is essentially a folder
- Conventionally, we use a backwards-form domain name to start our package name
- Google Style Guide tells us not to use camelcase or hyphens in our package names
- At the top of every java file in the package, we declare it as part of the package

```java
package com.launchacademy.startupideas;
```

---

## Use the JSTL, Luke

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
```

---

## JSTL is Prettier than Random Java in Your View

```jsp
<%@ page import="com.launchacademy.startupideas.Idea" %>
<c:set var="idea" value="${requestScope.idea}" scope="request" />
```

---

## Outputting Our Data with JSTL

```jsp
<h1><c:out value="${idea.title}" /></h1>
<p><c:out value="${idea.description}" /></p>
```

Note: JSP and Beans can derive the getter off of the property based on convention. Both getters are actually being called here

---

## So to Explain the Flow Concretely

- Tomcat looks for the Servlet to process the GET to `/idea?id=1`
- The Servlet we configured can respond and it has a `doGet` method that can prepare a response
- The Servlet queries the **model** to see if an `Idea` exists with that id
- The Servlet then decides whether it should render the JSP or show a 404 error
- The JSP (**view**) handles the presentation of the `Idea`

---

## Let's Look at the Listing

- The premise is the same: we created a Servlet that handles a GET to `/ideas`
- It queries the database for the list
- It renders the JSP
- The JSP (**view**) handles the presentation and formatting of the list of `Idea` entities

---

## IdeasController

_It's too big for a slide, let's pull up the code_

---

## `/views/ideas/index.jsp`

_It's too big for a slide, let's pull up the code_

Like with handlebars, we try to avoid too much logic and programming in our JSP

---

## Using JSTL to Loop

```jsp
<c:forEach var="idea" items="${requestScope.ideas}">
  <li>
    <a href="/idea?id=<c:out value="${idea.id}"/>">
      <c:out value="${idea.title}"/>
    </a>
    </li>
</c:forEach>
```

- `requestScope` gives us access to everything set via the servlet
- Using the JSTL we can "assign" an `idea` every loop around the race track
- With our seeded data, this will render 3 `<li>` tags

---

## MVC at Work, Again

- We created a Servlet that handles a GET to `/ideas`
- It queries the database for the list
- It renders the JSP
- The JSP (**view**) handles the presentation and formatting of the list of `Idea` entities

---

## A Caveat: Skinny Controller, Fat Model

- Arguably, we should refactor the code that's in the controller. It has too much query logic
- Query logic is a responsibility of the model layer, but we didn't want to get overly complex in this example
- We could define static methods `Idea.getById` and `Idea.getList` that would do the job for us
- Why? Single responsibility and testability
