package br.calcard.arquitetura.core.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.calcard.arquitetura.core.model.Proposta;
import br.calcard.arquitetura.core.model.PropostaModel;
import br.calcard.arquitetura.core.service.PropostaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller 
@Api(value="onlinestore", description="Operações relacionadas com a Proposta")
public class PropostaController {
	
	@Autowired
	private PropostaService service; 
	
	@ApiOperation(value = "Veja uma lista de propostas disponíveis",response = Proposta.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Lista recuperada com sucesso"),
//            @ApiResponse(code = 401, message = "Você não está autorizado a visualizar o recurso"),
//            @ApiResponse(code = 403, message = "Acessar o recurso que você estava tentando acessar é proibido"),
//            @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
//    }
//    )
	@GetMapping("/pesquisarProposta")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("/proposta");
		
		mv.addObject("propostas", getPropostas());
		mv.addObject("propostaModel", new PropostaModel());
		
		return mv;
	}
	
	private List<Proposta> getPropostas()
	{
	    final String uri = "http://localhost:8080/portalCalcardPropostaRestService/rest/proposta/pesquisarPropostas/0";
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    System.out.println(result);
	    ObjectMapper mapper = new ObjectMapper();
	    List<Proposta> lista = null;
	    try 
	    {
	    	lista = mapper.readValue(result, new TypeReference<List<Proposta>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return lista;
	}
	
	@ApiOperation(value = "Incluir uma Proposta",response = Proposta.class)
	@GetMapping("/adicionarProposta")
	public ModelAndView add(Proposta post) {
		
		ModelAndView mv = new ModelAndView("/propostaAdd");
		
		mv.addObject("proposta", post);
		
		return mv;
	}
	
	public static Date getDataAtual() {
		return new Date();
	}
	
	@ApiOperation(value = "Excluir uma Proposta",response = Proposta.class)
	@GetMapping("/excluirProposta/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll();
	}
	
	@ApiOperation(value = "Salvar uma Proposta",response = Proposta.class)
	@PostMapping("/salvarProposta")
	public ModelAndView save(@Valid Proposta post, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(post);
		}
		
		post.setData(getDataAtual());
		
		AnaliseCredito(post);
		
		try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		Salvar(post);
		
		return findAll();
	}
	
	// Chama o motor analise de cre
	private Proposta AnaliseCredito(Proposta post)
	{
		
		final String uri = "http://localhost:8080/portalCalcardCreditoRestService/rest/motor/processarCredito";
	    
	    RestTemplate restTemplate = new RestTemplate();
	    Proposta result = restTemplate.postForObject( uri, post, Proposta.class);
	    
	    if (result!=null)
	    {
	    	if (!isEmpty(result.getResultadoanalise()))
	    		post.setResultadoanalise(result.getResultadoanalise());
	    	
	    	if (!isEmpty(result.getLimite()))
	    		post.setLimite(result.getLimite());
	    		
	    }
	    
	    System.out.println(result);
	    return post;
	}
	
	// salvar 
	private void Salvar(Proposta post)
	{
		
		final String uri = "http://localhost:8080/portalCalcardPropostaRestService/rest/proposta/adicionarProposta";
	    
	    RestTemplate restTemplate = new RestTemplate();
	    Proposta result = restTemplate.postForObject( uri, post, Proposta.class);
	    
	    System.out.println(result);
	}
	
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	@SuppressWarnings("resource")
	public static Double toDouble(Object objeto) {
		if (isEmpty(objeto.toString())) {
			return null;
		}
		if ((objeto instanceof Double)) {
			return (Double) objeto;
		}
		if ((objeto instanceof String)) {
			Scanner scanner = new Scanner(objeto.toString());
			scanner.useLocale(new Locale("pt", "BR"));
			return Double.valueOf(scanner.nextDouble());
		}
		return Double.valueOf(objeto.toString());
	}
	
	@ApiOperation(value = "Pesquise uma proposta com um CPF",response = PropostaModel.class)
	@RequestMapping(value="/buscarProposta", method=RequestMethod.POST)
	public ModelAndView buscarProposta(@ModelAttribute PropostaModel post, Model model) {	
		
		ModelAndView mv = new ModelAndView("/proposta");
		if (!isEmpty(post.getCpf()))
			mv.addObject("propostas", service.pesquisarCPF(post.getCpf()));
		else
			mv.addObject("propostas", getPropostas());
		
		mv.addObject("proposta", post);
		
		return mv;
	}
	
}
