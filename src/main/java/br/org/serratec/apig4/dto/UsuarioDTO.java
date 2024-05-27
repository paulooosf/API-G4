package br.org.serratec.apig4.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.org.serratec.apig4.model.Perfil;
import br.org.serratec.apig4.model.Usuario;
import br.org.serratec.apig4.model.UsuarioPerfil;

public class UsuarioDTO {

	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	private LocalDate dataNascimento;

	private Set<Perfil> perfis;

	public UsuarioDTO() {

	}

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
		this.email = usuario.getEmail();
		this.dataNascimento = usuario.getDataNascimento();
		this.perfis = new HashSet<>();
		for (UsuarioPerfil usuarioPerfil : usuario.getUsuarioPerfis()) {
			this.perfis.add(usuarioPerfil.getId().getPerfil());

		}
	}

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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
