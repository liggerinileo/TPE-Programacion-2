package PrimeraParte;

import java.util.ArrayList;
public class Mazo {
	private ArrayList <Carta> cartas;
	private String nombre;
	
	public Mazo(String nombre) {
		cartas = new ArrayList<Carta>();
		this.nombre=nombre;
	}
	public void agregar_carta(Carta c) {
		if(this.cartas.size()>0){	
			if(this.cartas.get(0).verificar(c)) {
				this.cartas.add(c);
			}
		}
		else {
			this.cartas.add(c);
		}
	}	
	public String toString() {
		return "Carta:"+get_cartas();
	}
	public ArrayList<Carta> get_cartas(){
		return cartas;
	}
	public ArrayList <Carta> devolver_cartas(){
		return cartas;
	}
	public int getCantidadCartas() {
		int contador=0;
		for(int i=0;i<cartas.size();i++) {
			contador++;
		}
		return contador;
	}
	public boolean verificar() {
		return true;
	}
	public Carta getCarta(int i) {
		return cartas.get(i);
	}
	
	public String get_nombre() {
		return this.nombre;
	}
}