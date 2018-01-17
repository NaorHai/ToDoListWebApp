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
    public boolean createItem(String userId, String title, String content) {
        try {

            if (userId == null || userId.equals("")) {
                logger.error("invalid user id: " + userId);
                throw new ItemException("invalid user id was provided!");
            }

            Item item = new Item(userId, title, content);

            iToDoListDAO.saveOrUpdate(item);
            logger.info("created a new item successfully: " + item.toString());
            return true;
        } catch (ItemException e) {
            e.printStackTrace();
            logger.error("failed to create a new item!");
            return false;
        }
    }

    @Override
    public List<Item> getItemsByUserId(String userId) {
        List<Item> userItems;
        try {

            if (userId == null || userId.equals("")) {
                logger.error("invalid user id: " + userId);
                throw new ItemException("invalid user id was provided!");
            }

            userItems = iToDoListDAO.getItemsByUserId(userId);

            if (userItems == null || userItems.size() == 0) {
                logger.info("no items were found for user: " + userId);
            }
            return userItems;
        } catch (ItemException e) {
            e.printStackTrace();
            logger.error("failed to get items for user: " + userId);
            return null;
        }
    }

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

    @Override
    public boolean deleteAllItemsByUserId(String userId) {
        try {

            if (userId == null || userId.equals("")) {
                logger.error("invalid user id: " + userId);
                throw new ItemException("invalid user id was provided!");
            }

            iToDoListDAO.deleteAllItemsByUserId(userId);
            logger.info("deleted all user: " + userId + " items successfully");
            return true;

        } catch (ItemException e) {
            e.printStackTrace();
            logger.error("failed to get items for user: " + userId);
            return false;
        }
    }
}
