package br.org.serratec.apig4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.apig4.dto.ComentarioDTO;
import br.org.serratec.apig4.model.Comentario;
import br.org.serratec.apig4.service.ComentarioService;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;

	@GetMapping("/lista")
	public ResponseEntity<Page<Comentario>> listar(
			@PageableDefault(sort = "horaCriacao", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pegeable) {
		return ResponseEntity.ok(comentarioService.listar(pegeable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Comentario> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(comentarioService.buscarPorId(id));
	}
	//("/postagem/{id}")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Comentario> inserir(@RequestBody ComentarioDTO comentarioDTO 
			//@PathVariable Long id
			) {
		comentarioService.inserir(comentarioDTO);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Comentario> editar(@PathVariable Long id, @RequestBody ComentarioDTO comentarioDTO) {
		return ResponseEntity.ok(comentarioService.editar(id, comentarioDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		comentarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
