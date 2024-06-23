# IMS - Inventory Management System

IMS es un REST API de gestión de inventario desarrollado con Spring Boot, que permite administrar vehículos, clientes,
usuarios, roles y permisos de manera eficiente.

La REST API Aún no está terminada, ya que solo cumple con las funcionalidades básicas, pero poco a poco se van a ir
agregando funcionalidades.

## Características Principales

- **Gestión de Usuarios:** Permite la creación, actualización, eliminación y búsqueda de usuarios con roles específicos.
- **Gestión de Clientes:** Facilita el manejo completo de clientes, incluyendo la creación y actualización de
  información.
- **Gestión de Vehículos:** Incluye la gestión de automóviles y motocicletas, con características específicas para cada
  tipo.
- **Gestión de Roles y Permisos:** Permite definir roles de usuario con permisos específicos utilizando autoridades
  configurables.

## Tecnologías Utilizadas

- Java 21
- Spring Boot 3.3.0
- Spring Boot Actuator
- Spring Boot Data JPA
- Spring Boot Validation
- Spring Boot Web
- Spring Boot DevTools
- MySQL Connector/J
- Lombok
- Spring Boot Data REST
- Spring Boot Security
- JSON Web Token (JWT)
- Spring Security Test

## Instalación

Para ejecutar este proyecto localmente, asegúrate de tener instalado:

- Java Development Kit (JDK) versión 21 o superior.
- MySQL y la base de datos configurada según la propiedad `spring.datasource.url` en `application.properties`.

Clona este repositorio y ejecuta el proyecto usando Maven:

```bash
mvn spring-boot:run
```

## Configuración Adicional

- Configuración de Seguridad: Se ha implementado seguridad utilizando JWT para autenticación y autorización de usuarios.
- Lombok: Se utiliza para reducir el código boilerplate en las entidades y DTOs.

## Uso

- Utiliza herramientas como Postman para interactuar con los endpoints proporcionados por la API.
- Consulta los diferentes controladores (UserController, CustomerController, CarController, MotorcycleController) para
  conocer los endpoints disponibles y cómo interactuar con ellos.

## Contribuciones

Las contribuciones son bienvenidas. Si deseas contribuir a este proyecto, por favor crea un pull request describiendo
tus cambios.

## Autor

Desarrollado por [Steven Ricardo Quiñones](https://www.linkedin.com/in/steven-ricardo-qui%C3%B1ones-334a61266/)

---

# Documentación de Endpoints

A continuación se detallan los endpoints disponibles en la API de IMS para gestionar usuarios, clientes, vehículos,
roles y permisos.

## Autoridades

### Crear una lista de autoridades

- URL: POST /auth/authorities/save-all
- Descripción: Crea una lista de autoridades en el sistema
- Body: JSON con el nombre de cada autoridad.

 ```json
[
  {
    "authorityEnum": "MANAGE_USER"
  },
  {
    "authorityEnum": "MANAGE_VEHICLE"
  },
  {
    "authorityEnum": "MANAGE_INVENTORY"
  },
  {
    "authorityEnum": "AUDIT_INVENTORY"
  },
  {
    "authorityEnum": "MANAGE_PURCHASE"
  },
  {
    "authorityEnum": "APPROVE_PURCHASE"
  },
  {
    "authorityEnum": "PROCESS_REFUND"
  },
  {
    "authorityEnum": "MANAGE_SALE"
  },
  {
    "authorityEnum": "APPROVE_SALE"
  },
  {
    "authorityEnum": "PROCESS_RETURN"
  },
  {
    "authorityEnum": "MANAGE_REPORT"
  },
  {
    "authorityEnum": "MANAGE_MAINTENANCE"
  },
  {
    "authorityEnum": "BASIC_READ"
  },
  {
    "authorityEnum": "VIEW_DASHBOARD"
  },
  {
    "authorityEnum": "MANAGE_ROLES"
  },
  {
    "authorityEnum": "MANAGE_AUTHORITIES"
  }
]
 ```

### Obtener Autoridad por ID

- URL: GET /auth/authorities/{id}
- Descripción: Obtiene una autoridad por su ID.
- Respuesta Exitosa: Retorna la autoridad encontrada en formato DTO.
- Códigos de Estado:
    - **200 OK**: Si se encuentra la autoridad.
    - **404 Not Found**: Si no se encuentra la autoridad con el ID especificado.

### Listar Todas las Autoridades

- URL: GET /auth/authorities
- Descripción: Obtiene todas las autoridades registradas en el sistema.
- Respuesta Exitosa: Retorna una lista de autoridades en formato DTO.
- Códigos de Estado:
    - **200 OK**: Si se obtienen las autoridades correctamente.

## Roles

### Crear una lista de roles

- URL: POST /auth/roles/save-all
- Descripción: Crea una lista de roles en el sistema
- Body: JSON con el nombre de cada rol.

 ```json
[
  {
    "roleEnum": "ADMIN",
    "authorities": []
  },
  {
    "roleEnum": "USER",
    "authorities": []
  },
  {
    "roleEnum": "MANAGER",
    "authorities": []
  },
  {
    "roleEnum": "FINANCE",
    "authorities": []
  }
]
 ```

### Obtener Rol por ID

- URL: GET /auth/roles/{id}
- Descripción: Obtiene un rol por su ID.
- Respuesta Exitosa: Retorna el rol encontrado en formato DTO.
- Códigos de Estado:
    - **200 OK**: Si se encuentra el rol.
    - **404 Not Found**: Si no se encuentra el rol con el ID especificado.

### Listar Todos los Roles

- URL: GET /auth/roles
- Descripción: Obtiene todos los roles registrados en el sistema.
- Respuesta Exitosa: Retorna una lista de roles en formato DTO.
- Códigos de Estado:
    - **200 OK**: Si se obtienen los roles correctamente.

## Usuarios

### Crear Usuario

- URL: POST /api/users
- Descripción: Crea un nuevo usuario en el sistema.
- Body: JSON con los datos del usuario.

 ```json
 {
  "identificationCard": 1000000006,
  "name": "Alice",
  "lastName": "Wonderland",
  "address": "789 Wonderland St",
  "phone": 5556789012,
  "email": "alice.wonderland@example.com",
  "username": "alicewonder",
  "password": "securePassword"
}
 ```

- Respuesta Exitosa: Retorna el usuario creado en formato DTO.
- Códigos de Estado:
    - **201 Created**: Usuario creado exitosamente.
    - **400 Bad Request**: Si hay errores de validación en los datos proporcionados.

### Obtener Usuario por ID

- URL: GET /api/users/{id}
- Descripción: Obtiene un usuario por su ID.
- Respuesta Exitosa: Retorna el usuario encontrado en formato DTO.
- Códigos de Estado:
    - **200 OK**: Si se encuentra el usuario.
    - **404 Not Found**: Si no se encuentra el usuario con el ID especificado.

### Actualizar Usuario

- URL: PUT /api/users/{id}
- Descripción: Actualiza un usuario existente por su ID.
- Body: JSON con los datos actualizados del usuario.

```json
{
  "identificationCard": 1000000006,
  "name": "...",
  "lastName": "...",
  "address": "...",
  "phone": 1111,
  "email": "...@example.com",
  "username": "...",
  "password": "..."
}
```

- Respuesta Exitosa: Retorna el usuario actualizado en formato DTO.
- Códigos de Estado:
    - **200 OK**: Si se actualiza correctamente el usuario.
    - **404 Not Found**: Si no se encuentra el usuario con el ID especificado.

### Eliminar Usuario

- URL: DELETE /api/users/{id}
- Descripción: Elimina un usuario por su ID.
- Códigos de Estado:
    - **204 No Content**: Si se elimina el usuario exitosamente.
    - **404 Not Found**: Si no se encuentra el usuario con el ID especificado.

### Listar Todos los Usuarios

- URL: GET /api/users
- Descripción: Obtiene todos los usuarios registrados en el sistema.
- Respuesta Exitosa: Retorna una lista de usuarios en formato DTO.
- Códigos de Estado:
    - **200 OK**: Si se obtienen los usuarios correctamente.

## Clientes

### Crear Cliente

- URL: POST /api/customers
- Descripción: Crea un nuevo cliente en el sistema.
- Body: JSON con los datos del cliente.

```json
{
  "identificationCard": 1000000007,
  "name": "Bob",
  "lastName": "Herm",
  "address": "456 Builder St",
  "phone": 5557890123,
  "email": "bob.builder@example.com",
  "taxIdentificationNumber": 3000000001
}
```

- Respuesta Exitosa: Retorna el cliente creado en formato DTO.
- Códigos de Estado:
    - **201 Created**: Cliente creado exitosamente.
    - **400 Bad Request**: Si hay errores de validación en los datos proporcionados.

### Obtener Cliente por ID

- URL: GET /api/customers/{id}
- Descripción: Obtiene un cliente por su ID.
- Respuesta Exitosa: Retorna el cliente encontrado en formato DTO.
- Códigos de Estado:
    - **200 OK**: Si se encuentra el cliente.
    - **404 Not Found**: Si no se encuentra el cliente con el ID especificado.

### Actualizar Cliente

- URL: PUT /api/customers/{id}
- Descripción: Actualiza un cliente existente por su ID.
- Body: JSON con los datos actualizados del cliente.

```json
{
  "identificationCard": 1000000007,
  "name": "...",
  "lastName": "...",
  "address": "...",
  "phone": 111,
  "email": "...@example.com",
  "taxIdentificationNumber": 3000000001
}
```

- Respuesta Exitosa: Retorna el cliente actualizado en formato DTO.
- Códigos de Estado:
    - **200 OK**: Si se actualiza correctamente el cliente.
    - **404 Not Found**: Si no se encuentra el cliente con el ID especificado.

### Eliminar Cliente

- URL: DELETE /api/customers/{id}
- Descripción: Elimina un cliente por su ID.
- Códigos de Estado:
    - **204 No Content**: Si se elimina el cliente exitosamente.
    - **404 Not Found**: Si no se encuentra el cliente con el ID especificado.

### Listar Todos los Clientes

- URL: GET /api/customers
- Descripción: Obtiene todos los clientes registrados en el sistema.
- Respuesta Exitosa: Retorna una lista de clientes en formato DTO.
- Códigos de Estado:
    - **200 OK**: Si se obtienen los clientes correctamente.

## Automóviles

## Crear Automóvil

- URL: POST /api/cars
- Descripción: Crea un nuevo automóvil en el sistema.
- Body: JSON con los datos del automóvil.

```json
{
  "trademark": "Chevrolet",
  "model": "Impala",
  "passengerCapacity": "5",
  "modelLine": "LS",
  "plateNumber": "XYZ678",
  "transmissionType": "Automatic",
  "engineNumber": "ENG987654",
  "serialNumber": "SER987654",
  "chassisNumber": "CHS987654",
  "color": "Green",
  "registeredIn": "Ohio",
  "manufacturingYear": 2020,
  "mileage": 12000,
  "status": "Available",
  "purchasePrice": 20000.00,
  "salePrice": 25000.00,
  "image": "image6.jpg",
  "fuelType": "Gasoline",
  "customer": {
    // ID del customer ya existente en la base de datos
    "id": 2
  },
  "bodyType": "Sedan",
  "doorsNumber": 4
}
```

- Respuesta Exitosa: Retorna el automóvil creado en formato DTO.
- Códigos de Estado:
    - **201 Created**: Automóvil creado exitosamente.
    - **400 Bad Request**: Si hay errores de validación en los datos proporcionados.

### Obtener Automóvil por ID

- URL: GET /api/cars/{id}
- Descripción: Obtiene un automóvil por su ID.
- Respuesta Exitosa: Retorna el automóvil encontrado en formato DTO.
- Códigos de Estado:
    - **200 OK**: Si se encuentra el automóvil.
    - **404 Not Found**: Si no se encuentra el automóvil con el ID especificado.

### Actualizar Automóvil

- URL: PUT /api/cars/{id}
- Descripción: Actualiza un automóvil existente por su ID.
- Body: JSON con los datos actualizados del automóvil.

```json
{
  "trademark": "Chevrolet",
  "model": "Impala",
  "passengerCapacity": "5",
  "modelLine": "LS",
  "plateNumber": "XYZ678",
  "transmissionType": "Automatic",
  "engineNumber": "ENG987654",
  "serialNumber": "SER987654",
  "chassisNumber": "CHS987654",
  "color": "...",
  "registeredIn": "Ohio",
  "manufacturingYear": 2020,
  "mileage": 12000,
  "status": "...",
  "purchasePrice": 20000.00,
  "salePrice": 25000.00,
  "image": "image6.jpg",
  "fuelType": "Gasoline",
  "customer": {
    // ID del customer ya existente en la base de datos
    "id": 2
  },
  "bodyType": "Sedan",
  "doorsNumber": 4
}
```

- Respuesta Exitosa: Retorna el automóvil actualizado en formato DTO.
- Códigos de Estado:
    - **200 OK**: Si se actualiza correctamente el automóvil.
    - **404 Not Found**: Si no se encuentra el automóvil con el ID especificado.

### Eliminar Automóvil

- URL: DELETE /api/cars/{id}
- Descripción: Elimina un automóvil por su ID.
- Códigos de Estado:
    - **204 No Content**: Si se elimina el automóvil exitosamente.
    - **404 Not Found**: Si no se encuentra el automóvil con el ID especificado.

### Listar Todos los Automóviles

- URL: GET /api/cars
- Descripción: Obtiene todos los automóviles registrados en el sistema.
- Respuesta Exitosa: Retorna una lista de automóviles en formato DTO.
- Códigos de Estado:
    - **200 OK**: Si se obtienen los automóviles correctamente.

## Motocicletas

### Crear motocicleta

- URL: POST /api/motorcycles
- Descripción: Crea una nueva motocicleta en el sistema.
- Body: JSON con los datos de la motocicleta.

```json
{
  "trademark": "Yamaha",
  "model": "MT-07",
  "passengerCapacity": "2",
  "modelLine": "Standard",
  "plateNumber": "UVW123",
  "transmissionType": "Manual",
  "engineNumber": "ENG456789",
  "serialNumber": "SER456789",
  "chassisNumber": "CHS456789",
  "color": "Blue",
  "registeredIn": "California",
  "manufacturingYear": 2019,
  "mileage": 5000,
  "status": "Available",
  "purchasePrice": 7000.00,
  "salePrice": 9000.00,
  "image": "image7.jpg",
  "fuelType": "Gasoline",
  "customer": {
    // ID del customer ya existente en la base de datos
    "id": 2
  },
  "motorcycleType": "Naked"
}
```

- Respuesta Exitosa: Retorna la motocicleta creada en formato DTO.
- Códigos de Estado:
    - **201 Created**: Motocicleta creada exitosamente.
    - **400 Bad Request**: Si hay errores de validación en los datos proporcionados.

### Obtener Motocicleta por ID

- URL: GET /api/motorcycles/{id}
- Descripción: Obtiene una motocicleta por su ID.
- Respuesta Exitosa: Retorna la motocicleta encontrada en formato DTO.
- Códigos de Estado:
    - **200 OK**: Si se encuentra la motocicleta.
    - **404 Not Found**: Si no se encuentra la motocicleta con el ID especificado.

### Actualizar Motocicleta

- URL: PUT /api/motorcycles/{id}
- Descripción: Actualiza una motocicleta existente por su ID.
- Body: JSON con los datos actualizados de la motocicleta.

```json
{
  "trademark": "Yamaha",
  "model": "MT-07",
  "passengerCapacity": "2",
  "modelLine": "Standard",
  "plateNumber": "UVW123",
  "transmissionType": "Manual",
  "engineNumber": "ENG456789",
  "serialNumber": "SER456789",
  "chassisNumber": "CHS456789",
  "color": "...",
  "registeredIn": "California",
  "manufacturingYear": 2019,
  "mileage": 5000,
  "status": "...",
  "purchasePrice": 7000.00,
  "salePrice": 9000.00,
  "image": "image7.jpg",
  "fuelType": "Gasoline",
  "customer": {
    // ID del customer ya existente en la base de datos
    "id": 2
  },
  "motorcycleType": "Naked"
}
```

- Respuesta Exitosa: Retorna la motocicleta actualizada en formato DTO.
- Códigos de Estado:
    - **200 OK**: Si se actualiza correctamente la motocicleta.
    - **404 Not Found**: Si no se encuentra la motocicleta con el ID especificado.

### Eliminar Motocicleta

- URL: DELETE /api/motorcycles/{id}
- Descripción: Elimina una motocicleta por su ID.
- Códigos de Estado:
    - **204 No Content**: Si se elimina la motocicleta exitosamente.
    - **404 Not Found**: Si no se encuentra la motocicleta con el ID especificado.

### Listar Todas las Motocicletas

- URL: GET /api/motorcycles
- Descripción: Obtiene todas las motocicletas registradas en el sistema.
- Respuesta Exitosa: Retorna una lista de motocicletas en formato DTO.
- Códigos de Estado:
    - **200 OK**: Si se obtienen las motocicletas correctamente.