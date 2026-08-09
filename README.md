# Taskflow API

Proyecto de aprendizaje para entender cómo funciona una API REST con autenticación, construida con Spring Boot y Kotlin.

El objetivo fue entender la arquitectura backend que consumen las apps Android — controllers, services, repositories, seguridad con JWT, y cómo se conecta todo.

## Stack

- **Kotlin** + **Spring Boot 3**
- **Spring Security** + **JWT**
- **Spring Data JPA** + **Hibernate**
- **PostgreSQL**

## Arquitectura

```
Controller → Service → Repository → Database
```

Cada request pasa por un `JwtFilter` antes de llegar a cualquier controller. Los únicos endpoints públicos son `/auth/register` y `/auth/login` — todo lo demás requiere token válido.

---

## Requisitos

- [Docker Desktop](https://www.docker.com/products/docker-desktop/)

## Correr el proyecto

```bash
docker-compose up
```

La API queda disponible en `http://localhost:8080`

---

## Cómo usarla

### 1. Registrarse

```http
POST http://localhost:8080/auth/register
Content-Type: application/json

{
  "email": "ejemplo@mail.com",
  "password": "1234",
  "name": "Facundo"
}
```

Respuesta:
```json
{
  "token": "eyJhbGci..."
}
```

Guardá ese token — lo vas a necesitar en todos los requests siguientes.

---

### 2. Crear una tarea

```http
POST http://localhost:8080/tasks
Authorization: Bearer eyJhbGci...
Content-Type: application/json

{
  "title": "Spring Boot",
  "description": "backend",
  "user": { "id": 1 },
  "category": null
}
```

---

### 3. Ver tus tareas

```http
GET http://localhost:8080/tasks?userId=1
Authorization: Bearer eyJhbGci...
```

Respuesta:
```json
[
  {
    "id": 1,
    "title": "Spring Boot",
    "description": "backend",
    "completed": false,
    "createdAt": "2026-08-09T16:08:52",
    "user": {
      "id": 1,
      "email": "ejemplo@mail.com",
      "name": "Facundo"
    },
    "category": null
  }
]
```

> Todos los endpoints excepto `/auth/**` requieren el header `Authorization: Bearer <token>`. Sin él, la API devuelve 403.

---

## Endpoints

### Auth
| Método | Endpoint | Requiere token |
|--------|----------|----------------|
| POST | `/auth/register` | No |
| POST | `/auth/login` | No |

### Tareas
| Método | Endpoint | Requiere token |
|--------|----------|----------------|
| GET | `/tasks?userId=1` | Sí |
| GET | `/tasks/filter?userId=1&completed=false` | Sí |
| POST | `/tasks` | Sí |
| PUT | `/tasks/{id}` | Sí |
| PATCH | `/tasks/{id}/complete` | Sí |
| DELETE | `/tasks/{id}` | Sí |

### Categorías
| Método | Endpoint | Requiere token |
|--------|----------|----------------|
| GET | `/categories?userId=1` | Sí |
| POST | `/categories` | Sí |
| DELETE | `/categories/{id}` | Sí |

---

## Seguridad

- Passwords hasheadas con **BCrypt**
- Autenticación stateless con **JWT** (expiración 24hs)
- El campo `password` no se expone en ninguna respuesta
