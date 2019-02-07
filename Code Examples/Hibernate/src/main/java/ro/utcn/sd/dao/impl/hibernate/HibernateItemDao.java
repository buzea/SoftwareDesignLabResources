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
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.dao.ItemsDao;
import ro.utcn.sd.dao.impl.hibernate.util.HibernateUtil;
import ro.utcn.sd.entities.Cart;
import ro.utcn.sd.entities.Item;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HibernateItemDao implements ItemsDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    @Transactional(readOnly = true)
    public Item find(long id) {
        Session currentSession = sessionFactory.openSession();
        Item items = currentSession.get(Item.class, id);
        return items;
    }

    @Override
    public void delete(Item objectToDelete) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(objectToDelete);
        transaction.commit();
        currentSession.close();
    }

    @Override
    public void update(Item objectToUpdate) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(objectToUpdate);
        transaction.commit();
        currentSession.close();
    }

    @Override
    public void insert(Item objectToCreate) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(objectToCreate);
        transaction.commit();
        currentSession.close();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Item> findByCartId(long cartId) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = builder.createQuery(Item.class);
        Root<Cart> root = criteriaQuery.from(Cart.class);
        criteriaQuery.where(builder.equal(root.get("id"), cartId));
        CriteriaQuery<Item> itemCriteriaQuery = criteriaQuery.select(root.join("items"));
        List<Item> resultList = currentSession.createQuery(itemCriteriaQuery).getResultList();
        transaction.commit();
        currentSession.close();
        return new HashSet<>(resultList);
    }
}
