# Learning Platform Validation - Documentación de Implementación

## Descripción del Proyecto

Este proyecto implementa una **plataforma de aprendizaje en línea** utilizando Spring Boot 3.3.5 con arquitectura de capas. El sistema gestiona cursos, usuarios, inscripciones y evaluaciones a través de microservicios REST conectados a una base de datos Oracle.

---

## 🚀 Quick Start

**Levanta el proyecto y Oracle XE en un comando:**

```bash
docker-compose up
```

✅ Aplicación en: `http://localhost:8080`  
✅ Oracle en: `localhost:1521` (usuario: `system`, contraseña: `oracle123`)

---

## Arquitectura de Capas

El proyecto sigue una arquitectura de **4 capas** que garantiza separación de responsabilidades:

```
Controller (Endpoints REST)
    ↓
Service (Lógica de Negocio)
    ↓
Repository (Acceso a Datos)
    ↓
Model (Entidades JPA)
    ↓
Base de Datos
```

### 1. **Model (Capa de Datos)**
Contiene las entidades JPA con validaciones y relaciones:
- `Usuario.java` - Usuarios del sistema (estudiantes y profesores)
- `Curso.java` - Cursos disponibles en la plataforma
- `Inscripcion.java` - Registros de estudiantes en cursos
- `Evaluacion.java` - Evaluaciones de cursos

**Estado:** ✅ **100% Implementado**

### 2. **Repository (Capa de Acceso a Datos)**
Interfaces que extienden `JpaRepository` para operaciones CRUD:
- `UsuarioRepository.java` - Gestiona consultas de usuarios
- `CursoRepository.java` - Gestiona consultas de cursos
- `InscripcionRepository.java` - Gestiona consultas de inscripciones
- `EvaluacionRepository.java` - Gestiona consultas de evaluaciones

**Estado:** ✅ **100% Implementado**

### 3. **Service (Capa de Lógica de Negocio)**

#### Interfaces
Define los contratos de servicios:
- `IUsuarioService.java`
- `ICursoService.java`
- `IInscripcionService.java`
- `IEvaluacionService.java`

#### Implementaciones
Implementan la lógica de negocio inyectando repositorios:
- `UsuarioServiceImpl.java`
- `CursoServiceImpl.java`
- `InscripcionServiceImpl.java`
- `EvaluacionServiceImpl.java`

**Estado:** ✅ **100% Implementado**

**Métodos implementados:**

| Servicio | Métodos |
|----------|---------|
| **IUsuarioService** | `obtenerTodos()`, `obtenerPorId()`, `crear()`, `actualizar()`, `eliminar()` |
| **ICursoService** | `obtenerTodos()`, `obtenerPorId()`, `crear()`, `actualizar()`, `eliminar()` |
| **IInscripcionService** | `obtenerPorCurso()`, `crear()`, `eliminar()` |
| **IEvaluacionService** | `obtenerTodos()`, `obtenerPorCurso()`, `crear()`, `actualizar()` |

### 4. **Controller (Capa de Presentación - REST)**
Expone endpoints REST que conectan con los servicios:
- `UsuarioController.java` - Endpoints de usuarios
- `CursoController.java` - Endpoints de cursos
- `InscripcionController.java` - Endpoints de inscripciones
- `EvaluacionController.java` - Endpoints de evaluaciones

**Estado:** ✅ **100% Implementado**

---

## Endpoints REST Disponibles

### 👥 Usuarios (`/api/usuarios`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/usuarios` | Obtener todos los usuarios |
| GET | `/api/usuarios/{id}` | Obtener usuario por ID |
| POST | `/api/usuarios` | Crear nuevo usuario |
| PUT | `/api/usuarios/{id}` | Actualizar usuario |
| DELETE | `/api/usuarios/{id}` | Eliminar usuario |

**Ejemplo de POST:**
```json
{
  "nombre": "Juan Pérez",
  "correo": "juan@example.com",
  "contrasenia": "password123",
  "rol": "estudiante"
}
```

### 📚 Cursos (`/api/cursos`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/cursos` | Obtener todos los cursos |
| GET | `/api/cursos/{id}` | Obtener curso por ID |
| POST | `/api/cursos` | Crear nuevo curso |
| PUT | `/api/cursos/{id}` | Actualizar curso |
| DELETE | `/api/cursos/{id}` | Eliminar curso |

**Ejemplo de POST:**
```json
{
  "nombre": "Programación en Java",
  "descripcion": "Curso introductorio de Java",
  "profesor": {
    "id": 1
  }
}
```

### 📝 Inscripciones (`/api/inscripciones`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/inscripciones/curso/{cursoId}` | Obtener inscripciones por curso |
| POST | `/api/inscripciones` | Registrar nueva inscripción |
| DELETE | `/api/inscripciones/{id}` | Eliminar inscripción |

**Ejemplo de POST:**
```json
{
  "curso": {
    "id": 1
  },
  "estudiante": {
    "id": 2
  }
}
```

### ⭐ Evaluaciones (`/api/evaluaciones`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/evaluaciones` | Obtener todas las evaluaciones |
| GET | `/api/evaluaciones/curso/{cursoId}` | Obtener evaluaciones por curso |
| POST | `/api/evaluaciones` | Crear nueva evaluación |
| PUT | `/api/evaluaciones/{id}` | Actualizar evaluación |

**Ejemplo de POST:**
```json
{
  "nombre": "Examen Final",
  "curso": {
    "id": 1
  },
  "puntajeMaximo": 100,
  "fechaAplicacion": "2026-06-15T10:00:00"
}
```

---

## Configuración

### 1. Base de Datos Oracle
El proyecto está configurado para usar Oracle XE en Docker. La configuración se encuentra en `application.properties` con variables de entorno que se pueden sobrescribir.

### 2. Generar base de datos
El archivo `application.properties` está configurado con `spring.jpa.hibernate.ddl-auto=update`, lo que genera automáticamente las tablas al iniciar la aplicación.

---

## Cómo Ejecutar

### ⚡ Con Docker Compose (Recomendado)

**Requisitos previos:**
- Docker instalado
- Docker Compose instalado

**Comando para levantar el proyecto:**
```bash
docker-compose up
```

Esto levantará:
- 🐳 Contenedor Oracle XE en `localhost:1521` (usuario: `system`, contraseña: `oracle123`)
- 🚀 Aplicación Spring Boot en `http://localhost:8080`

**Para detener:**
```bash
docker-compose down
```

**Para detener y limpiar volúmenes:**
```bash
docker-compose down -v
```

---

### 🔧 Alternativa: Sin Docker (Requiere Java 21+ y Maven)

**Opción 1: Ejecutar con Maven**
```bash
./mvnw spring-boot:run
```

**Opción 2: Compilar y ejecutar**
```bash
./mvnw clean package
java -jar target/LearningPlatformValidation-0.0.1-SNAPSHOT.jar
```

> **Nota:** Requiere Oracle Database XE instalado localmente en `localhost:1521`

---

## Testing con Postman

### 1. Crear un Usuario
```
POST http://localhost:8080/api/usuarios
Content-Type: application/json

{
  "nombre": "Carlos García",
  "correo": "carlos@example.com",
  "contrasenia": "securePass123",
  "rol": "profesor"
}
```

### 2. Obtener todos los usuarios
```
GET http://localhost:8080/api/usuarios
```

### 3. Crear un Curso
```
POST http://localhost:8080/api/cursos
Content-Type: application/json

{
  "nombre": "Desarrollo Web",
  "descripcion": "Aprende HTML, CSS y JavaScript",
  "profesor": {
    "id": 1
  }
}
```

### 4. Registrar una Inscripción
```
POST http://localhost:8080/api/inscripciones
Content-Type: application/json

{
  "curso": {
    "id": 1
  },
  "estudiante": {
    "id": 2
  }
}
```

### 5. Crear una Evaluación
```
POST http://localhost:8080/api/evaluaciones
Content-Type: application/json

{
  "nombre": "Quiz 1",
  "curso": {
    "id": 1
  },
  "puntajeMaximo": 50,
  "fechaAplicacion": "2026-05-20T14:30:00"
}
```

---

## Validaciones Implementadas

### Usuario
- ✅ Correo único (no se permiten duplicados)
- ✅ Correo válido (formato email)
- ✅ Rol válido ("estudiante" o "profesor")
- ✅ Contraseña mínimo 8 caracteres
- ✅ Nombre entre 2 y 150 caracteres

### Curso
- ✅ Nombre requerido (2-150 caracteres)
- ✅ Descripción máximo 500 caracteres
- ✅ Profesor asignado requerido

### Inscripción
- ✅ No se permiten inscripciones duplicadas (mismo estudiante en mismo curso)
- ✅ Fecha de inscripción se asigna automáticamente

### Evaluación
- ✅ Nombre requerido (2-150 caracteres)
- ✅ Puntaje máximo entre 1 y 100
- ✅ Fecha de aplicación debe ser futura

---

## Manejo de Errores

Los servicios implementan validaciones y manejo de excepciones:

| Situación | Respuesta HTTP |
|-----------|----------------|
| Recurso no encontrado | `404 Not Found` |
| Datos inválidos | `400 Bad Request` |
| Creación exitosa | `201 Created` |
| Actualización exitosa | `200 OK` |
| Eliminación exitosa | `204 No Content` |

---

## Estructura de Directorios

```
src/main/java/com/duoc/LearningPlatformValidation/
├── model/
│   ├── Usuario.java
│   ├── Curso.java
│   ├── Inscripcion.java
│   └── Evaluacion.java
├── repository/
│   ├── UsuarioRepository.java
│   ├── CursoRepository.java
│   ├── InscripcionRepository.java
│   └── EvaluacionRepository.java
├── service/
│   ├── IUsuarioService.java
│   ├── ICursoService.java
│   ├── IInscripcionService.java
│   ├── IEvaluacionService.java
│   └── impl/
│       ├── UsuarioServiceImpl.java
│       ├── CursoServiceImpl.java
│       ├── InscripcionServiceImpl.java
│       └── EvaluacionServiceImpl.java
├── controller/
│   ├── UsuarioController.java
│   ├── CursoController.java
│   ├── InscripcionController.java
│   └── EvaluacionController.java
└── LearningPlatformValidationApplication.java

src/main/resources/
└── application.properties
```

---

## Validación de Capas

✅ **Model**: Todas las entidades JPA implementadas con validaciones  
✅ **Repository**: Todos los repositorios extendiendo JpaRepository  
✅ **Service**: Interfaces e implementaciones con lógica de negocio  
✅ **Controller**: Endpoints REST conectados a servicios  

---

## Notas Importantes

1. **Base de Datos**: Asegúrate de tener Oracle XE instalado y ejecutándose
2. **Dependencias**: El proyecto usa Spring Data JPA, Spring Web y Jakarta Validation
3. **CORS**: Habilitado para todas las direcciones (`origins = "*"`)
4. **Validación**: Utiliza anotaciones de Jakarta Validation en modelos

---

## Tecnologías Utilizadas

- **Spring Boot**: 3.3.5
- **Spring Data JPA**: Acceso a datos
- **Hibernate**: ORM
- **Oracle Database**: Base de datos
- **Maven**: Build tool
- **Jakarta Validation**: Validaciones
- **Lombok**: Reducción de boilerplate

---

## Autor

Proyecto desarrollado como ejercicio de validación de componentes Spring en arquitectura de microservicios.
