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
package com.journaldev.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.journaldev.hibernate.model.Cart;
import com.journaldev.hibernate.util.HibernateUtil;

public class CartDao implements Dao<Cart>
{
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Cart find(long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        return ;
    }

    @Override
    public void delete(Cart objectToDelete)
    {

    }

    @Override
    public void update(Cart objectToUpdate)
    {

    }

    @Override
    public void insert(Cart objectToCreate)
    {

    }
}
