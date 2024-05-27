package br.org.serratec.apig4.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.org.serratec.apig4.dto.ComentarioDTO;
import br.org.serratec.apig4.dto.ComentarioInserirDTO;
import br.org.serratec.apig4.exception.NotFoundException;
import br.org.serratec.apig4.model.Comentario;
import br.org.serratec.apig4.model.Postagem;
import br.org.serratec.apig4.model.Usuario;
import br.org.serratec.apig4.repository.ComentarioRepository;
import br.org.serratec.apig4.repository.PostagemRepository;
import br.org.serratec.apig4.repository.UsuarioRepository;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;;

	public Page<Comentario> listar(Pageable pageable, Long postagemId) {

		return comentarioRepository.findAllByPostagemId(pageable, postagemId);
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

	public Comentario inserir(ComentarioInserirDTO comentarioInserirDTO, Long postagemId) {

		Long autorId = comentarioInserirDTO.getAutorId();
		Optional<Postagem> postagemOpt = postagemRepository.findById(postagemId);
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(autorId);

		Comentario comentario = new Comentario();

		if (!postagemOpt.isPresent()) {
			throw new NotFoundException();
		}
		if (!usuarioOpt.isPresent()) {
			throw new NotFoundException();
		}

		comentario.setConteudoCom(comentarioInserirDTO.getConteudoCom());
		comentario.setHoraCriacao(LocalDateTime.now());
		comentario.setPostagem(postagemOpt.get());
		comentario.setUsuario(usuarioOpt.get());

		return comentarioRepository.save(comentario);
	}

	public Comentario editar(Long id, ComentarioDTO comentarioDTO, Long postagemId) {

		Optional<Comentario> comentarioOpt = comentarioRepository.findByIdAndPostagemId(id, postagemId);

		if (!comentarioOpt.isPresent()) {
			throw new NotFoundException();
		}

		Comentario comentario = comentarioOpt.get();
		comentario.setHoraCriacao(LocalDateTime.now());
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
