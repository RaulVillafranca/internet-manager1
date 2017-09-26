# internet-manager1
Software para la gestion de servicios de Internet comunitario.
Proyecto final de carrera Raul Villafranca Arcenegui.

# Caracteristicas del proyecto
Se trata de un proyecto desarrollado en Java, haciendo uso de Spring Framework y repositorios Maven.
Se ha usado para el desarrollo de este proyecto el IDE IntelliJ. Este proyecto consta de un Tomcat incrustado.
La interfaz es web, se accede a la aplicacion mediante el navegador.

# Requisitos para ejecutar el proyecto en local

Para ejecutar el proyecto en local son necesarios los siguientes componentes:

IntelliJ IDEA
JDK 1.8
Servidor MySQL Mamp

Los 3 componentes pueden ser descargados gratuitamente de las siguentes webs respectivamente:
https://www.jetbrains.com/idea/
https://www.java.com/es/download/
https://www.mamp.info/en/

Para que la persistencia funcione correctamente es necesario disponer un servidor MySQL. En mi caso he usado
Mamp y recomiendo su uso dado que no requiere ninguna configuracion; pero puede usarse cualquier otro servidor MySQL.

#Como acceder a la aplicacion
Una vez se este ejecutando el proyecto, se accede a la aplicacion mediante la url http://localhost:8080

Se ha de introducir usuario y password.
El usuario: admin, password: admin es el administrador de la aplicacion.

En el archivo CommunityLoader que esta en la carpeta bootstrap hay introducidos una muestra de residentes, presidentes, comunidades,
proovedores y contratos.
Se puede entrar a la aplicacion con cualquiera de los usuario creados, o con los que quieran crear con la aplicaci?n,
el usuario ser? siempre el correo electronico.
Como ejemplo de presidentes cualquiera tenemos:
user: president1@gmail.com password: 1234
user: president2@gmail.com password: 1234
Como ejemplo de residentes tenemos:
user: jordi@api.com password: 1234
user: eloy@api.com password: 1234



# Despliegue en un servidor cloud
Este proyecto esta preparado para ser desplegada en un servidor cloud, no es necesario un contenedor web Tomcat ya
que esta incrustado en la aplicacion. En la memoria se detalla su despliegue en Heroku.










