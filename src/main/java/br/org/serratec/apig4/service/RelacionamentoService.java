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
			RelacionamentoDTO relacDTO = new RelacionamentoDTO(relacionamento);
			relacionamentoDTO.add(relacDTO);
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

	public RelacionamentoDTO inserir(RelacionamentoInserirDTO relacionamentoInserirDTO) {
		// Armazena o iD
		Optional<Usuario> seguidorOpt = usuarioRepository.findById(relacionamentoInserirDTO.getSeguidorId());
		Optional<Usuario> seguidoOpt = usuarioRepository.findById(relacionamentoInserirDTO.getSeguidoId());

		// Cria uma instância de Relacionamento
		Relacionamento relacionamento = new Relacionamento();

		// Chama essa instância e insere os dados armazenados em seguidorOpt e
		// seguidoOpt
		relacionamento.getId().setUsuarioSeguidor(seguidorOpt.get());
		relacionamento.getId().setUsuarioSeguido(seguidoOpt.get());
		relacionamento.setDataInicio(LocalDate.now());

		relacionamento = relacionamentoRepository.save(relacionamento);

		RelacionamentoDTO relacionamentoDTO = new RelacionamentoDTO(relacionamento);
		return relacionamentoDTO;
	}
}
