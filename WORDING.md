# Entrega

Envía el proyecto para que podamos inspeccionar el código y ejecutar el proyecto de la forma que prefieras(.zip, link a git...)

# Añadir un nuevo API endpoint

Añade un nuevo endpoint que realize una búsqueda de anuncios de forma inteligente en función de una cadena de palabras clave.

- El endpoint recibirá una lista de palabras separadas por espacios, simulando un buscador clásico. Ejemplo: "localhost:8080/ads/appartments for sale in marrakech"

- El endpoint también debe estar preparado para no recibir ninguna palabra. Ejemplo: "localhost:8080/ads/"

## Filtrado

El método de filtrado debe seguir las siguientes normas:

- Queremos poder buscar por las propiedades de los anuncios: city, town, category y subcategory.

- Se identificarán las palabras no clave por la que no queremos buscar. Estas serán: for, in, at. De tal forma que si en la cadena de texto que nos llega tenemos "appartments for sale in Casablanca" descartaremos las palabras 'for' e 'in'.

- La búsqueda por categorías y subcategorías se realizará utilizando el **código**. La búsqueda de ciudades y barrios se realizará utilizando el **nombre**.

- La búsqueda por palabras deberá ser ignore case. Ejemplo: para las dos siguientes cadenas de búsqueda el resultado será el mismo:
	* "appartments in casablanca"
	* "Appartments in Casablanca"

- La búsqueda debe ser restrictiva. Si recibimos palabras de más de un tipo de dato, se deberán cumplir todas las condiciones. Ejemplos:
	* "appartments in casablanca JawadiTown" -> sólo se devuelven los apartamentos de esa city + town. Es decir, no se devuelven apartamentos de otros barrios de Casblanca.
	* "appartments in rabat JawadiTown" -> no se devuelve ningún resultado. Las town JawadiTown no pertenece a esa ciudad.
	* "villas for rent in rabat" -> sólo se devuelven las villas de categoria rent en ciudad Rabat.

## Resultados

- Si encontramos alguna palabra que coincida deberemos devolver todos los resultados para esa palabra.

- Si no recibimos ninguna palabra de filtrado se devuelven todos los anuncios.

- Si ninguna palabra coincide no deberemos devolver ningún anuncio.

## Que valoraremos

- Codigo limpio y bien estructurado.
- Buena cobertura de tests.
- Buenas practicas de programación orientada a objetos.
- Adherencia a principios SOLID.

## Notas

- Este proyecto está hecho con SpringBoot y Java 8, pero su solución se puede implementar igual con Java7. Importalo en tu IDE y trabaja con el.
- Asegura que el proyecto se pueda ejecutar con ./run o ejecutando la clase `com.mubawab.tech.Application::main()`
- Saca ventaja de las clases entregadas
- Si quieres usar librerías extra puedes hacerlo sin problemas.

