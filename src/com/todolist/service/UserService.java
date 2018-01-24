package com.todolist.service;

import com.todolist.exception.user.UserException;
import com.todolist.pojo.User;
import org.hibernate.QueryException;

/**
 * Created by Haimov on 17/01/2018.
 * UserService interface represents user CRUD operations
 */
public interface UserService {
    boolean registerUser(String email, String password, String firstName, String lastName) throws UserException;
    boolean updateUser(String id, String email, String password, String firstName, String lastName) throws UserException;
    boolean deleteUserById(String id) throws UserException;
    boolean checkUserLogin(String email, String password) throws QueryException;
    User getUserById(String id) throws UserException;

}
