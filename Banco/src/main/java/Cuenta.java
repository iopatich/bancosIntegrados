import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    private int idCuenta;
    private TipoCuenta tipoCuenta;
    private double saldo;
    private ArrayList<Transaccion> historial;

    public Cuenta(int id, TipoCuenta tipo) {
        this.idCuenta = id;
        this.tipoCuenta = tipo;
        this.saldo = 0;
        this.historial = new ArrayList<>();
    }

    public List<Transaccion> getHistorial() {
        return historial;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void depositar(double monto) {
        saldo += monto;
        historial.add(new Transaccion("Deposito", monto));
    }

    public void retirar(double monto) {
        saldo -= monto;
        historial.add(new Transaccion("Retiro", monto));
    }

    public void transferir(Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto) {
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
        historial.add(new Transaccion("Transferencia", monto));
    }

}
