package com.todolist.service;

import com.todolist.dao.UserDAO;
import com.todolist.dao.UserDAOImpl;
import com.todolist.exception.item.ItemException;
import com.todolist.exception.user.UserException;
import com.todolist.pojo.User;
import org.apache.log4j.Logger;

/**
 * Created by Haimov on 04/01/2018.
 * implementation of UserService interface
 */
public class UserServiceImpl implements UserService {

    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);
    private UserDAO userDAO = UserDAOImpl.getInstance();
    private IToDoListService iToDoListService = new IToDoListServiceImpl();

    public UserServiceImpl() {
    }

    /**
     * Register new user
     *
     * @param email
     * @param password
     * @param firstName
     * @param lastName
     * @throws UserException
     */
    @Override
    public boolean registerUser(String email, String password, String firstName, String lastName) throws UserException {
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
     * update  user
     *
     * @param email
     * @param password
     * @param firstName
     * @param lastName
     * @throws UserException
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
     * Delete user by mail
     *
     * @param email
     * @throws UserException
     */
    @Override
    public boolean deleteUserById(String email) throws UserException {
        User userToDelete;

        if (email == null || email.equals("")) {
            logger.error("invalid user id: " + email);
            return false;
        }

        try {
            try {
                iToDoListService.deleteAllItemsByUserId(email);
            } catch (ItemException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                return false;
            }
            userToDelete = userDAO.getUserById(email);

            if (userToDelete == null) {
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
     * Check user credentials
     *
     * @param email
     * @param password
     * @throws UserException
     */
    @Override
    public User checkUserLogin(String email, String password) throws UserException {
        User user;
        try {
            user = userDAO.getUserById(email);

            if (user == null) {
                logger.error("User not found with id: " + email);
                return null;
            }

            return (user.getPassword().equals(password)) ? user : null;
        } catch (UserException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * Get user by email
     *
     * @param email
     * @throws UserException
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

            if (user == null) {
                throw new UserException("cannot find user to update with id: " + email);
            }

            logger.info("created a new user successfully: " + user.toString());
            return user;
        } catch (UserException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * Check if user already exist
     *
     * @param email
     * @throws UserException
     */
    @Override
    public boolean isUserAlreadyExist(String email) throws UserException {
        User user;

        if (email == null || email.equals("")) {
            logger.error("invalid user id: " + email);
            return false;
        }
        try {
            user = userDAO.getUserById(email);
            return user != null;
        } catch (UserException e) {
            e.printStackTrace();
            logger.error("failed to delete a user with id: " + email);
            return false;
        }
    }

}
