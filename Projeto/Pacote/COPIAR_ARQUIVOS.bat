@rem ##########################################################################
@rem
@rem  Windows limpa,copia e renomeia os arquivos em Windows 10 (CALCARD)
@rem
@rem ##########################################################################

@echo off
SET TOMCAT_HOME=%ProgramFiles%\Apache Software Foundation\Tomcat 9.0\webapps
SET DEVELOPMENT_HOME=C:\Users\andy\eclipse-workspace\

cd %TOMCAT_HOME%\
call del /f portalCalcardCreditoRestService-0.0.1.war
call del /f portalCalcardPropostaRestService-0.0.1.war
call del /f portalCalcardRestService-0.0.1.war

SET src="%DEVELOPMENT_HOME%\portalCalcardCreditoRestService\target\portalCalcardCreditoRestService-0.0.1.war"
SET dest="%ProgramFiles%\Apache Software Foundation\Tomcat 9.0\webapps"
copy %src% %dest%\portalCalcardCreditoRestService-0.0.1.war
rename portalCalcardCreditoRestService-0.0.1.war portalCalcardCreditoRestService.war

SET src1="%DEVELOPMENT_HOME%\portalCalcardPropostaRestService\target\portalCalcardPropostaRestService-0.0.1.war"
SET dest1="%ProgramFiles%\Apache Software Foundation\Tomcat 9.0\webapps"
copy %src1% %dest1%\portalCalcardPropostaRestService-0.0.1.war
rename portalCalcardPropostaRestService-0.0.1.war portalCalcardPropostaRestService.war

SET src1="%DEVELOPMENT_HOME%\portalCalcardRestService\target\portalCalcardRestService-0.0.1.war"
SET dest1="%ProgramFiles%\Apache Software Foundation\Tomcat 9.0\webapps"
copy %src1% %dest1%\portalCalcardRestService-0.0.1.war
rename portalCalcardRestService-0.0.1.war portalCalcardRestService.war

