package com.yeren.cms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.yeren.cms.modle.DbUser;

public class UserDao {

	protected static Logger logger = Logger.getLogger("dao");

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

	/**
	 * 初始化数据
	 */
	private List<DbUser> internalDatabase() {

		List<DbUser> users = new ArrayList<DbUser>();
		DbUser user = null;

		user = new DbUser();
		user.setUsername("admin");

		// "admin"经过MD5加密后
		user.setPassword("admin");
		user.setAccess(1);

		users.add(user);

		user = new DbUser();
		user.setUsername("user");

		// "user"经过MD5加密后
		user.setPassword("user");
		user.setAccess(2);

		users.add(user);

		return users;

	}
}
