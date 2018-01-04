package com.todolist.service;

import com.todolist.dao.UserDAO;
import com.todolist.dao.UserDAOImpl;

/**
 * Created by Haimov on 04/01/2018.
 */
public class UserService {

    UserDAO userDAO = UserDAOImpl.getInstance();

    /**
     * Created by Haimov on 04/01/2018.
     */
    public boolean createUser(String user) {
        return false;
    }
}
