package SegundaParte;

public class PocimaPorcentaje implements Pocima {
	private int porcentaje;
	private String nombre;
	
	public PocimaPorcentaje(int porcentaje, String nombre) {
		this.porcentaje = porcentaje;
		this.nombre = nombre;
	}
	
	@Override
	public int agregarPocima(Atributo atributo) {
		int valorActual = atributo.get_valor();
		int nuevoValor = valorActual + ((porcentaje * valorActual)/100);
		
		return nuevoValor;
	}

	public String getNombre() {
		return nombre;
	}
	
}
