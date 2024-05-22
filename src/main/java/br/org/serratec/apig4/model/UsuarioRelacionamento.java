package br.org.serratec.apig4.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario-relacionamento")
public class UsuarioRelacionamento {
	
	@EmbeddedId
	private UsuarioRelacionamentoPK id = new UsuarioRelacionamentoPK();
	
	@Column(name = "data_criacao")
	private LocalDate dataCriacao;

	public UsuarioRelacionamento() {
		
	}
	
	public UsuarioRelacionamento(UsuarioRelacionamentoPK id, LocalDate dataCriacao) {
		super();
		this.id = id;
		this.dataCriacao = dataCriacao;
	}

	public UsuarioRelacionamentoPK getId() {
		return id;
	}

	public void setId(UsuarioRelacionamentoPK id) {
		this.id = id;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
}
