package com.todolist.dao;

import com.todolist.configuration.HibernateHelper;
import com.todolist.exception.user.UserException;
import com.todolist.pojo.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * Created by Haimov on 21/12/2017.
 * UserDaoImpl class - an implementation of UserDAO interface
 */
public class UserDAOImpl implements UserDAO {

    private final static Logger logger = Logger.getLogger(UserDAOImpl.class);
    private static UserDAO instance;
    private Session session;

    private UserDAOImpl() {}

    /**
     * returns a singleton object which implements UserDAO interface
     * lazy initialization
     * */
    public static synchronized UserDAO getInstance() {
        if (instance == null) {
            return new UserDAOImpl();
        }
        return instance;
    }
    /**
     * creating or updating user
     * */
    @Override
    public void saveOrUpdate(User user) throws UserException {
        session = HibernateHelper.getSession();
        session.beginTransaction();

        try{
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            logger.info("User with id " + user.getEmail() +" was saved successfully");
        }catch (Exception e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            logger.error("Failed to save a user " + user.toString());
            logger.error(e.getStackTrace());
            throw new UserException(e.getMessage(), e);
        }finally {
            session.close();
        }
    }
    /**
     * deleting an user
     * */
    @Override
    public void deleteUser(User user) throws UserException {
        session = HibernateHelper.getSession();
        session.beginTransaction();

        try{
            session.delete(user);
            session.getTransaction().commit();
            logger.info("User with id: " + user.getEmail() + " was deleted successfully");
        }catch (Exception e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete a user with id: " + user);
            logger.error(e.getStackTrace());
            throw new UserException(e.getMessage(), e);
        }finally {
            session.close();
        }
    }
    /**
     * getting user by id
     * */
    @Override
    public User getUserById(String userId) throws UserException {
        session = HibernateHelper.getSession();
        session.beginTransaction();
        User user;
        try{
            user = (User)session.get(User.class, userId);
            session.getTransaction().commit();
            if (user == null) {
                throw new UserException("User: " + userId + " not found");
            }
        }catch (UserException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            logger.error("Failed to get a user with id: " + userId);
            logger.error(e.getStackTrace());
            throw new UserException(e.getMessage(), e);
        }finally {
            session.close();
        }
        return user;
    }
}
