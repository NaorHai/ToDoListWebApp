package com.todolist.service;

import com.todolist.configuration.HibernateHelper;
import com.todolist.dao.UserDAO;
import com.todolist.dao.UserDAOImpl;
import com.todolist.exception.user.UserException;
import com.todolist.pojo.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.Session;

/**
 * Created by Haimov on 04/01/2018.
 * implementation of UserService interface
 */
public class UserServiceImpl implements UserService{

    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);
    private UserDAO userDAO = UserDAOImpl.getInstance();

    public UserServiceImpl() {}

    /**
     * creates a new user
     * returns true in success or false in failure
     */
    @Override
    public boolean registerUser(String email, String password, String firstName, String lastName) {
        User user = new User(email, password, firstName, lastName);
        try {
            userDAO.saveOrUpdate(user);
            logger.info("created a new user successfully: " + user.toString());
            return true;
        } catch (UserException e) {
            e.printStackTrace();
            logger.info("failed to create a new user: " + user.toString());
            return false;
        }
    }

    /**
     * updates an existed user
     * returns true in success or false in failure
     */
    @Override
    public boolean updateUser(String email, String password, String firstName, String lastName) throws UserException {
        try {

            if (email == null || email.equals("")) {
                logger.error("invalid user id: " + email);
                throw new UserException("invalid user id was provided!");
            }

            User user = userDAO.getUserById(email);

            if (user == null) {
                throw new UserException("cannot find user to update with id: " + email);
            }

            user.setFirstName(firstName != null ? firstName : user.getFirstName());
            user.setLastName(lastName != null ? lastName : user.getLastName());
            user.setPassword(password != null ? password : user.getPassword());

            userDAO.saveOrUpdate(user);
            logger.info("user: " + email + " was updated successfully");
            return true;

        } catch (UserException e) {
            e.printStackTrace();
            logger.error("failed to update user");
            return false;
        }
    }

    /**
     * delete an user by providing user id
     * returns true in success or false in failure
     */
    @Override
    public boolean deleteUserById(String email) throws UserException {
        User userToDelete;

        if (email == null || email.equals("")) {
            logger.error("invalid user id: " + email);
            return false;
        }

        try {
            userToDelete = userDAO.getUserById(email);

            if(userToDelete == null) {
                throw new UserException("cannot find user to update with id: " + email);
            }

            userDAO.deleteUser(userToDelete);
            logger.info("deleted user with id: " + email + " successfully");
            return true;
        } catch (UserException e) {
            e.printStackTrace();
            logger.error("failed to delete a user with id: " + email);
            return false;
        }
    }

    /**
     * checks user login credentials
     * returns true in success or false in failure
     */
    @Override
    public boolean checkUserLogin(String email, String password) throws UserException {
        Session session = HibernateHelper.getSession();
        session.beginTransaction();
        String hql = "SELECT COUNT(*) FROM User WHERE email = :email AND password = :password";
        try {
            Query q = session.createQuery(hql);

            if (q == null) {
                throw new UserException("unexpected query error");
            }

            q.setString("email", email);
            q.setString("password", password);

            boolean isCredentialsValid = q.executeUpdate() > 0;
            logger.debug("user: " + email + " loginSuccess: " + isCredentialsValid);

            return isCredentialsValid;
        } catch (UserException e) {
            e.printStackTrace();
            logger.error("failed to check user credentials: ", e);
            return false;
        }
    }

    /**
     * creates a new user
     * returns true in success or false in failure
     */
    @Override
    public User getUserById(String email) throws UserException {
        User user;

        if (email == null || email.equals("")) {
            logger.error("invalid user id: " + email);
            return null;
        }
        try {
            user = userDAO.getUserById(email);

            if(user == null) {
                throw new UserException("cannot find user to update with id: " + email);
            }

            logger.info("created a new user successfully: " + user.toString());
            return user;
        } catch (UserException e) {
            e.printStackTrace();
            logger.error("failed to get user with id: " + email);
            return null;
        }
    }
}
