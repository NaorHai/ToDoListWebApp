package com.todolist.exception.item;

public class ItemException extends Exception {

    public ItemException(String msg) {
        super(msg);
    }

    public ItemException(String msg, Throwable t) {
        super(msg, t);
    }
}
