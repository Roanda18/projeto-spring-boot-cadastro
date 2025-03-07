package br.com.matheus.cadastro.services;

import br.com.matheus.cadastro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UsuarioServices implements UserDetailsService {

    private Logger logger = Logger.getLogger(UsuarioServices.class.getName());

    @Autowired
    UsuarioRepository repository;

    public UsuarioServices(UsuarioRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException{
        logger.info("Buscando pesssoa por nome: " + nomeUsuario);
        var usuario = repository.findByUserName(nomeUsuario);
        if (usuario != null){
            return usuario;
        }else{
            throw new UsernameNotFoundException("Usuario: " + nomeUsuario + " não foi encotrado");
        }
    }
}
