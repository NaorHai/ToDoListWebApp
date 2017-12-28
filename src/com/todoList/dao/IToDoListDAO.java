package com.todoList.dao;

import com.todoList.exception.task.TaskException;
import com.todoList.pojo.Item;

import java.util.List;


/**
 * Created by Haimov on 14/12/2017.
 */
public interface IToDoListDAO {
    void saveOrUpdate(Item item) throws TaskException;
    boolean deleteItem(Item item) throws TaskException;
    List<Item> getItemsByUserId(String userId) throws TaskException;
//    TODO delete user items(all)
}
