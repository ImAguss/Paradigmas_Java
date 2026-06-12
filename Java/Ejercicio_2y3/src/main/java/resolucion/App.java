package resolucion;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    String bandera = "S";
    Gestor unGestor = new Gestor();
    Integer opcion = 0;
    Scanner in = new Scanner(System.in);

    while (bandera.equals("S")) {

      System.out.println("Opcion: ");
      opcion = in.nextInt();

      try {

        if (opcion == 1) {
          unGestor.cargar_viajeros(in);
        } else if (opcion == 2) {
          System.out.println("Ingrese numero: ");
          Integer numero = in.nextInt();
          unGestor.mostrar_viajero(numero);
        } else if (opcion == 3) {
          System.out.println("Ingrese dni: ");
          String dni = in.next();
          unGestor.mostrat_viajero_dni_millas(dni);
        } else if (opcion == 4) {

          System.out.println("Ingrese dni: ");
          String dni = in.next();

          System.out.println("Ingrese millas: ");
          Integer cant_millas = in.nextInt();

          unGestor.acumular_millas_dni(dni, cant_millas);
        } else if (opcion == 5) {
          unGestor.mejorViajero();
        } else if (opcion == 0) {
          System.out.println("Saliendo del programa....");
          bandera = "N";
        } else {
          System.out.println("Ingrese una opcion valida!");
        }

      } catch (Excepciones_Personalizadas error) {
        System.out.println(error.getMessage());
      }

    }

    in.close();
  }
}
