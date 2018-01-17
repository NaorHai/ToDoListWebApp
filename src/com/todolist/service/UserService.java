package com.todolist.service;

import com.todolist.dao.UserDAO;
import com.todolist.dao.UserDAOImpl;
import com.todolist.exception.user.UserException;
import com.todolist.pojo.User;
import org.apache.log4j.Logger;

/**
 * Created by Haimov on 04/01/2018.
 */
public class UserService {

    private final static Logger logger = Logger.getLogger(UserService.class);
    private UserDAO userDAO = UserDAOImpl.getInstance();

    public UserService() {}

    /**
     * creates a new user
     * returns true in success or false in failure
     */
    public boolean createUser(String email, String firstName, String lastName) {
        User user = new User(email, firstName, lastName);
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
    public boolean updateUser(String id, String email, String firstName, String lastName) {
        User user;

        if (id == null || id.equals("")) {
            logger.error("invalid user id: " + id);
            return false;
        }

        try {
            user = userDAO.getUserById(id);

            if(user == null) {
                throw new UserException("cannot find user to update with id: " + id);
            }

            user.setEmail(email != null ? email : user.getEmail());
            user.setFirstName(firstName != null ? firstName : user.getFirstName());
            user.setLastName(lastName != null ? lastName : user.getLastName());

            userDAO.saveOrUpdate(user);
            logger.info("user: " + id + " was updated successfully");
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
    public boolean deleteUserById(String id) {
        User user;

        if (id == null || id.equals("")) {
            logger.error("invalid user id: " + id);
            return false;
        }

        try {
            user = userDAO.getUserById(id);

            if(user == null) {
                throw new UserException("cannot find user to update with id: " + id);
            }

            userDAO.deleteUser(user);
            logger.info("deleted user with id: " + id + " successfully");
            return true;
        } catch (UserException e) {
            e.printStackTrace();
            logger.error("failed to delete a user with id: " + id);
            return false;
        }
    }

    /**
     * creates a new user
     * returns true in success or false in failure
     */
    public User getUserById(String id) {
        User user;

        if (id == null || id.equals("")) {
            logger.error("invalid user id: " + id);
            return null;
        }
        try {
            user = userDAO.getUserById(id);

            if(user == null) {
                throw new UserException("cannot find user to update with id: " + id);
            }

            logger.info("created a new user successfully: " + user.toString());
            return user;
        } catch (UserException e) {
            e.printStackTrace();
            logger.error("failed to get user with id: " + id);
            return null;
        }
    }
}
