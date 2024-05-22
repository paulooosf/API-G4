package br.org.serratec.apig4.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Comentario {

	private Long id;
	private String conteudoCom;
	private LocalDateTime horaCriacao;
	
	@ManyToOne
	private Postagem postagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudoCom() {
		return conteudoCom;
	}

	public void setConteudoCom(String conteudoCom) {
		this.conteudoCom = conteudoCom;
	}

	public LocalDateTime getHoraCriacao() {
		return horaCriacao;
	}

	public void setHoraCriacao(LocalDateTime horaCriacao) {
		this.horaCriacao = horaCriacao;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	public Comentario() {

	}

	public Comentario(Long id, String conteudoCom, LocalDateTime horaCriacao, Postagem postagem) {
		super();
		this.id = id;
		this.conteudoCom = conteudoCom;
		this.horaCriacao = horaCriacao;
		this.postagem = postagem;
	}

	@Override
	public int hashCode() {
		return Objects.hash(conteudoCom, horaCriacao, id, postagem);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comentario other = (Comentario) obj;
		return Objects.equals(conteudoCom, other.conteudoCom) && Objects.equals(horaCriacao, other.horaCriacao)
				&& Objects.equals(id, other.id) && Objects.equals(postagem, other.postagem);
	}
	
	
}
