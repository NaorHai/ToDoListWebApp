package com.todoList.dao;

import com.todoList.configuration.HibernateHelper;
import com.todoList.pojo.Item;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;


/**
 * Created by Papushe on 14/12/2017.
 */
public class HibernateToDoListDAO implements IToDoListDAO {

    private final static Logger logger = Logger.getLogger(HibernateToDoListDAO.class);
    private static IToDoListDAO instance;
    private Session session;

    private HibernateToDoListDAO() {}

    public static synchronized IToDoListDAO getInstance() {
        if (instance == null) {
            return new HibernateToDoListDAO();
        }
        return instance;
    }

    @Override
    public void saveOrUpdate(Item item) {
        session = HibernateHelper.getSession();
        session.beginTransaction();

        try{
            session.saveOrUpdate(item);
            session.getTransaction().commit();
            logger.info("Item with id " + item.getItemId() +" was saved successfully");
        }catch (Exception e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            logger.error("Failed to save an item " + item.toString());
            logger.error(e.getStackTrace());
        }finally {
            session.close();
        }
    }

    @Override
    public boolean deleteItem(Item item) {
        session = HibernateHelper.getSession();
        session.beginTransaction();

        try{
            session.delete(item);
            session.getTransaction().commit();
            logger.info("Item with id " + item +" was deleted successfully");
            return true;
        }catch (Exception e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete an item with id: " + item);
            logger.error(e.getStackTrace());
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Item> getItemsByUserId(String userId) {
        session = HibernateHelper.getSession();
        session.beginTransaction();
        List<Item> items = null;

        try{

            items =  (List<Item>) session.createCriteria(Item.class)
                    .add(Restrictions.eq("userId", userId)).list();
            session.getTransaction().commit();
            logger.info("Got " + items.size() + "Item(s) for user");
        }catch (Exception e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            logger.error("Failed to get items for user with id: " + userId);
            logger.error(e.getStackTrace());
        }finally {
            session.close();
        }
        return items;
    }
}
