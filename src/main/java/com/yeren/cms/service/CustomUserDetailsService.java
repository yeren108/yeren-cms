package com.yeren.cms.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yeren.cms.dao.UserDao;
import com.yeren.cms.modle.DbUser;

/**
 * 一个自定义的service用来和数据库进行操作. 即以后我们要通过数据库保存权限.则需要我们继承UserDetailsService
 * 
 */
public class CustomUserDetailsService implements UserDetailsService {

	protected static Logger logger = Logger.getLogger("CustomUserDetailsService");

	private UserDao userDAO = new UserDao();

	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException, DataAccessException {
		UserDetails user = null;
		try {
			DbUser dbUser = userDAO.getDatabase(username);
			user = new User(dbUser.getUsername(), dbUser.getPassword()
					.toLowerCase(), true, true, true, true,
					getAuthorities(dbUser.getAccess()));

		} catch (Exception e) {
			logger.error("Error in retrieving user");
			throw new UsernameNotFoundException("Error in retrieving user");
		}

		return user;
	}

	/**
	 * 获得访问角色权限
	 * 
	 * @param access
	 * @return
	 */
	public Collection<GrantedAuthority> getAuthorities(Integer access) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

		// 所有的用户默认拥有ROLE_USER权限
		logger.debug("Grant ROLE_USER to this user");
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));
//		authList.add(new GrantedAuthorityImpl("ROLE_USER"));

		// 如果参数access为1.则拥有ROLE_ADMIN权限
		if (access.compareTo(1) == 0) {
			logger.debug("Grant ROLE_ADMIN to this user");
//			authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		return authList;
	}
}
