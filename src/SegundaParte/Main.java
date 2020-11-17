package SegundaParte;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class Main {
	public static void visorMazo(String jsonFile) {
        //URL url = getClass().getResource(jsonFile);
        File jsonInputFile = new File(jsonFile);
        InputStream is;
        try {
            is = new FileInputStream(jsonInputFile);
            JsonReader reader = Json.createReader(is);
            JsonArray cartas = (JsonArray) reader.readObject().getJsonArray("cartas");  
            
            
            Mazo mazoMaestro = new Mazo(); // MAZO ORIGINAL DEL JUEGO 
            
//            Mazo mazoMaestro = new Mazo("Autos"); // MAZO ORIGINAL DEL JUEGO 
           
            
            for (JsonObject carta : cartas.getValuesAs(JsonObject.class)) {
            	ArrayList<Atributo> atributosCarta = new ArrayList<Atributo>();
                String nombreCarta = carta.getString("nombre");
                JsonObject atributos = (JsonObject) carta.getJsonObject("atributos");
                String atributosStr = "";
                int atributoValor = 0;
                for (String nombreAtributo:atributos.keySet()) {
                    atributosStr = atributosStr + nombreAtributo + ": " +
                            atributos.getInt(nombreAtributo) + "; ";
                	atributoValor = atributos.getInt(nombreAtributo);
                	Atributo atributo = new Atributo(nombreAtributo, atributoValor);
                	atributosCarta.add(atributo);
                }
                Carta c = new Carta(nombreCarta, atributosCarta);
                mazoMaestro.agregar_carta(c);
                
            }
            
            EstrategiaAmbicioso ambicioso = new EstrategiaAmbicioso();
            EstrategiaTimbero timbero = new EstrategiaTimbero();
            Jugador jugador1 = new Jugador("Ligge", ambicioso);
            Jugador jugador2 = new Jugador("Guille", timbero);
            Juego juego = new Juego(jugador1, jugador2, mazoMaestro, 10, 1);
            
            juego.agregar_pocima(new PocimaPorcentaje(20, "Fortalecedora"));
            juego.agregar_pocima(new PocimaPorcentaje(55, "Fortalecedora Plus"));
            juego.agregar_pocima(new PocimaPorcentaje(-25, "Kriptonita"));
            juego.agregar_pocima(new PocimaPorcentaje(-55, "Reductor de Plomo"));
            juego.agregar_pocima(new PocimaNumeroEspecifico(4, "Quiero Vale Cuatro"));
            juego.agregar_pocima(new PocimaNumeroEspecifico(23, "Numero Magico"));
            juego.agregar_pocima(new PocimaSelectiva(35, "Selectiva Fuerza", "fuerza"));
            juego.agregar_pocima(new PocimaSelectiva(43, "Selectiva Peso", "peso"));
            juego.agregar_pocima(new PocimaCocktail(new PocimaPorcentaje(20, "Fortalecedora"), new PocimaPorcentaje(50, "Fortalecedora Plus")));
            
   
            int eleccionJuego = juego.elegirTipoJuego();
            if (eleccionJuego == 1) {
            	juego.jugar();
			} else if(eleccionJuego == 2) {
				juego.jugar();
			}
            
            reader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String mazoPath = "./superheroes.json";
//        String mazoPath = "./autos.json";
        Main.visorMazo(mazoPath);   
    }
   
}