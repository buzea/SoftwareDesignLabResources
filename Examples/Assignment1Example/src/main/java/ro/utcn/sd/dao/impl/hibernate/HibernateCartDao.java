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
package ro.utcn.sd.dao.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.utcn.sd.dao.CartDao;
import ro.utcn.sd.dao.impl.hibernate.util.HibernateUtil;
import ro.utcn.sd.entities.Cart;

public class HibernateCartDao implements CartDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Cart find(long id) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        Cart cart = currentSession.get(Cart.class, id);
        transaction.commit();
        currentSession.close();
        return cart;
    }

    @Override
    public void delete(Cart objectToDelete) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(objectToDelete);
        transaction.commit();
        currentSession.close();
    }

    @Override
    public void update(Cart objectToUpdate) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(objectToUpdate);
        transaction.commit();
        currentSession.close();
    }

    @Override
    public void insert(Cart objectToCreate) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.merge(objectToCreate);
        transaction.commit();
        currentSession.close();
    }
}
