package com.todolist.service;

import com.todolist.pojo.User;

/**
 * Created by Haimov on 17/01/2018.
 * UserService interface represents user CRUD operations
 */
public interface UserService {
    boolean createUser(String email, String firstName, String lastName);
    boolean updateUser(String id, String email, String firstName, String lastName);
    boolean deleteUserById(String id);
    User getUserById(String id);

}
