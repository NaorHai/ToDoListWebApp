package com.todolist.exception.toDoList;

public class ToDoListException extends Exception {

    public ToDoListException(String msg) {
        super(msg);
    }

    public ToDoListException(String msg, Throwable t) {
        super(msg, t);
    }
}
