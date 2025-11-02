public class Carta {

    
    public enum Palo {
        TREBOLES, DIAMANTES, CORAZONES, PICAS
    }

    private final Palo palo;
    private final int valor; // 2 al 10, J=11, Q=12, K=13, A=14

    public Carta(Palo palo, int valor) {
        this.palo = palo;
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public Palo getPalo() {
        return palo;
    }

    
    @Override
    public String toString() {
        String nombreValor;
        switch (valor) {
            case 11: nombreValor = "J"; break;
            case 12: nombreValor = "Q"; break;
            case 13: nombreValor = "K"; break;
            case 14: nombreValor = "A"; break;
            default: nombreValor = String.valueOf(valor);
        }
        return nombreValor + " de " + palo;
    }
}