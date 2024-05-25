package br.org.serratec.apig4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.apig4.dto.RelacionamentoDTO;
import br.org.serratec.apig4.dto.RelacionamentoInserirDTO;
import br.org.serratec.apig4.model.Relacionamento;
import br.org.serratec.apig4.service.RelacionamentoService;

@RestController
@RequestMapping("/relacionamentos")
public class RelacionamentoController {

	@Autowired
	private RelacionamentoService relacionamentoService;

	@GetMapping
	public ResponseEntity<List<RelacionamentoDTO>> findAll() {
		return ResponseEntity.ok(relacionamentoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Relacionamento>> buscarSeguidoresByUsuarioId(@PathVariable Long id){
		return ResponseEntity.ok(relacionamentoService.buscarSeguidoresByUsuarioId(id));
	}
	
	@PostMapping
	public ResponseEntity<RelacionamentoDTO> inserir(@RequestBody RelacionamentoInserirDTO relacionamentoInserirDTO){
		RelacionamentoDTO relacionamentoDTO = relacionamentoService.inserir(relacionamentoInserirDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(relacionamentoDTO);
	}
	
	@DeleteMapping("/{idUsuarioSeguidor}/{idUsuarioSeguido}")
	public ResponseEntity<Void> deletar(@PathVariable Long idUsuarioSeguidor, @PathVariable Long idUsuarioSeguido){
		relacionamentoService.deletar(idUsuarioSeguidor, idUsuarioSeguido);
		return ResponseEntity.noContent().build();
	}
}
