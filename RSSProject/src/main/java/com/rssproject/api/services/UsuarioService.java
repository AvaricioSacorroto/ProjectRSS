package com.rssproject.api.services;

import java.util.List;

import com.rssproject.api.modelo.Feed;
import com.rssproject.api.modelo.Usuario;
import com.rssproject.web.forms.UrlForm;
import com.rssproject.web.forms.UserForm;
import com.rssproject.web.forms.UsuarioForm;


public interface UsuarioService {

	public void addUser(UsuarioForm usuarioForm);

	public void updateUser(UserForm userForm);

	public List<Usuario> listUsuarios();

	public Usuario getUsuarioById(long id);

	public Usuario getUsuarioByName(String username);

	public void removeUsuario(Long id);

	public List<Feed> getFeedFromId(Long id);

	public void addURL(Long id, UrlForm urlForm);

	public void removeUrl(UrlForm urlForm);

	public UserForm getUserFormbyID(Long id);

	public void promoteUser(long id);
}
