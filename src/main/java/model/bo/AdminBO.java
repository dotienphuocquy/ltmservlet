package model.bo;

import model.dao.AdminDAO;

public class AdminBO {
	
	public static boolean isValidAdmin(String username, String password) {
		return AdminDAO.checkAdmin(username, password);
	}
}
