package com.rssproject.negocios.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rssproject.api.modelo.Entries;
import com.rssproject.api.modelo.Feed;
import com.rssproject.api.modelo.Usuario;
import com.rssproject.api.services.UsuarioService;
import com.rssproject.persistencia.dao.UsuarioDAO;
import com.rssproject.persistencia.model.UrlEntity;
import com.rssproject.persistencia.model.UsuarioEntity;
import com.rssproject.web.forms.UrlForm;
import com.rssproject.web.forms.UsuarioForm;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Repository("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioDAO usuarioDAO;

	@Override
	@Transactional
	public void addURL(Long id, UrlForm urlForm) {
		System.out.println("USUARIO:" + usuarioDAO.getUsuarioById(id).getName());
		System.out.println("URL to add:" + urlForm.getUrl());
		UsuarioEntity usuarioEntity = usuarioDAO.getUsuarioById(id);
		UrlEntity urlEntity = new UrlEntity(urlForm.getUrl());
		urlEntity.setUsuarioId(usuarioEntity);
		usuarioEntity.getUrls().add(urlEntity);
	}

	@Override
	@Transactional
	public void addUser(UsuarioForm usuarioForm) {
		UsuarioEntity usuarioEntity = transformFormtoEntity(usuarioForm);
		usuarioDAO.addUsuario(usuarioEntity);
	}

	@Override
	@Transactional
	public void updateUser(UsuarioForm usuarioForm) {
		UsuarioEntity usuarioEntity = transformFormtoEntity(usuarioForm);
		usuarioDAO.updateUsuario(usuarioEntity);
	}

	@Override
	@Transactional
	public List<Usuario> listUsuarios() {
		return transformarListUsuarioEntityToListUsuario(usuarioDAO.listUsuarios());
	}

	@Override
	@Transactional
	public Usuario getUsuarioById(long id) {
		Usuario usuario = transformUsuarioEntitytoUsuario(usuarioDAO.getUsuarioById(id));
		return usuario;
	}

	@Override
	@Transactional
	public Usuario getUsuarioByName(String name) {
		UsuarioEntity userEntity = usuarioDAO.getUsuarioByUsername(name);
		System.out.println("UserEntity:" + userEntity);
		Usuario usuario = transformUsuarioEntitytoUsuario(userEntity);
		return usuario;
	}

	@Override
	@Transactional
	public void removeUsuario(Long id) {
		this.usuarioDAO.removeUsuario(id);
	}


	private List<Usuario> transformarListUsuarioEntityToListUsuario(List<UsuarioEntity> usuariosEntity) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		for (UsuarioEntity usuarioEntity : usuariosEntity) {
			usuarios.add(transformUsuarioEntitytoUsuario(usuarioEntity));
		}
		return usuarios;
	}

	private Usuario transformUsuarioEntitytoUsuario(UsuarioEntity usuarioEntity) {
		if (usuarioEntity == null) {
			return null;
		}
		Usuario usuario = new Usuario();
		usuario.setName(usuarioEntity.getName());
		usuario.setPassword(usuarioEntity.getPassword());
		usuario.setRol(usuario.getRol());
		usuario.setFeeds(getFeeds(usuarioEntity.getUrls()));
		return usuario;

	}

	@Override
	@Transactional
	public List<Feed> getFeedFromId(Long id) {
		Usuario usuario = getUsuarioById(id);
		System.out.println("Usuario FIND:" + usuario.getName());
		return usuario.getFeeds();
	}

	private List<Feed> getFeeds(List<UrlEntity> urlEntities) {
		List<Feed> feeds = new ArrayList<Feed>();
		for (UrlEntity urlEntity : urlEntities) {
			Feed feed = getFeedsfromUrl(urlEntity.getUrl());
			if (feed != null) {
				feeds.add(feed);
			}
		}

		return feeds;
	}

	private Feed getFeedsfromUrl(String url) {
		SyndFeed feed = null;
		Feed customFeed = new Feed();
		try {
			feed = new SyndFeedInput().build(new XmlReader(new URL(url)));

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FeedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (feed == null) {
			return null;
		}
		customFeed.setTitle(feed.getTitle());
		customFeed.setImageTitle(feed.getImage().getTitle());
		customFeed.setImageUrl(feed.getImage().getUrl());
		customFeed.setLanguaje(feed.getLanguage());
		customFeed.setLink(feed.getLink());
		List<Entries> entries = getEntriesFromFeed(feed.getEntries());

		customFeed.setEntries(entries);

		return customFeed;
	}

	private List<Entries> getEntriesFromFeed(List<SyndEntryImpl> feedEntries) {
		List<Entries> entries = new ArrayList<Entries>();

		for (SyndEntryImpl syndEntrie : feedEntries) {
			entries.add(transformSyndEntrytoEntries(syndEntrie));
		}
		return entries;

	}

	private Entries transformSyndEntrytoEntries(SyndEntryImpl syndEntrie) {
		Entries entries = new Entries();
		if (syndEntrie.getTitle() != null) {
			entries.setTitle(syndEntrie.getTitle());
		}
		// if (syndEntrie.getPublishedDate().toString() == "") {
		// entries.setDate(syndEntrie.getPublishedDate().toString());
		// }
		if (syndEntrie.getLink() != null) {
			entries.setLink(syndEntrie.getLink());
		}
		if (syndEntrie.getDescription().getValue() != null) {
			entries.setDescriptionValue(syndEntrie.getDescription().getValue());
		}

		return entries;
	}
	private UsuarioEntity transformFormtoEntity(UsuarioForm usuarioForm) {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setName(usuarioForm.getName());
		usuarioEntity.setPassword(usuarioForm.getPassword());
		usuarioEntity.setRol("ROLE_USER");
		return usuarioEntity;

	}
}
