package interfaz;

import bancoIgnacio.Banco;
import interfazComun.MediadorTransferencia;

public class MainInterfaz {
    public static void main(String[] args) {

        Banco banco1 = new Banco();
        bancoGisela.Banco banco2 = new bancoGisela.Banco();

        MediadorTransferencia mediador = new MediadorTransferencia();

        mediador.registrarBanco(banco1);
        mediador.registrarBanco(banco2);

        System.out.println("Bancos conectados correctamente");
    }
}