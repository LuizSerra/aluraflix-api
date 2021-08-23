package br.com.lserra.aluraflixapi.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.lserra.aluraflixapi.model.Usuario;
import br.com.lserra.aluraflixapi.repository.UsuarioRepository;


@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		if (usuario.isPresent())
			return usuario.get();
		else
			throw new UsernameNotFoundException("Dados inválidos!");
	}

}
