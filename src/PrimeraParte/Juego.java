package PrimeraParte;

public class Juego {
	private Jugador jugador1;
	private Jugador jugador2;
	private int maximoRondas;
	private Mazo mazo;
	private int turno = 1;

	public Juego(Mazo mazo, Jugador jugador1, Jugador jugador2, int maximoRondas) {
		this.mazo = mazo;
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.maximoRondas = maximoRondas;
	}

	public void jugar() {
		int rondasJugadas = 1;

		repartir();

		while(!verificarPartida(rondasJugadas)) {
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

	public boolean verificarPartida(int rondasJugadas) {
		if(rondasJugadas > maximoRondas) {
			ganador_juego();
			return true;
		}
		else if((jugador1.getCantidadCartas() == 0)||(jugador2.getCantidadCartas() == 0)) {
			ganador_juego();
			return true;
		}
		return false;
	}

	public void ganador_juego() {
		if(jugador1.getCantidadCartas() > jugador2.getCantidadCartas()) {
			System.out.println("--------------- Ganador  ---------------");
			System.out.println("Ganó el juego: "+jugador1.getNombre());
		}
		else {
			System.out.println("--------------- Ganador  ---------------");
			System.out.println("Ganó el juego: "+jugador2.getNombre());
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
		int posAtributoRandom = 0; 
		Atributo atributoSeleccionado = null;
		double valorAtriJugMano = 0;
		double valorAtriJugSinMano = 0;

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
		posAtributoRandom = jugadorMano.atributo_aleatorio(cartaJugadorMano);
		atributoSeleccionado = cartaJugadorMano.get_atributos().get(posAtributoRandom);
		valorAtriJugMano = atributoSeleccionado.get_valor();
		valorAtriJugSinMano = cartaJugadorSinMano.get_atributo(atributoSeleccionado.get_nombre()).get_valor();

		System.out.println("El jugador "+nombreJugMano+" selecciona competir por el atributo "+atributoSeleccionado.get_nombre());


		System.out.println("La carta de "+nombreJugMano+" es "+nomCartaJugMano+" con "+
				atributoSeleccionado.get_nombre()+" "+valorAtriJugMano);
		
		System.out.println("La carta de "+nomJugSinMano+" es "+nomCartaJugSinMano+" con "+
				atributoSeleccionado.get_nombre()+" "+valorAtriJugSinMano);

		ganadorRonda(valorAtriJugMano, valorAtriJugSinMano, jugadorMano, jugadorSinMano, cartaJugadorMano, cartaJugadorSinMano);


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