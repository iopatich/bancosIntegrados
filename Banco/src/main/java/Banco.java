import java.util.ArrayList;

public class Banco {
    private ArrayList<Sucursal> sucursales = new ArrayList<>();
    private AdminBanco adminBanco;

    public void setAdminBanco(AdminBanco adminBanco) {
        this.adminBanco = adminBanco;
    }

    public void crearSucursal(String nombre, Admin admin) {
        sucursales.add(new Sucursal(nombre, admin));
    }

    public void mostrarBalanceCuentas() {
        double total = 0;

        for (Sucursal sucursal : sucursales) {
            System.out.println("Sucursal: " + sucursal.getNombre());

            for (Cliente cliente : sucursal.getClientes()) {
                for (Cuenta cuenta : cliente.getCuentas()) {
                    System.out.println("Cuenta: " + cuenta.getIdCuenta() + "  Saldo: $" + cuenta.getSaldo());
                    total += cuenta.getSaldo();
                }
            }

            System.out.println();
        }

        System.out.println("Total en el banco: $" + total);
    }

    public Object loginAdmin(String nombre, String contrasenia) {
        if (adminBanco.getNombre().equals(nombre) &&
                adminBanco.getContrasenia().equals(contrasenia)) {
            return adminBanco;
        }

        for (Sucursal sucursal : sucursales) {
            Admin admin = sucursal.getAdmin();

            if (admin.getNombre().equals(nombre) && admin.getContrasenia().equals(contrasenia)) {
                return sucursal;
            }
        }
        return null;
    }
}