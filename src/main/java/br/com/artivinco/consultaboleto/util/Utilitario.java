package br.com.artivinco.consultaboleto.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.artivinco.consultaboleto.model.Tblogacesso;
import br.com.artivinco.consultaboleto.repository.TblogacessoRepository;

public class Utilitario {

	public String TransformaSenha(String senha) {

		String s = senha;
		BCryptPasswordEncoder passordEncoder = new BCryptPasswordEncoder();
		senha= passordEncoder.encode(s);
		return senha;
	}
	
	@Autowired
	private TblogacessoRepository tblogacessoRepository;
	
	public void GeraLog (String login, String mensagem) {	
    	Tblogacesso tblogacesso = new Tblogacesso();
    	tblogacesso.setLogin(login);
    	tblogacesso.setDtCreated(new Date());
    	tblogacesso.setOcorrencia(mensagem);    	
    	tblogacessoRepository.save(tblogacesso);
		return ;	
	}
}
