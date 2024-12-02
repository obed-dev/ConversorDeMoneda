## Currency Converter Challenge
# Descripción del Proyecto
Este proyecto es un convertidor de monedas basado en un desafío que utiliza la API de ExchangeRate para convertir entre diferentes monedas seleccionadas. La aplicación se ejecuta en la consola y ofrece una interfaz interactiva para que los usuarios seleccionen las monedas de origen y destino, ingresen el monto a convertir, y obtengan el resultado de la conversión.

# Características principales:
Interfaz interactiva:
El usuario puede ver las monedas disponibles, realizar conversiones y salir del programa mediante un menú en la consola.
Filtrado de monedas:
Solo se permiten conversiones entre las siguientes monedas:
ARS - Peso argentino
BOB - Boliviano boliviano
BRL - Real brasileño
CLP - Peso chileno
COP - Peso colombiano
USD - Dólar estadounidense

# Fórmula de conversión:
La conversión utiliza las tasas proporcionadas por la API y sigue la fórmula:

Monto convertido
=
Monto ingresado
Tasa de la moneda de origen
×
Tasa de la moneda de destino
Monto convertido= 
Tasa de la moneda de origen
Monto ingresado
​
# ×Tasa de la moneda de destino
Modularización:
Métodos separados para:
Obtener tasas de cambio de la API.
Mostrar monedas disponibles.
Realizar conversiones.
Calcular el monto convertido.
Requisitos Previos
Software necesario:
Java 11 o superior: Se utiliza HttpClient, disponible desde Java 11.
Gson: Biblioteca para manejar JSON.
Dependencia de Gson:
Si usas Maven, agrega la dependencia al archivo pom.xml:

xml
Copiar código
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>
Si usas otro sistema de construcción, descarga el archivo JAR desde Gson GitHub Repository y añádelo al proyecto.

# Instalación y Ejecución
Clona el repositorio:


git clone https://github.com/tuusuario/currency-converter-challenge.git
cd currency-converter-challenge
Compila el código:

javac -cp gson-2.10.1.jar CurrencyConverterChallenge.java

# Ejecuta el programa:

java -cp .:gson-2.10.1.jar CurrencyConverterChallenge
Cómo Usar

# Menú interactivo:

Al iniciar el programa, se presenta un menú con tres opciones:
Ver monedas disponibles.
Convertir moneda.
Salir.
Conversión de monedas:

Selecciona la opción "2" para realizar una conversión.
Ingresa el código de la moneda de origen (ejemplo: USD).
Ingresa el código de la moneda de destino (ejemplo: ARS).
Ingresa el monto a convertir.
El programa calcula y muestra el resultado de la conversión.
Salir:

Selecciona la opción "3" para cerrar el programa.
Estructura del Código
Clase principal: CurrencyConverterChallenge
Contiene el menú interactivo y organiza la lógica principal.


# Métodos principales:
fetchExchangeRates(): Obtiene las tasas de cambio desde la API y filtra las monedas permitidas.
displayAvailableCurrencies(): Muestra las monedas disponibles con sus tasas.
performConversion(): Solicita datos al usuario, realiza la validación y ejecuta la conversión.
convertCurrency(): Realiza el cálculo matemático de la conversión.

# Ejemplo de Ejecución
Entrada:
--- Conversor de Monedas ---
1. Ver monedas disponibles
2. Convertir moneda
3. Salir
Seleccione una opción: 1
Salida:
Monedas disponibles para conversión:
ARS - Tasa: 1011.75
BOB - Tasa: 6.9137
BRL - Tasa: 5.9862
CLP - Tasa: 977.8676
COP - Tasa: 4414.1048
USD - Tasa: 1.0
Entrada:


--- Conversor de Monedas ---
Seleccione una opción: 2
Ingrese la moneda de origen (Ejemplo: USD): USD
Ingrese la moneda de destino (Ejemplo: ARS): ARS
Ingrese el monto a convertir: 100
Salida:
Resultado: 100.00 USD equivale a 101175.00 ARS

# Consideraciones
Validación de entradas:
Solo se aceptan las monedas listadas.
Si el usuario ingresa un código de moneda inválido, el programa muestra un mensaje de error y regresa al menú.
Conexión a la API:
Si la API no responde correctamente, el programa muestra un mensaje de error.


# Mejoras Futuras
Soporte para más monedas, manteniendo el filtrado dinámico.
Interfaz gráfica con JavaFX o Swing.
Persistencia local de tasas de cambio para usarlas sin conexión.

# Créditos
Desarrollado como parte de un desafío de programación para demostrar habilidades en:

Manejo de JSON con Gson.
Uso de HttpClient para consumir APIs.
Interacción en la consola y modularización del código.

Autor: Obed baltodano 
Fecha: Diciembre 2024
