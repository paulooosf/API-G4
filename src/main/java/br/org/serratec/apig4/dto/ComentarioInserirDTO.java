package br.org.serratec.apig4.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ComentarioInserirDTO {

	@NotBlank(message = "Digite algo")
	private String conteudoCom;

	@NotNull(message = "Insira o id do usuario do comentario")
	private Long autorId;

	public String getConteudoCom() {
		return conteudoCom;
	}
	
	public void setConteudoCom(String conteudoCom) {
		this.conteudoCom = conteudoCom;
	}

	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

	public ComentarioInserirDTO(String conteudoCom, Long autorId) {
		super();
		this.conteudoCom = conteudoCom;
		this.autorId = autorId;
	}

}
