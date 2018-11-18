package br.calcard.arquitetura.core.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.calcard.arquitetura.core.model.Proposta;
import br.calcard.arquitetura.core.service.PropostaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController 
@RequestMapping("/rest/proposta")
@Api(value="onlinestore", description="Operações relacionadas com a Proposta")
public class PropostaRestController {
	
	@Autowired
	private PropostaService service; 
	
	@ApiOperation(value = "Veja uma lista de propostas disponíveis",response = Proposta.class)
	@ApiResponses(value = {
          @ApiResponse(code = 200, message = "Lista recuperada com sucesso"),
          @ApiResponse(code = 401, message = "Você não está autorizado a visualizar o recurso"),
          @ApiResponse(code = 403, message = "Acessar o recurso que você estava tentando acessar é proibido"),
          @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
	  }
	)
	@RequestMapping(method = RequestMethod.GET, 
					value = "/pesquisarPropostas/{id}", 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<br.calcard.arquitetura.core.model.Proposta> get(@PathVariable("id") Long id) 
	{
		if (id.equals(0L))
			return service.findAll();
		
		Proposta oProposta =service.findOne(id);
		if (oProposta==null)
		{			
			oProposta=new Proposta();
			oProposta.setId(-1L);
			oProposta.setNome("Proposta não encontrada");
		}
		
		Collection<Proposta> o=new ArrayList<Proposta>();
		o.add(oProposta );
		return o;
	}
	
	@ApiOperation(value = "Salvar uma Proposta",response = Proposta.class)
	@RequestMapping(method = RequestMethod.POST, 
					value = "/adicionarProposta", 
				    produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Proposta> add(@RequestBody Proposta post) {

		try {
			post = service.save(post);
			return new ResponseEntity<Proposta>(post, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Proposta>(post, HttpStatus.BAD_REQUEST);
        }
	}
}
