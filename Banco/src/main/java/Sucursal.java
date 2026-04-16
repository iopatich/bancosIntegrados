import java.util.ArrayList;
import java.util.List;

public class Sucursal {
    private String nombre;
    private Admin admin;
    private List<Cliente> clientes;
    private List<Cuenta> cuentas;

    public Sucursal(String nombre, Admin admin) {
        this.nombre = nombre;
        this.admin = admin;
        this.clientes = new ArrayList<>();
        this.cuentas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void crearCliente(Admin admin, Cliente cliente) {
        if (!admin.tienePermiso(Permiso.CREAR_CLIENTE)) {
            System.out.println("No tienes permisos para crear un cliente");
            return;
        }
        clientes.add(cliente);
    }

    public void crearCuenta(Admin admin, Cliente cliente, int idCuenta, TipoCuenta tipoCuenta) {
        if (!admin.tienePermiso(Permiso.CREAR_CUENTA)) {
            System.out.println("No tienes permisos para crear una cuenta");
            return;
        }
        Cuenta cuenta = new Cuenta(idCuenta, tipoCuenta);
        cuentas.add(cuenta);
        cliente.agregarCuenta(cuenta);
        System.out.println("Se ha creado la cuenta " + idCuenta + " con exito");
    }

    public void borrarCuenta(Admin admin, int idCuenta) {
        if (!admin.tienePermiso(Permiso.DAR_BAJA_CUENTA)) {
            System.out.println("No tienes permisos para borrar una cuenta");
        }

        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getIdCuenta() == idCuenta) {
                cuentas.remove(i);
                return;
            }
        }
        for (Cliente cliente : clientes) {
            for (int i = 0; i < cliente.getCuentas().size(); i++) {
                if (cliente.getCuentas().get(i).getIdCuenta() == idCuenta) {
                    cliente.getCuentas().remove(i);
                    return;
                }
            }
        }

        System.out.println("Se ha eliminado la cuenta " + idCuenta);
    }

    public Cuenta buscarCuenta(int idCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getIdCuenta() == idCuenta) {
                return cuenta;
            }
        }
        System.out.println("No se ha encontrado la cuenta");
        return null;
    }

    public void mostrarCuentas(Admin admin) {
        if (!admin.tienePermiso(Permiso.VER_CUENTAS)) {
            System.out.println("No cuenta con permisos para ver las cuentas");
            return;
        }
        for (Cuenta cuenta : cuentas) {
            System.out.println("Cuenta: " + cuenta.getIdCuenta() + "\n Saldo: $" + cuenta.getSaldo());

        }
    }

    public void verTransacciones(Admin admin, Cuenta cuenta) {
        if (!admin.tienePermiso(Permiso.VER_TRANSACCIONES)) {
            System.out.println("Sin permiso");
            return;
        }

        for (Transaccion transaccion : cuenta.getHistorial()) {
            System.out.println(transaccion);
        }
    }

    public void depositar(Admin admin, int idCuenta, double monto) {
        if (!admin.tienePermiso(Permiso.DEPOSITAR_PROPIO)) {
            System.out.println("No tienes permisos para depositar");
            return;
        }
        Cuenta cuenta = buscarCuenta(idCuenta);

        if (cuenta != null) {
            cuenta.depositar(monto);
            System.out.println("Se ha depositado $" + monto + " en la cuenta " + cuenta.getIdCuenta());
        }
    }

    public void transferir(Admin admin, int idCuentaOrigen, int idCuentaDestino, double monto) {
        if (!admin.tienePermiso(Permiso.TRANSFERIR_PROPIO)) {
            System.out.println("Sin permiso");
            return;
        }

        Cuenta origen = buscarCuenta(idCuentaOrigen);
        Cuenta destino = buscarCuenta(idCuentaDestino);

        if (origen != null && destino != null && origen.getSaldo() >= monto && origen != destino) {
            origen.transferir(origen, destino, monto);
            System.out.println("Transferencia con exito");
        }
    }

    public void retirar(Usuario cliente, int idCuenta, double monto) {
        if (!cliente.tienePermiso(Permiso.RETIRAR_PROPIO)) {
            System.out.println("Sin permiso");
            return;
        }

        Cuenta cuenta = buscarCuenta(idCuenta);

        if (cuenta != null && cuenta.getSaldo() >= monto) {
            cuenta.retirar(monto);
            System.out.println("Retiro realizado");
        }
    }
}
