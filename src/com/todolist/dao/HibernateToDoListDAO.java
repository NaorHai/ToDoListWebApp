package com.todolist.dao;

import com.todolist.configuration.HibernateHelper;
import com.todolist.exception.item.ItemException;
import com.todolist.pojo.Item;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionException;
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
     * Save or update entity
     * @param item
     * @throws ItemException
     */
    @Override
    public void saveOrUpdate(Item item) throws ItemException {
        try{
            session = HibernateHelper.getSession();

            if (session == null) {
                throw new ItemException("could not open session");
            }

            session.beginTransaction();

            session.saveOrUpdate(item);
            session.getTransaction().commit();
            logger.info("Item with id " + item.getItemId() +" was saved successfully");
        }catch (Exception e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            logger.error("Failed to save an item " + item.toString());
            logger.error(e.getStackTrace());
            throw new ItemException(e.getMessage(), e);
        }finally {
            session.close();
        }
    }
    /**
     * Save or update entity
     * @param
     * @throws ItemException
     */
//    @Override
//    public boolean updateItem(Item item) throws ItemException {
//        try{
//            session = HibernateHelper.getSession();
//
//            if (session == null) {
//                throw new ItemException("could not open session");
//            }
//
//            session.beginTransaction();
//
//            session.saveOrUpdate(item);
//            session.getTransaction().commit();
//            logger.info("Item with id " + item.getItemId() +" was saved successfully");
//            return true;
//        }catch (Exception e){
//            if(session.getTransaction() != null){
//                session.getTransaction().rollback();
//            }
//            logger.error("Failed to save an item " + item.toString());
//            logger.error(e.getStackTrace());
//            return false;
////            throw new ItemException(e.getMessage(), e);
//        }finally {
//            session.close();
//        }
//    }
    /**
     * Deleting entity
     * @param item
     * @throws ItemException
     */
    @Override
    public void deleteItem(Item item) throws ItemException {
        try{
            session = HibernateHelper.getSession();

            if (session == null) {
                throw new ItemException("could not open session");
            }

            session.beginTransaction();

            session.delete(item);
            session.getTransaction().commit();
            logger.info("Item with id " + item.getItemId() +" was deleted successfully");
        }catch (Exception e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete an item with id: " + item.getItemId());
            logger.error(e.getStackTrace());
            throw new ItemException(e.getMessage(), e);
        }finally {
            session.close();
        }
    }

    /**
     * Get entity by email
     * @param email
     * @throws ItemException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Item> getItemsByUserId(String email) throws ItemException {
        List<Item> items;

        try{
            session = HibernateHelper.getSession();

            if (session == null) {
                throw new ItemException("could not open session");
            }

            session.beginTransaction();

            items =  (List<Item>) session.createCriteria(Item.class)
                    .add(Restrictions.eq("email", email)).list();
            session.getTransaction().commit();
            if (items == null) {
                throw new ItemException("Got null instead items for user: " + email);
            }
            logger.info("Got " + items.size() + "Item(s) for user id: " + email);
        }catch (ItemException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            logger.error("Failed to get items for user with id: " + email);
            logger.error(e.getStackTrace());
            throw new ItemException(e.getMessage(), e);
        }finally {
            session.close();
        }
        return items;
    }

    /**
     * Get entity by id
     * @param itemId
     * @throws ItemException
     */
    @Override
    public Item getItemById(String itemId) throws ItemException {
        Item item;
        try {
            session = HibernateHelper.getSession();

            if (session == null) {
                throw new ItemException("could not open session");
            }

            session.beginTransaction();

            item = (Item) session.get(Item.class, itemId);
            session.getTransaction().commit();
            if (item == null) {
                throw new ItemException("Item: " + itemId + " not found");
            }
        } catch (ItemException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get an item with id: " + itemId);
            logger.error(e.getStackTrace());
            throw new ItemException(e.getMessage(), e);
        } finally {
            session.close();
        }
        return item;
    }

    /**
     * Delete all entities by foreign key
     * @param userId
     * @throws ItemException
     */
    @Override
    @SuppressWarnings("unchecked")
    public void deleteAllItemsByUserId(String userId) throws ItemException {
        try{
            session = HibernateHelper.getSession();

            if (session == null) {
                throw new ItemException("could not open session");
            }

            session.beginTransaction();

            List<Item> itemsToDelete = (List<Item>) session.createCriteria(Item.class)
                    .add(Restrictions.eq("email",userId)).list();

            if (itemsToDelete == null) {
                throw new ItemException("Error while searching for items to delete");
            }

            for (Item item : itemsToDelete) {
                session.delete(item);
            }

            session.getTransaction().commit();
            logger.info("Items with user id " + userId +" was deleted successfully");

        }catch (ItemException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete an items with user id: " + userId);
            logger.error(e.getStackTrace());
            throw new ItemException(e.getMessage(), e);
        }finally {
            session.close();
        }
    }
}
