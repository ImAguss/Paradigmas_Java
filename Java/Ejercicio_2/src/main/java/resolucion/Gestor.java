package resolucion;

import resolucion.Frecuente;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gestor {
  private List<Frecuente> viajeros;

  public Gestor() {
    viajeros = new ArrayList<>();
  }

  public List<Frecuente> getViajeros() {
    return viajeros;
  }

  public void setViajeros(List<Frecuente> viajeros) {
    this.viajeros = viajeros;
  }

  public void cargar_viajeros() {
    Scanner in = new Scanner(System.in);

    System.out.println("Cual es tu Numero?");
    Integer numero = in.nextInt();

    System.out.println("Cual es tu dni?");
    String dni = in.nextLine();

    System.out.println("Cual es tu nombre?");
    String nombre = in.nextLine();

    System.out.println("Cual es tu apellido?");
    String apellido = in.nextLine();

    System.out.println("Cuales son tus millas?");
    Integer millas = in.nextInt();

    Frecuente unViajero = new Frecuente(numero, dni, nombre, apellido, millas);
    viajeros.add(unViajero);
    in.close();
  }

  public void mostrar_viajero(Integer numero) {
    viajeros.stream().filter(x -> x.getNumero() == numero).findFirst().ifPresent(x -> System.out.println(x.toString()));
  }
}
