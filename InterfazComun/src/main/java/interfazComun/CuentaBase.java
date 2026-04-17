package interfazComun;

public interface CuentaBase {
    double getSaldo();
    void depositar(double monto);
    void retirar(double monto);
}
