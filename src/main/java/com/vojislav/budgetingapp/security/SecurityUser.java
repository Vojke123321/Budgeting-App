package com.vojislav.budgetingapp.security;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.vojislav.budgetingapp.domain.Authority;
import com.vojislav.budgetingapp.domain.User;

public class SecurityUser extends User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -604518387262746725L;
	public SecurityUser() {
	}
	

	public SecurityUser(User user) {
		this.setAuthorities(user.getAuthorities());
		this.setBudgets(user.getBudgets());
		this.setPassword(user.getPassword());
		this.setUsername(user.getUsername());
		this.setId(user.getId());
	}


	@Override
	public Set<Authority> getAuthorities() {
		return super.getAuthorities();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
