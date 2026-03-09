# Foro Hub API 🚀

Bienvenido al proyecto **Foro Hub**, una API REST robusta desarrollada con **Java** y **Spring Boot** para la gestión de un foro de discusión académica. Este proyecto permite la interacción con tópicos de consulta, siguiendo las mejores prácticas de desarrollo, Clean Code y principios SOLID.

## 📋 Características

* **CRUD Completo**: Gestión integral de Tópicos, Usuarios, Cursos, Respuestas y Perfiles.
* **Seguridad con JWT**: Autenticación Stateless mediante JSON Web Tokens para proteger los recursos de la API.
* **Control de Acceso (RBAC)**: Restricción de endpoints basada en perfiles (ESTUDIANTE, MODERADOR, ADMINISTRADOR).
* **Validaciones de Negocio**: Control estricto de integridad para asegurar que cada entidad cumpla con las reglas del dominio antes de la persistencia.
* **Borrado Lógico**: Implementación de `eliminacionLogica()` para mantener la integridad referencial y datos históricos.
* **Paginación y Ordenamiento**: Consultas optimizadas utilizando `Pageable` para manejar eficientemente grandes volúmenes de datos.

## 🛠️ Tecnologías Utilizadas

* **Java 21**: Utilizando las últimas características del lenguaje.
* **Spring Boot 3**: Framework base para la creación de microservicios.
* **Spring Security**: Configuración de filtros personalizados para la validación de tokens.
* **Spring Data JPA**: Abstracción de persistencia con Hibernate.
* **MySQL**: Base de datos relacional para el almacenamiento persistente.
* **Flyway**: Control de versiones de la base de datos mediante migraciones SQL.
* **Lombok**: Reducción de código boilerplate para entidades y DTOs.
* **Jakarta Validation**: Reglas de validación para asegurar la calidad de los datos de entrada.

## 🏗️ Estructura del Proyecto

El proyecto se organiza siguiendo el principio de separación de responsabilidades:
* **Controller**: Clases que gestionan los endpoints y las respuestas HTTP (ej. `TopicoController`, `UsuarioController`, `CursoController`).
* **Domain**: Contiene las entidades JPA, repositorios y Records (DTOs) para la transferencia de datos.
* **Infra**: Configuración de infraestructura, seguridad (`SecurityFilter`, `TokenService`) y manejo global de excepciones.
* **Service**: Capa de lógica de negocio (ej. `RegistroDeTopicos`, `RegistroDeUsuarios`, `RegistroDeCursos`).



## 🛣️ Endpoints Principales

### Autenticación
| Método | Endpoint | Acción | Acceso |
| :--- | :--- | :--- | :--- |
| **POST** | `/login` | Iniciar sesión y obtener JWT | Público |

### Tópicos
| Método | Endpoint | Acción | Acceso |
| :--- | :--- | :--- | :--- |
| **POST** | `/topicos` | Registrar Tópico | Autenticado |
| **GET** | `/topicos` | Listar Tópicos Activos | Autenticado |
| **PUT** | `/topicos/{id}` | Actualizar Tópico | Autor/Admin |
| **DELETE** | `/topicos/{id}` | Borrado Lógico | Moderador/Admin |

### Usuarios y Cursos
| Método | Endpoint | Acción | Acceso |
| :--- | :--- | :--- | :--- |
| **POST** | `/usuarios` | Registrar nuevo usuario | ADMINISTRADOR |
| **GET** | `/usuarios` | Listar usuarios activos | MODERADOR/ADMIN |
| **POST** | `/cursos` | Registrar nuevo curso | ADMINISTRADOR |



## 🚀 Cómo empezar

1.  **Clonar el repositorio**:
    ```bash
    git clone [https://github.com/Jvm124/foro.git](https://github.com/Jvm124/foro.git)
    ```
2.  **Configurar base de datos**: Crea la base de datos `foro_api` en tu servidor MySQL local.
3.  **Configurar `application.properties`**:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/foro_api?serverTimezone=America/Lima
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contrasenia
    api.security.token.secret=${JWT_SECRET:clave_secreta_para_desarrollo}
    ```
4.  **Ejecutar la aplicación**: Flyway aplicará automáticamente las migraciones necesarias para crear las tablas y los perfiles base (ESTUDIANTE, INSTRUCTOR, MODERADOR, ADMINISTRADOR).

## 👤 Autor

* **Nombre:** Jose Alberto
* **GitHub:** [Jvm124](https://github.com/Jvm124)
* **Rol:** Desarrollador Web y Estudiante de Ingeniería de Sistemas de Perú

---
Desarrollado como parte del desafío de **Alura Latam**.