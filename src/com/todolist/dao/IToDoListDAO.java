package com.todolist.dao;

import com.todolist.exception.item.ItemException;
import com.todolist.pojo.Item;

import java.util.List;


/**
 * Created by Haimov on 14/12/2017.
 * IToDoListDAO interface represents user CRUD operations
 */
public interface IToDoListDAO {
    boolean saveOrUpdate(Item item) throws ItemException;
    boolean deleteItem(Item item) throws ItemException;
    List<Item> getItemsByUserId(String userId) throws ItemException;
    boolean deleteAllItemsByUserId(String user) throws ItemException;
}
