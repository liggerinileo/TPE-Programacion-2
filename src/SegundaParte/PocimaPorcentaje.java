package SegundaParte;

public class PocimaPorcentaje implements Pocima {
	private double porcentaje;
	private String nombre;
	
	public PocimaPorcentaje(double porcentaje, String nombre) {
		this.porcentaje = porcentaje;
		this.nombre = nombre;
	}
	
	@Override
	public double agregarPocima(Atributo atributo) {
		double valorActual = atributo.get_valor();
		double nuevoValor = valorActual + ((porcentaje * valorActual)/100);
		
		return nuevoValor;
	}

	public String getNombre() {
		return nombre;
	}
	
}
