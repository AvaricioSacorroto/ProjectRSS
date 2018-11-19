package com.rssproject.persistencia.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario")
public class UsuarioEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String name;

	private String password;

	private String rol;
	@OneToMany(cascade = CascadeType.ALL)
	private List<UrlEntity> urls=new ArrayList<UrlEntity>();

	public UsuarioEntity() {

	}

	public UsuarioEntity(Long id, String name, String password, String role) {

		this.id = id;
		this.name = name;
		this.password = password;
		this.rol = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String role) {
		this.rol = role;
	}

	public List<UrlEntity> getUrls() {
		return urls;
	}

	public void setUrls(List<UrlEntity> urls) {
		this.urls = urls;
	}

}
