package resolucion;

import java.util.ArrayList;

public class ConjuntoPalabras {
  private ArrayList<Palabra> listaPalabras;

  public ConjuntoPalabras() {
    listaPalabras = new ArrayList<Palabra>();
  }

  /*
   * Se tiene que usar synchronized para especificar a java que este metodo usa
   * una coleccion de datos que es accedida simultaneamente por varios hilos
   * asi java implementa operaciones como la exclusion mutua para evitar
   * colisiones.
   */
  public synchronized void agregarPalabra(Palabra p) {
    Palabra palabra;
    palabra = buscar(p.getPalabra());

    if (palabra != null) {
      palabra.sumarCantVeces();
    } else {
      listaPalabras.add(p);
    }
  }

  /*
   * Busco una palabra recibida desde agregarPalabra() si esta en la coleccion de
   * datos entonces significa que no hay que agregarla. En caso de que sea null,
   * cosa que no fue encontrada, entonces se incrementa su contador de
   * apariciones.
   */
  public synchronized Palabra buscar(String palabra) {
    Palabra palabraEncontrada;

    palabraEncontrada = listaPalabras.stream()
        .filter(p -> p.getPalabra().equals(palabra.toLowerCase()))
        .findFirst()
        .orElse(null);

    return palabraEncontrada;
  }

  public void mostrar() {
    String texto;

    // Para cada palabra mostrar la palabra y la cantidad de veces que aparecio.
    for (Palabra p : listaPalabras) {
      texto = "Palabra: " + p.getPalabra() + "\n"
          + "Cantidad: " + p.getCantidadVeces() + "\n";
      System.out.println(texto);
    }
  }
}
