package com.start.mario.seguridad;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.start.mario.usuario.Usuario;
import com.start.mario.usuario.UsuarioDAO;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioDao.findById(username);
		if(usuario.isPresent()) {
			return (UserDetails) usuario.get();
		}
		throw new UsernameNotFoundException(username);
	}

}
