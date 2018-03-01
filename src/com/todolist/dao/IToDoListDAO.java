package com.todolist.dao;

import com.todolist.exception.item.ItemException;
import com.todolist.pojo.Item;

import java.util.List;


/**
 * Created by Haimov on 14/12/2017.
 * IToDoListDAO interface represents DB operations on item entity
 */
public interface IToDoListDAO {
    void saveOrUpdate(Item item) throws ItemException;
    void deleteItem(Item item) throws ItemException;
    List<Item> getItemsByUserId(String email) throws ItemException;
    Item getItemById(String itemId) throws ItemException;
    void deleteAllItemsByUserId(String user) throws ItemException;
//    boolean updateItem(Item item) throws ItemException;
}
