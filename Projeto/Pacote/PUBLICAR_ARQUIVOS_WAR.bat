@rem ##########################################################################
@rem
@rem  MAVEN no workspace do eclipse aonde estão os projetos e  
@rem		gerado os pacote(war) para tomcat9 em Windows 10 (CALCARD)
@rem
@rem ##########################################################################

@echo off
SET JAVA_HOME=C:\Program Files (x86)\Java\jdk1.8.0_131
SET DEVELOPMENT_HOME=C:\Users\andy\eclipse-workspace\

cd %DEVELOPMENT_HOME%\portalCalcardCreditoRestService\
call mvn clean install

cd %DEVELOPMENT_HOME%\/portalCalcardPropostaRestService\
call mvn clean install

cd %DEVELOPMENT_HOME%\portalCalcardRestService\
call mvn clean install

