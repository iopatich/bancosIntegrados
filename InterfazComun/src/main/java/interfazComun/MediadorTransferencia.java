package interfazComun;

import java.util.ArrayList;
import java.util.List;

public class MediadorTransferencia {
    private List<BancoConectable> bancos = new ArrayList<>();

    public void registrarBanco(BancoConectable banco) {
        bancos.add(banco);
    }

    public boolean transferir(BancoConectable bancoOrigen, int cuentaOrigen, int cuentaDestino, double monto) {
        CuentaBase origen = bancoOrigen.buscarCuenta(cuentaOrigen);
        if (origen == null || origen.getSaldo() < monto) {
            return false;
        }
        for (BancoConectable banco : bancos) {
            if (banco != bancoOrigen) {
                boolean conexion = banco.transferirDestino(cuentaDestino, monto);
                if (conexion) {
                    origen.retirar(monto);
                    return true;
                }
            }
        }
        return false;
    }
}