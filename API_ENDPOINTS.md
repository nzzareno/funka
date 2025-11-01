# FUNKA - Game Store API Endpoints

## 📋 Documentación Completa de Endpoints

### Base URL: `http://localhost:8081`

---

## 🎮 GAMES ENDPOINTS

### **GET /api/games**
- **Descripción:** Obtener lista paginada de juegos de PC
- **Parámetros:**
  - `page` (opcional, default: 1) - Número de página
- **Ejemplo:** `GET /api/games?page=2`
- **Respuesta:** `GameListDTO` con lista de juegos

### **GET /api/games/search**
- **Descripción:** Buscar juegos por término
- **Parámetros:**
  - `query` (opcional) - Término de búsqueda
  - `page` (opcional, default: 1) - Número de página
- **Ejemplo:** `GET /api/games/search?query=minecraft&page=1`
- **Respuesta:** `GameListDTO` con resultados de búsqueda

### **GET /api/games/{id}**
- **Descripción:** Obtener detalles completos de un juego
- **Parámetros:**
  - `id` (path) - ID del juego
- **Ejemplo:** `GET /api/games/3498`
- **Respuesta:** `GameDetailDTO` con información completa

### **GET /api/games/{id}/screenshots**
- **Descripción:** Obtener screenshots de un juego
- **Parámetros:**
  - `id` (path) - ID del juego
- **Ejemplo:** `GET /api/games/3498/screenshots`
- **Respuesta:** Lista de `ScreenshotDTO`

### **GET /api/games/genre/{genreId}**
- **Descripción:** Obtener juegos por género específico
- **Parámetros:**
  - `genreId` (path) - ID del género
  - `page` (opcional, default: 1) - Número de página
- **Ejemplo:** `GET /api/games/genre/4?page=1`
- **Respuesta:** `GameListDTO` filtrado por género

### **GET /api/games/advanced-search**
- **Descripción:** Búsqueda avanzada con múltiples filtros
- **Parámetros:**
  - `search` (opcional) - Término de búsqueda
  - `genres` (opcional) - IDs de géneros separados por coma
  - `stores` (opcional) - IDs de tiendas separados por coma
  - `ordering` (opcional) - Criterio de ordenamiento
  - `page` (opcional, default: 1) - Número de página
- **Ejemplo:** `GET /api/games/advanced-search?search=action&genres=4,5&ordering=-rating&page=1`
- **Respuesta:** `GameListDTO` con resultados filtrados

---

## 🏷️ GENRES ENDPOINTS

### **GET /api/genres**
- **Descripción:** Obtener todos los géneros disponibles
- **Ejemplo:** `GET /api/genres`
- **Respuesta:** Lista de `GenreDTO`

### **GET /api/genres/{id}**
- **Descripción:** Obtener información de un género específico
- **Parámetros:**
  - `id` (path) - ID del género
- **Ejemplo:** `GET /api/genres/4`
- **Respuesta:** `GenreDTO`

---

## 🏪 STORES ENDPOINTS

### **GET /api/stores**
- **Descripción:** Obtener todas las tiendas disponibles
- **Ejemplo:** `GET /api/stores`
- **Respuesta:** Lista de `StoreDTO`

### **GET /api/stores/pc**
- **Descripción:** Obtener tiendas específicas para PC
- **Ejemplo:** `GET /api/stores/pc`
- **Respuesta:** Lista de `StoreDTO` filtrada para PC

### **GET /api/stores/{id}**
- **Descripción:** Obtener información de una tienda específica
- **Parámetros:**
  - `id` (path) - ID de la tienda
- **Ejemplo:** `GET /api/stores/1`
- **Respuesta:** `StoreDTO`

---

## 🏷️ TAGS ENDPOINTS

### **GET /api/tags**
- **Descripción:** Obtener todos los tags disponibles
- **Ejemplo:** `GET /api/tags`
- **Respuesta:** Lista de `TagDTO`

### **GET /api/tags/popular**
- **Descripción:** Obtener tags más populares
- **Ejemplo:** `GET /api/tags/popular`
- **Respuesta:** Lista de `TagDTO` ordenada por popularidad

### **GET /api/tags/search**
- **Descripción:** Buscar tags por término
- **Parámetros:**
  - `query` (requerido) - Término de búsqueda
- **Ejemplo:** `GET /api/tags/search?query=multiplayer`
- **Respuesta:** Lista de `TagDTO` que coinciden con la búsqueda

---

## 👨‍💻 DEVELOPERS ENDPOINTS

### **GET /api/developers**
- **Descripción:** Obtener todos los desarrolladores
- **Ejemplo:** `GET /api/developers`
- **Respuesta:** Lista de `DeveloperDTO`

### **GET /api/developers/popular**
- **Descripción:** Obtener desarrolladores más populares
- **Ejemplo:** `GET /api/developers/popular`
- **Respuesta:** Lista de `DeveloperDTO` ordenada por popularidad

### **GET /api/developers/search**
- **Descripción:** Buscar desarrolladores por término
- **Parámetros:**
  - `query` (requerido) - Término de búsqueda
- **Ejemplo:** `GET /api/developers/search?query=rockstar`
- **Respuesta:** Lista de `DeveloperDTO` que coinciden con la búsqueda

### **GET /api/developers/{id}**
- **Descripción:** Obtener información de un desarrollador específico
- **Parámetros:**
  - `id` (path) - ID del desarrollador
- **Ejemplo:** `GET /api/developers/3612`
- **Respuesta:** `DeveloperDTO`

---

## 🏢 PUBLISHERS ENDPOINTS

### **GET /api/publishers**
- **Descripción:** Obtener todos los editores/publicadores
- **Ejemplo:** `GET /api/publishers`
- **Respuesta:** Lista de `PublisherDTO`

### **GET /api/publishers/popular**
- **Descripción:** Obtener editores más populares
- **Ejemplo:** `GET /api/publishers/popular`
- **Respuesta:** Lista de `PublisherDTO` ordenada por popularidad

### **GET /api/publishers/search**
- **Descripción:** Buscar editores por término
- **Parámetros:**
  - `query` (requerido) - Término de búsqueda
- **Ejemplo:** `GET /api/publishers/search?query=ubisoft`
- **Respuesta:** Lista de `PublisherDTO` que coinciden con la búsqueda

### **GET /api/publishers/{id}**
- **Descripción:** Obtener información de un editor específico
- **Parámetros:**
  - `id` (path) - ID del editor
- **Ejemplo:** `GET /api/publishers/354`
- **Respuesta:** `PublisherDTO`

---

## 📊 RESUMEN DE ENDPOINTS

### **Total de Endpoints:** 18

| Controlador | Endpoints | Funcionalidades |
|-------------|-----------|-----------------|
| GameController | 6 | Lista, búsqueda, detalles, screenshots, filtros |
| GenreController | 2 | Lista y detalles de géneros |
| StoreController | 3 | Lista general, PC específico, detalles |
| TagController | 3 | Lista, populares, búsqueda |
| DeveloperController | 4 | Lista, populares, búsqueda, detalles |
| PublisherController | 4 | Lista, populares, búsqueda, detalles |

---

## 🔧 CONFIGURACIÓN CORS

- **Orígenes permitidos:** `http://localhost:4200`, `http://127.0.0.1:4200`
- **Métodos:** GET, POST, PUT, DELETE, OPTIONS
- **Headers:** Todos los headers permitidos

---

## 📝 NOTAS IMPORTANTES

1. **Filtrado automático:** Todos los juegos son filtrados para mostrar solo juegos de PC
2. **Imágenes requeridas:** Solo se devuelven juegos que tengan imágenes
3. **Paginación:** Tamaño de página por defecto: 20 elementos
4. **Ordenamiento por defecto:** 
   - Juegos generales: `-released` (más recientes primero)
   - Búsquedas: `-rating` (mejor rating primero)
5. **Manejo de errores:** Respuestas 404 para recursos no encontrados, 400 para parámetros inválidos

---

## 🚀 EJEMPLOS DE USO PARA FRONTEND

```typescript
// Obtener juegos para la página principal
GET /api/games?page=1

// Buscar juegos
GET /api/games/search?query=minecraft

// Obtener géneros para filtros
GET /api/genres

// Búsqueda avanzada
GET /api/games/advanced-search?genres=4,5&stores=1,3&ordering=-rating

// Detalles de un juego específico
GET /api/games/3498
```

---

*Última actualización: Noviembre 2025*
*Backend: Spring Boot 3.5.7*
*API Externa: RAWG Video Games Database*
