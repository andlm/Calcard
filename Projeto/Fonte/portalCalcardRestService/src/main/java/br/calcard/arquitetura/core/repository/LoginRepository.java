package br.calcard.arquitetura.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.calcard.arquitetura.core.model.Usuario;

@Repository 
public interface LoginRepository extends JpaRepository<Usuario, Long> 
{
	//@Query("select u from Usuario u where u.sNmUsuario = ?1 and u.sDsSenha = ?2")
	@Query(value = "select * from usuario where s_nm_usuario=:sUsuario  and s_ds_senha=:sSenha", nativeQuery = true)
	Usuario findByUsername(@Param("sUsuario") String sUsuario,@Param("sSenha") String sSenha);
} 


