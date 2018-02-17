package com.todolist.service;

import com.todolist.dao.HibernateToDoListDAO;
import com.todolist.dao.IToDoListDAO;
import com.todolist.exception.item.ItemException;
import com.todolist.pojo.Item;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Papushe on 04/01/2018.
 * implementation of IToDoListService interface
 */
public class IToDoListServiceImpl implements IToDoListService {
    private final static Logger logger = Logger.getLogger(IToDoListServiceImpl.class);
    private IToDoListDAO iToDoListDAO = HibernateToDoListDAO.getInstance();

    public IToDoListServiceImpl(){}

    /**
     * creates a new item
     * returns true in success or false in failure
     */
    @Override
    public boolean createItem(String email, String title, String content) {
        try {

            if (email == null || email.equals("")) {
                logger.error("invalid user id: " + email);
                throw new ItemException("invalid user id was provided!");
            }

            Item item = new Item(email, title, content);

            iToDoListDAO.saveOrUpdate(item);
            logger.info("created a new item successfully: " + item.toString());
            return true;
        } catch (ItemException e) {
            e.printStackTrace();
            logger.error("failed to create a new item!");
            return false;
        }
    }

    /**
     * get all user items
     * return list of items in success or null in case of failure
     */
    @Override
    public List<Item> getItemsByUserId(String email) {
        List<Item> userItems;
        try {

            if (email == null || email.equals("")) {
                logger.error("invalid user id: " + email);
                throw new ItemException("invalid user id was provided!");
            }

            userItems = iToDoListDAO.getItemsByUserId(email);

            if (userItems == null || userItems.size() == 0) {
                logger.info("no items were found for user: " + email);
            }
            return userItems;
        } catch (ItemException e) {
            e.printStackTrace();
            logger.error("failed to get items for user: " + email);
            return null;
        }
    }

    /**
     * delete item by item id
     * returns true in success or false in failure
     */
    @Override
    public boolean deleteItemById(String itemId) {
        try {
            if (itemId == null || itemId.equals("")) {
                logger.error("invalid item id: " + itemId);
                throw new ItemException("invalid item id was provided!");
            }

            Item itemToDelete = iToDoListDAO.getItemById(itemId);

            if (itemToDelete == null) {
                throw new ItemException("cannot find item to delete with id: " + itemId);
            }

            iToDoListDAO.deleteItem(itemToDelete);
            logger.info("deleted item with id: " + itemId + " successfully");
            return true;
        }catch (ItemException e) {
            e.printStackTrace();
            logger.error("failed to delete item: " + itemId);
        }
        return false;
    }

    /**
     * delete all user items by user id
     * returns true in success or false in failure
     */
    @Override
    public boolean deleteAllItemsByUserId(String email) {
        try {

            if (email == null || email.equals("")) {
                logger.error("invalid user id: " + email);
                throw new ItemException("invalid user id was provided!");
            }

            iToDoListDAO.deleteAllItemsByUserId(email);
            logger.info("deleted all user: " + email + " items successfully");
            return true;

        } catch (ItemException e) {
            e.printStackTrace();
            logger.error("failed to get items for user: " + email);
            return false;
        }
    }
}
