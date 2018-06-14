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
import javax.validation.constraints.NotBlank;

@Entity
public class Tblogacesso implements Serializable {

	private static final long serialVersionUID = 276609447311419246L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "dt_created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCreated;
	private String login;
	@NotBlank
	private String ocorrencia;
	
	public Tblogacesso() {
	}

	public Tblogacesso(Long id, Date dtCreated, String login, @NotBlank String ocorrencia) {
		super();
		this.id = id;
		this.dtCreated = dtCreated;
		this.login = login;
		this.ocorrencia = ocorrencia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDtCreated() {
		return dtCreated;
	}

	public void setDtCreated(Date dtCreated) {
		this.dtCreated = dtCreated;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	@Override
	public String toString() {
		return "Tblogacesso [id=" + id + ", dtCreated=" + dtCreated + ", login=" + login + ", ocorrencia=" + ocorrencia
				+ "]";
	}


	

}
