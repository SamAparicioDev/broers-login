El paso a paso para poder correr el proyecto es: 
  - Clonemos el proyecto y los abrimos con un IDE que soporte Spring Boot; sugieron IntelliJ o EclipeSpringBoot
  - Cargar las dependencias, las cuales estan en maven
  - Inicializar XAMPP para iniciar el servidor de MySQL / O tener instalado directamente el servidor de MySQL: https://dev.mysql.com/downloads/mysql/
  
Configuramos el archivo application.properties que se encuentra en la carpeta src/main/resources para que se ejecute:
  En este propiedad: spring.datasource.password=       : Es la contraseña que tenemos, generalmente cuando iniciamos con xampp no tenemos clave, si descargamos el servidor de mysql si nos exige una contraseña
  al instalarlo, ponemos esa.


  El flujo es el siguiente con Swagger: 
  Ejecutamos y compilamos, abrimos la siguiente ruta: http://localhost:8080/swagger-ui/index.html

  Alli encontraremos los endpoints y los modelos; en los entities encontraremos dos:

  El de usuario: User-Entity-Controller, el cual tiene el siguiente flujo:
    - Creamos un usuario primero con la ruta /api/user/save , mandandole en el body el nombre, el apellido y el correo (En caso de no llegar por favor revisar el spam), al correo de la persona llegara un token
    - Con ese token consumimos el endpoint api/user/create-password/{token} donde mandamos por la ruta el token que nos llego al correo
    y en el body enviamos la clave que queremos asignar, (Recordemos que este token es de unico uso) con eso, ya podemos iniciar sesion, en caso de olvidar la clave, vamos al endpoint
    /api/user el cual nos trae todos los usuarios, vemos el de nosotros, y agarramos el id, para consumir el endpoint:
  - /api/user/view-password/{id} mandamos por la ruta el id que copiamos de nuestro usuario, al correo nos llegara el token
  - y nuevamente consumimos la ruta api/user/create-password/{token} y mandamos por el body la nueva clave.

Ahora bien, para iniciar sesion y que nos retorne el jwt token; consumimos en el auth-controller el servicio
/api/auth/login y mandamos en el body la clave que colocamos y el email que registramos, y listo el nos retornara un jwt token con el que podemos
proteger nuestras rutas en un futuro. Recordemos que tiene un tiempo de caducar.


