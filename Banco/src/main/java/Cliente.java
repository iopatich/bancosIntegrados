import java.util.ArrayList;

public class Cliente {
    private Usuario usuario;
    private ArrayList<Cuenta> cuentas;

    public Cliente(Usuario usuario) {
        this.usuario = usuario;
        this.cuentas = new ArrayList<>();
        usuario.agregarPermisosCliente();
    }

    public Usuario getUsuario() {
            return usuario;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

}
