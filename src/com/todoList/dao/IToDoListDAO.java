package com.todoList.dao;

import com.todoList.pojo.Item;

import java.util.List;


/**
 * Created by Haimov on 14/12/2017.
 */
public interface IToDoListDAO {
    void saveOrUpdate(Item item);
    void deleteItemById(String itemId);
    List<Item> getItemsByUserId(String userId);
}
