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
import br.org.serratec.apig4.model.Postagem;
import br.org.serratec.apig4.repository.ComentarioRepository;
import br.org.serratec.apig4.repository.PostagemRepository;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private PostagemRepository postagemRepository;

	public Page<Comentario> listar(Pageable pageable, Long postagemId) {
		
		return comentarioRepository.findByPostagemId(pageable ,postagemId);
	}

	public Comentario buscarPorId(Long id, Long postagemId) throws NotFoundException {
		Optional<Comentario> comentarioOpt = comentarioRepository.findByIdAndPostagemId(id, postagemId);
		if (comentarioOpt.isEmpty()) {
			throw new NotFoundException();
		}
		if (!postagemRepository.existsById(postagemId)) {
			throw new NotFoundException();
		}

		return comentarioOpt.get();
	}

	public Comentario inserir(ComentarioDTO comentarioDTO, Long postagemId) {

		// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		Comentario comentario = new Comentario();
		Optional<Postagem> postagemOpt = postagemRepository.findById(postagemId);

		if (!postagemOpt.isPresent()) {
			throw new NotFoundException();
		}

		comentario.setConteudoCom(comentarioDTO.getConteudoCom());
		comentario.setHoraCriacao(LocalDateTime.now());
		comentario.setPostagem(postagemOpt.get());

		return comentarioRepository.save(comentario);
	}

	public Comentario editar(Long id, ComentarioDTO comentarioDTO, Long postagemId) {
		Optional<Comentario> comentarioOpt = comentarioRepository.findByIdAndPostagemId(id, postagemId);

		if (!comentarioOpt.isPresent()) {
			throw new NotFoundException();
		}

		Comentario comentario = new Comentario();
		comentario.setConteudoCom(comentarioDTO.getConteudoCom());
		
		return comentarioRepository.save(comentario);
	}

	public void deletar(Long id, Long postagemId) throws NotFoundException {
		Optional<Comentario> comentarioOpt = comentarioRepository.findByIdAndPostagemId(id, postagemId);

		if (!comentarioOpt.isPresent()) {
			throw new NotFoundException();
		}

		comentarioRepository.deleteById(id);
	}

}
