package resolucion;

import java.util.ArrayList;

public class ConjuntoPalabras {
  private ArrayList<Palabra> listaPalabras;

  public ConjuntoPalabras() {
    listaPalabras = new ArrayList<Palabra>();
  }

  public synchronized void agregarPalabra(Palabra p) {
    Palabra palabra;
    palabra = buscar(p.getPalabra());

    if (palabra != null) {
      palabra.sumarCantVeces();
    } else {
      listaPalabras.add(p);
    }
  }

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

    for (Palabra p : listaPalabras) {
      texto = "Palabra: " + p.getPalabra() + "\n"
          + "Cantidad: " + p.getCantidadVeces() + "\n";
      System.out.println(texto);
    }
  }
}
