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
import ro.utcn.sd.dao.CartDao;
import ro.utcn.sd.model.Cart;

public class TestCartDao {

    private CartDao subject;
    private long latestId;

    @Before
    public void setup() {
        subject = new CartDao();
        Cart cart = new Cart();
        cart.setName("TestCart");
        cart.setTotal(10);
        subject.insert(cart);
        latestId = cart.getId();
    }

    @Test
    public void testFind() {
        Cart cart = subject.find(latestId);
        Assert.assertNotNull(cart);
        Assert.assertEquals(latestId, cart.getId());
        Assert.assertEquals("TestCart", cart.getName());
        Assert.assertEquals(0, Double.compare(10, cart.getTotal()));
    }

    @After
    public void teardown() {
        subject.deleteById(latestId);
    }
}
