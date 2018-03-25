package ro.utcn.sd.dao.impl.jdbc;

import ro.utcn.sd.dao.ItemsDao;
import ro.utcn.sd.dao.ItemsDao;
import ro.utcn.sd.model.Items;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class JdbcItemsDao implements ItemsDao {
	@Override
	public Items find(long id) {
		throw new NotImplementedException();
	}

	@Override
	public void delete(Items objectToDelete) {
		throw new NotImplementedException();
	}

	@Override
	public void update(Items objectToUpdate) {
		throw new NotImplementedException();
	}

	@Override
	public void insert(Items objectToCreate) {
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
