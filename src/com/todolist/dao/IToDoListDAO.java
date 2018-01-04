package com.todolist.dao;

import com.todolist.exception.item.ItemException;
import com.todolist.pojo.Item;

import java.util.List;


/**
 * Created by Haimov on 14/12/2017.
 * IToDoListDAO interface represents user CRUD operations
 */
public interface IToDoListDAO {
    void saveOrUpdate(Item item) throws ItemException;
    void deleteItem(Item item) throws ItemException;
    List<Item> getItemsByUserId(String userId) throws ItemException;
    void deleteAllItemsByUserId(String user) throws ItemException;
}
