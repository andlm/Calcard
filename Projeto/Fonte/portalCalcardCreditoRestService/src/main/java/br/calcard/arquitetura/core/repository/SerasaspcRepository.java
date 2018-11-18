package br.calcard.arquitetura.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.calcard.arquitetura.core.model.Serasaspc;


@Repository 
public interface SerasaspcRepository extends JpaRepository<Serasaspc, Long> { 
	
	List<Serasaspc> pesquisarCPF(String cpf);
} 


