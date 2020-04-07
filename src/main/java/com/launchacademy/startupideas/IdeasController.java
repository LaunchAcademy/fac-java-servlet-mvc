package com.launchacademy.startupideas;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/ideas")
public class IdeasController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    //Create our db connection
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.launchacademy.javaStartupIdeas");
    EntityManager em = emf.createEntityManager();

    // get id from url query


    //if id exists, find idea and render
    if
      //if no id was given then show the index
    } else {

      //Query for our list

      //Send the list to the JSP


      //Render the JSP

      //Close our connection
      em.close();
      emf.close();
    }
  }
}
