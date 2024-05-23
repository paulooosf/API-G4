package br.org.serratec.apig4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.apig4.model.Relacionamento;
import br.org.serratec.apig4.service.RelacionamentoService;

@RestController
@RequestMapping("/relacionamentos")
public class RelacionamentoController {

	@Autowired
	private RelacionamentoService relacionamentoService;

	@GetMapping
	public List<Relacionamento> findAll() {
		return relacionamentoService.findAll();
	}

}
