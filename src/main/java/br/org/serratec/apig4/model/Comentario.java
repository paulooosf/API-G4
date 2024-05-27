package br.org.serratec.apig4.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "comentario")
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comentario")
	private Long id;

	@NotBlank(message = "Digite Algo")
	@Column(name = "conteudo_comentario")
	private String conteudoCom;

	@Column(name = "hora_criacao")
	private LocalDateTime horaCriacao;

	@ManyToOne
	private Postagem postagem;

	@ManyToOne
	private Usuario usuario;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Comentario() {

	}

	public Comentario(Long id, String conteudoCom, LocalDateTime horaCriacao, Postagem postagem, Usuario usuario) {
		super();
		this.id = id;
		this.conteudoCom = conteudoCom;
		this.horaCriacao = horaCriacao;
		this.postagem = postagem;
		this.usuario = usuario;
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

	@PrePersist
	protected void onCreate() {
		horaCriacao = LocalDateTime.now();
	}

}
