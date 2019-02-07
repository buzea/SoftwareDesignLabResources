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

import ro.utcn.sd.dao.CartDao;
import ro.utcn.sd.dao.DaoFactory;
import ro.utcn.sd.dao.ItemsDao;
import ro.utcn.sd.entities.Cart;
import ro.utcn.sd.entities.Item;
import ro.utcn.sd.output.CartDetailsDTO;
import ro.utcn.sd.output.ItemDTO;

import java.util.Set;

public class ShowCartDetails {

    private final DaoFactory daoFactory;
    private       long       cartId;

    public ShowCartDetails(DaoFactory daoFactory, long cartId) {
        this.daoFactory = daoFactory;
        this.cartId = cartId;
    }

    public CartDetailsDTO execute() {
        CartDao cartDao = daoFactory.getCartDao();
        Cart cart = cartDao.find(cartId);
        ItemsDao itemsDao = daoFactory.getItemsDao();
        Set<Item> items = itemsDao.findByCartId(cartId);

        double total = 0;

        String name = cart.getName();
        CartDetailsDTO result = new CartDetailsDTO(name);
        for (Item item : items) {
            result.getItems().add(createItemDTO(item));
            total += item.getPrice();
        }
        result.setTotal(total);
        return result;
    }

    private ItemDTO createItemDTO(Item item) {
        return new ItemDTO(item.getName(), item.getPrice());
    }
}
