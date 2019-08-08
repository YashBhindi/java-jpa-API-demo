/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiber.simple.java.jpa.maven.demo2.driver;

/**
 *
 * @author yash
 */
import com.hiber.simple.java.jpa.maven.demo2.model.HelloWorld;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Driver {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PERSISTENCE");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Scanner sc = new Scanner(System.in);
        getInputs(entityManagerFactory, entityManager, sc);

    }

    public static void getInputs(EntityManagerFactory entityManagerFactory, EntityManager entityManager, Scanner sc) {
        boolean flag = true;
        while (flag) {
            System.out.println("Enter the choice:");
            System.out.println("1 to add message:");
            System.out.println("2 to real all message:");
            System.out.println("3 to exit:");

            int n = sc.nextInt();
            switch (n) {
                case 1:
                    addMessage(entityManagerFactory, entityManager, sc);
                    break;
                case 2:
                    getAllMessages(entityManager);
                    break;
                case 3:
                    flag = false;
                    entityManager.close();
                    entityManagerFactory.close();
                    break;
            }

        }
    }

    public static void addMessage(EntityManagerFactory entityManagerFactory, EntityManager entityManager, Scanner sc) {
        String msg = new String();
        System.out.println("Enter message:");
        msg = sc.next();
        if (msg == null) {
            System.out.println("Provide valid message..");
        } else {
            HelloWorld helloWorld = new HelloWorld(msg);
            entityManager.persist(helloWorld);
            entityManager.getTransaction().commit();
            System.out.println("Message added..");
        }
    }

    public static void getAllMessages(EntityManager entityManager) {
        //retrieve data'
        Query query = entityManager.createQuery("SELECT m FROM HelloWorld m", HelloWorld.class);
        List<HelloWorld> message = query.getResultList();
        for (HelloWorld m : message) {
            System.out.println("Message:" + m.getMessage());
        }

    }
}
