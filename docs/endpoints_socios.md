
# 📘 Documentación de la API - Socios Club Teclas Unidos

Este documento describe los endpoints disponibles en el microservicio de **Socios**, incluyendo ejemplos de request/response, validaciones y posibles errores.

---

## 📌 Endpoints disponibles

### 🔍 GET `/socios`

**Descripción:** Lista todos los socios, con posibilidad de aplicar filtros opcionales.

**Parámetros opcionales:**

| Nombre     | Tipo    | Descripción                            |
|------------|---------|----------------------------------------|
| apellido   | string  | Filtrar por apellido del socio         |
| edad       | integer | Filtrar por edad exacta del socio      |

**Ejemplo de Request:**

```
GET /socios?apellido=Gomez&edad=30
```

**Posibles respuestas:**

- `200 OK`: Lista de socios que coinciden con los filtros.
- `[]` si no hay coincidencias.

---

### 🔍 GET `/socios/{dni}`

**Descripción:** Obtiene un socio por su DNI.

**Ejemplo:**

```
GET /socios/12345678
```

**Respuestas posibles:**

- `200 OK`: Socio encontrado
- `404 Not Found`: No existe socio con ese DNI

---

### 🆕 POST `/socios`

**Descripción:** Crea un nuevo socio.

**Request Body (válido):**

```json
{
  "dni": "33445566",
  "apellido": "Ramirez",
  "nombre": "Carla",
  "edad": 31,
  "fechaNacimiento": "1992-04-22",
  "direccion": "Calle Siempre Viva 123",
  "telefono": "123456789"
}
```

**Respuestas posibles:**

- `201 Created`: Socio creado correctamente
- `400 Bad Request`: Algún dato inválido (ver errores comunes más abajo)

---

### ✏️ PUT `/socios/{dni}`

**Descripción:** Actualiza los datos de un socio identificado por su DNI.

**Request Body (válido):**

```json
{
  "apellido": "Ramirez",
  "nombre": "Carla",
  "edad": 32,
  "fechaNacimiento": "1992-04-22",
  "direccion": "Nueva dirección",
  "telefono": "111222333"
}
```

**Respuestas:**

- `200 OK`: Datos actualizados
- `404 Not Found`: Socio con ese DNI no existe
- `400 Bad Request`: Error en los datos ingresados

---

### 🗑️ DELETE `/socios/{dni}`

**Descripción:** Elimina un socio del sistema usando su DNI.

**Respuesta:**

- `204 No Content`: Eliminado correctamente
- `404 Not Found`: No existe el socio

---

## ⚠️ Validaciones y errores comunes

La entidad `Socio` tiene las siguientes validaciones:

| Campo            | Reglas de Validación                                                                 |
|------------------|---------------------------------------------------------------------------------------|
| `dni`            | Obligatorio, entre 7 y 8 dígitos, solo números, sin puntos ni espacios               |
| `apellido`       | Obligatorio, no puede estar vacío                                                    |
| `nombre`         | Obligatorio, no puede estar vacío                                                    |
| `edad`           | Obligatorio, entre 1 y 99                                                            |
| `fechaNacimiento`| Obligatorio, en formato `yyyy-MM-dd`, debe ser una fecha pasada                     |
| `direccion`      | Obligatoria                                                                          |
| `telefono`       | Obligatorio                                                                          |

**Ejemplo de Error 400 (campo faltante):**

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "El DNI es obligatorio"
}
```

---

## 🧪 Sugerencias de pruebas en Postman

- ✅ Probar GET `/socios` sin filtros y con filtros (apellido, edad, ambos)
- ✅ POST con socio válido y con campos faltantes (verifica errores 400)
- ✅ GET `/socios/{dni}` con un socio que exista y que no
- ✅ PUT `/socios/{dni}` cambiando datos válidamente
- ✅ DELETE `/socios/{dni}` para verificar eliminación

---

> 📂 Este archivo puede ir en `/docs/endpoints_socios.md` o directamente como README en una carpeta `/api-docs` de tu repo.

¡Listo para subir a GitHub! 🚀
