package br.com.artivinco.consultaboleto.json;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginImport {

	@NotNull
	private String usuario;
	@NotNull
	private String senhaUsuario;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	@Override
	public String toString() {
		return "LoginImport [usuario=" + usuario + ", senhaUsuario=" + senhaUsuario + "]";
	}

}
