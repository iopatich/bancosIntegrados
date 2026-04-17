package interfaz;

import bancoIgnacio.Admin;
import bancoIgnacio.Banco;
import bancoIgnacio.Cliente;
import bancoIgnacio.Cuenta;
import bancoIgnacio.Sucursal;
import bancoIgnacio.TipoCuenta;
import bancoIgnacio.Usuario;
import interfazComun.MediadorTransferencia;

import java.util.Scanner;

public class MainInterfaz {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        Banco bancoIgnacio = new Banco();
        bancoGisela.Banco bancoGisela = new bancoGisela.Banco();

        MediadorTransferencia mediador = new MediadorTransferencia();

        mediador.registrarBanco(bancoIgnacio);
        mediador.registrarBanco(bancoGisela);


        Admin adminBoedo = new Admin(1, "adminBoedo", "456");
        bancoIgnacio.crearSucursal("Boedo", adminBoedo);

        Object login = bancoIgnacio.loginAdmin("adminBoedo", "456");

        Sucursal sucursalIgnacio = null;
        if (login instanceof Sucursal) {
            sucursalIgnacio = (Sucursal) login;
        }

        Usuario usuarioIgnacio = new Usuario(101, "Ignacio", "123") {};
        Cliente clienteIgnacio = new Cliente(usuarioIgnacio);

        sucursalIgnacio.crearCliente(adminBoedo, clienteIgnacio);
        sucursalIgnacio.crearCuenta(adminBoedo, clienteIgnacio, 1001, TipoCuenta.AHORRO);
        sucursalIgnacio.crearCuenta(adminBoedo, clienteIgnacio, 1002, TipoCuenta.AHORRO);
        sucursalIgnacio.crearCuenta(adminBoedo, clienteIgnacio, 1003, TipoCuenta.CORRIENTE);

        Cuenta cuentaIgnacio = sucursalIgnacio.buscarCuenta(1001);
        Cuenta cuentaIgnacio2 = sucursalIgnacio.buscarCuenta(1002);
        Cuenta cuentaIgnacio3 = sucursalIgnacio.buscarCuenta(1003);

        cuentaIgnacio.depositar(10000);
        cuentaIgnacio2.depositar(5000);
        cuentaIgnacio3.depositar(2500);


        bancoGisela.agregarCuentaPrueba("Maria", 1004, 10000);
        bancoGisela.agregarCuentaPrueba("Martin", 1005, 7500);
        bancoGisela.agregarCuentaPrueba("Sofia", 1005, 22000);


        int opcion;

        do {
            System.out.println("Transferencia interbancaria");
            System.out.println("1. Transferir desde Banco Ignacio");
            System.out.println("2. Transferir desde Banco Gisela");
            System.out.println("3. Ver balance de cuentas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Cuenta origen Banco Ignacio: ");
                    int origenIgnacio = teclado.nextInt();

                    System.out.print("Cuenta destino: ");
                    int destinoIgnacio = teclado.nextInt();

                    System.out.print("Monto a transferir: ");
                    double montoIgnacio = teclado.nextDouble();

                    boolean conexion1 = sucursalIgnacio.transferirOtroBanco(adminBoedo, origenIgnacio, destinoIgnacio, montoIgnacio, mediador, bancoIgnacio
                    );

                    if (conexion1) {
                        System.out.println("La transferencia se realizo con exito");
                    } else {
                        System.out.println("No se pudo realizar la transferencia");
                    }
                    break;
                case 2:
                    System.out.print("Cuenta origen Banco Gisela: ");
                    int origenGisela = teclado.nextInt();

                    System.out.print("Cuenta destino: ");
                    int destinoGisela = teclado.nextInt();

                    System.out.print("Monto a transferir: ");
                    double montoGisela = teclado.nextDouble();

                    boolean conexion2 = bancoGisela.transferirOtroBanco(origenGisela, destinoGisela, montoGisela, mediador, bancoGisela
                    );
                    if (conexion2) {
                        System.out.println("La transferencia se realizo con exito");
                    } else {
                        System.out.println("No se pudo realizar la transferencia");
                    }
                    break;
                case 3:
                    System.out.println("Banco Ignacio");
                    bancoIgnacio.mostrarBalanceCuentas();
                    System.out.println();
                    bancoGisela.mostrarBalanceCuentas();
                case 0:
                    System.out.println("Finalizado");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 0);
    }
}