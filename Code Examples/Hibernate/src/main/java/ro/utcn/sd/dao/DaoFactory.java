package ro.utcn.sd.dao;

import ro.utcn.sd.dao.impl.hibernate.HibernateDaoFactory;
import ro.utcn.sd.dao.impl.jdbc.JdbcDaoFactory;

public abstract class DaoFactory {

    public enum Type {
        HIBERNATE,
        JDBC
    }

    protected DaoFactory() {

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
