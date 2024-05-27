package br.org.serratec.apig4.dto;

import jakarta.validation.constraints.NotBlank;

public class ComentarioDTO {

	@NotBlank(message = "Digite algo")
	private String conteudoCom;

	public String getConteudoCom() {
		return conteudoCom;
	}

	public void setConteudoCom(String conteudoCom) {
		this.conteudoCom = conteudoCom;
	}

}
