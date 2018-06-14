package br.com.artivinco.consultaboleto.resource;

import static br.com.artivinco.consultaboleto.security.SecurityConstants.EXPIRATION_TIME;
import static br.com.artivinco.consultaboleto.security.SecurityConstants.SECRET;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.artivinco.consultaboleto.model.Tbtoken;
import br.com.artivinco.consultaboleto.model.Tbusuario;
import br.com.artivinco.consultaboleto.repository.TbtokenRepository;
import br.com.artivinco.consultaboleto.repository.TbusuarioRepository;
import br.com.artivinco.consultaboleto.util.AtualizaLog;
import br.com.artivinco.consultaboleto.util.Utilitario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = { "http://18.231.91.243", "http://localhost:4200" }, maxAge = 4800, allowCredentials = "false")
@RequestMapping("/users")
public class TbusuarioTrocaSenhaResource {

	@Autowired
	private TbtokenRepository tbtokenepository;
	
//	private AtualizaLog  atualizalog = new AtualizaLog();
	
/*	@Autowired
	private AtualizaLog  atualizalog;*/
	
	@Autowired
	private TbusuarioRepository tbusuarioRepository;
	
	private Utilitario util = new Utilitario();
	
	@PostMapping("esqueceuSenha/{login}")
	public ResponseEntity<?> esqueceu(@PathVariable String login) {
		Tbusuario tbusuario= new Tbusuario();
		tbusuario = tbusuarioRepository.findBylogin(login);
		if (tbusuario == null) {
//			 atualizalog.GeraLog(login, "Falha de Segurança não encontrado"); 
			return new ResponseEntity<>("Id inexistente " + login, HttpStatus.NOT_FOUND);
		}
		
		Calendar calendar = Calendar.getInstance();
//		atualizalog.GeraLog(login, "Esqueceu Senha");
		String token = Jwts.builder().setSubject(login)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		Tbtoken tbtoken = new Tbtoken();
		tbtoken.setLogin(login);
		tbtoken.setToken(util.TransformaSenha(token));
		tbtoken.setDtCreated(new Date());
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, 10);
		tbtoken.setDtFinish(calendar.getTime());
		tbtoken.setStatus("");
		tbtokenepository.save(tbtoken);
    	System.out.println("envia token cadastro nova senha ="+ tbusuario.getEmail() +"- " + token);
		return new ResponseEntity<>("senha enviada", HttpStatus.OK);
	}

	@PostMapping("trocasenha/{token}")
	public ResponseEntity<?> nova(@PathVariable String token) {
		Tbtoken tbtoken = new Tbtoken();
		Date data = new Date();
		tbtoken = tbtokenepository.findBytoken(token,data);
		if (tbtoken == null) {
//			atualizalog.GeraLog("Nulo", "Falha de Segurança não encontrado de token");
			return new ResponseEntity<>("Id inexistente " + token, HttpStatus.NOT_FOUND);
		}
//		atualizalog.GeraLog(tbtoken.getLogin(), "Falha de Segurança não encontrado de token");
    	tbtoken.setStatus("A");
    	tbtokenepository.save(tbtoken);

    	System.out.println("envia token cadastro nova senha =" + token);
		return new ResponseEntity<>("senha trocada", HttpStatus.OK);
	}
	
}
