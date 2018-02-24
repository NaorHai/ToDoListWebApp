package com.todolist.configuration;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Haimov on 21/12/2017.
 */
public final class HibernateHelper {
    private final static Logger logger = Logger.getLogger(HibernateHelper.class);

    private HibernateHelper() {}

    /**
     * Open new hibernate session
     */
    public static Session getSession() {
        try {
            //creating factory for getting sessions
            SessionFactory factory = new Configuration().configure().buildSessionFactory();
            //creating a new session for adding products
            logger.debug("opening new session...");
            return factory.openSession();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
}
