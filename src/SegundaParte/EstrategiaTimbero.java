package SegundaParte;

public class EstrategiaTimbero implements EstrategiaDeJuego {
	
	public Atributo definirEstrategia(Carta c) {
		Atributo atributo = null;
		int posAtributo = (int)(Math.random()*(c.get_atributos().size()));
		atributo = c.get_atributos().get(posAtributo);
		
		return atributo;
	}

}
