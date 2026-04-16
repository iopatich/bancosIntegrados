package interfazComun;

import java.util.ArrayList;
import java.util.List;

public class MediadorTransferencia {
    private List<BancoConectable> bancos = new ArrayList<>();

    public void registrarBanco(BancoConectable banco) {
        bancos.add(banco);
    }

    public boolean transferirEntreBancos(BancoConectable bancoOrigen, int cuentaDestino, double monto) {
        for (BancoConectable banco : bancos) {
            if (banco != bancoOrigen) {
                if (banco.transferirDestino(cuentaDestino, monto)) {
                    return true;
                }
            }
        }
        return false;
    }
}