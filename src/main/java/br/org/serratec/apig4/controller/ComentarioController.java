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
import br.org.serratec.apig4.dto.ComentarioInserirDTO;
import br.org.serratec.apig4.dto.ComentarioResponseDTO;
import br.org.serratec.apig4.exception.NotFoundException;
import br.org.serratec.apig4.model.Comentario;
import br.org.serratec.apig4.service.ComentarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagem/{postagemId}/comentarios")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;

	@GetMapping
	public ResponseEntity<Page<ComentarioResponseDTO>> listar(
			@PageableDefault(sort = "horaCriacao", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable,
			@PathVariable Long postagemId) {
		
		Page<Comentario> comentarioPage = comentarioService.listar(pageable, postagemId); 
		Page<ComentarioResponseDTO> comentariosDTOPage = comentarioPage.map(ComentarioResponseDTO::new);
		
		return ResponseEntity.ok().body(comentariosDTOPage);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ComentarioResponseDTO> buscarPorId(@PathVariable Long id, @PathVariable Long postagemId) {

		 try {
			 ComentarioResponseDTO comentarioResponseDTO = new ComentarioResponseDTO(comentarioService.buscarPorId(id, postagemId)); 
			 return ResponseEntity.ok().body(comentarioResponseDTO);
		       
		    } catch (NotFoundException e) {
		        return ResponseEntity.notFound().build();
		    }
		
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ComentarioResponseDTO> inserir(@Valid @RequestBody ComentarioInserirDTO comentarioInserirDTO,
			@PathVariable Long postagemId) {

		Comentario comentario = comentarioService.inserir(comentarioInserirDTO, postagemId);
		ComentarioResponseDTO responseDTO = new ComentarioResponseDTO(comentario);

		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ComentarioResponseDTO> editar(@PathVariable Long id, @RequestBody ComentarioDTO comentarioDTO,
			@PathVariable Long postagemId) {
		try {
		comentarioService.editar(id, comentarioDTO, postagemId);
		
		Comentario comentario = comentarioService.editar(id, comentarioDTO, postagemId);
		ComentarioResponseDTO responseDTO = new ComentarioResponseDTO(comentario);

		return ResponseEntity.ok().body(responseDTO);
		
		} catch (NotFoundException e) {
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id, @PathVariable Long postagemId) {
		try {
			comentarioService.deletar(id, postagemId);
	        return ResponseEntity.noContent().build();
	    } catch (NotFoundException e) {
	        return ResponseEntity.notFound().build();
	    }
	}
}
