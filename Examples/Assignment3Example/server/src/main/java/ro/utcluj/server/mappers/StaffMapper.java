package ro.utcluj.server.mappers;

import ro.utcluj.server.model.Staff;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StaffMapper extends Mapper {
	private static StaffMapper         instance;
	private        Map<Integer, Staff> buffer;

	private StaffMapper() throws DataSourceException {
		buffer = new HashMap<>();
	}

	public static StaffMapper getInstance() throws DataSourceException {
		if (instance == null) {
			instance = new StaffMapper();
		}
		return instance;
	}

	public void create(Staff staff) throws DataSourceException {
		String sql = "INSERT INTO `hospital`.`staff` (`username`, `password`, `role`) VALUES (?, ?, ?);";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, staff.getUsername());
			statement.setString(2, staff.getPassword());
			statement.setString(3, staff.getRole().toString());
			statement.executeUpdate();
			// can't add to buffer yet
		} catch (SQLException e) {
			throw new DataSourceException(e);
		}
	}

	public void update(Staff staff) throws DataSourceException {
		String sql = "UPDATE `hospital`.`staff` SET `username`=?, `password`=?, `role`=? WHERE `id`=?;";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, staff.getUsername());
			statement.setString(2, staff.getPassword());
			statement.setString(3, staff.getRole().toString());
			statement.setInt(4, staff.getId());
			statement.executeUpdate();
			// can't add to buffer yet
		} catch (SQLException e) {
			throw new DataSourceException(e);
		}
	}

	public void delete(int id) throws DataSourceException {
		String sql = "DELETE FROM `hospital`.`staff` WHERE `id`=?;";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
			buffer.remove(id);
		} catch (SQLException e) {
			throw new DataSourceException(e);
		}
	}

	public Staff getStaff(String username) throws DataSourceException {
		String sql = "select * from staff where username= ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				if (buffer.containsKey(id)) {
					return buffer.get(id);
				} else {
					Staff staff = new Staff();
					staff.setId(id);
					staff.setUsername(rs.getString("username"));
					staff.setPassword(rs.getString("password"));
					staff.setRole(Staff.Type.valueOf(rs.getString("role")));

					return staff;
				}
			}
		} catch (SQLException e) {
			throw new DataSourceException(e);
		}

		return null;
	}
}
