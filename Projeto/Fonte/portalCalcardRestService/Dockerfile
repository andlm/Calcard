# THIS DOCKER FILE WAS DEVELOPED BY ANDERSON 
# CONFLITO COM A INSTALAÇÃO DOS TOMCAT EXISTENTE
# ARQUIVO IMCOMPLETO FEITO NOS BATS(PUBLICAR_ARQUIVOS_WAR.BAT E COPIAR_ARQUIVOS.bat)

# SPECIFY THE CONTAINER IMAGE
FROM microsoft/windowsservercore

# COPY THE APACHE INSTALLATION FILES INTO THE CONTAINER
ADD ./setup c:/source/Tomcat9

RUN powershell.exe -command \

# INSTALLING JAVA JRE
./source/jre.cmd; \

# INSTALLING JAVA JDK
./source/jdk.cmd; \

# COPYING CUSTOMIZE TOMCAT FILES TO C drive
Copy-Item c:/source/Tomcat9 -Destination %ProgramFiles%/Apache Software Foundation/Tomcat 9.0 -Recurse; \

# SETTING UP ENVIRONMENT PATH VARIABLES FOR JRE & JDK FOLDERS
./source/Environmentvariable.ps1; \

# Installing Tomcat service
cmd.exe /c c:/source/Tomcat9/tomcatservice.bat; \

# SET TOMCAT SERVICE TO RUN UNDER LOCAL SYSTEM ACCOUNT
sc.exe config tomcat9 obj=LocalSystem start=auto; \

# REMOVE TOMCAT SETUP FILES FROM SOURCE DIRECTORY
cmd.exe /c rd /S /Q c:/source/Tomcat9

