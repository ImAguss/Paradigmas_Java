package resolucion;

public class Frecuente {

  private Integer numero;
  private String dni;
  private String nombre;
  private String apellido;
  private Integer millas;

  public Frecuente(Integer numero, String dni, String nombre, String apellido, Integer millas) {
    this.numero = numero;
    this.dni = dni;
    this.nombre = nombre;
    this.apellido = apellido;
    this.millas = millas;
  }

  public Integer getNumero() {
    return numero;
  }

  public String getApellido() {
    return apellido;
  }

  public String getDni() {
    return dni;
  }

  public Integer getMillas() {
    return millas;
  }

  public String getNombre() {
    return nombre;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public void setMillas(Integer millas) {
    this.millas = millas;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setNumero(Integer numero) {
    this.numero = numero;
  }

  public Integer acumularMillas(Integer cantidadMillas) {

    millas = getMillas();
    Integer millas_actualizadas = millas + cantidadMillas;
    setMillas(millas_actualizadas);

    return millas_actualizadas;

  }

  public Integer canjearMillas(Integer cantidadMillas) {

    Integer millas = getMillas();
    Integer millas_actualizadas = 0;

    if (cantidadMillas <= millas) {
      millas_actualizadas -= cantidadMillas;
      setMillas(millas_actualizadas);
      return millas_actualizadas;
    } else {
      return 0;
    }

  }

  @Override
  public String toString() {
    return "Viajero{" + "dni=" + dni + "nombre=" + nombre + "apellido=" + apellido + "millas=" + millas + '}';
  }
}
