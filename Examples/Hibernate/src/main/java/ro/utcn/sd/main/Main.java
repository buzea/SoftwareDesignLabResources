package ro.utcn.sd.main;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.utcn.sd.dao.CartDao;
import ro.utcn.sd.dao.ItemsDao;
import ro.utcn.sd.dao.factory.DaoFactory;
import ro.utcn.sd.model.Cart;
import ro.utcn.sd.model.Items;
import ro.utcn.sd.util.HibernateUtil;

public class Main
{

    public static void main(String[] args)
    {
        Cart cart = new Cart();
        cart.setName("MyCart1");

        Items item1 = new Items("I10", 10, 1, cart);
        Items item2 = new Items("I20", 20, 2, cart);
        Set<Items> itemsSet = new HashSet<Items>();
        itemsSet.add(item1);
        itemsSet.add(item2);

        cart.setItems(itemsSet);
        cart.setTotal(10 * 1 + 20 * 2);

        CartDao cartDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getCartDao();

        cartDao.insert(cart);
        cartDao.closeConnection();
        System.out.println("Done");
    }

}
