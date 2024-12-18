package model.dao;

import model.bean.Admin;

public class AdminDAO {
	private static final GenericDAO<Admin> genericDAO = new GenericDAO<Admin>(rs -> new Admin(rs.getString("username"), rs.getString("password")));
	
	public static boolean checkAdmin(String username, String password) {
		String sql = "Select * from admin where username = ? and password = ?";
		if(genericDAO.find(sql, username, password) != null)
			return true;
		return false;
	}
}
