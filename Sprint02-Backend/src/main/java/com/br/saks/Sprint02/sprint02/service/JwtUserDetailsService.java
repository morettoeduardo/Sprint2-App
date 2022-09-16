package com.br.saks.Sprint02.sprint02.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.br.saks.Sprint02.sprint02.model.Usuario;
import com.br.saks.Sprint02.sprint02.repository.UsuarioRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuarioResponse = usuarioRepository.findByEmail(email);
                Usuario usuario = usuarioResponse.get();
		
		if (usuario.getEmail().equals(email)) {
			return new User(email, usuario.getSenha(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("usuário não encontrado - email: " + email);
		}
	}
}