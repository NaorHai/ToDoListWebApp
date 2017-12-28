package com.todoList.dao;

import com.todoList.exception.user.UserException;
import com.todoList.pojo.User;


/**
 * Created by Haimov on 21/12/2017.
 */
public interface UserDAO {
    boolean saveOrUpdate(User user);
    boolean deleteUser(User user);
    User getUserById(String userId);
}
