package com.todolist.dao;

import com.todolist.exception.user.UserException;
import com.todolist.pojo.User;


/**
 * Created by Haimov on 21/12/2017.
 * UserDao interface represents user CRUD operations
 */
public interface UserDAO {
    void saveOrUpdate(User user) throws UserException;
    void deleteUser(User user) throws UserException;
    User getUserById(String userId) throws UserException;
}
