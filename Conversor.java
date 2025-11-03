package lad.com.alura.conversormoneda;
System.out.printf("\nTasa %s -> %s : %.6f\n", origen, destino, tasa);
System.out.printf("Resultado: %.2f %s = %.2f %s\n\n", monto, origen, resultado, destino);
} catch (Exception e) {
System.out.println("Ocurrió un error al obtener la tasa: " + e.getMessage());
e.printStackTrace();
}


System.out.println("Desea realizar otra conversión? (S/N): ");
String rta = scanner.next().trim().toUpperCase();
if (!rta.equals("S") && !rta.equals("SI")) {
seguir = false;
}
}


System.out.println("Gracias por usar el Conversor de Monedas. Hasta luego!");
scanner.close();
}


private static void mostrarOpcionesMonedas() {
for (Map.Entry<Integer, String> entry : monedas.entrySet()) {
System.out.printf("%d) %s\n", entry.getKey(), entry.getValue());
}
}


private static int leerEntero(Scanner scanner) {
while (!scanner.hasNextInt()) {
System.out.println("Entrada inválida. Ingrese un número.");
scanner.next();
}
return scanner.nextInt();
}


private static double leerDouble(Scanner scanner) {
while (!scanner.hasNextDouble()) {
System.out.println("Entrada inválida. Ingrese un número (ej: 100.50).");
scanner.next();
}
return scanner.nextDouble();
}


private static String construirUrlTasa(String from, String to) {
return BASE_URL + API_KEY + "/pair/" + from + "/" + to;
}


public static double obtenerTasa(String urlFinal) throws IOException, InterruptedException {
HttpClient cliente = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
.uri(URI.create(urlFinal))
.GET()
.build();


try {
HttpResponse<String> respuesta = cliente.send(request, HttpResponse.BodyHandlers.ofString());


if (respuesta.statusCode() != 200) {
throw new IOException("Respuesta de la API con código: " + respuesta.statusCode());
}


JsonElement elemento = JsonParser.parseString(respuesta.body());
JsonObject objectRoot = elemento.getAsJsonObject();


if (objectRoot.has("conversion_rate")) {
return objectRoot.get("conversion_rate").getAsDouble();
} else if (objectRoot.has("rate")) {
return objectRoot.get("rate").getAsDouble();
} else {
if (objectRoot.has("conversion_rates")) {
JsonObject conv = objectRoot.getAsJsonObject("conversion_rates");
  
throw new IOException("Estructura JSON inesperada: no se encontró 'conversion_rate' ni 'rate'.");
