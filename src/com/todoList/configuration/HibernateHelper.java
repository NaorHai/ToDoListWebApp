package com.todoList.configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Created by Haimov on 21/12/2017.
 */
public class HibernateHelper {

     Session getSession() {
         //creating factory for getting sessions
         SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
         //creating a new session for adding products
         return factory.openSession();
    }
}
