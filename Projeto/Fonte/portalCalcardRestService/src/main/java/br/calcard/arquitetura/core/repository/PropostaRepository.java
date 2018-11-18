package br.calcard.arquitetura.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.calcard.arquitetura.core.model.Proposta;


@Repository 
public interface PropostaRepository extends JpaRepository<Proposta, Long> { 
	
	List<Proposta> pesquisarCPF(String cpf);
} 


