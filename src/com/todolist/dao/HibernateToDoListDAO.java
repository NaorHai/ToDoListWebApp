package com.todolist.dao;

import com.todolist.pojo.Item;
import java.util.List;
import java.util.UUID;

/**
 * Created by Papushe on 14/12/2017.
 */
public class HibernateToDoListDAO implements IToDoListDAO {

    @Override
    public void createItem(Item item) {

    }

    @Override
    public void updateItem(Item item) {

    }

    @Override
    public void deleteItemById(UUID item_id) {

    }

    @Override
    public List<Item> getUserItems(UUID user_id) {
        return null;
    }
}
