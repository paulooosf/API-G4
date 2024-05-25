package br.org.serratec.apig4.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.org.serratec.apig4.exception.NotFoundException;
import br.org.serratec.apig4.model.Usuario;
import br.org.serratec.apig4.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Page<Usuario> listar(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}
	
	public Usuario buscar(Long id) throws NotFoundException {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isEmpty()) {
			throw new NotFoundException();
		}
		return usuarioOpt.get();
	}
	
	public Usuario cadastrar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario editar(Long id, Usuario usuario) throws NotFoundException {
		if (!usuarioRepository.existsById(id)) {
			throw new NotFoundException();
		}
		usuario.setId(id);
		usuario = usuarioRepository.save(usuario);
		return usuario;
	}
	
	public void deletar(Long id) throws NotFoundException {
		if (!usuarioRepository.existsById(id)) {
			throw new NotFoundException();
		}
		usuarioRepository.deleteById(id);
	}
}
