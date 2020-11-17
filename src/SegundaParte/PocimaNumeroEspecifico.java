package SegundaParte;


public class PocimaNumeroEspecifico implements Pocima {
	private double valor;
	private String nombre;
	
	public PocimaNumeroEspecifico(double valor, String nombre) {
		this.valor = valor;
		this.nombre = nombre;
	}
	
	@Override
	public double agregarPocima(Atributo atributo) {
		return valor;
		
	}

	public String getNombre() {
		return nombre;
	}

}
