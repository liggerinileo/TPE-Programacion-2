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
			mazo.get_cartas().get(i).setPocima(pocima);
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
		if(jugador1.getCantidadCartas()>jugador2.getCantidadCartas()) {
			System.out.println("--------------- Ganador  ---------------");
			System.out.println("Ganó el juego: "+jugador1.getNombre());
		}
		else {
			System.out.println("--------------- Ganador  ---------------");
			System.out.println("Ganó el juego: "+jugador2.getNombre());
		}
	}	

	private void ronda() {
		Carta CartaJugador1=jugador1.devolver_mazo().get_cartas().get(0);
		Carta CartaJugador2=jugador2.devolver_mazo().get_cartas().get(0);
		String nombreJug1 = jugador1.getNombre();
		String nombreJug2 = jugador2.getNombre();
		String nomCartaJug1 = CartaJugador1.get_nombre();
		String nomCartaJug2 = CartaJugador2.get_nombre();


		if (turno == 1) {
			Atributo atributoEstrategia = CartaJugador1.elegirEstrategia(jugador1.get_estrategia());
			double valorAtributo = atributoEstrategia.get_valor();
			double valorAtributoJug2 = CartaJugador2.get_valor_atributo(atributoEstrategia.get_nombre());
			boolean cartaJug1TienePocima = false;
			double valorAtributoPocimado = 0;
			double valorAtributoPocimadoJug2 = 0;
			String nombrePocimaJug1 = "";

			System.out.println("El jugador "+nombreJug1+" selecciona competir por el atributo "+atributoEstrategia.get_nombre());

			if (CartaJugador1.getPocima() != null) {
				valorAtributoPocimado = CartaJugador1.getPocima().agregarPocima(atributoEstrategia);
				nombrePocimaJug1 = CartaJugador1.getPocima().getNombre();
				cartaJug1TienePocima = true;
			} else {
				valorAtributoPocimado = valorAtributo;
			}
			if (CartaJugador2.getPocima() != null) {
				valorAtributoPocimadoJug2 = CartaJugador2.getPocima().agregarPocima(CartaJugador2.get_atributo(atributoEstrategia.get_nombre()));
			} else {
				valorAtributoPocimadoJug2 = valorAtributoJug2;
			}

			if (cartaJug1TienePocima) {
				System.out.println("La carta de "+nombreJug1+ " es "+nomCartaJug1+" con "+atributoEstrategia.get_nombre()+
						" "+valorAtributo+", se aplicó pócima "+nombrePocimaJug1+" valor resultante "+valorAtributoPocimado);
				System.out.println("La carta de "+nombreJug2+ " es "+nomCartaJug2+" con "+atributoEstrategia.get_nombre()+" "+valorAtributoPocimadoJug2);

				if (valorAtributoPocimado > valorAtributoPocimadoJug2) {
					jugador1.agregar_carta(CartaJugador1);
					jugador1.agregar_carta(CartaJugador2);
					jugador1.devolver_mazo().get_cartas().remove(0);
					jugador2.devolver_mazo().get_cartas().remove(0);

					System.out.println("Gana la ronda "+nombreJug1+" y queda con "+jugador1.getCantidadCartas()+
							" cartas ("+nombreJug2+" posee ahora "+jugador2.getCantidadCartas()+")");

				} else if (valorAtributoPocimado < valorAtributoPocimadoJug2) {
					jugador2.agregar_carta(CartaJugador2);
					jugador2.agregar_carta(CartaJugador1);
					jugador2.devolver_mazo().get_cartas().remove(0);
					jugador1.devolver_mazo().get_cartas().remove(0);

					System.out.println("Gana la ronda "+nombreJug2+" y queda con "+jugador2.getCantidadCartas()+
							" cartas ("+nombreJug1+" posee ahora "+jugador1.getCantidadCartas()+")");
				} else {
					jugador1.agregar_carta(CartaJugador1);
					jugador1.devolver_mazo().get_cartas().remove(0);
					jugador2.agregar_carta(CartaJugador2);
					jugador2.devolver_mazo().get_cartas().remove(0);

					System.out.println("Hubo Empate!");
				}
			} else {
				System.out.println("La carta de "+nombreJug1+" es "+nomCartaJug1+" con "+
						atributoEstrategia.get_nombre()+" "+valorAtributo);
				System.out.println("La carta de "+nombreJug2+" es "+nomCartaJug2+" con "+
						atributoEstrategia.get_nombre()+" "+valorAtributoJug2);

				if (valorAtributo > valorAtributoJug2) {				
					jugador1.agregar_carta(CartaJugador1);
					jugador1.agregar_carta(CartaJugador2);
					jugador1.devolver_mazo().get_cartas().remove(0);
					jugador2.devolver_mazo().get_cartas().remove(0);

					System.out.println("Gana la ronda "+nombreJug1+" y queda con "+jugador1.getCantidadCartas()+
							" cartas y "+nombreJug2+" posee ahora "+jugador2.getCantidadCartas());

					cambiarTurno(1);

				} else if (valorAtributo < valorAtributoJug2) {				
					jugador2.agregar_carta(CartaJugador2);
					jugador2.agregar_carta(CartaJugador1);
					jugador2.devolver_mazo().get_cartas().remove(0);
					jugador1.devolver_mazo().get_cartas().remove(0);

					System.out.println("Gana la ronda "+nombreJug2+" y queda con "+jugador2.getCantidadCartas()+
							" cartas y "+nombreJug1+" posee ahora "+jugador1.getCantidadCartas());

					cambiarTurno(2);

				} else {
					jugador1.agregar_carta(CartaJugador1);
					jugador1.devolver_mazo().get_cartas().remove(0);
					jugador2.agregar_carta(CartaJugador2);
					jugador2.devolver_mazo().get_cartas().remove(0);

					System.out.println("Hubo Empate!");

				}
			}
		} else {
			Atributo atributoEstrategia = CartaJugador2.elegirEstrategia(jugador1.get_estrategia());
			double valorAtributo = atributoEstrategia.get_valor();
			double valorAtributoJug1 = CartaJugador1.get_valor_atributo(atributoEstrategia.get_nombre());
			boolean cartaJug2TienePocima = false;
			double valorAtributoPocimado = 0;
			double valorAtributoPocimadoJug1 = 0;
			String nombrePocimaJug2 = "";

			System.out.println("El jugador "+nombreJug2+" selecciona competir por el atributo "+atributoEstrategia.get_nombre());

			if (CartaJugador2.getPocima() != null) {
				valorAtributoPocimado = CartaJugador2.getPocima().agregarPocima(atributoEstrategia);
				nombrePocimaJug2 = CartaJugador2.getPocima().getNombre();
				cartaJug2TienePocima = true;
			} else {
				valorAtributoPocimado = valorAtributo;
			}
			if (CartaJugador1.getPocima() != null) {
				valorAtributoPocimadoJug1 = CartaJugador1.getPocima().agregarPocima(CartaJugador1.get_atributo(atributoEstrategia.get_nombre()));
			} else {
				valorAtributoPocimadoJug1 = valorAtributoJug1;
			}

			if (cartaJug2TienePocima) {
				System.out.println("La carta de "+nombreJug2+ " es "+nomCartaJug2+" con "+atributoEstrategia.get_nombre()+
						" "+valorAtributo+", se aplicó pócima "+nombrePocimaJug2+" valor resultante "+valorAtributoPocimado );
				System.out.println("La carta de "+nombreJug1+ " es "+nomCartaJug1+" con "+atributoEstrategia.get_nombre()+" "+valorAtributoPocimadoJug1);

				if (valorAtributoPocimado > valorAtributoPocimadoJug1) {
					jugador2.agregar_carta(CartaJugador2);
					jugador2.agregar_carta(CartaJugador1);
					jugador2.devolver_mazo().get_cartas().remove(0);
					jugador1.devolver_mazo().get_cartas().remove(0);

					System.out.println("Gana la ronda "+nombreJug2+" y queda con "+jugador2.getCantidadCartas()+
							" cartas ("+nombreJug1+" posee ahora "+jugador1.getCantidadCartas()+")");

				} else if (valorAtributoPocimado < valorAtributoPocimadoJug1) {
					jugador1.agregar_carta(CartaJugador1);
					jugador1.agregar_carta(CartaJugador2);
					jugador1.devolver_mazo().get_cartas().remove(0);
					jugador2.devolver_mazo().get_cartas().remove(0);

					System.out.println("Gana la ronda "+nombreJug1+" y queda con "+jugador1.getCantidadCartas()+
							" cartas ("+nombreJug2+" posee ahora "+jugador1.getCantidadCartas()+")");

				} else {
					jugador1.agregar_carta(CartaJugador1);
					jugador1.devolver_mazo().get_cartas().remove(0);
					jugador2.agregar_carta(CartaJugador2);
					jugador2.devolver_mazo().get_cartas().remove(0);

					System.out.println("Hubo Empate!");

				}
			} else {
				System.out.println("La carta de "+nombreJug2+" es "+nomCartaJug2+" con "+
						atributoEstrategia.get_nombre()+" "+valorAtributo);
				System.out.println("La carta de "+nombreJug1+" es "+nomCartaJug1+" con "+
						atributoEstrategia.get_nombre()+" "+valorAtributoJug1);

				if (valorAtributo > valorAtributoJug1) {
					jugador2.agregar_carta(CartaJugador2);
					jugador2.agregar_carta(CartaJugador1);
					jugador2.devolver_mazo().get_cartas().remove(0);
					jugador1.devolver_mazo().get_cartas().remove(0);

					System.out.println("Gana la ronda "+nombreJug2+" y queda con "+jugador2.getCantidadCartas()+
							" cartas y "+nombreJug1+" posee ahora "+jugador1.getCantidadCartas());

					cambiarTurno(2);

				} else if (valorAtributo < valorAtributoJug1) {
					jugador1.agregar_carta(CartaJugador1);
					jugador1.agregar_carta(CartaJugador2);
					jugador1.devolver_mazo().get_cartas().remove(0);
					jugador2.devolver_mazo().get_cartas().remove(0);

					System.out.println("Gana la ronda "+nombreJug1+" y queda con "+jugador1.getCantidadCartas()+
							" cartas y "+nombreJug2+" posee ahora "+jugador2.getCantidadCartas());

					cambiarTurno(1);

				} else {
					jugador1.agregar_carta(CartaJugador1);
					jugador1.devolver_mazo().get_cartas().remove(0);
					jugador2.agregar_carta(CartaJugador2);
					jugador2.devolver_mazo().get_cartas().remove(0);

					System.out.println("Hubo Empate!");

				}
			}
		}
	}	

	private void cambiarTurno(int turno) {
		this.turno=turno;
	}

}


