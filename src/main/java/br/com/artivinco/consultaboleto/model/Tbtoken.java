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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tbtoken implements Serializable {

	private static final long serialVersionUID = 276609447311419246L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String login;
	@JsonIgnore
	@Column(unique = true)
	private String token;
	@Column(name = "dt_created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCreated;
	@Column(name = "dt_finish")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtFinish;
	private String status ;

	public Tbtoken() {
	}

	public Tbtoken(Long id, String login, String token, Date dtCreated, Date dtFinish, String status) {
		super();
		this.id = id;
		this.login = login;
		this.token = token;
		this.dtCreated = dtCreated;
		this.dtFinish = dtFinish;
		this.status = status;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Tbtoken [id=" + id + ", login=" + login + ", token=" + token + ", dtCreated=" + dtCreated
				+ ", dtFinish=" + dtFinish + ", status=" + status + "]";
	}




}
