package ro.utcn.sd.dao.impl.jdbc;

import ro.utcn.sd.dao.ItemsDao;
import ro.utcn.sd.entities.Item;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

/**
 * Your job is to implement JDBC. You should know that by now
 */
public class JdbcItemsDao implements ItemsDao {
    @Override
    public Item find(long id) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(Item objectToDelete) {
        throw new NotImplementedException();
    }

    @Override
    public void update(Item objectToUpdate) {
        throw new NotImplementedException();
    }

    @Override
    public void insert(Item objectToCreate) {
        throw new NotImplementedException();
    }

    @Override
    public Set<Item> findByCartId(long cartId) {
        throw new NotImplementedException();
    }
}
