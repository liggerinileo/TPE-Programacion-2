package SegundaParte;


public class PocimaNumeroEspecifico implements Pocima {
	private int valor;
	private String nombre;
	
	public PocimaNumeroEspecifico(int valor, String nombre) {
		this.valor = valor;
		this.nombre = nombre;
	}
	
	@Override
	public int agregarPocima(Atributo atributo) {
		return valor;
		
	}

	public String getNombre() {
		return nombre;
	}

}
