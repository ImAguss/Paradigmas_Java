package resolucion;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Clase MiHilo que se encarga de leer un archivo limpiar su contenido para el
 * analisis
 * y de insertar las palabras relevantes para el analisis en otra clase donde se
 * guardan tanto la palabra como
 * la cantidad de veces que se repite.
 */

public class MiHilo implements Runnable {

  private String rutaArchivo;
  private ConjuntoPalabras conjunto;

  /*
   * Constructor que debe recibir una ruta y el objeto donde se va a guardar el
   * conjunto
   * de palabras, porque debe recibirlo?
   * Debe recibir el objeto porque varios hilos operaran sobre el mismo conjunto
   * si el conjunto se crease en el constructor entonces seria independiente de
   * cada hilo.
   */
  public MiHilo(String ruta, ConjuntoPalabras conjunto) {
    this.rutaArchivo = ruta;
    this.conjunto = conjunto;
  }

  @Override
  public void run() {
    leerArchivo();
  }

  /*
   * El metodo que se encarga de leer el archivo con la clase "Files" que otorga
   * java
   * En el pdf de la practica sugieren otro pero el problema que tuve con ese es
   * que
   * escribia caracter por caracter haciendo que sea un dolor de huevo usar los
   * strings
   * por eso use esta alternativa mas "Simple" por decirlo asi
   */
  public void leerArchivo() {

    try {
      List<String> lineas = Files.readAllLines(Path.of(rutaArchivo));
      String[] palabras;

      for (String linea : lineas) {
        linea = limpiarSignos(linea); // Limpia cada linea del texto recibido.
        palabras = linea.split(" ");
        /*
         * Una vez que la linea esta limpia el split se encarga de separar cada palabra
         * de esa linea siempre
         * y cuando haya un espacio que las separe a las palabras, esto provoca que se
         * forme un array de palabras
         * Sin el split quedaria todo en un solo bloque de palabras
         * por ejemplo:
         * Sin split: ["Hola Mundo"]
         * Con split: ["Hola", "Mundo"]
         */
        palabras = limpiarStopWords(palabras);// Simplemente elimina palabras irrelevantes, esta hardcodeado porque no
                                              // es la idea del ejercicio

        /*
         * Esto es un foreach agarra cada palabra del array en cada iteracion y si no
         * esta vacia
         * la agrega, ejecutando el constructor con el string agarrado
         */
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

  /* Metodo que reemplaza los signos con nada */
  public String limpiarSignos(String texto) {
    return texto.replace(",", "")
        .replace(".", "")
        .replace(";", "");
  }

  /*
   * Metodo que se agarra un set de palabras "Set" es una coleccion de datos
   * Este metodo se ejecuta despues de que se cagaran todas las palabras de una
   * linea
   * Por lo que busca en cada linea las palabras que estan en el set, si una
   * palabra es parte del set
   * entonces el filter las elimina.
   */
  public String[] limpiarStopWords(String[] texto) {
    Set<String> stopWords = Set.of("el", "la", "los", "las");

    return Arrays.stream(texto)
        .filter(p -> !stopWords.contains(p.toLowerCase()))
        .toArray(String[]::new);
  }

  public static void main(String[] args) {
    ConjuntoPalabras conjunto = new ConjuntoPalabras();
    /*
     * Como no estoy extendiendo o heredando directamente de Thread provoca que
     * tenga que crear asi a los hilos.
     */
    Thread hilo1 = new Thread(new MiHilo("Texto1.txt", conjunto));
    Thread hilo2 = new Thread(new MiHilo("Texto2.txt", conjunto));

    try {
      hilo1.start();
      hilo2.start();

      /*
       * Espera que ambos hilos terminen sus tareas antes de seguir con la ejecucion
       */
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
