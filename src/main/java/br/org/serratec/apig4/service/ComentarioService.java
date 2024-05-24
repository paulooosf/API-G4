package br.org.serratec.apig4.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.org.serratec.apig4.dto.ComentarioDTO;
import br.org.serratec.apig4.exception.NotFoundException;
import br.org.serratec.apig4.model.Comentario;
import br.org.serratec.apig4.repository.ComentarioRepository;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository comentarioRepository;

	public Page<Comentario> listar(Pageable pegeable) {
		return comentarioRepository.findAll(pegeable);
	}

	public Comentario buscarPorId(Long id) throws NotFoundException {
		Optional<Comentario> comentarioOpt = comentarioRepository.findById(id);
		if (comentarioOpt.isEmpty()) {
			throw new NotFoundException();
		}
		return comentarioOpt.get();
	}

	public Comentario inserir(ComentarioDTO comentarioDTO) {
		
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		Comentario comentario = new Comentario();
		comentario.setConteudoCom(comentarioDTO.getConteudoCom());
		comentario.setHoraCriacao(LocalDateTime.now());
		//comentario.getPostagem().setId(comentario.getPostagem().getId());

		return comentarioRepository.save(comentario);
	}

	public Comentario editar(Long id, ComentarioDTO comentarioDTO) {
		if (!comentarioRepository.existsById(id)) {
			throw new NotFoundException();
		}

		Comentario comentario = new Comentario();
		comentario.setId(id);
		comentario.setConteudoCom(comentarioDTO.getConteudoCom());
		return comentarioRepository.save(comentario);
	}

	public void deletar(Long id) throws NotFoundException {
		if (!comentarioRepository.existsById(id)) {
			throw new NotFoundException();
		}
		comentarioRepository.deleteById(id);
	}

}
