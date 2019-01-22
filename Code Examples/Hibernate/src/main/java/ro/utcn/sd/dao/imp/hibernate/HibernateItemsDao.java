/*************************************************************************
 * ULLINK CONFIDENTIAL INFORMATION
 * _______________________________
 *
 * All Rights Reserved.
 *
 * NOTICE: This file and its content are the property of Ullink. The
 * information included has been classified as Confidential and may
 * not be copied, modified, distributed, or otherwise disseminated, in
 * whole or part, without the express written permission of Ullink.
 ************************************************************************/
package ro.utcn.sd.dao.imp.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.utcn.sd.dao.ItemsDao;
import ro.utcn.sd.dao.ItemsDao;
import ro.utcn.sd.model.Items;
import ro.utcn.sd.model.Items;
import ro.utcn.sd.util.HibernateUtil;

public class HibernateItemsDao implements ItemsDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Items find(long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Items items = (Items) currentSession.get(Items.class, id);
        transaction.commit();
        return items;
    }

    @Override
    public void delete(Items objectToDelete)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(objectToDelete);
        transaction.commit();
    }

    @Override
    public void update(Items objectToUpdate)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(objectToUpdate);
        transaction.commit();
    }

    @Override
    public void insert(Items objectToCreate)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(objectToCreate);
        transaction.commit();
    }

    @Override
    public void deleteById(long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query hqlQuery = currentSession.createQuery("delete from Items where id= :idParameter");
        hqlQuery.setLong("idParameter",id);
        hqlQuery.executeUpdate();
        transaction.commit();
    }

    @Override
    public void closeConnection() {
        sessionFactory.close();
    }
}
