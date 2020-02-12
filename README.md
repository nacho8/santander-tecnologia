# santander-tecnologia
> Resolucion de la parte del back-end del ejercicio de MeetUp propuesto por santander tecnología

## Tecnología usada
Framework: Spring boot con java 8.

Base de datos: H2 --> Usuario: Santander, Password: admin1234

## Puerto y Host
La aplicacion levanta en localhost, en el puerto 8080

## Swagger
La url para visualizar el swagger es la siguiente: http://localhost:8080/swagger-ui.html

## Comentarios
En la utilización de la api del clima tome como valida la temperatura máxima. Me base en el supuesto de que "es siempre preferible a que sobre". Igualmente guardo todas las temperaturas que me devuelve la api por si en un futuro se desea cambiar la lógica.

La relación temperatura-cantidad de cervezas, la guarde en la BD para que sea parametrizable.

Utilizo dos caches, una para la relación de la temperatura-cantidad de cervezas y otra para el resultado de la api del clima(decidí cachearla porque considero que no se deberia modificar demasiado en el transcurso del tiempo). Igualmente cada cache se refresca cada X cantidad de tiempo.