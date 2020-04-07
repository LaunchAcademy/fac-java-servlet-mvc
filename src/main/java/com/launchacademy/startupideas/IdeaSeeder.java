package com.launchacademy.startupideas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class IdeaSeeder {
  public static final String[][] INITIAL_IDEAS = {
      {"Dog Twitter", "Twitter for Dogs"},
      {"Catstagram", "Instagram...but for cats"},
      {"BunnyBook", "Facebook...but for hippity hoppers"}
  };

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.launchacademy.javaStartupIdeas");
    EntityManager em = emf.createEntityManager();

    em.getTransaction().begin();
    for(String[] titleAndDescription : INITIAL_IDEAS) {
      Idea idea = new Idea();
      idea.setTitle(titleAndDescription[0]);
      idea.setDescription(titleAndDescription[1]);
      em.persist(idea);
    }
    em.getTransaction().commit();
  }

}
