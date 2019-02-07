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
package ro.utcn.sd;

import ro.utcn.sd.business.ShowCartDetails;
import ro.utcn.sd.dao.DaoFactory;
import ro.utcn.sd.dao.impl.hibernate.util.HibernateUtil;
import ro.utcn.sd.entities.Cart;
import ro.utcn.sd.entities.Item;
import ro.utcn.sd.output.CartDetailsDTO;

import java.io.IOException;
import java.util.Scanner;

import static ro.utcn.sd.entities.builders.ItemBuilder.createItemBuilder;

public class Main {

    public static final int CART_ID = 1;

    /**
     * This is just a small demo.
     * <p>
     * Please also see  ShowCartDetailsTest. (Note ShowCartDetailsTest is not seen in the src folder)
     */
    public static void main(String[] args) throws IOException {
        DaoFactory daoFactory = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE);
        insertInitialData(daoFactory);

        ShowCartDetails transactionScript = new ShowCartDetails(daoFactory, CART_ID);

        CartDetailsDTO execute = transactionScript.execute();

        if (execute != null) {
            System.out.println("Name: " + execute.getName());
            System.out.println("Total:" + execute.getTotal());
            System.out.println("Items:");
            execute.getItems().forEach(System.out::println);
        }

        // wait for key pressed
        System.in.read();
        HibernateUtil.getSessionFactory().close();
    }

    private static void insertInitialData(DaoFactory daoFactory) {
        Item rope = createItemBuilder()
                .name("rope")
                .price(1.0)
                .build();

        Cart cart = new Cart();
        cart.setId(CART_ID);
        cart.setName("Yes it's stupid to give a name to a cart. But this is just an example!");

        cart.add(rope);

        daoFactory.getCartDao().insert(cart);
    }
}
