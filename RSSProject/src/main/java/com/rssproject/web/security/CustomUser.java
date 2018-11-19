package com.rssproject.web.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {

	private Long id;

	private CustomUser(final String username, final String password,
	        final Collection<? extends GrantedAuthority> authorities, final Long id) {
		super(username, password, authorities);
		this.id = id;
	}


	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public static class Builder {

		private String username;
		private String password;
		private Collection<? extends GrantedAuthority> authorities;
		private Long id;

		public Builder setAuthorities(final Collection<? extends GrantedAuthority> authorities) {
			this.authorities = authorities;
			return this;
		}

		public Builder setPassword(final String password) {
			this.password = password;
			return this;
		}

		public Builder setUsername(final String username) {
			this.username = username;
			return this;
		}

		public Builder setId(final Long id) {
			this.id = id;
			return this;
		}

		public CustomUser build() {
			return new CustomUser(username, password, authorities, id);
		}

	}

}
