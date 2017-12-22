package com.todoList.dao;

import com.todoList.configuration.HibernateHelper;
import com.todoList.pojo.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.UUID;

/**
 * Created by Haimov on 21/12/2017.
 */
public class UserDAOImpl implements UserDAO{

    private final static Logger logger = Logger.getLogger(UserDAOImpl.class);
    private static UserDAO instance;
    private Session session;


    private UserDAOImpl() {}

    public static synchronized UserDAO getInstance() {
        if (instance == null) {
            return new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public void saveOrUpdate(User user) {
        session = HibernateHelper.getSession();
        session.beginTransaction();

        try{
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            session.close();
            logger.info("User with id " + user.getUserId() +" was saved successfully");
        }catch (Exception e){
            logger.error("Failed to save a user " + user.toString());
            logger.error(e.getStackTrace());
        }
    }

    @Override
    public void deleteUserById(UUID userId) {
        session = HibernateHelper.getSession();
        session.beginTransaction();

        try{
            session.delete(userId);
            session.getTransaction().commit();
            session.close();
            logger.info("User with id " + userId +" was deleted successfully");
        }catch (Exception e){
            logger.error("Failed to delete a user with id: " + userId);
            logger.error(e.getStackTrace());
        }
    }

    @Override
    public User getUserById(UUID userId) {
        session = HibernateHelper.getSession();
        session.beginTransaction();
        User user = null;
        try{
            user = (User)session.get(User.class, userId);
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            logger.error("Failed to get a user with id: " + userId);
            logger.error(e.getStackTrace());
        }
        return user;
    }
}