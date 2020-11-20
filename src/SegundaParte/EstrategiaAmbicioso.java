package SegundaParte;

import java.util.ArrayList;
import java.util.Collections;

public class EstrategiaAmbicioso implements EstrategiaDeJuego{

	public Atributo definirEstrategia(Carta c) {
		Atributo mayor = null;
		ArrayList <Atributo> atributos = c.get_atributos();
		
		Collections.sort(atributos);
		mayor = atributos.get(atributos.size()-1);
		
		return mayor;
	}
}
