package com.todoList.dao;

import com.sun.xml.internal.bind.v2.TODO;
import com.todoList.pojo.Item;

import java.util.List;


/**
 * Created by Haimov on 14/12/2017.
 */
public interface IToDoListDAO {
    void saveOrUpdate(Item item);
    boolean deleteItem(Item item);
    List<Item> getItemsByUserId(String userId);
//    TODO delete user items(all)
}
