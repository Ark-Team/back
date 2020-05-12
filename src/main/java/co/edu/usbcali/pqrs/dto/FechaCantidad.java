package co.edu.usbcali.pqrs.dto;

public class FechaCantidad {

  private int dia;
  private int cantidad;

  public int getDia() {
    return dia;
  }

  public void setDia(int dia) {
    this.dia = dia;
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public FechaCantidad(int dia, int cantidad) {
    this.dia = dia;
    this.cantidad = cantidad;
  }
}
