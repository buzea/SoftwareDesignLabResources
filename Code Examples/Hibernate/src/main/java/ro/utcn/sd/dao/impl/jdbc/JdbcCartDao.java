package ro.utcn.sd.dao.impl.jdbc;

import ro.utcn.sd.dao.CartDao;
import ro.utcn.sd.model.Cart;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class JdbcCartDao  implements CartDao{
	@Override
	public Cart find(long id) {
		throw new NotImplementedException();
	}

	@Override
	public void delete(Cart objectToDelete) {
		throw new NotImplementedException();
	}

	@Override
	public void update(Cart objectToUpdate) {
		throw new NotImplementedException();
	}

	@Override
	public void insert(Cart objectToCreate) {
		throw new NotImplementedException();
	}

	@Override
	public void deleteById(long id) {
		throw new NotImplementedException();
	}

	@Override
	public void closeConnection() {

	}
}
