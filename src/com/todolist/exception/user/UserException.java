package com.todolist.exception.user;

/**
 * User Exception class
 */
public class UserException extends Exception {

    public UserException(String msg) {
        super(msg);
    }

    public UserException(String msg, Throwable t) {
        super(msg, t);
    }
}
