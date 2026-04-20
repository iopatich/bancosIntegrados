package app;

import bancoIgnacio.Admin;
import bancoIgnacio.Banco;
import bancoIgnacio.Menu;
import interfazComun.MediadorTransferencia;
import java.util.Scanner;

public class MainInterfaz {
    public MainInterfaz() {
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        Banco bancoIgnacio = new Banco();
        bancoGisela.Banco bancoGisela = new bancoGisela.Banco();

        MediadorTransferencia mediador = new MediadorTransferencia();

        mediador.registrarBanco(bancoIgnacio);
        mediador.registrarBanco(bancoGisela);

        Menu menuIgnacio = new Menu(bancoIgnacio, mediador);

        int opcion;

        do {
            System.out.println("--- Sistema Interbancario ---");
            System.out.println("1. Banco Ignacio");
            System.out.println("2. Banco Gisela");
            System.out.println("3. Ver balances de ambos bancos");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    menuIgnacio.iniciar();
                    break;
                case 2:
                    bancoGisela.iniciarSistema(mediador);
                    break;
                case 3:
                    System.out.println("--- Balance total ---");
                    System.out.println("Banco Ignacio:");
                    bancoIgnacio.mostrarBalanceCuentas();

                    System.out.println("Banco Gisela:");
                    bancoGisela.mostrarBalanceCuentas();
                    break;
                case 0:
                    System.out.println("Finalizado");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 0);
    }
}
