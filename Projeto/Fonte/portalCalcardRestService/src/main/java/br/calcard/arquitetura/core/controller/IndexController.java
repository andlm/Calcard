package br.calcard.arquitetura.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import br.calcard.arquitetura.core.model.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@Api(value="onlinestore", description="Operações relacionadas com uma Proposta")
public class IndexController {

	@ApiOperation(value = "Iniciar as Operações de uma Proposta",response = String.class)
		  @ApiResponses(value = {
		  @ApiResponse(code = 200, message = "Lista recuperada com sucesso"),
		  @ApiResponse(code = 401, message = "Você não está autorizado a visualizar o recurso"),
		  @ApiResponse(code = 403, message = "Acessar o recurso que você estava tentando acessar é proibido"),
		  @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
		}
	)
	@GetMapping("/")
    public String index() {
        return "index";
    }
	
	@ApiOperation(value = "Sair do sitema efetuar logout",response = String.class)
	@GetMapping("/logout")
    public String logout() {
        return "index";
    }

	@ApiOperation(value = "Redirecionar e Incluir uma nova Proposta",response = RedirectView.class)
    @PostMapping("/form")
    public RedirectView formPost(Usuario user, Model model) {
        model.addAttribute("user", user);
        RedirectView rv = new RedirectView();
        rv.setContextRelative(true);
        rv.setUrl("/adicionarProposta");
        return rv;
    }
}
