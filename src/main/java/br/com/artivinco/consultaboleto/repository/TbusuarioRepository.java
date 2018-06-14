package br.com.artivinco.consultaboleto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.artivinco.consultaboleto.model.Tbusuario;



public interface TbusuarioRepository extends JpaRepository<Tbusuario, Long> {
    
    Tbusuario findBylogin(String login);
	
}
