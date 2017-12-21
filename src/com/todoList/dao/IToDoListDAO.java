package com.todoList.dao;

import com.todoList.pojo.Item;

import java.util.List;
import java.util.UUID;

/**
 * Created by Haimov on 14/12/2017.
 */
public interface IToDoListDAO {
    void createItem(Item item);
    void updateItem(Item item);
    void deleteItemById(UUID itemId);
    List<Item> getItemsByUserId(UUID userId);
}
