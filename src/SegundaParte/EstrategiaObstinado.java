package SegundaParte;

import java.util.ArrayList;

public class EstrategiaObstinado implements EstrategiaDeJuego {
	private String atributo;

	public EstrategiaObstinado(String atributo) {
		this.atributo = atributo;
	}

	public Atributo  definirEstrategia(Carta c) {
		Atributo atributo = null;
		ArrayList<Atributo> atributos = c.get_atributos();
		
		for (int i = 0; i < atributos.size(); i++) {
			if (atributos.get(i).get_nombre().equals(this.atributo)) {
				atributo = atributos.get(i);
			}
		}
		return atributo;
	}
	
}
