# apiSucursal

# Proyecto

API Rest para Gestión de Sucursales.

## Comenzando 🚀

El proyecto API Sucursal esta confeccionado para correr como un servicio standalone , es decir, como un microservicio. El mismo esta escrito en  java, con lo cual permite que dicho servicio sea multiplataforma. 
A continuación se detallan las dependencias mandatorias para la construcción del mismo.


### Dependencias 📋

De forma general, se enuncian las dependecias principales.

```
- Java 8 (o versiones superiores) 
- Maven 3.1.0
- Spring boot 2.4.5
- Spring Web
- Spring Data Jpa
- springfox-swagger2
- springfox-swagger-ui
- mysql-connector-java
- h2database
```
A continuación se detalla con exactitud las dependencias mandatorias suministradas por maven, extraidas del archivo pom.xml del proyecto.
```
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.9.2</version>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.9.2</version>
		</dependency>
	</dependencies>
```

### Construción y Compilación del proyecto 🔧

A continuación se detallan los pasos:

- Nos posicionamos en la directorio raíz del proyecto : /api , donde encontraremos el archivo pom.xml y ejecutamos el comando:
 

```
mvn clean
```
- Para compilar y generar el archivo jar , ejecute :

```
mvn package
```
## Despliegue 📦

- Nos posicionamos en el directorio /api/target y ejecutamos el comando :

```
java -jar api-0.0.1-SNAPSHOT.jar
```
Siendo api-0.0.1-SNAPSHOT.jar nuestro servicio compilado y empaquetado. De esta forma se realiza el despliegue del mismo, en el web container embebido , suministrado por Spring boot.

## Accediendo al servicio 📄

- URL del servicio : http://localhost:8080/api/sucursal
- URL del servicio swagger para documentacion y pruebas de la API : http://localhost:8080/swagger-ui.html

## Descripción de los Endpoints del Servicio 📄

* **URL BASE**
```
/api/sucursal
 ```

## getById 

* **Descripción**
 ```
Se encarga de buscar una sucursal por medio de su atributo: id.
```
* **URL**
```
url BASE
```

* **Metodo Http:**
  
```
GET
```
*  **Parametro Requerido**

```
id = [Long]
```
 

*  **Request**

```
curl -X GET "http://localhost:8080/api/sucursal?id=value" -H "accept: */*
```
 
* **Success Response:**
 ```
 {
  "direccion": "string",
  "latitud": 0,
  "longitud": 0
}
``` 
 **Code:**
 ```
 200
```

**Ejemplo:**

 ```
 Request :
 curl -X GET "http://localhost:8080/api/sucursal?id=2" -H "accept: */*
 
 Response:
 
 Http Status 200 OK 
 Description: Se obtuvo correctamente el recurso solicitado 
 {
  "direccion": "avenida 7 , La Plata . Buenos Aires",
  "latitud": '-4773737373728.222',
  "longitud": '-656367867676.345'
}
 
```

* **Error Response:**


  * **Code:** 400 Bad Request <br />
    **Content:** 
 ``` 
 {
  "errors": [
    "string"
  ],
  "message": "string",
  "status": 0,
  "timestamp": "2021-04-26T02:20:56.254Z"
}
``` 

  * **Code:** 500 Internal Server Error <br />
    **Content:** 
    
     ```
     { "message" : "Error inesperado del sistema" 
     "status": ,
     "timestamp": "YYYY-MM-yyyy"}
     ```
  
  ## create 

* **Descripción**
 ```
Crea una Sucursal y la retorna.
```
* **URL**
```
url BASE.
```

* **Metodo Http:**
  
```
POST
```
* **Parametros Requeridos**
 
```
Latitud : [Double]
Longitud : [Double]
dirreccion: [String]
Body:
{
  "direccion": "string",
  "latitud": 0,
  "longitud": 0
}

*El campo Dirección es unico para cada sucursal

```
 
* **Request:**
```

curl -X POST "http://localhost:8080/api/sucursal" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"direccion\": \"VALUE_DIRECCION", \"latitud\": LATITUD_VALUE, \"longitud\": LONGITUD_VALUE}"

```
 
* **Success Response:**
 ```
 {"id":0
  "direccion": string,
  "latitud": 0.0,
  "longitud":0.0
}
``` 
 **Code:**
 ```
 201 Created
```
```
 Mesagge : El recurso se creo correcamente
```

 **Ejemplo:**

 ```
 Request :
 
 curl -X POST "http://localhost:8080/api/sucursal" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"direccion\": \"C. 12 1249, B1900 La Plata, Provincia de Buenos Aires\", \"latitud\": -32.95650816650679, \"longitud\": -57.94894639999999}"
 
 Response:
 
 Http Status 201 CREATED 
 Description: OK. El recurso se creo correctamente 
 {
  "id":2
  "direccion": "C. 12 1249, B1900 La Plata, Provincia de Buenos Aires",
  "latitud": -32.95650816650679,
  "longitud":-57.94894639999999
}
 
  ```
  
 ## getNearbySucursal 

* **Descripción**
 ```
A partir de un punto geografico determinado, se encarga de retornar
la Sucursal mas cercana al mismo.
```

* **URL**
```
 /getNearbySucursal
```

* **Metodo Http:**
```
GET
```


*  **Parametros Requeridos**
```

latitud = [Double]
longitud = [Double]
```


*  **Request**
```

curl -X GET "http://localhost:8080/api/sucursal/getNearbySucursal?latitud=LATITUD_VALUE&longitud=LONGITUD_VALUE" -H "accept: */*"
```

* **Success Response:**

```
 {
  id:0
  "direccion": "string",
  "latitud": 0.0,
  "longitud": 0.0
}

 ```
 

**Code:**
``` 
200
 
 ```
 
 **Ejemplo:**
```
Request :
 
 curl -X GET "http://localhost:8080/api/sucursal/getNearbySucursal?latitud=32.95650446654656&longitud=57.948946333334444" -H "accept: */*" 
 
 Response:
 
 Http Status 200 OK 
 Description: Se obtuvo correctamente el recurso solicitado 
 {
  "direccion": "C. 12 1249, B1900 La Plata, Provincia de Buenos Aires",
  "latitud": -32.95650816650679,
  "longitud":-57.94894639999999
}


 ```
 
  



## Ejecutando las pruebas ⚙️

Para ejecutar los tests asociados a la aplicacion , posicionarse en /api y ejecutar :
```
mvn test
```

## Construido con 🛠️


* [Spring Initializr](https://start.spring.io/) - Herramienta online para armar un proyecto basado en Spring.
* [Maven](https://maven.apache.org/) - Manejador de dependencias.




