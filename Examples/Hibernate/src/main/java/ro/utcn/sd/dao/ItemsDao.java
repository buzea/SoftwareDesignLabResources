package ro.utcn.sd.dao;

import ro.utcn.sd.model.Cart;
import ro.utcn.sd.model.Items;

public interface ItemsDao extends Dao<Items> {

	@Override
	Items find(long id);

	@Override
	void delete(Items objectToDelete);

	@Override
	void update(Items objectToUpdate);

	@Override
	void insert(Items objectToCreate);

}
