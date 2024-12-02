package com.aluralatam.conversordemonedajava.main;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final String[] ALLOWED_CURRENCIES = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        try {
            // Obtener las tasas de cambio
            Map<String, Double> exchangeRates = fetchExchangeRates();

            if (exchangeRates.isEmpty()) {
                System.out.println("No se pudo obtener las tasas de cambio.");
                return;
            }

            // Menú interactivo
            boolean exit = false;
            while (!exit) {
                System.out.println("\n--- Conversor de Monedas ---");
                System.out.println("1. Ver monedas disponibles");
                System.out.println("2. Convertir moneda");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        displayAvailableCurrencies(exchangeRates);
                        break;
                    case 2:
                        performConversion(scanner, exchangeRates);
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Gracias por usar el conversor de monedas.");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Método para obtener las tasas de cambio desde la API
    private static Map<String, Double> fetchExchangeRates() throws Exception {
        Map<String, Double> filteredRates = new HashMap<>();

        String apiUrl = "https://v6.exchangerate-api.com/v6/9186431b0760b50a3853ce33/latest/USD";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            // Usar JsonParser para parsear el JSON y extraer las monedas
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");

            // Crear una instancia de Moneda para encapsular la respuesta
            Gson gson = new Gson();
            Moneda moneda = gson.fromJson(response.body(), Moneda.class);

            // Filtrar las monedas permitidas
            for (String currency : ALLOWED_CURRENCIES) {
                if (conversionRates.has(currency)) {
                    filteredRates.put(currency, moneda.getConversion_rates().get(currency));
                }
            }
        }
        return filteredRates;
    }

    // Método para mostrar las monedas disponibles
    private static void displayAvailableCurrencies(Map<String, Double> exchangeRates) {
        System.out.println("Monedas disponibles para conversión:");
        exchangeRates.forEach((currency, rate) -> System.out.println(currency + " - Tasa: " + rate));
    }

    // Método para realizar la conversión de monedas
    private static void performConversion(Scanner scanner, Map<String, Double> exchangeRates) {
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese la moneda de origen (Ejemplo: USD): ");
        String sourceCurrency = scanner.nextLine().toUpperCase();

        if (!exchangeRates.containsKey(sourceCurrency)) {
            System.out.println("Moneda de origen no válida.");
            return;
        }

        System.out.print("Ingrese la moneda de destino (Ejemplo: ARS): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        if (!exchangeRates.containsKey(targetCurrency)) {
            System.out.println("Moneda de destino no válida.");
            return;
        }

        System.out.print("Ingrese el monto a convertir: ");
        double amount = scanner.nextDouble();

        double convertedAmount = convertCurrency(amount, exchangeRates.get(sourceCurrency), exchangeRates.get(targetCurrency));
        System.out.printf("Resultado: %.2f %s equivale a %.2f %s%n", amount, sourceCurrency, convertedAmount, targetCurrency);
    }

    // Método para calcular la conversión de monedas
    private static double convertCurrency(double amount, double sourceRate, double targetRate) {
        return (amount / sourceRate) * targetRate;
    }
}
