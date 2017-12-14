package com.todolist.dao;

import com.todolist.pojo.Item;

import java.util.List;
import java.util.UUID;

/**
 * Created by Haimov on 14/12/2017.
 */
public interface IToDoListDAO {
    void createItem(Item item);
    void updateItem(Item item);
    void deleteItemById(UUID item_id);
    List<Item> getItemsByUserId(UUID user_id);
}
