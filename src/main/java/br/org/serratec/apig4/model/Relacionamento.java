package br.org.serratec.apig4.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Relacionamento {
	
	private Long id;
	private LocalDate dataInicioSeguimento;
	
	@ManyToOne
	private Usuario seguidor;
	
	@ManyToOne
	private Usuario seguidos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataInicioSeguimento() {
		return dataInicioSeguimento;
	}

	public void setDataInicioSeguimento(LocalDate dataInicioSeguimento) {
		this.dataInicioSeguimento = dataInicioSeguimento;
	}

	public Usuario getSeguidor() {
		return seguidor;
	}

	public void setSeguidor(Usuario seguidor) {
		this.seguidor = seguidor;
	}

	public Usuario getSeguidos() {
		return seguidos;
	}

	public void setSeguidos(Usuario seguidos) {
		this.seguidos = seguidos;
	}

	public Relacionamento() {

	}

	public Relacionamento(Long id, LocalDate dataInicioSeguimento, Usuario seguidor, Usuario seguidos) {
		super();
		this.id = id;
		this.dataInicioSeguimento = dataInicioSeguimento;
		this.seguidor = seguidor;
		this.seguidos = seguidos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataInicioSeguimento, id, seguidor, seguidos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Relacionamento other = (Relacionamento) obj;
		return Objects.equals(dataInicioSeguimento, other.dataInicioSeguimento) && Objects.equals(id, other.id)
				&& Objects.equals(seguidor, other.seguidor) && Objects.equals(seguidos, other.seguidos);
	}
	
	
}
