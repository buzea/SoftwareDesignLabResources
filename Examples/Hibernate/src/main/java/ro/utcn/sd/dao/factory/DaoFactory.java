package ro.utcn.sd.dao.factory;

import ro.utcn.sd.dao.CartDao;
import ro.utcn.sd.dao.ItemsDao;
import ro.utcn.sd.dao.factory.impl.HibernateDaoFactory;
import ro.utcn.sd.dao.factory.impl.JdbcDaoFactory;

public abstract class DaoFactory {

	public enum Type {
		HIBERNATE,
		JDBC;
	}


	protected DaoFactory(){

	}

	public static DaoFactory getInstance(Type factoryType) {
		switch (factoryType) {
			case HIBERNATE:
				return new HibernateDaoFactory();
			case JDBC:
				return new JdbcDaoFactory();
			default:
				throw new IllegalArgumentException("Invalid factory");
		}
	}

	public abstract CartDao getCartDao();

	public abstract ItemsDao getItemsDao();
}
