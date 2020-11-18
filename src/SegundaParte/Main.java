package SegundaParte;

public class Main {
    public static void main(String[] args) {
        String mazoPath = "./superheroes.json";
//      String mazoPath = "./autos.json";
        //VisorMazo VisorMazo = new VisorMazo();
        Mazo mazoMaestro = VisorMazo.cargarMazo(mazoPath);   
        
        EstrategiaAmbicioso ambicioso = new EstrategiaAmbicioso();
        EstrategiaTimbero timbero = new EstrategiaTimbero();
        Jugador jugador1 = new Jugador("Ligge", ambicioso);
        Jugador jugador2 = new Jugador("Guille", timbero);
        Juego juego = new Juego(jugador1, jugador2, mazoMaestro, 10);
        
        juego.agregar_pocima(new PocimaPorcentaje(20, "Fortalecedora"));
        juego.agregar_pocima(new PocimaPorcentaje(55, "Fortalecedora Plus"));
        juego.agregar_pocima(new PocimaPorcentaje(-25, "Kriptonita"));
        juego.agregar_pocima(new PocimaPorcentaje(-55, "Reductor de Plomo"));
        juego.agregar_pocima(new PocimaNumeroEspecifico(4, "Quiero Vale Cuatro"));
        juego.agregar_pocima(new PocimaNumeroEspecifico(23, "Numero Magico"));
        juego.agregar_pocima(new PocimaSelectiva(35, "Selectiva Fuerza", "fuerza"));
        juego.agregar_pocima(new PocimaSelectiva(43, "Selectiva Peso", "peso"));
        juego.agregar_pocima(new PocimaCocktail(new PocimaPorcentaje(20, "Fortalecedora"), new PocimaPorcentaje(50, "Fortalecedora Plus")));
        
        /*Si juego con pocimas las reparto*/
        juego.repartir_pocimas();
        
       	juego.jugar();
    }
}