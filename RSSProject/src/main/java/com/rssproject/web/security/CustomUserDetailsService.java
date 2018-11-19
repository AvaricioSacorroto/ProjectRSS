package com.rssproject.web.security;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rssproject.persistencia.dao.UsuarioDAO;
import com.rssproject.persistencia.model.UsuarioEntity;



@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UsuarioDAO usuarioDAO;

	final static Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
		final UsuarioEntity usuarioEntity = usuarioDAO.getUsuarioByUsername(userName);//
		final Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(usuarioEntity.getRol()));

		final CustomUser customUser = new CustomUser.Builder().setId(usuarioEntity.getId())
		        .setAuthorities(grantedAuthorities).setPassword(usuarioEntity.getPassword())
		        .setUsername(usuarioEntity.getName()).build();

		return customUser;
	}

}
