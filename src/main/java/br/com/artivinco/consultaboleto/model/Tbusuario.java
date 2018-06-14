package br.com.artivinco.consultaboleto.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tbusuario implements Serializable {

	private static final long serialVersionUID = 276609447311419246L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String login;
	@JsonIgnore
	private String senha;
	@Column(name = "dt_created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCreated;
	@Column(name = "dt_finish")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtFinish;
	private int conterro;
	private String nome;
	@Email
	private String email;
	
	public Tbusuario() {
	}

	public Tbusuario(Long id, String login, String senha, Date dtCreated, Date dtFinish, int conterro, String nome,
			@Email String email) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.dtCreated = dtCreated;
		this.dtFinish = dtFinish;
		this.conterro = conterro;
		this.nome = nome;
		this.email = email;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDtCreated() {
		return dtCreated;
	}

	public void setDtCreated(Date dtCreated) {
		this.dtCreated = dtCreated;
	}

	public Date getDtFinish() {
		return dtFinish;
	}

	public void setDtFinish(Date dtFinish) {
		this.dtFinish = dtFinish;
	}

	public int getConterro() {
		return conterro;
	}

	public void setConterro(int conterro) {
		this.conterro = conterro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Tbusuario [id=" + id + ", login=" + login + ", senha=" + senha + ", dtCreated=" + dtCreated
				+ ", dtFinish=" + dtFinish + ", conterro=" + conterro + ", nome=" + nome + "]";
	}



}
