package com.todoList.dao;

import com.todoList.pojo.User;


/**
 * Created by Haimov on 21/12/2017.
 */
public interface UserDAO {
    void saveOrUpdate(User user);
    boolean deleteUser(User user);
    User getUserById(String userId);
}
