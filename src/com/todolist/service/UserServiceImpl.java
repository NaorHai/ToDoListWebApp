package com.todolist.service;

import com.todolist.configuration.CookieHelper;
import com.todolist.configuration.HibernateHelper;
import com.todolist.dao.UserDAO;
import com.todolist.dao.UserDAOImpl;
import com.todolist.exception.user.UserException;
import com.todolist.pojo.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public boolean registerUser(HttpSession session,
                                String email,
                                String password,
                                String firstName,
                                String lastName,
                                HttpServletRequest request,
                                HttpServletResponse response) throws UserException {

        User user = new User(email, password, firstName, lastName);
        try {
            userDAO.saveOrUpdate(user);
            session.setAttribute("username", user.getEmail());

            // create cookies for first name and last name
            CookieHelper.createCookie("firstName" ,  user.getFirstName(), "", response);
            CookieHelper.createCookie("lastName" ,  user.getLastName(), "", response);
            session.setAttribute("isAuthenticated", true);
            request.setAttribute("list", "");

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
    public boolean updateUser(String email,
                              String password,
                              String firstName,
                              String lastName) throws UserException {

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
    public boolean deleteUserById(String id) throws UserException {
        User userToDelete;

        if (id == null || id.equals("")) {
            logger.error("invalid user id: " + id);
            return false;
        }

        try {
            userToDelete = userDAO.getUserById(id);

            if(userToDelete == null) {
                throw new UserException("cannot find user to update with id: " + id);
            }

            userDAO.deleteUser(userToDelete);
            logger.info("deleted user with id: " + id + " successfully");
            return true;
        } catch (UserException e) {
            e.printStackTrace();
            logger.error("failed to delete a user with id: " + id);
            return false;
        }
    }

    /**
     * checks user login credentials
     * returns true in success or false in failure
     */
    @Override
    public boolean checkUserLogin(String email,
                                  String password,
                                  HttpServletResponse response) throws QueryException {

        Session session = HibernateHelper.getSession();
        session.beginTransaction();
        String hql = "SELECT COUNT(*) FROM User WHERE email = :email AND password = :password";
        try {
            Query q = session.createQuery(hql);

            if (q == null) {
                throw new QueryException("unexpected query error");
            }

            q.setString("email", email);
            q.setString("password", password);

            boolean isCredentialsValid = q.executeUpdate() > 0;
            logger.debug("user: " + email + " loginSuccess: " + isCredentialsValid);

            if(isCredentialsValid) {
                CookieHelper.createCookie("userName" ,  email, null, response);
            }
            return isCredentialsValid;
        } catch (QueryException e) {
            e.printStackTrace();
            logger.error("failed to check user credentials: ", e);
            return false;
        }    }

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

    /**
     * check if user exist
     * @param email
     * @throws UserException
     */
    @Override
    public boolean isUserAlreadyExist(String email) throws UserException {
        return getUserById(email) != null;
    }

    /**
     * function did steps that required for login
     * @param session
     * @param userName
     * @param logink if user wont to be keeping login
     * @param request
     * @param response
     * @param isAuthenticated
     * @throws UserException
     */
    @Override
    public void login(HttpSession session,
                      String userName,
                      String logink,
                      HttpServletRequest request,
                      HttpServletResponse response,
                      boolean isAuthenticated ) throws UserException{

        session.setAttribute("username", userName);
        CookieHelper.createCookie("username" , userName, "/", response);
        CookieHelper.createCookie("loginkeep" , logink, "/", response);
        User user = userDAO.getUserById(userName);
        // create cookies for first name and last name for display it in todolist
        CookieHelper.createCookie("firstName" ,  user.getFirstName(), null, response);
        CookieHelper.createCookie("lastName" ,  user.getLastName(), null, response);
        session.setAttribute("isAuthenticated", isAuthenticated);
    }
}
