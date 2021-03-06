package SegundaParte;


public class Atributo implements Comparable<Atributo>{
	private String nombre;
	private double valor;
	
	public Atributo(String nombre, double valor) { //CONSTRUCTOR CON VALORES DE LOS PARAMETROS NOMBRE Y VALOR
		this.nombre=nombre;
		this.valor = valor;
	}
	
	public String get_nombre() { //DEVUELVE NOMBRE
		return nombre;
	}
	
	public double get_valor() {
		return valor;
	}

	public void set_valor(double valor) {
		this.valor = valor;
	}
	
	public String toString() {
		return get_nombre()+get_valor();
	}

	@Override
	public int compareTo(Atributo atributos) {
		if(get_valor() > atributos.get_valor()) {
			return 1;
		}else if(get_valor() < atributos.get_valor()) {
			return -1;
		}else {
			return 0;
		}
	}
	
	public boolean equals(Object o) {
		try {
			Atributo otro = (Atributo) o;
			return get_nombre().equals(otro.get_nombre());
		} catch (Exception e) {
			return false;
		}
	}
	
}
