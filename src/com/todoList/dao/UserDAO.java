package com.todoList.dao;

import com.todoList.pojo.User;

import java.util.UUID;

/**
 * Created by Haimov on 21/12/2017.
 */
public interface UserDAO {
    void createUser(User user);
    void updateUser(User user);
    void deleteUserById(UUID userId);
    User getUserById(UUID userId);
}
