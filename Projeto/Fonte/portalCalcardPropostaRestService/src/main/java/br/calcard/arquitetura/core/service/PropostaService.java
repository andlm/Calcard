package br.calcard.arquitetura.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.calcard.arquitetura.core.model.Proposta;
import br.calcard.arquitetura.core.repository.PropostaColetaRepository;

@Service 
public class PropostaService {
	
	@Autowired
	private PropostaColetaRepository repository; 
	
	public List<Proposta> findAll() {
		return repository.findAll(); 
	}
	
	public List<Proposta> pesquisarCPF(String cpf) {
		return repository.pesquisarCPF(cpf); 
	}
	
	public Proposta findOne(Long id) {
		return repository.findOne(id);
	}
	
	public Proposta save(Proposta post) {
		return repository.saveAndFlush(post);
	}
	
	public void delete(Long id) {
		repository.delete(id);
	}
}
