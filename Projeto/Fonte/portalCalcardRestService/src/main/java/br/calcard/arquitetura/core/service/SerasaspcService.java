package br.calcard.arquitetura.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.calcard.arquitetura.core.model.Serasaspc;
import br.calcard.arquitetura.core.repository.SerasaspcRepository;

@Service 
public class SerasaspcService {
	
	@Autowired
	private SerasaspcRepository repository; 
	
	public List<Serasaspc> findAll() {
		return repository.findAll(); 
	}
	
	public Serasaspc findOne(Long id) {
		return repository.findOne(id);
	}
	
	public Serasaspc save(Serasaspc post) {
		return repository.saveAndFlush(post);
	}
	
	public void delete(Long id) {
		repository.delete(id);
	}
}
