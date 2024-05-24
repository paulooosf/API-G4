package br.org.serratec.apig4.dto;

import java.time.LocalDate;

import br.org.serratec.apig4.model.Relacionamento;

public class RelacionamentoDTO {

	private Long seguidorId;

	private String nomeSeguidor;

	private Long seguidoId;

	private String nomeSeguido;

	private LocalDate dataInicio;

	public RelacionamentoDTO(Relacionamento relacionamento) {
		this.seguidorId = relacionamento.getId().getUsuarioSeguidor().getId();
		this.nomeSeguidor = relacionamento.getId().getUsuarioSeguidor().getNome();
		this.seguidoId = relacionamento.getId().getUsuarioSeguido().getId();
		this.nomeSeguido = relacionamento.getId().getUsuarioSeguido().getNome();
		this.dataInicio = relacionamento.getDataInicio();
	}

	public Long getSeguidorId() {
		return seguidorId;
	}

	public void setSeguidorId(Long seguidorId) {
		this.seguidorId = seguidorId;
	}

	public String getNomeSeguidor() {
		return nomeSeguidor;
	}

	public void setNomeSeguidor(String nomeSeguidor) {
		this.nomeSeguidor = nomeSeguidor;
	}

	public Long getSeguidoId() {
		return seguidoId;
	}

	public void setSeguidoId(Long seguidoId) {
		this.seguidoId = seguidoId;
	}

	public String getNomeSeguido() {
		return nomeSeguido;
	}

	public void setNomeSeguido(String nomeSeguido) {
		this.nomeSeguido = nomeSeguido;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

}
