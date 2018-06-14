package br.com.artivinco.consultaboleto.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginReturn {
	
	private String token;
	private String login;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Override
	public String toString() {
		return "LoginReturn [token=" + token + ", login=" + login + "]";
	}
	

}
