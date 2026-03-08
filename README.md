# Foro Hub API 🚀

Bienvenido al proyecto **Foro Hub**, una API REST robusta desarrollada con **Java** y **Spring Boot** para la gestión de un foro de discusión académica. Este proyecto permite la interacción con tópicos de consulta, siguiendo las mejores prácticas de desarrollo y Clean Code.

## 📋 Características

* **CRUD de Tópicos**: Gestión integral de temas de discusión (Crear, Leer, Actualizar y Eliminar).
* **Validaciones de Negocio**: Control de integridad para asegurar que cada tópico esté asociado a un autor y curso existentes en la base de datos.
* **Borrado Lógico**: Implementación de `eliminacionLogica()` para mantener la integridad de los datos históricos sin eliminarlos físicamente.
* **Paginación y Ordenamiento**: Consultas optimizadas utilizando `Pageable` para manejar grandes volúmenes de datos.

## 🛠️ Tecnologías Utilizadas

* **Java 17**
* **Spring Boot 3**
* **Spring Data JPA** (Hibernate)
* **MySQL** (Base de datos relacional)
* **Flyway**: Control de versiones de la base de datos (Migraciones SQL).
* **Lombok**: Reducción de código boilerplate mediante anotaciones como `@Getter` y `@NoArgsConstructor`.
* **Jakarta Validation**: Reglas de validación para los datos de entrada.

## 🏗️ Estructura del Proyecto



El proyecto está organizado siguiendo el principio de separación de responsabilidades:
* **Controller**: Clases como `TopicoController` que gestionan los endpoints y las respuestas HTTP.
* **Domain**: Contiene las entidades JPA (`Topico`), repositorios y registros DTO para la transferencia de datos.
* **Service**: Capa de lógica de negocio, como `RegistroDeTopicos`, que orquesta las reglas antes de la persistencia.

## 🛣️ Endpoints Principales

| Método | Endpoint | Acción | Regla de Negocio |
| :--- | :--- | :--- | :--- |
| **POST** | `/topicos` | Registrar Tópico | Valida existencia de autor y curso. |
| **GET** | `/topicos` | Listar Activos | Devuelve tópicos con estado `true` de forma paginada. |
| **GET** | `/topicos/{id}` | Detalle de Tópico | Consulta obligatoria por ID para ver mensaje y autor. |
| **PUT** | `/topicos/{id}` | Actualizar | Permite modificar título y mensaje actualizando la fecha. |
| **DELETE** | `/topicos/{id}` | Eliminar | Cambia el estatus a `false` (eliminación lógica). |

## 👤 Autor

* **Nombre:** Jose Alberto
* **GitHub:** [Jvm124](https://github.com/Jvm124)
* **Rol:** Desarrollador Web y Estudiante de Ingeniería de Sistemas de Perú

---
Desarrollado como parte del desafío de Alura Latam.