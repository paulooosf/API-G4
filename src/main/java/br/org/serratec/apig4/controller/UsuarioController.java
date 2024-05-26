package br.org.serratec.apig4.controller;

import java.net.URI;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.apig4.dto.UsuarioDTO;
import br.org.serratec.apig4.model.Usuario;
import br.org.serratec.apig4.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/lista")
	public ResponseEntity<Page<UsuarioDTO>> listar(@PageableDefault(sort="nome", direction=Sort.Direction.ASC,
            page=0, size=10) Pageable pegeable) {
		return ResponseEntity.ok(usuarioService.listar(pegeable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(usuarioService.buscar(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UsuarioDTO> cadastrar(@Valid @RequestBody Usuario usuario) {
		UsuarioDTO usuarioDTO = usuarioService.cadastrar(usuario);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(usuario.getId())
				.toUri();
		return ResponseEntity.created(uri).body(usuarioDTO);
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<UsuarioDTO> editar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		return ResponseEntity.ok(usuarioService.editar(id, usuario));
	}
	
	@PutMapping("/trocarsenha/{id}")
	public ResponseEntity<UsuarioDTO> trocarSenha(@PathVariable Long id, @Valid @RequestBody String senha) {
		return ResponseEntity.ok(usuarioService.trocarSenha(id, senha));
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		usuarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
