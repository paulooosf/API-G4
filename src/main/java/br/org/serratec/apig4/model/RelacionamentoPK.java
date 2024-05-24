package br.org.serratec.apig4.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class RelacionamentoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_usuario_seguidor")
	private Usuario usuarioSeguidor;

	@ManyToOne
	@JoinColumn(name = "id_usuario_seguido")
	private Usuario usuarioSeguido;

	public Usuario getUsuarioSeguidor() {
		return usuarioSeguidor;
	}

	public void setUsuarioSeguidor(Usuario usuarioSeguidor) {
		this.usuarioSeguidor = usuarioSeguidor;
	}

	public Usuario getUsuarioSeguido() {
		return usuarioSeguido;
	}

	public void setUsuarioSeguido(Usuario usuarioSeguido) {
		this.usuarioSeguido = usuarioSeguido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(usuarioSeguido, usuarioSeguidor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelacionamentoPK other = (RelacionamentoPK) obj;
		return Objects.equals(usuarioSeguido, other.usuarioSeguido)
				&& Objects.equals(usuarioSeguidor, other.usuarioSeguidor);
	}

}
