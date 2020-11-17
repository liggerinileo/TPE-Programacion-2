package PrimeraParte;

public class Jugador {
	private Mazo mazo;
	private String nombre;
		
	public Jugador(String nombre ,String nombreMazo) {
		this.nombre=nombre;
		this.mazo=new Mazo(nombreMazo);
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
		return (int)(Math.random()*(c.get_atributos().size()));
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public Carta getCarta(int i) {
		return this.mazo.getCarta(i);
	}
}