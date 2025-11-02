import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Partida {

    private List<Jugador> jugadores;
    private Baraja baraja;
    private int cartasPorRonda;
    private boolean direccionRondas; // true = bajando (5,4,3..), false = subiendo (1,2,3..)
    
    // Para saber quién empieza apostando y jugando
    private int indiceJugadorInicial; 
    
    // Guarda el estado del juego
    private Map<Jugador, Carta> cartasEnMesa;
    private FaseDeJuego fase;

    public enum FaseDeJuego {
        ESPERANDO_JUGADORES,
        APOSTANDO,
        JUGANDO_MINIRRONDA
    }

    public Partida(List<String> nombresJugadores) {
        this.jugadores = new ArrayList<>();
        for (String nombre : nombresJugadores) {
            this.jugadores.add(new Jugador(nombre));
        }
        
        this.cartasPorRonda = 5; [cite_start]// [cite: 888]
        this.direccionRondas = true; // Empezamos bajando
        this.indiceJugadorInicial = 0;
        this.fase = FaseDeJuego.ESPERANDO_JUGADORES;
    }
    
    /**
     * Inicia una nueva ronda (reparte, resetea, etc.)
     */
    public void iniciarRonda() {
        this.baraja = new Baraja();
        this.baraja.barajar();
        this.cartasEnMesa = new HashMap<>();
        
        for (Jugador j : jugadores) {
            j.resetearParaSiguienteRonda();
            j.recibirMano(baraja.repartir(cartasPorRonda));
        }
        
        this.fase = FaseDeJuego.APOSTANDO;
        // Faltaría notificar al primer jugador (indiceJugadorInicial) que apueste
    }
    
    /**
     * Lógica para recibir una apuesta de un jugador.
     * @return Un mensaje de OK o de Error (ej. "No puedes empatar")
     */
    public String recibirApuesta(String nombreJugador, int apuesta) {
        // ... (Lógica para validar si es el turno de apostar de ese jugador)
        
        [cite_start]// ... (Lógica para validar si es el último y se aplica la regla de desempate) [cite: 893]
        // EJ: int sumaTotal = jugadores.stream().mapToInt(Jugador::getApuesta).sum();
        // EJ: if (esUltimo && (sumaTotal + apuesta) == cartasPorRonda) {
        //        return "Error: No puedes hacer que la suma sea " + cartasPorRonda;
        //    }
        
        // ... (Si todo OK, se guarda la apuesta y se pasa al siguiente)
        return "OK";
    }

    /**
     * Lógica para recibir una carta de un jugador.
     */
    public String jugarCarta(String nombreJugador, int indiceCarta) {
        [cite_start]// ... (Lógica para validar que es el turno de jugar de ese jugador) [cite: 896, 900]
        
        Jugador j = ...; // Buscar jugador por nombre
        Carta c = j.jugarCarta(indiceCarta);
        cartasEnMesa.put(j, c);
        
        // Si todos han jugado, se finaliza la minirronda
        if (cartasEnMesa.size() == jugadores.size()) {
            finalizarMinirronda();
        }
        
        return "OK";
    }
    
    /**
     * Comprueba quién ganó la minirronda y le da la victoria.
     */
    private void finalizarMinirronda() {
        // ... (Lógica para comparar todas las cartas en 'cartasEnMesa')
        [cite_start]// Ojo con la regla de desempate: "Si hay empate, gana quien jugó antes" [cite: 898]
        
        Jugador ganador = ...; // El jugador que ganó
        ganador.sumarVictoria();
        
        // Limpiar la mesa
        cartasEnMesa.clear();
        
        // Comprobar si se acabaron las cartas (fin de la ronda)
        if (ganador.getMano().isEmpty()) {
            finalizarRonda();
        }
    }
    
    /**
     * Aplica penalizaciones, comprueba eliminados y prepara la siguiente ronda.
     */
    private void finalizarRonda() {
        for (Jugador j : jugadores) {
            j.calcularPenalizacion(); [cite_start]// [cite: 904]
        }
        
        [cite_start]// ... (Comprobar si hay jugadores eliminados) [cite: 908]
        
        // ... (Comprobar si solo queda un ganador)
        
        [cite_start]// Mover el turno inicial [cite: 907]
        this.indiceJugadorInicial = (this.indiceJugadorInicial + 1) % jugadores.size();
        
        [cite_start]// Ajustar el número de cartas para la siguiente ronda (5,4,3,2,1,2,3...) [cite: 888]
        if (direccionRondas) {
            cartasPorRonda--;
            if (cartasPorRonda == 1) direccionRondas = false;
        } else {
            cartasPorRonda++;
            if (cartasPorRonda == 5) direccionRondas = true;
        }
        
        // Iniciar la nueva ronda
        iniciarRonda();
    }
}
