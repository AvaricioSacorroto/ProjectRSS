package com.rssproject.persistencia.dao;

import java.util.List;

import com.rssproject.persistencia.model.UsuarioEntity;


public interface UsuarioDAO {
	public void addUsuario(UsuarioEntity usuarioEntity);

	public void updateUsuario(UsuarioEntity usuarioEntity);

	public List<UsuarioEntity> listUsuarios();

	public UsuarioEntity getUsuarioById(long id);

	public UsuarioEntity getUsuarioByUsername(String name);

	public void removeUsuario(long id);

	public void removeUrl(long id, String url);

	public void promoteUser(long id);
}
