package com.todolist.service;

import com.todolist.dao.HibernateToDoListDAO;
import com.todolist.dao.IToDoListDAO;
import com.todolist.exception.item.ItemException;
import com.todolist.pojo.Item;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Papushe on 04/01/2018.
 * implementation of IToDoListService interface
 */
public class IToDoListServiceImpl implements IToDoListService {
    private final static Logger logger = Logger.getLogger(IToDoListServiceImpl.class);
    private IToDoListDAO iToDoListDAO = HibernateToDoListDAO.getInstance();

    public IToDoListServiceImpl() {
    }

    /**
     * Create new Item
     *
     * @param email
     * @param title
     * @param content
     * @throws ItemException
     */
    @Override
    public boolean createItem(String email, String title, String content) throws ItemException {
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
     * Update Item
     *
     * @param email
     * @param title
     * @param content
     * @param itemId
     * @throws ItemException
     */
    @Override
    public boolean updateItem(String email, String title, String content, String itemId) throws ItemException {
        Item item;
        try {
            if (itemId == null || itemId.equals("")) {
                logger.error("invalid user id: " + itemId);
                throw new ItemException("invalid user id was provided!");
            }

            item = iToDoListDAO.getItemById(itemId);

            if (item == null) {
                throw new ItemException("could not find item: " + itemId + " to update");
            }

            item.setTitle((title == null) ? item.getTitle() : title);
            item.setContent((content == null) ? item.getContent() : content);

            iToDoListDAO.saveOrUpdate(item);

            return true;
        } catch (ItemException e) {
            e.printStackTrace();
            logger.error("failed to create a new item!");
            return false;
        }
    }

    /**
     * Get item by user id
     *
     * @param email
     * @throws ItemException
     */
    @Override
    public List<Item> getItemsByUserId(String email) throws ItemException {
        List<Item> userItems;
        try {

            if (email == null || email.equals("")) {
                logger.error("invalid user id: " + email);
                throw new ItemException("invalid user id was provided!");
            }

            userItems = iToDoListDAO.getItemsByUserId(email);

            if (userItems == null || userItems.size() == 0) {
                logger.info("no items were found for user: " + email);
                return new ArrayList<>();
            }
            return userItems;
        } catch (ItemException e) {
            e.printStackTrace();
            logger.error("failed to get items for user: " + email);
            return null;
        }
    }

    /**
     * Delete item by item id
     *
     * @param itemId
     * @throws ItemException
     */
    @Override
    public boolean deleteItemById(String itemId) throws ItemException {
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
        } catch (ItemException e) {
            e.printStackTrace();
            logger.error("failed to delete item: " + itemId);
        }
        return false;
    }

    /**
     * Delete all items by user id
     *
     * @param email
     * @throws ItemException
     */
    @Override
    public boolean deleteAllItemsByUserId(String email) throws ItemException {
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
