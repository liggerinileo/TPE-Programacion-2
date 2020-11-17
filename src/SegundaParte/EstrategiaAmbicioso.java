package SegundaParte;

import java.util.ArrayList;

public class EstrategiaAmbicioso implements EstrategiaDeJuego{

	public Atributo  definirEstrategia(Carta c) {
		int mayor=-10000;
		Atributo atributo = null;
		ArrayList <Atributo> atributos = c.get_atributos();
		for(int i=0; i<atributos.size();i++) {
			if(atributos.get(i).get_valor()>mayor) {
				mayor=atributos.get(i).get_valor();
				atributo = atributos.get(i);
			}
		}
		return atributo;
	}
}
