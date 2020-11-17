package PrimeraParte;

public class Atributo {
	private String nombre;
	private double valor;
	
	public Atributo(String nombre, double valor) { //CONSTRUCTOR CON VALORES DE LOS PARAMETROS NOMBRE Y VALOR
		this.nombre=nombre;
		this.valor = valor;
	}
	
	public String get_nombre() { //DEVUELVE NOMBRE
		return this.nombre;
	}
	
	public double get_valor() { // DEVUELVE VALOR
		return this.valor;
	}
	
	public void set_valor(int valor) { //SETEA VALOR 
		this.valor=valor;
	}
	
	public String toString() {
		return this.get_nombre()+this.get_valor();
	}
}