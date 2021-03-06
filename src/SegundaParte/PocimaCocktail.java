package SegundaParte; 

public class PocimaCocktail implements Pocima  {
	private Pocima pocima1;
	private Pocima pocima2;
	private String nombre;

	public PocimaCocktail(Pocima pocima1,Pocima pocima2) {//43% mas
		this.pocima1=pocima1;
		this.pocima2=pocima2;
		this.nombre = "Cocktail";
	}
	
	@Override
	public double agregarPocima(Atributo atributo) {
		Atributo copiaAtributo = new Atributo(atributo.get_nombre(), atributo.get_valor());
		copiaAtributo.set_valor(pocima1.agregarPocima(copiaAtributo));
		
		return pocima2.agregarPocima(copiaAtributo);
	}
	
	@Override
	public String getNombre() {
		return this.nombre;
	}

}


