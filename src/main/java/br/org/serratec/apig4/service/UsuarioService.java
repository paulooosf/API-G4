package br.org.serratec.apig4.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<UsuarioDTO> listar(Pageable pageable) {
		List<Usuario> usuarios = usuarioRepository.findAll();

		List<UsuarioDTO> usuarioDTO = new ArrayList<>();

		for (Usuario usuario : usuarios) {
			UsuarioDTO usuDTO = new UsuarioDTO(usuario);
			usuarioDTO.add(usuDTO);
		}

		return usuarioDTO;
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
