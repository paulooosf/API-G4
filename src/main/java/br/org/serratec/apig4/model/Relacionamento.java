package br.org.serratec.apig4.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "relacionamento")
public class Relacionamento {

	@EmbeddedId
	private RelacionamentoPK id = new RelacionamentoPK();

	@Column(name = "data_inicio")
	private LocalDate dataInicio;

	public RelacionamentoPK getId() {
		return id;
	}

	public void setId(RelacionamentoPK id) {
		this.id = id;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
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
