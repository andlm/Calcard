# Avaliação Técnica - Anderson

[![drawing](https://sitegabriela.conductor.com.br/App_Themes/8/Images/Logos/BannerSite.png)](http://www.calcard.com.br/)

Foi desenvolvida uma aplicação web que realiza o cadastro de proposta de crédito para um determinado cliente, 
efetua a análise de dados e efetiva a aprovação ou negação de um limite de crédito para o mesmo.
  
### Tecnologias

  - Frontend: [AngularJS], [Html5], [Bootstrap], [Thymeleaf], [CSS]
  - Backend: [Java], [Spring-boot], [Postgresql 9], [Gson]
  - Servidor Aplicação: [Tomcat 9]
  - Integrações: [Rest] conceito Restful e RestTemplate		  	
  - Documentação: [Swagger]						  		
  

### Arquitetura 

  - Frontend/Backend.	
  - Backend com conceito de Api contendo documentação swagger dos endpoints.		
  - API para cadastro e consulta das propostas. 									
  - API motor de crédito que efetuará a análise da proposta e realizará a tomada de decisão do limite de crédito. 

### Observações 

  - Pontos a melhorar que não foram completados com 100% conforme especificações são: 95% Arquitetura (Frontend-Angular) e Desafios (Docker incompleto).
  - Frontend coloquei pouco de [AngularJS], ponto a melhorar, foco no negócio.
  - Integração com docker deu conflito com as versões do apache tomcat, ponto a melhorar, solução intermediária foi à criação dos arquivos(s) bat (PUBLICAR_ARQUIVOS_WAR e COPIAR_ARQUIVOS) para deploy da aplicação no servidor de aplicação.
  - Todos os outros pontos da especificação foram completados com sucesso e estão referenciados nos três projetos.
  - O projeto portalCalcardAngularPropostaNOK não está OK, somente foi adicionado como demonstração de conhecimento para atendimento de requisito [AngularJS], entretanto o que vale como Avaliação Técnica é o "portalCalcardRestService" junto com os serviços rest.
  
### Publicação

O projeto está documentado e publicado no github.


License
----

Calcard Administradora de Cartões


https://github.com/andlm/Calcard



   

   




	
	
	









