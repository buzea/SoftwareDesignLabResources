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
package ro.utcn.sd.business;

import org.junit.Assert;
import org.junit.Test;
import ro.utcn.sd.dao.CartDao;
import ro.utcn.sd.dao.DaoFactory;
import ro.utcn.sd.dao.ItemsDao;
import ro.utcn.sd.entities.Cart;
import ro.utcn.sd.entities.Item;
import ro.utcn.sd.output.CartDetailsDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class ShowCartDetailsTest {

    private ShowCartDetails subject;

    @Test
    public void testCartTotalIsComputed() {
        // given
        Item item1 = new Item();
        item1.setPrice(5);
        Item item2 = new Item();
        item2.setPrice(6);
        ItemsDao mockItemsDao = mockItemsDao(asList(item1, item2));

        long cartId = 1;
        Cart cart = new Cart();
        cart.setId(1);
        cart.add(item1);
        cart.add(item2);
        CartDao mockCartDao = mockCartDao(asList(cart));

        subject = new ShowCartDetails(new DaoFactory() {
            @Override
            public CartDao getCartDao() {
                return mockCartDao;
            }

            @Override
            public ItemsDao getItemsDao() {
                return mockItemsDao;
            }
        }, cartId);

        // when:
        CartDetailsDTO result = subject.execute();

        // then:
        Assert.assertEquals(11, result.getTotal(), 0.0001);
    }

    private ItemsDao mockItemsDao(List<Item> items) {
        return new ItemsDao() {
            @Override
            public Item find(long id) {
                return items.stream().filter(it -> it.getId() == id).findAny().get();
            }

            @Override
            public void delete(Item objectToDelete) {
                items.remove(objectToDelete);
            }

            @Override
            public void update(Item objectToUpdate) {

            }

            @Override
            public void insert(Item objectToCreate) {
                items.add(objectToCreate);
            }

            @Override
            public Set<Item> findByCartId(long cartId) {
                return items
                        .stream()
                        .filter(item -> item.getCarts()
                                            .stream()
                                            .map(Cart::getId)
                                            .filter(id -> id == cartId)
                                            .count() == 1
                               )
                        .collect(Collectors.toSet());
            }
        };
    }

    private CartDao mockCartDao(List<Cart> data) {
        return new CartDao() {
            @Override
            public Cart find(long id) {
                return data.stream().filter(it -> it.getId() == id).findAny().get();
            }

            @Override
            public void delete(Cart objectToDelete) {
                data.remove(objectToDelete);
            }

            @Override
            public void update(Cart objectToUpdate) {

            }

            @Override
            public void insert(Cart objectToCreate) {
                data.add(objectToCreate);
            }
        };
    }
}
