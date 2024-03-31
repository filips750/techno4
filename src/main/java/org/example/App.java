package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory(
                        "myPersistenceUnit"
                );
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // Dodawanie nowego maga
        Mage mage1 = new Mage();
        Mage mage2 = new Mage();
        Tower tower = new Tower();
        tower.setName("Straszna wieża");
        tower.setHeight(30);
        mage1.setName("Filip");
        mage1.setLevel(20);
        mage1.setTower(tower);
        mage2.setName("Aureoliusz");

        // Rozpoczęcie transakcji i zapisanie maga
        tx.begin();
        em.persist(mage1);
        em.persist(mage2);
        em.persist(tower);
        tx.commit();

        // Wyświetlenie wszystkich magów
        List<Mage> mages = em.createQuery("SELECT m FROM Mage m", Mage.class).getResultList();
        System.out.println("Lista wszystkich magów:");
        for (Mage mage : mages) {
            System.out.println("ID: " + mage.getId() + ", Name: " + mage.getName() + ", Level: " + mage.getLevel());
            System.out.println("Tower of: " + mage.getName() +" is: "+ mage.getTower().getName());
        }
        // Usuwanie maga
        tx.begin();
        em.remove(mage1);
        tx.commit();
        // Wyświetlenie zaktualizowanej listy magów
        mages = em.createQuery("SELECT m FROM Mage m", Mage.class).getResultList();
        System.out.println("Po usunięciu maga:");
        for (Mage mage : mages) {
            System.out.println("ID: " + mage.getId() + ", Name: " + mage.getName() + ", Level: " + mage.getLevel());
        }

        // Zamykanie EntityManager i EntityManagerFactory
        em.close();
        emf.close();
    }
}
