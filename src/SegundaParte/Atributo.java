package SegundaParte;


public class Atributo implements Comparable<Atributo>{
	private String nombre;
	private int valor;
	
	public Atributo(String nombre,int valor) { //CONSTRUCTOR CON VALORES DE LOS PARAMETROS NOMBRE Y VALOR
		this.nombre=nombre;
		this.valor = valor;
	}
	
	public String get_nombre() { //DEVUELVE NOMBRE
		return nombre;
	}
	
	public int get_valor() {
		return valor;
	}

	public void set_valor(int valor) {
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
