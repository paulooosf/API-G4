package br.org.serratec.apig4.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {

	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	private Long senha;
	private LocalDate dataNascimento;

	@OneToMany(mappedBy = "autor")
	private List<Postagem> posts;

	@OneToMany(mappedBy = "seguidor")
	private List<Relacionamento> seguidores;

	@OneToMany(mappedBy = "seguido")
	private List<Relacionamento> seguindo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getSenha() {
		return senha;
	}

	public void setSenha(Long senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Postagem> getPosts() {
		return posts;
	}

	public void setPosts(List<Postagem> posts) {
		this.posts = posts;
	}

	public List<Relacionamento> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(List<Relacionamento> seguidores) {
		this.seguidores = seguidores;
	}

	public List<Relacionamento> getSeguindo() {
		return seguindo;
	}

	public void setSeguindo(List<Relacionamento> seguindo) {
		this.seguindo = seguindo;
	}

	public Usuario() {

	}

	public Usuario(Long id, String nome, String sobrenome, String email, Long senha, LocalDate dataNascimento,
			List<Postagem> posts, List<Relacionamento> seguidores, List<Relacionamento> seguindo) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.posts = posts;
		this.seguidores = seguidores;
		this.seguindo = seguindo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataNascimento, email, id, nome, posts, seguidores, seguindo, senha, sobrenome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(dataNascimento, other.dataNascimento) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(posts, other.posts) && Objects.equals(seguidores, other.seguidores)
				&& Objects.equals(seguindo, other.seguindo) && Objects.equals(senha, other.senha)
				&& Objects.equals(sobrenome, other.sobrenome);
	}

}
