package br.org.serratec.apig4.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.org.serratec.apig4.dto.UsuarioDTO;
import br.org.serratec.apig4.exception.NotFoundException;
import br.org.serratec.apig4.model.Usuario;
import br.org.serratec.apig4.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Page<UsuarioDTO> listar(Pageable pageable) {
		Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
		Page<UsuarioDTO> usuariosDTO = usuarios.map(UsuarioDTO::new);
		return usuariosDTO;
	}

	public UsuarioDTO buscar(Long id) throws NotFoundException {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isEmpty()) {
			throw new NotFoundException();
		}
		return new UsuarioDTO(usuarioOpt.get());
	}

	public UsuarioDTO cadastrar(Usuario usuario) {
		usuarioRepository.save(usuario);
		return new UsuarioDTO(usuario);
	}

	public UsuarioDTO editar(Long id, Usuario usuario) throws NotFoundException {
		if (!usuarioRepository.existsById(id)) {
			throw new NotFoundException();
		}
		usuario.setId(id);
		return new UsuarioDTO(usuarioRepository.save(usuario));
	}
	
	public UsuarioDTO trocarSenha(Long id, String senha) throws NotFoundException {
		if (!usuarioRepository.existsById(id)) {
			throw new NotFoundException();
		}
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		Usuario usuario = usuarioOpt.get();
		usuario.setSenha(senha);
		usuarioRepository.save(usuario);
		
		return new UsuarioDTO(usuario);
	}

	public void deletar(Long id) throws NotFoundException {
		if (!usuarioRepository.existsById(id)) {
			throw new NotFoundException();
		}
		usuarioRepository.deleteById(id);
	}
}
