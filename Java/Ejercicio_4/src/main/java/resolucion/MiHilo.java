package resolucion;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MiHilo implements Runnable {

  private String rutaArchivo;
  private ConjuntoPalabras conjunto;

  public MiHilo(String ruta, ConjuntoPalabras conjunto) {
    this.rutaArchivo = ruta;
    this.conjunto = conjunto;
  }

  @Override
  public void run() {
    leerArchivo();
  }

  public void leerArchivo() {

    try {
      List<String> lineas = Files.readAllLines(Path.of(rutaArchivo));
      String[] palabras;

      for (String linea : lineas) {
        linea = limpiarSignos(linea);
        palabras = linea.split(" ");
        palabras = limpiarStopWords(palabras);

        for (String p : palabras) {
          if (!p.isEmpty()) {
            conjunto.agregarPalabra(new Palabra(p));
          }
        }
      }
    } catch (IOException errorLectura) {
      System.out.println(errorLectura.getMessage());
    }

  }

  public String limpiarSignos(String texto) {
    return texto.replace(",", "")
        .replace(".", "")
        .replace(";", "");
  }

  public String[] limpiarStopWords(String[] texto) {
    Set<String> stopWords = Set.of("el", "la", "los", "las", "si",
        "no");

    return Arrays.stream(texto)
        .filter(p -> !stopWords.contains(p.toLowerCase()))
        .toArray(String[]::new);
  }

  public static void main(String[] args) {
    ConjuntoPalabras conjunto = new ConjuntoPalabras();
    Thread hilo1 = new Thread(new MiHilo("Texto1.txt", conjunto));
    Thread hilo2 = new Thread(new MiHilo("Texto2.txt", conjunto));

    try {
      hilo1.start();
      hilo2.start();

      hilo1.join();
      hilo2.join();

      conjunto.mostrar();
    } catch (InterruptedException error) {
      System.out.println("Hilo frenado de forma abrupta.");
    } catch (Exception error) {
      System.out.println(error.getMessage());
    }

    System.out.println("Finalizando Ejecucion...");
  }

}
