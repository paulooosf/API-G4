package br.org.serratec.apig4.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.org.serratec.apig4.exception.NotFoundException;
import br.org.serratec.apig4.model.Postagem;
import br.org.serratec.apig4.repository.PostagemRepository;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;

	public Page<Postagem> listar(Pageable pegeable) {
		return postagemRepository.findAll(pegeable);
	}

	public Postagem buscar(Long id) throws NotFoundException {
		Optional<Postagem> postagemOpt = postagemRepository.findById(id);
		if (postagemOpt.isEmpty()) {
			throw new NotFoundException();
		}
		return postagemOpt.get();
	}

	public Postagem postar(Postagem postagem) {
		return postagemRepository.save(postagem);
	}

	public Postagem editar(Long id, Postagem postagem) throws NotFoundException {
		if (!postagemRepository.existsById(id)) {
			throw new NotFoundException();
		}
		postagem.setId(id);
		postagem = postagemRepository.save(postagem);
		return postagem;
	}

	public void deletar(Long id) throws NotFoundException {
		if (!postagemRepository.existsById(id)) {
			throw new NotFoundException();
		}
		postagemRepository.deleteById(id);
	}
}
