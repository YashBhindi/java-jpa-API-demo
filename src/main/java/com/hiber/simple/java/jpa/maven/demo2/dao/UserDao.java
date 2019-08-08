/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiber.simple.java.jpa.maven.demo2.dao;

import com.hiber.simple.java.jpa.maven.demo2.model.CompositeKey;
import com.hiber.simple.java.jpa.maven.demo2.model.HelloWorld;
import com.hiber.simple.java.jpa.maven.demo2.model.UserProfile;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author yash
 */
public class UserDao {

    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    EntityTransaction entityTransaction;
    Scanner sc;

    public UserDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("PERSISTENCE");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        sc = new Scanner(System.in);
    }

    public void addUser() {
        String name = new String();
        String email = new String();
        Long phone;
        System.out.println("Enter user detail:");
        System.out.println("Enter name:");
        name = sc.next();
        System.out.println("Enter phone:");
        phone = sc.nextLong();
        System.out.println("Enter email:");
        email = sc.next();
        if (name == null || email == null || phone == null) {
            System.out.println("Provide valid details..");
        } else {
            CompositeKey ck = new CompositeKey(name, phone);
            UserProfile up = new UserProfile(ck, email);
            entityManager.persist(up);
            entityManager.getTransaction().commit();
            System.out.println("Message added..");
        }

    }

    public void getAllUser() {
        //retrieve data'
        Query query = entityManager.createQuery("SELECT m FROM UserProfile m", UserProfile.class);
        List<UserProfile> users = query.getResultList();
        for (UserProfile up : users) {
            System.out.println("Message:" + up.toString());
        }
    }

    public void closeConnection() {
        entityManager.close();
        entityManagerFactory.close();
    }

}
