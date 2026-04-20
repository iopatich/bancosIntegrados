package bancoIgnacio;

import interfazComun.MediadorTransferencia;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        MediadorTransferencia mediador = new MediadorTransferencia();
        Menu menu = new Menu(banco, mediador);
        menu.iniciar();
    }
}
