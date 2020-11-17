package PrimeraParte;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
public class VisorMazo {
    public static void mostrarMazo(String jsonFile) {
        File jsonInputFile = new File(jsonFile);
        InputStream is;
        try {
            is = new FileInputStream(jsonInputFile);
            JsonReader reader = Json.createReader(is);
            JsonArray cartas = (JsonArray) reader.readObject().getJsonArray("cartas");
        
            Mazo mazoMaestro = new Mazo("Superh�roes"); // MAZO ORIGINAL DEL JUEGO
           
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
            Juego juego = new Juego(mazoMaestro,20);//SE LE PASA EL MAZO, Y EL MAXIMO DE RONDAS 
            juego.jugar();
            reader.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String mazoPath = "./superheroes.json";
//        String mazoPath = "./autos.json";
        VisorMazo.mostrarMazo(mazoPath);   
    }
}