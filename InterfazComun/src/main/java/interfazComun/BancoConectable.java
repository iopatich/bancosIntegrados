package interfazComun;

public interface BancoConectable {
    boolean transferirDestino(int idCuentaDestino, double monto);
    CuentaBase buscarCuenta(int idNumeroCuenta);
    String getNombreBanco();
}
