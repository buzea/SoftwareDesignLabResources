package ro.utcn.sd.dao.impl.hibernate;

import ro.utcn.sd.dao.CartDao;
import ro.utcn.sd.dao.ItemsDao;
import ro.utcn.sd.dao.DaoFactory;

public class HibernateDaoFactory extends DaoFactory {
	@Override
	public CartDao getCartDao() {
		return new HibernateCartDao();
	}

	@Override
	public ItemsDao getItemsDao() {
		return new HibernateItemDao();
	}

}
