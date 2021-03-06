package SegundaParte;

import java.util.ArrayList;
public class Mazo {
	private ArrayList <Carta> cartas;

	public Mazo() {
		cartas = new ArrayList<Carta>();
	}

	public void agregar_carta(Carta c) {
		if(this.cartas.size()>0){	
			if(this.cartas.get(0).verificar(c)) {
				this.cartas.add(c);
			}
		}
		else {
			cartas.add(c);
		}
	}

	public String toString() {
		return "Carta: "+get_cartas();
	}

	public ArrayList<Carta> get_cartas(){
		ArrayList<Carta> copia = cartas; 
		return copia;
	}

	public int getCantidadCartas() {
		int contador=0;
		for(int i=0;i<cartas.size();i++) {
			contador++;
		}
		return contador;
	}

	public Carta getCarta(int i) {
		return cartas.get(i);
	}
	
	public void eliminarCarta() {
		cartas.remove(0);
	}

}