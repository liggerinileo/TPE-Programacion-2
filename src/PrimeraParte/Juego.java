package PrimeraParte;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Juego {
	private Jugador jugador1;
	private Jugador jugador2;
	private int maximoRondas;
	private Mazo mazo;
	private int turno=1;
	public Juego(Mazo mazo,Jugador jugador1,Jugador jugador2,int maximoRondas) {
		this.mazo=mazo;
		this.jugador1=jugador1;
		this.jugador2=jugador2;
		this.maximoRondas=maximoRondas;
	}
	public Juego(Mazo mazo,int maximoRondas) {
		this.mazo=mazo;
		this.maximoRondas=maximoRondas;
        jugador1=new Jugador(obtener_string_valido(1),this.mazo.get_nombre());
        jugador2=new Jugador(obtener_string_valido(2),this.mazo.get_nombre());     
	}
	private String obtener_string_valido(int jugador) {
		String nombre="";
		boolean salidaDoWhile=false;
		BufferedReader entrada=new BufferedReader(new InputStreamReader(System.in));
		do {
			try {
				System.out.println("Jugador " +jugador+ " ingrese su nombre por favor: ");
				nombre=entrada.readLine();
				salidaDoWhile=true;
				}
				catch(Exception exc){
				System.out.println("Error al ingresar");
			}
		}while(!salidaDoWhile);
		return nombre;
	}
	public void jugar() {
		int rondasJugadas=0;
		repartir();
		while(!verificarPartida(rondasJugadas)) {
			System.out.println("--------------- Ronda "+rondasJugadas+ " ---------------");

			ronda();	
			rondasJugadas++;
		}
	}

	private void repartir() {	
		int contador=1;
		for(int i=mazo.devolver_cartas().size();i>0;i--) {
			if(contador==1){
				jugador1.devolver_mazo().devolver_cartas().add(this.mazo.devolver_cartas().get(0));	
				this.mazo.devolver_cartas().remove(0);	
				contador=2;
			}
			else if(contador==2){
				jugador2.devolver_mazo().devolver_cartas().add(this.mazo.devolver_cartas().get(0));	
				this.mazo.devolver_cartas().remove(0);
				contador=1;
			}
		}
	}
	public boolean verificarPartida(int rondasJugadas) {
		if(rondasJugadas==maximoRondas) {
			ganador_juego();
			return true;
		}
		else if((jugador1.getCantidadCartas()==0)||(jugador2.getCantidadCartas()==0)) {
			ganador_juego();
			return true;
		}
		return false;
	}
	public void ganador_juego() {
		if(jugador1.getCantidadCartas()>jugador2.getCantidadCartas()) {
			System.out.println("--------------- Ganador  ---------------");
			System.out.println("Ganó el juego: "+jugador1.getNombre());
		}
		else {
			System.out.println("--------------- Ganador  ---------------");
			System.out.println("Ganó el juego: "+jugador2.getNombre());
		}
	}
	public void ronda() {//comparar con la primer carta siempre get.(0)
		int pos=0;
		if(turno==1) {
			 pos=jugador1.atributo_aleatorio(jugador1.getCarta(0));
				System.out.println("El jugador "+jugador1.getNombre()+" selecciona competir por el atributo "+jugador1.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_nombre());

		}
		else if(turno==2) {
			 pos=jugador2.atributo_aleatorio(jugador2.getCarta(0));
				System.out.println("El jugador "+jugador2.getNombre()+" selecciona competir por el atributo "+jugador2.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_nombre());

		}
		if((jugador1.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_valor()) > (jugador2.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_valor())){

			 System.out.println("La carta de "+jugador1.getNombre()+ " es "+jugador1.devolver_mazo().get_cartas().get(0).get_nombre()+" con "+jugador1.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_nombre()+" "+jugador1.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_valor() );
			 System.out.println("La carta de "+jugador2.getNombre()+ " es "+jugador2.devolver_mazo().get_cartas().get(0).get_nombre()+" con "+jugador2.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_nombre()+" "+jugador2.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_valor() );
			 
			 System.out.println("Ganó la ronda: "+jugador1.getNombre());
			 
			jugador1.devolver_mazo().get_cartas().add(jugador1.devolver_mazo().get_cartas().get(0));	
			jugador1.devolver_mazo().get_cartas().remove(0);	
			jugador1.devolver_mazo().get_cartas().add(jugador2.devolver_mazo().get_cartas().get(0));	
			jugador2.devolver_mazo().get_cartas().remove(0);	
			cambiarTurno(1);
		}
		else if ((jugador1.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_valor()) < (jugador2.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_valor())){
			 System.out.println("La carta de "+jugador2.getNombre()+ " es "+jugador2.devolver_mazo().get_cartas().get(0).get_nombre()+" con "+jugador2.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_nombre()+" "+jugador2.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_valor() );
			 System.out.println("La carta de "+jugador1.getNombre()+ " es "+jugador1.devolver_mazo().get_cartas().get(0).get_nombre()+" con "+jugador1.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_nombre()+" "+jugador1.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_valor() );
			 System.out.println("Ganó la ronda: "+jugador2.getNombre());
			
			jugador2.devolver_mazo().get_cartas().add(jugador1.devolver_mazo().get_cartas().get(0));	
			jugador2.devolver_mazo().get_cartas().remove(0);	
			jugador2.devolver_mazo().get_cartas().add(jugador2.devolver_mazo().get_cartas().get(0));	
			jugador1.devolver_mazo().get_cartas().remove(0);	
			cambiarTurno(2);
		}
		else {
			if(turno==1) {
				 System.out.println("La carta de "+jugador1.getNombre()+ " es "+jugador1.devolver_mazo().get_cartas().get(0).get_nombre()+" con "+jugador1.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_nombre()+" "+jugador1.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_valor() );			
				 System.out.println("La carta de "+jugador2.getNombre()+ " es "+jugador2.devolver_mazo().get_cartas().get(0).get_nombre()+" con "+jugador2.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_nombre()+" "+jugador2.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_valor() );
			}
			else {
				 System.out.println("La carta de "+jugador2.getNombre()+ " es "+jugador2.devolver_mazo().get_cartas().get(0).get_nombre()+" con "+jugador2.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_nombre()+" "+jugador2.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_valor() );
				 System.out.println("La carta de "+jugador1.getNombre()+ " es "+jugador1.devolver_mazo().get_cartas().get(0).get_nombre()+" con "+jugador1.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_nombre()+" "+jugador1.devolver_mazo().get_cartas().get(0).get_atributos().get(pos).get_valor() );
			}
			System.out.println("Empate");
			jugador1.devolver_mazo().get_cartas().add(jugador1.devolver_mazo().get_cartas().get(0));	
			jugador1.devolver_mazo().get_cartas().remove(0);	
			jugador2.devolver_mazo().get_cartas().add(jugador2.devolver_mazo().get_cartas().get(0));	
			jugador2.devolver_mazo().get_cartas().remove(0);
		}
		System.out.println(jugador1.getNombre()+" posee ahora "+jugador1.getCantidadCartas()+" cartas y "+jugador2.getNombre()+ " posee ahora "+jugador2.getCantidadCartas()+" cartas.");
	}	
	public void cambiarTurno(int turno) {
		this.turno=turno;
	}
}