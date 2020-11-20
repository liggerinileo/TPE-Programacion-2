package PrimeraParte;

public class Jugador {
	private Mazo mazo;
	private String nombre;
		
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.mazo = new Mazo();
	}
	
	public String getNombre() {
		return this.nombre;
	}	
	
	public int getCantidadCartas() {
		return this.mazo.getCantidadCartas();
	}
	
	public Mazo devolver_mazo() {
		return mazo;
	}
	
	public int atributo_aleatorio(Carta c) {
		return (int)(Math.random() * (c.get_atributos().size()));
	}
	
	public void agregar_carta(Carta c) {
		this.mazo.agregar_carta(c);
	}
	
	public Carta getCarta(int i) {
		return this.mazo.getCarta(i);
	}

}