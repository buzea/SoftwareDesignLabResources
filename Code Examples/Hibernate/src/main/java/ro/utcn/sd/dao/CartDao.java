package ro.utcn.sd.dao;

import ro.utcn.sd.entities.Cart;

public interface CartDao extends Dao<Cart> {

	@Override
	Cart find(long id);

	@Override
	void delete(Cart objectToDelete);

	@Override
	void update(Cart objectToUpdate);

	@Override
	void insert(Cart objectToCreate);


}
