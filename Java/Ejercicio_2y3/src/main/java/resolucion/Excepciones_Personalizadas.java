package resolucion;

public class Excepciones_Personalizadas extends Exception {
  public Excepciones_Personalizadas() {
    super();
  }

  public Excepciones_Personalizadas(String mensaje) {
    super(mensaje);
  }

  public Excepciones_Personalizadas(String mensaje, Throwable causa) {
    super(mensaje, causa);
  }

  public Excepciones_Personalizadas(Throwable causa) {
    super(causa);
  }

  public static void validar_existente(String dni) {

  }
}
