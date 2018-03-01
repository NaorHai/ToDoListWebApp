package com.todolist.service;

import com.todolist.exception.item.ItemException;
import com.todolist.pojo.Item;

import java.util.List;

/**
 * Created by Haimov on 17/01/2018.
 * IToDoListService interface represents item CRUD operations service
 */
public interface IToDoListService {
    boolean createItem(String email, String title, String content) throws ItemException;
    List<Item> getItemsByUserId(String email) throws ItemException;
    boolean deleteItemById(String itemId) throws ItemException;
    boolean deleteAllItemsByUserId(String email) throws ItemException;
    boolean updateItem(String email, String title, String content, String itemId) throws ItemException;
}
