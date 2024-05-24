package br.org.serratec.apig4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.apig4.model.Relacionamento;
import br.org.serratec.apig4.repository.RelacionamentoRepository;

@Service
public class RelacionamentoService {

	@Autowired
	private RelacionamentoRepository relacionamentoRepository;

	public List<Relacionamento> findAll() {
		return relacionamentoRepository.findAll();
	}

}
