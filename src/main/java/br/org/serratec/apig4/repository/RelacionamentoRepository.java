package br.org.serratec.apig4.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.serratec.apig4.model.Relacionamento;
import br.org.serratec.apig4.model.RelacionamentoPK;

@Repository
public interface RelacionamentoRepository extends JpaRepository<Relacionamento, RelacionamentoPK> {

	@Query(value = "Select * from relacionamento r where r.id_usuario_seguido = :id",
			nativeQuery = true)
	List<Relacionamento> buscarSeguidoresByUsuarioId(@Param(value = "id") Long id);
	
    Optional<Relacionamento> findByIdUsuarioSeguidorIdAndIdUsuarioSeguidoId(Long idUsuarioSeguidor, Long idUsuarioSeguido);
	
    void deleteByIdUsuarioSeguidorIdAndIdUsuarioSeguidoId(Long idUsuarioSeguidor, Long idUsuarioSeguido);
}