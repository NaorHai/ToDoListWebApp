package com.todolist.service;

import com.todolist.exception.user.UserException;
import com.todolist.pojo.User;
import org.hibernate.QueryException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Haimov on 17/01/2018.
 * UserService interface represents user CRUD operations
 */
public interface UserService {
    boolean registerUser(HttpSession session, String email, String password, String firstName, String lastName, HttpServletRequest request, HttpServletResponse response) throws UserException;
    boolean updateUser(String email, String password, String firstName, String lastName) throws UserException;
    boolean deleteUserById(String email) throws UserException;
    User getUserById(String email) throws UserException;
    boolean isUserAlreadyExist(String email) throws UserException;
    boolean checkUserLogin(String email, String password, HttpServletResponse response) throws QueryException;
    void login(HttpSession session, String userName, String logink, HttpServletRequest request,
               HttpServletResponse response, boolean isAuthenticated) throws UserException;

}
