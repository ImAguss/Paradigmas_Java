package resolucion;

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

  public void cargar_viajeros(Scanner in) {
    try {
      System.out.println("Cual es tu Numero?");
      Integer numero = in.nextInt();
      in.nextLine();

      System.out.println("Cual es tu dni?");
      String dni = in.nextLine();
      buscar_existente(dni);

      System.out.println("Cual es tu nombre?");
      String nombre = in.nextLine();

      System.out.println("Cual es tu apellido?");
      String apellido = in.nextLine();

      System.out.println("Cuales son tus millas?");
      Integer millas = in.nextInt();
      in.nextLine();

      Frecuente unViajero = new Frecuente(numero, dni, nombre, apellido, millas);
      viajeros.add(unViajero);
    } catch (Excepciones_Personalizadas e) {
      System.out.println(e);
    }

  }

  public boolean dni_existe(String dni) {
    if (viajeros.stream().filter(v -> v.getDni().equals(dni)).findFirst().isPresent()) {
      return true;
    } else {
      return false;
    }
  }

  public void buscar_existente(String dni) throws Excepciones_Personalizadas {
    if (viajeros.stream().filter(x -> x.getDni().equals(dni)).findFirst().isPresent()) {
      throw new Excepciones_Personalizadas("El DNI ingresado ya existe");
    }
  }

  public void mostrar_viajero(Integer numero) {
    viajeros.stream().filter(x -> x.getNumero() == numero).findFirst().ifPresent(x -> System.out.println(x.toString()));
  }

  public void mostrat_viajero_dni_millas(String dni) throws Excepciones_Personalizadas {

    if (dni_existe(dni)) {
      viajeros.stream().filter(x -> x.getDni().equals(dni)).findFirst()
          .ifPresent(x -> System.out.println(x.getMillas()));
    } else {
      throw new Excepciones_Personalizadas("El DNI ingresado NO EXISTE");
    }
  }

  public void acumular_millas_dni(String dni, Integer cant_millas) throws Excepciones_Personalizadas {

    if (dni_existe(dni)) {
      viajeros.stream().filter(x -> x.getDni().compareTo(dni) == 0).findFirst()
          .ifPresent(x -> x.acumularMillas(cant_millas));
    } else {
      throw new Excepciones_Personalizadas("El DNI ingresado NO EXISTE");
    }
  }

  public void mejorViajero() {

    int maximo = viajeros.stream()
        .mapToInt(Frecuente::getMillas)
        .reduce(0, Integer::max);

    List<Frecuente> mejores = viajeros.stream()
        .filter(f -> f.getMillas() == maximo)
        .toList();

    for (Frecuente frecuente : mejores) {
      System.out.println(frecuente.getNombre());
    }
  }

}
