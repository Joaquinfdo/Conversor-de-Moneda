package lad.com.alura.conversormoneda;


import java.io.IOException;


public class ConversorApp {
public static void main(String[] args) throws IOException, InterruptedException {
Conversor.eleccionUserMenu();
}


// Método auxiliar si necesitás llamar a obtenerTasa desde otro lugar.
public static double obtenerTasa(String urlFinal) throws IOException, InterruptedException {
return Conversor.obtenerTasa(urlFinal);
}
}
