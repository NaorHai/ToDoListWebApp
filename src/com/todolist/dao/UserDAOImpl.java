package com.todolist.dao;

import com.todolist.configuration.HibernateHelper;
import com.todolist.exception.item.ItemException;
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

    private UserDAOImpl() {
    }

    /**
     * returns a singleton object which implements UserDAO interface
     * lazy initialization
     */
    public static synchronized UserDAO getInstance() {
        if (instance == null) {
            return new UserDAOImpl();
        }
        return instance;
    }

    /**
     * Save or update entity
     *
     * @param user
     * @throws UserException
     */
    @Override
    public void saveOrUpdate(User user) throws UserException {

        try {
            session = HibernateHelper.getSession();

            if (session == null) {
                throw new UserException("could not open session");
            }

            session.beginTransaction();

            session.saveOrUpdate(user);
            session.getTransaction().commit();
            logger.info("User with id " + user.getEmail() + " was saved successfully");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save a user " + user.toString());
            logger.error(e.getStackTrace());
            throw new UserException(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    /**
     * Delete entity
     *
     * @param user
     * @throws UserException
     */
    @Override
    public void deleteUser(User user) throws UserException {
        try {
            session = HibernateHelper.getSession();

            if (session == null) {
                throw new UserException("could not open session");
            }

            session.beginTransaction();

            session.delete(user);
            session.getTransaction().commit();
            logger.info("User with id: " + user.getEmail() + " was deleted successfully");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete a user with id: " + user);
            logger.error(e.getStackTrace());
            throw new UserException(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    /**
     * Get entity by id
     *
     * @param email
     * @throws UserException
     */
    @Override
    public User getUserById(String email) throws UserException {
        User user;

        try {
            session = HibernateHelper.getSession();

            if (session == null) {
                throw new UserException("could not open session");
            }

            session.beginTransaction();

            user = (User) session.get(User.class, email);
            session.getTransaction().commit();
            if (user == null) {
                throw new UserException("User: " + email + " not found");
            }
            return user;
        } catch (UserException e) {
            logger.error("Failed to get a user with id: " + email);
            logger.error(e.getStackTrace());
        } finally {
            session.close();
        }
        return null;
    }
}
