package br.org.serratec.apig4.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comentario")
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comentario")
	private Long id;

	@Column(name = "conteudo_comentario")
	private String conteudoCom;

	@Column(name = "hora_criacao")
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
		Comentario other = (Comentario) obj;
		return Objects.equals(id, other.id);
	}

}
