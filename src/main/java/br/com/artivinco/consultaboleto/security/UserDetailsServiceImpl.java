package br.com.artivinco.consultaboleto.security;

import static java.util.Collections.emptyList;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.artivinco.consultaboleto.model.Tblogacesso;
import br.com.artivinco.consultaboleto.model.Tbusuario;
import br.com.artivinco.consultaboleto.repository.TblogacessoRepository;
import br.com.artivinco.consultaboleto.repository.TbusuarioRepository;
import br.com.artivinco.consultaboleto.util.AtualizaLog;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
//    private ApplicationUserRepository applicationUserRepository;
    private TbusuarioRepository tbusuarioRepository;
	
/*	@Autowired
	private AtualizaLog  atualizalog;*/
    
    public UserDetailsServiceImpl(TbusuarioRepository tbusuarioRepository) {
        this.tbusuarioRepository = tbusuarioRepository;
    }
    
/*    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }*/
    
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Tbusuario tbusuario = tbusuarioRepository.findBylogin(login);
        if (tbusuario == null) {
//        	GeraLog(login, "Usuario não encontrado");     	
            throw new UsernameNotFoundException(login);
        }
        UserDetails usuario = null;
        
        usuario = new User(tbusuario.getLogin(), tbusuario.getSenha(), emptyList());
        
        return usuario;
    }
   
}