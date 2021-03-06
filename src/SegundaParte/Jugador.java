package SegundaParte;

public class Jugador {
	private Mazo mazo;
	private String nombre;
	private EstrategiaDeJuego estrategia;
	
	public Jugador(String nombre, EstrategiaDeJuego estrategia) {
		this.nombre=nombre;
		this.estrategia=estrategia;
		this.mazo=new Mazo();
	}
	
	public EstrategiaDeJuego get_estrategia() {
		return this.estrategia; 
	}
	
	public void set_estrategia(EstrategiaDeJuego estrategia) {
		this.estrategia = estrategia;
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
	
	public Carta getCarta(int i) {
		return this.mazo.getCarta(i);
	}
	
	public void agregar_carta(Carta c) {
		this.mazo.agregar_carta(c);
	}
	
}