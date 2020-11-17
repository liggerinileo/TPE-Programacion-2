package SegundaParte;

public class PocimaSelectiva implements Pocima {
	private int valor;
	private String nombre;
	private String atributo;

	public PocimaSelectiva(int valor, String nombre, String atributo) {
		this.valor = valor;
		this.nombre = nombre;
		this.atributo = atributo;
	}

	@Override
	public int agregarPocima(Atributo atributo) {
		int valorActual = atributo.get_valor();
		int nuevoValor =  valorActual;
		if (this.atributo.equals(atributo.get_nombre())) {
			nuevoValor = valorActual + ((valor * valorActual)/100);

			return nuevoValor;
		} 
		return nuevoValor;

	}

	public String getNombre() {
		return nombre;
	}

}
