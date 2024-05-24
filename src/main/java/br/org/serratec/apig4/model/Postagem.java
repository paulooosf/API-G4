package br.org.serratec.apig4.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "postagem")
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_postagem")
	private Long id;
	
	@NotBlank(message = "Preencha o conteúdo da postagem!")
	@Column(nullable = false)
	private String conteudo;
	
	@NotNull(message = "Preencha a data e hora da postagem! Formato correto: ano-mes-dia")
	@Column(name = "hora_criacao")
	private LocalDate dataCriacao;

	@NotNull(message = "Preencha o autor da postagem!")
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

	public LocalDate getHoraCriacao() {
		return dataCriacao;
	}

	public void setHoraCriacao(LocalDate horaCriacao) {
		this.dataCriacao = horaCriacao;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Postagem() {

	}

	public Postagem(Long id, String conteudo, LocalDate dataCriacao, Usuario autor) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.dataCriacao = dataCriacao;
		this.autor = autor;
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
		Postagem other = (Postagem) obj;
		return Objects.equals(id, other.id);
	}

}
