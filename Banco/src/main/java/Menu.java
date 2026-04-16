import java.util.Scanner;

public class Menu {
    private Banco banco = new Banco();
    private Scanner teclado = new Scanner(System.in);

    public void iniciar() {
        Admin adminParquePatricios = new Admin(1, "adminParquePatricios", "123");
        Admin adminBoedo = new Admin(2, "adminBoedo", "456");
        Admin adminRetiro = new Admin(3, "adminRetiro", "789");

        AdminBanco adminBanco = new AdminBanco(99, "adminBanco", "999");

        banco.crearSucursal("Parque Patricios", adminParquePatricios);
        banco.crearSucursal("Boedo", adminBoedo);
        banco.crearSucursal("Retiro", adminRetiro);

        banco.setAdminBanco(adminBanco);

        boolean finalizar = false;

        while (!finalizar) {
            System.out.println("\nIngrese sus datos de admin");
            System.out.print("Nombre: ");
            String nombre = teclado.nextLine();

            System.out.print("Contrasenia: ");
            String contrasenia = teclado.nextLine();

            Object login = banco.loginAdmin(nombre, contrasenia);

            if (login == null) {
                System.out.println("Login fallido");
                continue;
            }

            if (login instanceof AdminBanco) {
                finalizar = menuAdminBanco();
            }

            else if (login instanceof Sucursal) {
                Sucursal sucursal = (Sucursal) login;
                finalizar = menuSucursal(sucursal);
            }

            teclado.nextLine(); // limpiar buffer
        }

        System.out.println("Programa finalizado");
    }

    private boolean menuAdminBanco() {
        int opcion;

        do {
            System.out.println("Menu Admin Banco");
            System.out.println("1: Ver balance total de las cuentas");
            System.out.println("0: Cerrar sesion");
            System.out.println("-1: Salir del sistema");

            opcion = teclado.nextInt();

            switch (opcion) {
                case 1 -> banco.mostrarBalanceCuentas();
                case 0 -> System.out.println("Cerrando sesion");
                case -1 -> {
                    System.out.println("Finalizando");
                    return true;
                }
                default -> System.out.println("Opcion invalida");
            }

        } while (opcion != 0);

        return false;
    }

    private boolean menuSucursal(Sucursal sucursal) {
        Admin admin = sucursal.getAdmin();
        int opcion;

        do {
            System.out.println("Menu\nSucursal: " + sucursal.getNombre());
            System.out.println("1: Crear cliente");
            System.out.println("2: Crear cuenta");
            System.out.println("3: Depositar");
            System.out.println("4: Transferir");
            System.out.println("5: Retirar");
            System.out.println("6: Ver historial de transacciones de una cuenta");
            System.out.println("7: Ver cuentas");
            System.out.println("8: Borrar cuenta");
            System.out.println("0: Cerrar sesion");
            System.out.println("-1: Salir del sistema");

            opcion = teclado.nextInt();

            switch (opcion) {
                case 1 -> crearCliente(sucursal, admin);
                case 2 -> crearCuenta(sucursal, admin);
                case 3 -> depositar(sucursal, admin);
                case 4 -> transferir(sucursal, admin);
                case 5 -> retirar(sucursal);
                case 6 -> verTransacciones(sucursal, admin);
                case 7 -> sucursal.mostrarCuentas(admin);
                case 8 -> borrarCuenta(sucursal, admin);

                case 0 -> System.out.println("Cerrando sesion");
                case -1 -> {
                    System.out.println("Finalizando");
                    return true;
                }

                default -> System.out.println("Opcion invalida");
            }

        } while (opcion != 0);

        return false;
    }

    private void crearCliente(Sucursal sucursal, Admin admin) {
        teclado.nextLine();

        System.out.print("id del cliente: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Nombre del cliente: ");
        String nombre = teclado.nextLine();

        System.out.print("Contrasenia del cliente: ");
        String pass = teclado.nextLine();

        Usuario usuario = new Usuario(id, nombre, pass) {};
        Cliente cliente = new Cliente(usuario);

        sucursal.crearCliente(admin, cliente);
    }

    private void crearCuenta(Sucursal sucursal, Admin admin) {
        System.out.print("id del cliente: ");
        int idCliente = teclado.nextInt();

        Cliente cliente = null;

        for (Cliente clientee : sucursal.getClientes()) {
            if (clientee.getUsuario().getId() == idCliente) {
                cliente = clientee;
                break;
            }
        }

        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return;
        }

        System.out.print("id de la cuenta: ");
        int idCuenta = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Tipo de cuenta (ahorro o corriente): ");
        TipoCuenta tipo = TipoCuenta.valueOf(teclado.nextLine().toUpperCase());

        sucursal.crearCuenta(admin, cliente, idCuenta, tipo);
    }

    private void depositar(Sucursal sucursal, Admin admin) {
        System.out.print("id de la cuenta: ");
        int idCuenta = teclado.nextInt();

        System.out.print("Monto: ");
        double monto = teclado.nextDouble();

        sucursal.depositar(admin, idCuenta, monto);
    }

    private void retirar(Sucursal sucursal) {
        System.out.print("id del cliente: ");
        int idCliente = teclado.nextInt();

        Cliente cliente = null;

        for (Cliente clientee : sucursal.getClientes()) {
            if (clientee.getUsuario().getId() == idCliente) {
                cliente = clientee;
                break;
            }
        }

        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return;
        }

        System.out.print("id de la cuenta: ");
        int idCuenta = teclado.nextInt();

        System.out.print("Monto: ");
        double monto = teclado.nextDouble();

        sucursal.retirar(cliente.getUsuario(), idCuenta, monto);
    }

    private void transferir(Sucursal sucursal, Admin admin) {
        System.out.print("id de la cuenta origen: ");
        int origen = teclado.nextInt();

        System.out.print("id de la cuenta destino: ");
        int destino = teclado.nextInt();

        System.out.print("Monto: ");
        double monto = teclado.nextDouble();

        sucursal.transferir(admin, origen, destino, monto);
    }

    private void verTransacciones(Sucursal sucursal, Admin admin) {
        System.out.print("id de la cuenta: ");
        int idCuenta = teclado.nextInt();

        Cuenta cuenta = sucursal.buscarCuenta(idCuenta);

        if (cuenta != null) {
            sucursal.verTransacciones(admin, cuenta);
        }
    }

    private void borrarCuenta(Sucursal sucursal, Admin admin) {
        System.out.print("id de la cuenta: ");
        int id = teclado.nextInt();

        sucursal.borrarCuenta(admin, id);
    }
}