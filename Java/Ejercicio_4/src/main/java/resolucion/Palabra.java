package resolucion;

public class Palabra {
  private String palabra;
  private int cantidadVeces;

  public Palabra(String palabra) {
    this.palabra = palabra;
    this.cantidadVeces = 1;
  }

  public String getPalabra() {
    return palabra.toLowerCase();
  }

  public int getCantidadVeces() {
    return cantidadVeces;
  }

  public void sumarCantVeces() {
    cantidadVeces += 1;
  }
}
