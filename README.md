# santander-tecnologia
> Resolucion de la parte del back-end del ejercicio de MeetUp propuesto por santander tecnología

## Tecnología usada
Framework: Spring boot con java 8.

Base de datos: H2 --> Usuario: Santander, Password: admin1234

La aplicacion se levanta desde la clase principal llamada MeetUpApplication, ejecutando Run As --> Java Application

## Puerto y Host
La aplicacion levanta en localhost, en el puerto 8080

## Swagger
La url para visualizar el swagger es la siguiente: http://localhost:8080/swagger-ui.html

## Security
Para la seguridad de las apis rest, debido a no contar con un SSO, utilice JWT.

El método para generar el token es el siguiente: http://localhost:8080/logIn. Este metodo recibe un json con los parametros username y password. El resultado de esto es un json que contiene el siguiente parametro: 

"token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiJ2l2YXJlbGEnIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTU4MTU5NDUzNywiZXhwIjoxNTgxNTk1MTM3fQ.xdNKisakk5UM3f32FlbDsf3s83kUQm_3KKayUykOf-ecsPzCycjq_69vA2CpiFFQjI7Wty6eBPfLadaDRKhWNw"

Luego, para realizar las peticiones al resto de las apis, es necesario agregar dicho token en el Header Authentication.

> NOTA: Para obtener el token, considero admin a todos los usuarios que arranquen con la palabra admin. Ejemplo: adminSantander. El resto de los usuarios que no tengan la palabra admin, los considero usuarios normales. El rol ADMIN puede realizar cualquiera acción, mientras que el rol USER solo puede confirmar asistencia a una meetUp y obtener el clima.

## Comentarios
En la utilización de la api del clima tome como valida la temperatura máxima. Me base en el supuesto de que "es siempre preferible a que sobre". Igualmente guardo todas las temperaturas que me devuelve la api por si en un futuro se desea cambiar la lógica.

La relación temperatura-cantidad de cervezas, la guarde en la BD para que sea parametrizable.

Utilizo dos caches, una para la relación de la temperatura-cantidad de cervezas y otra para el resultado de la api del clima(decidí cachearla porque considero que no se deberia modificar demasiado en el transcurso del tiempo). Igualmente cada cache se refresca cada X cantidad de tiempo.
