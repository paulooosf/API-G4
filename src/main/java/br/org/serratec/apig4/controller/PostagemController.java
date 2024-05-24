package br.org.serratec.apig4.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.apig4.model.Postagem;
import br.org.serratec.apig4.service.PostagemService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagem")
public class PostagemController {

	@Autowired
	PostagemService postagemService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Postagem>> listar() {
		return ResponseEntity.ok(postagemService.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(postagemService.buscar(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Postagem> postar(@Valid @RequestBody Postagem postagem) {
		postagemService.postar(postagem);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(postagem.getId())
				.toUri();
		return ResponseEntity.created(uri).body(postagem);
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<Postagem> editar(@PathVariable Long id, @RequestBody Postagem postagem) {
		return ResponseEntity.ok(postagemService.editar(id, postagem));
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		postagemService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
