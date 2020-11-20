package PrimeraParte;

import java.util.ArrayList;

public class Carta {
	private String nombre;
	private ArrayList <Atributo> atributos;
	
	public Carta(String nombre, ArrayList<Atributo> atributos) {
		this.nombre = nombre;
		this.atributos = atributos;
	}
	
	public String get_nombre() {
		return this.nombre;
	}
	
	public ArrayList<Atributo> get_atributos() {
		ArrayList<Atributo> copia = atributos;
		return copia;
	}
	
	public boolean verificar(Carta c) {
		int contador = 0;
		if(this.atributos.size()==c.atributos.size()) {
			for(int i=0;i<this.atributos.size();i++) {
				for (int j = 0; j < c.atributos.size(); j++) {
					if(this.atributos.get(i).get_nombre().equals(c.atributos.get(j).get_nombre())) {
						contador++;
					}
				}
			}
			if (this.atributos.size() == contador) {
				return true;
			}
		}
		return false;
	}
	
	public Atributo get_atributo(String nombreAtributo) {
		Atributo atributo = null;
		for (int i = 0; i < atributos.size(); i++) {
			atributo = atributos.get(i);
			if (atributo.get_nombre().equals(nombreAtributo)) {
				return atributo;
			}
		}
		return atributo;
	}
	
	public double get_valor_atributo(String nombreAtributo) {
		Atributo atributo = null;
		int valor = 0; 
		for (int i = 0; i < atributos.size(); i++) {
			atributo = atributos.get(i);
			if (atributo.get_nombre().equals(nombreAtributo)) {
				return atributo.get_valor();
			}
		}
		return valor;
	}
	
	public String toString() {
		return this.get_nombre() +""+ this.get_atributos();
	}
	
}