# Sistema de Gestión de Inventario y Ventas - El Serranito del Norte

## Descripción
Aplicación de escritorio en JavaFX para gestionar envases reutilizables (bidones, cilindros, galones) con control de inventario, ventas y reportes.

## Tecnologías
- Java 21
- JavaFX 21
- PostgreSQL (o H2)
- Maven
- Patrones: MVC, DAO, SOLID
- Librerías: Logback, Apache Commons Lang

## Requisitos previos
- Java JDK 17 o superior
- Maven
- (Opcional) PostgreSQL si usas esa BD, o H2 incluido.

## Cómo ejecutar
1. Clonar el repositorio
2. Abrir en VS Code (con extensiones Java y Maven)
3. Ejecutar `mvn clean javafx:run`
4. Usuario: `admin` / Contraseña: `123456`

## Estructura del proyecto
- `src/main/java/com/serranito/model` → Entidades
- `src/main/java/com/serranito/view` → Interfaces JavaFX
- `src/main/java/com/serranito/controller` → Lógica de negocio
- `src/main/java/com/serranito/dao` → Acceso a datos (interfaz + impl)
- `src/main/java/com/serranito/util` → Conexión a BD

## Autores
- Ormeño Soria, Victor

## Licencia
Proyecto académico - Curso Integrador 1