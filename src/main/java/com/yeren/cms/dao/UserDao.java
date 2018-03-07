package com.yeren.cms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.yeren.cms.modle.DbUser;

public class UserDao {

	protected static Logger logger = Logger.getLogger("UserDao");

	public DbUser getDatabase(String username) {
		List<DbUser> users = internalDatabase();
		for (DbUser dbUser : users) {
			if (dbUser.getUsername().equals(username) == true) {
				logger.debug("User found");
				return dbUser;
			}
		}
		logger.error("User does not exist!");
		throw new RuntimeException("User does not exist!");

	}

	private List<DbUser> internalDatabase() {
		List<DbUser> users = new ArrayList<DbUser>();
		DbUser user = null;

		user = new DbUser();
		user.setUsername("admin");
		user.setPassword("admin");
		user.setAccess(1);
		users.add(user);
		
		user = new DbUser();
		user.setUsername("user");
		user.setPassword("user");
		user.setAccess(2);
		users.add(user);

		return users;
	}
}
