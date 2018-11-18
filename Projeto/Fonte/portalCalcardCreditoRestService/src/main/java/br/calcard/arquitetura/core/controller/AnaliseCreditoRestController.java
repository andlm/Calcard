package br.calcard.arquitetura.core.controller;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.calcard.arquitetura.core.MotorAnaliseCredito;
import br.calcard.arquitetura.core.model.Proposta;
import br.calcard.arquitetura.core.model.Serasaspc;
import br.calcard.arquitetura.core.service.SerasaspcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController 
@RequestMapping("/rest/motor")
@Api(value="onlinestore", description="Operações relacionadas com a análise de crédito")
public class AnaliseCreditoRestController {
	
	@Autowired
	private SerasaspcService service; 
	
	
	@ApiOperation(value = "Processamento da análise de crédito, retornando se a proposta está aprovada ou reprovada ",response = Proposta.class)
	@ApiResponses(value = {
          @ApiResponse(code = 200, message = "Lista recuperada com sucesso"),
          @ApiResponse(code = 401, message = "Você não está autorizado a visualizar o recurso"),
          @ApiResponse(code = 403, message = "Acessar o recurso que você estava tentando acessar é proibido"),
          @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
	  }
	)
	@RequestMapping(method = RequestMethod.POST, 
					value = "/processarCredito", 
				    produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Proposta> add(@RequestBody Proposta post) {

		try {
			
			// verifca a restrição spc e serasa pelo cpf

			boolean bFlPermiteAnaliseCredito = true;

			if (!isEmpty(post.getCpf()))
			{
				List<Serasaspc> lista =service.pesquisarCPF(post.getCpf());
				if (lista!=null)
					if (!lista.isEmpty())
						bFlPermiteAnaliseCredito = false;
			}
			
			if (bFlPermiteAnaliseCredito)
			{
				String[] arrRetorno 				= new String[2];
				arrRetorno =MotorAnaliseCredito.processar(toDouble(post.getRenda()), post.getDependentes().intValue());
				if (!isEmpty(arrRetorno[0]))
					post.setResultadoanalise(arrRetorno[0]);
				if (!isEmpty(arrRetorno[1]))
					post.setLimite(arrRetorno[1]);
			}
			else
			{
				post.setResultadoanalise("Negado");
				post.setLimite("reprovado pela política de crédito");
			}
			
			return new ResponseEntity<Proposta>(post, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Proposta>(post, HttpStatus.BAD_REQUEST);
        }
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
}
