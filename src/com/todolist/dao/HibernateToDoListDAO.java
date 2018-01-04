package com.todolist.dao;

import com.todolist.configuration.HibernateHelper;
import com.todolist.exception.item.ItemException;
import com.todolist.pojo.Item;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;


/**
 * Created by Papushe on 14/12/2017.
 * HibernateToDoListDAO class - an implementation of UserDAO interface
 */
public class HibernateToDoListDAO implements IToDoListDAO {

    private final static Logger logger = Logger.getLogger(HibernateToDoListDAO.class);
    private static IToDoListDAO instance;
    private Session session;

    private HibernateToDoListDAO() {}

    /**
    * returns a singleton object which implements IToDoListDAO interface
    * lazy initialization
    * */
    public static synchronized IToDoListDAO getInstance() {
        if (instance == null) {
            return new HibernateToDoListDAO();
        }
        return instance;
    }

    /**
     * creating or updating item
     * */
    @Override
    public boolean saveOrUpdate(Item item) throws ItemException {
        session = HibernateHelper.getSession();
        session.beginTransaction();

        try{
            session.saveOrUpdate(item);
            session.getTransaction().commit();
            logger.info("Item with id " + item.getItemId() +" was saved successfully");
            return true;
        }catch (Exception e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            logger.error("Failed to save an item " + item.toString());
            logger.error(e.getStackTrace());
            throw new ItemException(e.getMessage(), e);
//            return false;
        }finally {
            session.close();
        }
    }

    /**
     * deleting an item
     * */
    @Override
    public boolean deleteItem(Item item) throws ItemException {
        session = HibernateHelper.getSession();
        session.beginTransaction();

        try{
            session.delete(item);
            session.getTransaction().commit();
            logger.info("Item with id " + item.getItemId() +" was deleted successfully");
            return true;
        }catch (Exception e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete an item with id: " + item.getItemId());
            logger.error(e.getStackTrace());
            throw new ItemException(e.getMessage(), e);
//            return false;
        }finally {
            session.close();
        }
    }

    /**
     * getting all user items by userId
     * */
    @Override
    @SuppressWarnings("unchecked")
    public List<Item> getItemsByUserId(String userId) throws ItemException {
        session = HibernateHelper.getSession();
        session.beginTransaction();
        List<Item> items = null;

        try{

            items =  (List<Item>) session.createCriteria(Item.class)
                    .add(Restrictions.eq("userId", userId)).list();
            session.getTransaction().commit();
            if (items == null) {
                throw new ItemException("Got null instead items for user: " + userId);
            }
            logger.info("Got " + items.size() + "Item(s) for user id: " + userId);
        }catch (ItemException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            logger.error("Failed to get items for user with id: " + userId);
            logger.error(e.getStackTrace());
            throw new ItemException(e.getMessage(), e);
        }finally {
            session.close();
        }
        return items;
    }

    /**
     * deleting all user Items by userId
     * */
    @Override
    @SuppressWarnings("unchecked")
    public boolean deleteAllItemsByUserId(String userId) throws ItemException {
        session = HibernateHelper.getSession();
        session.beginTransaction();

        try{
            List<Item> itemsToDelete = (List<Item>) session.createCriteria(Item.class)
                    .add(Restrictions.eq("userId",userId)).list();

            if (itemsToDelete == null) {
                throw new ItemException("Error while searching for items to delete");
            }

            for (Item item : itemsToDelete) {
                session.delete(item);
            }

            session.getTransaction().commit();
            logger.info("Items with user id " + userId +" was deleted successfully");
            return true;
        }catch (ItemException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete an items with user id: " + userId);
            logger.error(e.getStackTrace());
            throw new ItemException(e.getMessage(), e);
//            return false;
        }finally {
            session.close();
        }
    }
}
