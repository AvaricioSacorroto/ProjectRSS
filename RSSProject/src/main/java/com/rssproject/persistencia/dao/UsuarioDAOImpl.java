package com.rssproject.persistencia.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rssproject.persistencia.model.UrlEntity;
import com.rssproject.persistencia.model.UsuarioEntity;

@Repository("usuarioDAO")
public class UsuarioDAOImpl implements UsuarioDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addUsuario(UsuarioEntity usuarioEntity) {
		final Session session = this.sessionFactory.getCurrentSession();
		session.persist(usuarioEntity);
	}

	@Override
	public void updateUsuario(UsuarioEntity usuarioEntity) {
		final Session session = this.sessionFactory.getCurrentSession();
		session.update(usuarioEntity);
	}

	@Override
	public List<UsuarioEntity> listUsuarios() {
		final Session session = this.sessionFactory.getCurrentSession();
		final List<UsuarioEntity> usuarioList = session.createCriteria(UsuarioEntity.class).list();
		return usuarioList;
	}

	@Override
	public UsuarioEntity getUsuarioById(long id) {
		final Session session = this.sessionFactory.getCurrentSession();
		final UsuarioEntity usuarioEntity = (UsuarioEntity) session.load(UsuarioEntity.class, id);
		return usuarioEntity;
	}

	@Override
	public UsuarioEntity getUsuarioByUsername(String name) {
		final Session session = this.sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(UsuarioEntity.class);
		criteria.add(Restrictions.eq("name", name));
		UsuarioEntity user = (UsuarioEntity) criteria.uniqueResult();
		System.out.println("UserEntity from criteria:" + user);
		return user;
	}

	@Override
	public void removeUsuario(long id) {
		final Session session = this.sessionFactory.getCurrentSession();
		final UsuarioEntity usuarioEntity = (UsuarioEntity) session.load(UsuarioEntity.class, id);
		if (null != usuarioEntity) {
			session.delete(usuarioEntity);
		}
	}

	@Override
	public void removeUrl(long id, String url) {
		final Session session = this.sessionFactory.getCurrentSession();
		final UsuarioEntity usuarioEntity = (UsuarioEntity) session.load(UsuarioEntity.class, id);
		final Criteria criteria = session.createCriteria(UrlEntity.class);
		criteria.add(Restrictions.eq("url", url));
		criteria.add(Restrictions.eq("usuarioId", usuarioEntity));
		final UrlEntity urlEntity = (UrlEntity) criteria.uniqueResult();
		usuarioEntity.getUrls().remove(urlEntity);
		session.delete(urlEntity);
	}

	@Override
	public void promoteUser(long id) {
		UsuarioEntity usuarioEntity = getUsuarioById(id);
		usuarioEntity.setRol("ROLE_ADMIN");
	}

}
