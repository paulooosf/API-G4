package br.org.serratec.apig4.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class UsuarioRelacionamentoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_relacionamento")
	private Relacionamento relacionamento;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Relacionamento getRelacionamento() {
		return relacionamento;
	}

	public void setRelacionamento(Relacionamento relacionamento) {
		this.relacionamento = relacionamento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(relacionamento, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioRelacionamentoPK other = (UsuarioRelacionamentoPK) obj;
		return Objects.equals(relacionamento, other.relacionamento) && Objects.equals(usuario, other.usuario);
	}
}
