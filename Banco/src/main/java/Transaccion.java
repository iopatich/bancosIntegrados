import java.time.LocalDateTime;

public class Transaccion {
    private String tipo; // DEPOSITO, RETIRO, TRANSFERENCIA
    private double monto;

    public Transaccion(String tipo, double monto) {
        this.tipo = tipo;
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Historial de transacciones\n" + tipo + " $" + monto;
    }
}
