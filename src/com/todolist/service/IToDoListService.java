package com.todolist.service;

import com.todolist.pojo.Item;
import java.util.List;

/**
 * Created by Haimov on 17/01/2018.
 * IToDoListService interface represents item CRUD operations
 */
public interface IToDoListService {
    boolean createItem(String userId, String title, String content);
    List<Item> getItemsByUserId(String userId);
    boolean deleteItemById(String itemId);
}
