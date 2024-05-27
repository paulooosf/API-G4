package br.org.serratec.apig4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.org.serratec.apig4.model.UsuarioPerfil;
import br.org.serratec.apig4.model.UsuarioPerfilPK;

@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, UsuarioPerfilPK> {

}
