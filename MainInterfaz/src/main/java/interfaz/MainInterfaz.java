package interfaz;

import bancoIgnacio.Banco;
import bancoIgnacio.Admin;
import bancoIgnacio.Cliente;
import bancoIgnacio.Cuenta;
import bancoIgnacio.Sucursal;
import bancoIgnacio.TipoCuenta;
import bancoIgnacio.Usuario;
import interfazComun.MediadorTransferencia;

import java.awt.desktop.SystemEventListener;

public class MainInterfaz {
    public static void main(String[] args) {

        Banco bancoIgnacio = new Banco();
        bancoGisela.Banco bancoGisela = new bancoGisela.Banco();

        MediadorTransferencia mediador = new MediadorTransferencia();

        mediador.registrarBanco(bancoIgnacio);
        mediador.registrarBanco(bancoGisela);

        //Datos de Banco de Ignacio
        Admin adminBoedo = new Admin(1, "adminBoedo", "456");
        bancoIgnacio.crearSucursal("Boedo", adminBoedo);

        Object resultadoLogin = bancoIgnacio.loginAdmin("adminBoedo", "456");

        Sucursal sucursal = null;
        if (resultadoLogin instanceof Sucursal) {
            sucursal = (Sucursal) resultadoLogin;
        }

        if (sucursal == null) {
            System.out.println("No se pudo iniciar sesion");
            return;
        }

        Usuario usuarioIgnacio = new Usuario(101, "Ignacio", "123") {};
        Cliente clienteIgnacio = new Cliente(usuarioIgnacio);

        sucursal.crearCliente(adminBoedo, clienteIgnacio);
        sucursal.crearCuenta(adminBoedo, clienteIgnacio, 1001, TipoCuenta.AHORRO);

        Cuenta cuentaOrigen = sucursal.buscarCuenta(1001);
        cuentaOrigen.depositar(10000);

        //Datos banco Gisela
        bancoGisela.agregarCuentaPrueba("Maria", 1002, 10000);

        //Ver los saldos antes de la transaccion
        System.out.println("---Saldos antes---");
        System.out.println("Banco Ignacio cuenta 1001: $" + cuentaOrigen.getSaldo());
        bancoGisela.mostrarSaldoCuenta(1002);

        //Transferencia entre los bancos
        boolean conexion = sucursal.transferirOtroBanco(adminBoedo, 1001, 1002, 5000, mediador, bancoIgnacio);

        //Resultado
        if (conexion) {
            System.out.println("Se ha realizado la transferencia con exito");
        } else {
            System.out.println("No se pudo transferir");
        }

        //Saldos despues
        System.out.println("---Saldos despues---");
        System.out.println("Banco Ignacio cuenta 1001: $" + cuentaOrigen.getSaldo());
        bancoGisela.mostrarSaldoCuenta(1002);
    }
}