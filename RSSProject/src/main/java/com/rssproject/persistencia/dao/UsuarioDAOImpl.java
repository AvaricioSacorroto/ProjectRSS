package com.rssproject.persistencia.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rssproject.persistencia.model.UsuarioEntity;


@Repository("usuarioDAO")
public class UsuarioDAOImpl implements UsuarioDAO{
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
	public UsuarioEntity getUsuarioByUsername(String username) {
		final Session session = this.sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(UsuarioEntity.class);
		criteria.add(Restrictions.eq("name", username));
		return (UsuarioEntity) criteria.uniqueResult();
	}
	
	@Override
	public void removeUsuario(long id) {
		final Session session = this.sessionFactory.getCurrentSession();
		final UsuarioEntity usuarioEntity = (UsuarioEntity) session.load(UsuarioEntity.class, id);
		if (null != usuarioEntity) {
			session.delete(usuarioEntity);
		}
	}
	

}
