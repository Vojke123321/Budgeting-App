package com.vojislav.budgetingapp.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vojislav.budgetingapp.dao.UserDao;
import com.vojislav.budgetingapp.domain.Authority;
import com.vojislav.budgetingapp.domain.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User saveUser(User user) {
		Authority authority = new Authority();
		authority.setAuthority("ROLE_USER");
		authority.setUser(user);
		
		Set<Authority> authorities = new HashSet<Authority>();
		authorities.add(authority);
		
		user.setAuthorities(authorities);
		user = userDao.save(user);
		
		return user;
	}

}
