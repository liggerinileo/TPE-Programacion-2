package SegundaParte;

import java.util.ArrayList;

public class Juego {
	private Jugador jugador1;
	private Jugador jugador2;
	private Mazo mazo;
	private int maximoRondas;
	private int turno = 1;
	private ArrayList<Pocima> pocimas;

	public Juego(Jugador jugador1, Jugador jugador2, Mazo mazo, int maximoRondas) {
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.mazo = mazo;
		this.maximoRondas = maximoRondas;
		this.pocimas = new ArrayList<Pocima>();
	}

	public void jugar() {
		int rondasJugadas=1;

		repartir();

		while(!verificarPartida(rondasJugadas)) {
			System.out.println("");
			System.out.println("--------------- Ronda "+rondasJugadas+ " ---------------");
			ronda();	
			rondasJugadas++;
		}
	}

	public void repartir() {
		for (int i = 0; i < mazo.get_cartas().size(); i++) {
			jugador1.agregar_carta(mazo.getCarta(i));
			i++;
			if(i < mazo.get_cartas().size()) {
				jugador2.agregar_carta(mazo.getCarta(i));
			}
		}
	}

	public void agregar_pocima(Pocima pocima) {
		this.pocimas.add(pocima);
	}

	public void repartir_pocimas() {
		Pocima pocima;
		for (int i = 0; i < pocimas.size(); i++) {
			pocima = pocimas.get(i);
			mazo.getCarta(i).setPocima(pocima);
		}
	}

	private boolean verificarPartida(int rondasJugadas) {
		if(rondasJugadas > maximoRondas) {
			ganador_juego();
			return true;
		} else if((jugador1.getCantidadCartas()==0)||(jugador2.getCantidadCartas()==0)) {
			ganador_juego();
			return true;
		} else if (rondasJugadas < maximoRondas){
			return false;
		}
		return false;
	}

	private void ganador_juego() {
		if(jugador1.getCantidadCartas() > jugador2.getCantidadCartas()) {
			System.out.println("--------------- Ganador  ---------------");
			System.out.println("Ganó el juego: "+jugador1.getNombre());
		}
		else if (jugador1.getCantidadCartas() < jugador2.getCantidadCartas()) {
			System.out.println("--------------- Ganador  ---------------");
			System.out.println("Ganó el juego: "+jugador2.getNombre());
		} else {
			System.out.println("--------------- Empate  ---------------");
			System.out.println("Hubo Empate!");
		}
	}	

	private void ronda() {
		Jugador jugadorMano = null;
		Jugador jugadorSinMano = null;
		Carta cartaJugadorMano = null;
		Carta cartaJugadorSinMano = null;
		String nombreJugMano = "";
		String nomJugSinMano = "";
		String nomCartaJugMano = "";
		String nomCartaJugSinMano = "";

		if (turno == 1) {
			jugadorMano = jugador1;
			jugadorSinMano = jugador2;
		} else {
			jugadorMano = jugador2;
			jugadorSinMano = jugador1;
		}
		
		cartaJugadorMano = jugadorMano.getCarta(0);
		cartaJugadorSinMano = jugadorSinMano.getCarta(0);
		nombreJugMano = jugadorMano.getNombre();
		nomJugSinMano = jugadorSinMano.getNombre();
		nomCartaJugMano = cartaJugadorMano.get_nombre();
		nomCartaJugSinMano = cartaJugadorSinMano.get_nombre();

		Atributo atributoEstrategia = cartaJugadorMano.elegirEstrategia(jugadorMano.get_estrategia());
		double valorAtriJugMano = atributoEstrategia.get_valor();
		double valorAtriJugSinMano = cartaJugadorSinMano.get_valor_atributo(atributoEstrategia.get_nombre());
		boolean cartaJugManoTienePocima = false;
		double valorAtriPocimadoJugMano = 0;
		double valorAtriPocimadoJugSinMano = 0;
		String nombrePocimaJugMano = "";

		System.out.println("El jugador "+nombreJugMano+" selecciona competir por el atributo "+atributoEstrategia.get_nombre());

		if (cartaJugadorMano.getPocima() != null) {
			valorAtriPocimadoJugMano = cartaJugadorMano.getPocima().agregarPocima(atributoEstrategia);
			nombrePocimaJugMano = cartaJugadorMano.getPocima().getNombre();
			cartaJugManoTienePocima = true;
		} else {
			valorAtriPocimadoJugMano = valorAtriJugMano;
		}
		
		if (cartaJugadorSinMano.getPocima() != null) {
			valorAtriPocimadoJugSinMano = cartaJugadorSinMano.getPocima().agregarPocima(cartaJugadorSinMano.get_atributo(atributoEstrategia.get_nombre()));
		} else {
			valorAtriPocimadoJugSinMano = valorAtriJugSinMano;
		}

		if (cartaJugManoTienePocima) {
			System.out.println("La carta de "+nombreJugMano+ " es "+nomCartaJugMano+" con "+atributoEstrategia.get_nombre()+
					" "+valorAtriJugMano+", se aplicó pócima "+nombrePocimaJugMano+" valor resultante "+valorAtriPocimadoJugMano);
			System.out.println("La carta de "+nomJugSinMano+ " es "+nomCartaJugSinMano+" con "+atributoEstrategia.get_nombre()+" "+valorAtriPocimadoJugSinMano);

			ganadorRonda(valorAtriPocimadoJugMano, valorAtriPocimadoJugSinMano, jugadorMano, jugadorSinMano, cartaJugadorMano, cartaJugadorSinMano);

		} else {
			System.out.println("La carta de "+nombreJugMano+" es "+nomCartaJugMano+" con "+
					atributoEstrategia.get_nombre()+" "+valorAtriJugMano);
			System.out.println("La carta de "+nomJugSinMano+" es "+nomCartaJugSinMano+" con "+
					atributoEstrategia.get_nombre()+" "+valorAtriJugSinMano);
			
			ganadorRonda(valorAtriJugMano, valorAtriJugSinMano, jugadorMano, jugadorSinMano, cartaJugadorMano, cartaJugadorSinMano);

		}
	}
	
	private void ganadorRonda(double valorAtriJugMano, double valorAtriJugSinMano, Jugador jugadorMano, Jugador jugadorSinMano, 
			Carta cartaJugadorMano, Carta cartaJugadorSinMano) {
		if (valorAtriJugMano > valorAtriJugSinMano) {	
			divisionCartasFinalRonda(jugadorMano, jugadorSinMano, cartaJugadorMano, cartaJugadorSinMano, false);			

			System.out.println("Gana la ronda "+jugadorMano.getNombre()+" y queda con "+jugadorMano.getCantidadCartas()+
					" cartas y "+jugadorSinMano.getNombre()+" posee ahora "+jugadorSinMano.getCantidadCartas());

			cambiarTurno(jugadorMano);

		} else if (valorAtriJugMano < valorAtriJugSinMano) {
			divisionCartasFinalRonda(jugadorSinMano, jugadorMano, cartaJugadorSinMano, cartaJugadorMano, false);

			System.out.println("Gana la ronda "+jugadorSinMano.getNombre()+" y queda con "+jugadorSinMano.getCantidadCartas()+
					" cartas y "+jugadorMano.getNombre()+" posee ahora "+jugadorMano.getCantidadCartas());

			cambiarTurno(jugadorSinMano);

		} else {
			divisionCartasFinalRonda(jugadorMano, jugadorSinMano, cartaJugadorMano, cartaJugadorSinMano, true);

			System.out.println("Hubo Empate!");

		}
	}

	private void divisionCartasFinalRonda(Jugador jugador1, Jugador jugador2, Carta cartaJugador1, Carta cartaJugador2, boolean empate) {
		if (!empate) {
			jugador1.agregar_carta(cartaJugador1);
			jugador1.agregar_carta(cartaJugador2);
			jugador1.devolver_mazo().eliminarCarta();
			jugador2.devolver_mazo().eliminarCarta();
		} else {
			jugador1.agregar_carta(cartaJugador1);
			jugador1.devolver_mazo().eliminarCarta();
			jugador2.agregar_carta(cartaJugador2);
			jugador2.devolver_mazo().eliminarCarta();
		}

	}

	private void cambiarTurno(Jugador jugador) {
		if (jugador == jugador1) {
			this.turno = 1;
		} else if (jugador == jugador2) {
			this.turno = 2;
		}

	}

}


