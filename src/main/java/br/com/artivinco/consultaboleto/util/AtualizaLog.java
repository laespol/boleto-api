package br.com.artivinco.consultaboleto.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.artivinco.consultaboleto.model.Tblogacesso;
import br.com.artivinco.consultaboleto.repository.TblogacessoRepository;

public class AtualizaLog {

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
