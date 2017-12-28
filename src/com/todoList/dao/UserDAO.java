package com.todoList.dao;

import com.todoList.exception.user.UserException;
import com.todoList.pojo.User;


/**
 * Created by Haimov on 21/12/2017.
 */
public interface UserDAO {
    void saveOrUpdate(User user) throws UserException;
    boolean deleteUser(User user) throws UserException;
    User getUserById(String userId) throws UserException;
}
