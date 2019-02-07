package ro.utcn.sd.dao;

import ro.utcn.sd.entities.Item;

import java.util.Set;

public interface ItemsDao extends Dao<Item> {

    @Override
    Item find(long id);

    @Override
    void delete(Item objectToDelete);

    @Override
    void update(Item objectToUpdate);

    @Override
    void insert(Item objectToCreate);

    Set<Item> findByCartId(long cartId);
}
