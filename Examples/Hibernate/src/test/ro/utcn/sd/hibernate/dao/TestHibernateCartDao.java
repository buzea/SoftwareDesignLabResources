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
package ro.utcn.sd.hibernate.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.utcn.sd.dao.imp.hibernate.HibernateCartDao;
import ro.utcn.sd.dao.CartDao;
import ro.utcn.sd.model.Cart;
import ro.utcn.sd.model.Items;

import java.util.HashSet;
import java.util.Set;

public class TestHibernateCartDao {

    private CartDao subject;
    private long    latestId;

    @Before
    public void setup() {
        subject = new HibernateCartDao();
        Cart cart = new Cart();
        cart.setName("TestCart");
        cart.setTotal(10);
        Items item1 = new Items("I10", 10, 1, cart);
        Items item2 = new Items("I20", 20, 2, cart);
        Set<Items> itemsSet = new HashSet<Items>();
        itemsSet.add(item1);
        itemsSet.add(item2);
        cart.setItems(itemsSet);
        subject.insert(cart);
        latestId = cart.getId();
    }

    @Test
    public void testFind() {
        Cart cart = subject.find(latestId);
        Assert.assertNotNull(cart);
        Assert.assertEquals(2,cart.getItems().size());
        Assert.assertEquals(latestId, cart.getId());
        Assert.assertEquals("TestCart", cart.getName());
        Assert.assertEquals(0, Double.compare(10, cart.getTotal()));
    }

    @After
    public void teardown() {
        subject.deleteById(latestId);
    }
}
