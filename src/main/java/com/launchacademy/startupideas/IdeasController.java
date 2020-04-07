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
    String requestedIdea =req.getParameter("id");

    //if id exists, find idea and render
    if(requestedIdea != null){
      String ideaQuery = "SELECT i FROM Idea i WHERE id = :id ";
      TypedQuery findIdea = em.createQuery(ideaQuery, Idea.class);
      findIdea.setParameter("id", Long.parseLong(requestedIdea));
      Idea idea = (Idea) findIdea.getSingleResult();
      req.setAttribute("idea", idea);
      System.out.println(idea);
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/ideas/show.jsp");
      dispatcher.forward(req,resp);

      em.close();
      emf.close();
      //if no id was given then show the index
    } else {

      //Query for our list
      Query findQuery = em.createQuery("SELECT i FROM Idea i", Idea.class);
      List<Idea> ideas = findQuery.getResultList();

      //Send the list to the JSP
      req.setAttribute("ideas", ideas);

      //Render the JSP
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/ideas/index.jsp");
      dispatcher.forward(req, resp);

      //Close our connection
      em.close();
      emf.close();
    }
  }
}
