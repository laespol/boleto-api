package br.com.artivinco.consultaboleto.resource;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.artivinco.consultaboleto.model.Tbusuario;
import br.com.artivinco.consultaboleto.repository.TbusuarioRepository;
import br.com.artivinco.consultaboleto.util.AtualizaLog;
import br.com.artivinco.consultaboleto.util.Utilitario;

@RestController
@CrossOrigin(origins = { "http://18.231.91.243", "http://localhost:4200" }, maxAge = 4800, allowCredentials = "false")
@RequestMapping("/admin")
public class TbusuarioResource {
	
	@Autowired
	private TbusuarioRepository tbusuarioRepository;
	
	private Utilitario util = new Utilitario();
	
//	private AtualizaLog atualizalog = new AtualizaLog();
	
	@GetMapping("tbusuarios/{login}")
	public ResponseEntity<?> login(@PathVariable String login) {
		Tbusuario tbusuario = new Tbusuario();
		tbusuario = tbusuarioRepository.findBylogin(login);

		if (tbusuario == null) {
//			atualizalog.GeraLog(login, "Falha de Segurança não encontrado");
			return new ResponseEntity<>("Id inexistente " + login, HttpStatus.NOT_FOUND);
		}
		
//		atualizalog.GeraLog(login, "Falha de Segurança não encontrado");
		return new ResponseEntity<>(tbusuario, HttpStatus.OK);
	}
	
	@PostMapping("tbusuarios")
	public ResponseEntity<?> salvar(@RequestBody @Valid Tbusuario tbusuario) {
		tbusuario.setSenha(util.TransformaSenha(tbusuario.getSenha()));
		tbusuario.setDtCreated(new Date());
//		atualizalog.GeraLog(tbusuario.getLogin(), "Falha de Segurança não encontrado");
		return new ResponseEntity<>( tbusuarioRepository.save(tbusuario), HttpStatus.OK);
	}

}
