package br.org.serratec.apig4.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "relacionamento")
public class Relacionamento {

	@EmbeddedId
	private RelacionamentoPK id; // = new RelacionamentoPK();

	@Column(name = "data_criacao")
	private LocalDate dataCriacao;

	public Relacionamento() {

	}

	public Relacionamento(RelacionamentoPK id, LocalDate dataCriacao) {
		super();
		this.id = id;
		this.dataCriacao = dataCriacao;
	}

	public RelacionamentoPK getId() {
		return id;
	}

	public void setId(RelacionamentoPK id) {
		this.id = id;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
}
