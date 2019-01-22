package ro.utcn.sd.dao.factory.impl;

import ro.utcn.sd.dao.CartDao;
import ro.utcn.sd.dao.ItemsDao;
import ro.utcn.sd.dao.factory.DaoFactory;
import ro.utcn.sd.dao.imp.hibernate.HibernateCartDao;
import ro.utcn.sd.dao.imp.hibernate.HibernateItemsDao;

public class HibernateDaoFactory extends DaoFactory {
	@Override
	public CartDao getCartDao() {
		return new HibernateCartDao();
	}

	@Override
	public ItemsDao getItemsDao() {
		return new HibernateItemsDao();
	}
}
