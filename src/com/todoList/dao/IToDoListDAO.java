package com.todoList.dao;

import com.todoList.exception.task.TaskException;
import com.todoList.pojo.Item;
import com.todoList.pojo.User;

import java.util.List;


/**
 * Created by Haimov on 14/12/2017.
 */
public interface IToDoListDAO {
    boolean saveOrUpdate(Item item);
    boolean deleteItem(Item item);
    List<Item> getItemsByUserId(String userId);
    boolean deleteAllItemsByUserId(String user);
}
