import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private String nombre;
    private int vidas;
    private List<Carta> mano;
    private int apuesta;
    private int victoriasRonda;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.vidas = 5; [cite_start]// Vidas iniciales [cite: 885]
        this.mano = new ArrayList<>();
        this.apuesta = 0;
        this.victoriasRonda = 0;
    }

    /**
     * Asigna una mano de cartas al jugador.
     */
    public void recibirMano(List<Carta> mano) {
        this.mano = mano;
    }

    /**
     * Juega una carta de la mano (quitándola).
     * @param indiceDeCarta El índice de la carta en la mano.
     * @return La carta jugada.
     */
    public Carta jugarCarta(int indiceDeCarta) {
        if (indiceDeCarta >= 0 && indiceDeCarta < mano.size()) {
            return mano.remove(indiceDeCarta);
        }
        return null; // Manejar error
    }

    /**
     * Calcula y aplica la penalización al final de la ronda.
     */
    public void calcularPenalizacion() {
        int diferencia = Math.abs(apuesta - victoriasRonda); [cite_start]// [cite: 904]
        this.vidas -= diferencia;
    }
    
    /**
     * Resetea las variables de la ronda.
     */
    public void resetearParaSiguienteRonda() {
        this.mano.clear();
        this.apuesta = 0;
        this.victoriasRonda = 0;
    }

    // --- Getters y Setters básicos ---
    
    public String getNombre() { return nombre; }
    public int getVidas() { return vidas; }
    public List<Carta> getMano() { return mano; }
    public int getApuesta() { return apuesta; }
    
    public void setApuesta(int apuesta) { this.apuesta = apuesta; }
    public void sumarVictoria() { this.victoriasRonda++; }
    public int getVictoriasRonda() { return victoriasRonda; }
    public boolean estaEliminado() { return vidas <= 0; }
}