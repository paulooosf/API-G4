package br.org.serratec.apig4.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.apig4.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	Page<Comentario> findByPostagemId(Pageable pageable, Long postagemId);
	
	Optional<Comentario> findByIdAndPostagemId(Long id, Long postagemId);

}
