package com.todolist.service;

import com.todolist.dao.HibernateToDoListDAO;
import com.todolist.dao.IToDoListDAO;
import com.todolist.exception.item.ItemException;
import com.todolist.pojo.Item;
import org.apache.log4j.Logger;
/**
 * Created by Papushe on 04/01/2018.
 */
public class IToDoListService {
    private final static Logger logger = Logger.getLogger(IToDoListService.class);
    private IToDoListDAO iToDoListDAO = HibernateToDoListDAO.getInstance();

    public IToDoListService(){}

    /**
     * creates a new item
     * returns true in success or false in failure
     */
    public boolean createItem(String userId, String title, String content){
        Item item = new Item(userId,title,content);
        try {
            iToDoListDAO.saveOrUpdate(item);
            logger.info("created a new item successfully: " + item.toString());
            return true;
        } catch (ItemException e) {
            e.printStackTrace();
            logger.info("failed to create a new item: " + item.toString());
            return false;
        }
    }
}
