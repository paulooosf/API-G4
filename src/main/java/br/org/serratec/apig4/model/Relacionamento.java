package br.org.serratec.apig4.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "relacionamento")
public class Relacionamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_relacionamento")
	private Long id;
	
	@Column(name = "data_inicio")
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
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}
}
