package br.org.serratec.apig4.dto;

import br.org.serratec.apig4.model.Comentario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ComentarioResponseDTO {

	@NotNull(message = "Insira o id do usuario do comentario")
	private Long id;

	@NotBlank(message = "Digite algo")
	private String conteudoCom;

	private String horaCriacao;

	private Long autorId;

	private Long postagemId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudoCom() {
		return conteudoCom;
	}

	public void setConteudoCom(String conteudoCom) {
		this.conteudoCom = conteudoCom;
	}

	public String getHoraCriacao() {
		return horaCriacao;
	}

	public void setHoraCriacao(String horaCriacao) {
		this.horaCriacao = horaCriacao;
	}

	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

	public Long getPostagemId() {
		return postagemId;
	}

	public void setPostagemId(Long postagemId) {
		this.postagemId = postagemId;
	}

	public ComentarioResponseDTO(Comentario comentario) {
		this.id = comentario.getId();
		this.conteudoCom = comentario.getConteudoCom();
		this.horaCriacao = (comentario.getHoraCriacao() != null) ? comentario.getHoraCriacao().toString() : null;
		this.postagemId = (comentario.getPostagem() != null) ? comentario.getPostagem().getId() : null;
		this.autorId = (comentario.getUsuario() != null) ? comentario.getPostagem().getId() : null;
	}

}
