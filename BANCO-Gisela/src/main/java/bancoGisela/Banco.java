package bancoGisela;

import java.util.ArrayList;
import java.util.Scanner;

import interfazComun.BancoConectable;

public class Banco implements BancoConectable{
    static ArrayList<Sucursal> listSucursales = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Credenciales Super Admin (Dueño del Banco)
        String superUser = "admin";
        String superPass = "123";

        // Sucursales precargadas
        listSucursales.add(new Sucursal("Sede Central", "Av. Rivadavia 100", "lautaro", "lau"));
        listSucursales.add(new Sucursal("Anexo 1", "9 de Julio 1200", "julieta", "juli"));

        while (true) {
            System.out.println("\n***--- SISTEMA BANCARIO ---***");
            System.out.println("******* INICIO DE SESIÓN*********");
            System.out.print("Usuario: ");
            String userIn = sc.nextLine().trim();
            System.out.print("Contraseña: ");
            String passIn = sc.nextLine().trim();

            // 1.  SUPER ADMIN
            if (userIn.equals(superUser) && passIn.equals(superPass)) {
                menuSuperAdmin(sc, listSucursales);
            }
            else {
                Sucursal sucLogueada = null;
                Cuenta clienteLogueado = null;

                // 2.  BUSCAR EN ADMINS DE SUCURSAL
                for (Sucursal s : listSucursales) {
                    if (s.administrador.usuario.equals(userIn) && s.administrador.contra.equals(passIn)) {
                        sucLogueada = s;
                        break;
                    }
                }

                // 3. BUSCAR EN CLIENTES
                if (sucLogueada == null) {
                    for (Sucursal s : listSucursales) {
                        for (Cuenta c : s.listaCuentas) {
                            if (c.usuario.equals(userIn) && c.contra.equals(passIn)) {
                                clienteLogueado = c;
                                break;
                            }
                        }
                    }
                }

                if (sucLogueada != null) {
                    menuAdminSucursal(sc, sucLogueada, listSucursales);
                } else if (clienteLogueado != null) {
                    menuUsuario(sc, clienteLogueado, listSucursales);
                } else {
                    System.out.println(" X Credenciales incorrectas. Reintente.");
                }
            }
        }
    }

    private static void menuSuperAdmin(Scanner sc, ArrayList<Sucursal> sucursales) {
        int op = 0;
        while (op != 3) {
            System.out.println("\n--- Te Logueaste como :   --- SUPER ADMINISTRADOR  BANCO  ---");
            System.out.println("1. ALTA de Sucursal (Asignar Admin)");
            System.out.println("2. REPORTE total del Banco");
            System.out.println("3. Cerrar Sesion");
            System.out.print("Seleccione opcion : ");
            op = sc.nextInt(); sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Nombre de Sucursal: "); String n = sc.nextLine();
                    System.out.print("Direccion: "); String d = sc.nextLine();
                    System.out.print("USUARIO para el nuevo ADMINISTRADOR DE SUCURSAL: "); String u = sc.nextLine();
                    System.out.print("CONTRASEÑA : "); String p = sc.nextLine();

                    sucursales.add(new Sucursal(n, d, u, p));
                    System.out.println("Sucursal y Administradores cargados correctamente");
                    System.out.println("---------------------------------------------------");
                    break;
                case 2:
                    mostrarReporteGeneral(sucursales);
                    break;
            }
        }
    }

    private static void menuAdminSucursal(Scanner sc, Sucursal miSucursal, ArrayList<Sucursal> todas) {
        int op = 0;
        while (op != 4) {
            System.out.println("\n--- Te Logueaste como : ADMIN SUCURSAL (" + miSucursal.nombreSucursal + ") ---");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("1. Alta de Cliente");
            System.out.println("2. Depositar Saldo");
            System.out.println("3. Lista de Clientes");
            System.out.println("4. Cerrar Sesión");
            op = sc.nextInt(); sc.nextLine();

            if (op == 1) {
                System.out.print("Nombre Cliente: "); String nom = sc.nextLine();
                System.out.print("Edad: "); int edad = sc.nextInt(); sc.nextLine();
                if (edad < 18) { System.out.println("Error x Menor de edad, nose puede crear cuenta"); continue; }

                System.out.print("Usuario: "); String usr = sc.nextLine();
                System.out.print("Contraseña: "); String psw = sc.nextLine();
                System.out.print("Nro Cuenta: "); int nro = sc.nextInt(); sc.nextLine();

                Cuenta nueva = new Cuenta(usr, psw, nom, nro, edad);
                miSucursal.listaCuentas.add(nueva);
                System.out.println("Cliente registrado.");
            } else if (op == 2) {
                System.out.print("Nro Cuenta: "); int nro = sc.nextInt();
                System.out.print("Monto: "); double monto = sc.nextDouble(); sc.nextLine();
                for(Cuenta c : miSucursal.listaCuentas) {
                    if(c.numeroDeCuenta == nro) {
                        c.saldo += monto;
                        System.out.println("Deposito exitoso.");
                        System.out.println("-----------------");
                    }
                }
            } else if (op == 3) {
                for(Cuenta c : miSucursal.listaCuentas) {
                    System.out.println("- " + c.nombre + " (Cta: " + c.numeroDeCuenta + ") Saldo: $" + c.saldo);
                }
            }
        }
    }

    // seccion del menu del usuario y sus acciones
    private static void menuUsuario(Scanner sc, Cuenta miCta, ArrayList<Sucursal> sucursales) {
        int op = 0;
        while (op != 3) {
            System.out.println("\n************ BIENVENIDO AL BANCO ******");
            System.out.println ("------------------------------------------");
            System.out.println("----Te Logueaste como : USUARIO (" + miCta.nombre + ") CAJA DE AHORRO EN PESOS----");
            System.out.println("Saldo Actual: $" + miCta.saldo);
            System.out.println("----------------------------------------");
            System.out.println("1. Transferir saldo");
            System.out.println("2. Retirar por Cajero");
            System.out.println("3. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");

            // Valida que ingrese un numero para la trasferencia
            if (!sc.hasNextInt()) {
                System.out.println("Error número invalido.");
                sc.nextLine(); // Limpiar vacio
                continue;
            }

            op = sc.nextInt(); sc.nextLine(); // Capturar opción y limpiar buffer sino me tomo vacios

            switch (op) {
                case 1:
                    System.out.println("\n--- REALIZAR UNA TRANSFERENCIA ---"); //TRASFIERE VALIDA SINO TENES SALDO O NO HAY CUENTA NO PODES
                    System.out.print("Ingrese Nro de Cuenta Destino: ");
                    int nroDestino = sc.nextInt(); sc.nextLine();
                    System.out.print("Monto a transferir: $");
                    double montoTransf = sc.nextDouble(); sc.nextLine();

                    if (montoTransf > miCta.saldo) {
                        System.out.println("X Saldo insuficiente para transferir.");
                        System.out.printf("-------------------------");
                    } else {
                        Cuenta cDestino = null;
                        // Buscamos la cuenta en todas las sucursales
                        for (Sucursal s : sucursales) {
                            for (Cuenta c : s.listaCuentas) {
                                if (c.numeroDeCuenta == nroDestino) {
                                    cDestino = c;
                                    break;
                                }
                            }
                        }

                        if (cDestino != null) {
                            // Usamos la clase de transferencias
                            Trasferencias.ejecutar(miCta, cDestino, montoTransf);
                        } else {
                            System.out.println("  X La cuenta destino no existe. volve a intentar");

                        }
                    }
                    break;

                case 2:
                    System.out.println("\n--- RETIRO POR CAJERO VIRTUAL ---");
                    System.out.print("Monto a retirar: $");
                    double montoRetiro = sc.nextDouble(); sc.nextLine();

                    if (montoRetiro <= miCta.saldo) {
                        miCta.saldo -= montoRetiro;
                        System.out.println("* Retire su dinero. Saldo restante: $" + miCta.saldo);
                    } else {
                        System.out.println("  X Saldo insuficiente.");
                    }
                    break;

                case 3:
                    System.out.println("Cerrando sesión de " + miCta.nombre + "...");
                    System.out.println("-----------------------");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    private static void mostrarReporteGeneral(ArrayList<Sucursal> sucursales) {
        double totalGlobal = 0;
        System.out.println("\n  *** REPORTE GENERAL DEL BANCO ***");
        for (Sucursal s : sucursales) {
            System.out.println("Sucursal: " + s.nombreSucursal + " (Admin: " + s.administrador.usuario + ")");
            for (Cuenta c : s.listaCuentas) {
                System.out.printf("  > Cliente: %s | Saldo: $%.2f%n", c.nombre, c.saldo);
                totalGlobal += c.saldo;
            }
        }
        System.out.println("CAPITAL TOTAL DEL BANCO: $" + totalGlobal);
    }


    @Override
    public boolean transferirDestino(int numeroCuenta, double monto) {
        for (Sucursal sucursal : listSucursales) {
            for (Cuenta cuenta : sucursal.listaCuentas) {
                if (cuenta.numeroDeCuenta == numeroCuenta) {
                    cuenta.saldo += monto;
                    System.out.println("Transferencia recibida en banco Gisela");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getNombreBanco() {
        return "Banco Gisela";
    }
}