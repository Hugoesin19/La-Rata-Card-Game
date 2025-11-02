import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {

    private List<Carta> mazo;

    /**
     * Crea una baraja estándar de 52 cartas.
     */
    public Baraja() {
        this.mazo = new ArrayList<>();
        // Itera por cada palo
        for (Carta.Palo palo : Carta.Palo.values()) {
            // Itera por cada valor (del 2 al 14)
            for (int valor = 2; valor <= 14; valor++) {
                mazo.add(new Carta(palo, valor));
            }
        }
    }

    /**
     * Baraja el mazo aleatoriamente.
     */
    public void barajar() {
        Collections.shuffle(mazo);
    }

    /**
     * Reparte un número específico de cartas y las quita del mazo.
     * @param cantidad El número de cartas a repartir.
     * @return Una lista de cartas.
     */
    public List<Carta> repartir(int cantidad) {
        List<Carta> mano = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            if (!mazo.isEmpty()) {
                // Quita la última carta del mazo y la añade a la mano
                mano.add(mazo.remove(mazo.size() - 1));
            }
        }
        return mano;
    }
}