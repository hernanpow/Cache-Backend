# Cache Backend para Servicio Meteorológico

## Español

Este proyecto implementa un servicio de caché para datos meteorológicos, utilizando la API de Tomorrow.io. El servicio almacena información meteorológica de ubicaciones clave y la sirve a través de una API REST.

### Tecnologías Utilizadas

- **Kotlin**: Lenguaje de programación principal, ofreciendo una sintaxis concisa y potentes características.
- **Ktor**: Framework para construir servidores asíncronos, utilizado para crear la API REST.
- **Redis**: Base de datos en memoria, empleada como caché para almacenar los datos meteorológicos.
- **Tomorrow.io API**: Fuente externa de datos meteorológicos.

### Características Principales

1. **Caché Periódico**: Actualiza los datos meteorológicos cada 5 minutos para las ubicaciones especificadas.
2. **API REST**: Proporciona un endpoint para consultar datos meteorológicos de ubicaciones específicas.
3. **Manejo de Errores**: Implementa una lógica de reintento para las llamadas a la API externa y registra los errores en Redis.
4. **Búsqueda Flexible**: Permite búsquedas parciales de ubicaciones.


### Despliegue Local

Para ejecutar el servicio localmente, sigue estos pasos:

1. **Clonar el repositorio**:
   git clone [URL_DEL_REPOSITORIO]
   cd [NOMBRE_DEL_DIRECTORIO]
   2. **Configurar variables de entorno**:
      Edita el archivo `src/main/resources/application.conf` y reemplaza las variables con tus propios valores:
      ktor {
      deployment {
        port = 8080
        }
      }
      redis {
        url = ${YOUR_REDIS_URL}
        }

    weather {
        api {
            key = ${YOUR_API_KEY}
        }
   3. **Instalar dependencias y compilar**:
      ./gradlew build
   4. **Ejecutar la aplicación**:
      ./gradlew run
   
   El servicio estará disponible en `http://localhost:8080`.


### Uso

Ubicaciones para busqueda -> Santiago,Zurich,Auckland,Sidney,Londres,Georgia

El servicio está desplegado y puede ser accedido en:

[https://cache-backend-production.up.railway.app/](https://cache-backend-production.up.railway.app/)

Para obtener datos meteorológicos, utiliza el endpoint `/weather/{searchTerm}`, donde `{searchTerm}` es el nombre de la ubicación que deseas consultar.


Ejemplo:
https://cache-backend-production.up.railway.app/weather/London


## English

# Weather Service Cache Backend

This project implements a caching service for weather data, utilizing the Tomorrow.io API. The service stores weather information for key locations and serves it through a REST API.

### Technologies Used

- **Kotlin**: Main programming language, offering concise syntax and powerful features.
- **Ktor**: Framework for building asynchronous servers, used to create the REST API.
- **Redis**: In-memory database, employed as a cache to store weather data.
- **Tomorrow.io API**: External source for weather data.

### Key Features

1. **Periodic Caching**: Updates weather data every 5 minutes for specified locations.
2. **REST API**: Provides an endpoint to query weather data for specific locations.
3. **Error Handling**: Implements retry logic for external API calls and logs errors in Redis.
4. **Flexible Search**: Allows partial location searches.

### Usage

The service is deployed and can be accessed at:

[https://cache-backend-production.up.railway.app/](https://cache-backend-production.up.railway.app/)

To retrieve weather data, use the endpoint `/weather/{searchTerm}`, where `{searchTerm}` is the name of the location you wish to query.

Example:
https://cache-backend-production.up.railway.app/weather/London
