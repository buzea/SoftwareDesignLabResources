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
package ro.utcn.sd.dao;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import ro.utcn.sd.model.Cart;
import ro.utcn.sd.util.HibernateUtil;

public class CartDao implements Dao<Cart>
{
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Cart find(long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Cart cart = (Cart) currentSession.get(Cart.class, id);
        transaction.commit();
        return cart;
    }

    @Override
    public void delete(Cart objectToDelete)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(objectToDelete);
        transaction.commit();
    }

    @Override
    public void update(Cart objectToUpdate)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(objectToUpdate);
        transaction.commit();
    }

    @Override
    public void insert(Cart objectToCreate)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(objectToCreate);
        transaction.commit();
    }

    public void deleteById(long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query hqlQuery = currentSession.createQuery("delete from Cart where id= :idParameter");
        hqlQuery.setLong("idParameter",id);
        hqlQuery.executeUpdate();
        transaction.commit();
    }
}
