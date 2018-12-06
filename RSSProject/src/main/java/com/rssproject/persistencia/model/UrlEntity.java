package com.rssproject.persistencia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Url")
public class UrlEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	private String url;

	@ManyToOne
	@JoinColumn(name = "usuarioId", referencedColumnName = "id", insertable = false, updatable = false)
	private UsuarioEntity usuarioId;

	public UrlEntity() {

	}

	public UrlEntity(String url) {
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UsuarioEntity getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(UsuarioEntity usuarioId) {
		this.usuarioId = usuarioId;
	}


}
