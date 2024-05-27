package br.org.serratec.apig4.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.apig4.dto.RelacionamentoDTO;
import br.org.serratec.apig4.dto.RelacionamentoInserirDTO;
import br.org.serratec.apig4.exception.NotFoundException;
import br.org.serratec.apig4.model.Relacionamento;
import br.org.serratec.apig4.model.Usuario;
import br.org.serratec.apig4.repository.RelacionamentoRepository;
import br.org.serratec.apig4.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class RelacionamentoService {

	@Autowired
	private RelacionamentoRepository relacionamentoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<RelacionamentoDTO> findAll() {
		List<Relacionamento> relacionamentos = relacionamentoRepository.findAll();

		List<RelacionamentoDTO> relacionamentoDTO = new ArrayList<>();

		for (Relacionamento relacionamento : relacionamentos) {
			RelacionamentoDTO relacaoDTO = new RelacionamentoDTO(relacionamento);
			relacionamentoDTO.add(relacaoDTO);
		}
		return relacionamentoDTO;
	}

	public List<Relacionamento> buscarSeguidoresByUsuarioId(Long id) {
		List<Relacionamento> relacionamentos = relacionamentoRepository.buscarSeguidoresByUsuarioId(id);
		if (relacionamentos.isEmpty()) {
			throw new NotFoundException();
		}

		return relacionamentos;
	}

	@Transactional
	public RelacionamentoDTO inserir(RelacionamentoInserirDTO relacionamentoInserirDTO) {
		Optional<Usuario> seguidorOpt = usuarioRepository.findById(relacionamentoInserirDTO.getSeguidorId());
		Optional<Usuario> seguidoOpt = usuarioRepository.findById(relacionamentoInserirDTO.getSeguidoId());

		Relacionamento relacionamento = new Relacionamento();

		relacionamento.getId().setUsuarioSeguidor(seguidorOpt.get());
		relacionamento.getId().setUsuarioSeguido(seguidoOpt.get());
		relacionamento.setDataInicio(LocalDate.now());

		relacionamento = relacionamentoRepository.save(relacionamento);

		RelacionamentoDTO relacionamentoDTO = new RelacionamentoDTO(relacionamento);
		return relacionamentoDTO;
	}

	@Transactional
	public void deletar(Long idUsuarioSeguidor, Long idUsuarioSeguido) {
		Optional<Relacionamento> relacionamentoOpt = relacionamentoRepository
				.findByIdUsuarioSeguidorIdAndIdUsuarioSeguidoId(idUsuarioSeguidor, idUsuarioSeguido);
		if (relacionamentoOpt.isEmpty()) {
			throw new NotFoundException();
		}
		relacionamentoRepository.deleteByIdUsuarioSeguidorIdAndIdUsuarioSeguidoId(idUsuarioSeguidor, idUsuarioSeguido);
	}
}
