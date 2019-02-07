package ro.utcn.sd.dao.impl.jdbc;

import ro.utcn.sd.dao.CartDao;
import ro.utcn.sd.dao.ItemsDao;
import ro.utcn.sd.dao.DaoFactory;
import ro.utcn.sd.dao.impl.jdbc.JdbcCartDao;
import ro.utcn.sd.dao.impl.jdbc.JdbcItemsDao;

public class JdbcDaoFactory extends DaoFactory {

	@Override
	public CartDao getCartDao() {
		return new JdbcCartDao();
	}

	@Override
	public ItemsDao getItemsDao() {
		return new JdbcItemsDao();
	}
}
