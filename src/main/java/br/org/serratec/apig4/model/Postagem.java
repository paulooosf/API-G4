package br.org.serratec.apig4.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Postagem {

	private Long id;
	private String conteudo;
	private LocalDateTime horaCriacao;

	@ManyToOne
	private Usuario autor;

	@OneToMany(mappedBy = "postagem")
	private List<Comentario> comentarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public LocalDateTime getHoraCriacao() {
		return horaCriacao;
	}

	public void setHoraCriacao(LocalDateTime horaCriacao) {
		this.horaCriacao = horaCriacao;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Postagem() {

	}

	public Postagem(Long id, String conteudo, LocalDateTime horaCriacao, Usuario autor, List<Comentario> comentarios) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.horaCriacao = horaCriacao;
		this.autor = autor;
		this.comentarios = comentarios;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autor, comentarios, conteudo, horaCriacao, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Postagem other = (Postagem) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(comentarios, other.comentarios)
				&& Objects.equals(conteudo, other.conteudo) && Objects.equals(horaCriacao, other.horaCriacao)
				&& Objects.equals(id, other.id);
	}

}
