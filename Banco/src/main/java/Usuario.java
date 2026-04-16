import java.util.HashSet;
import java.util.Set;

public abstract class Usuario {
    protected int id;
    protected String nombre;
    protected String contrasenia;
    protected Set<Permiso> permisos;

    public Usuario(int id, String nombre, String contrasenia) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.permisos = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void agregarPermisosAdminBanco() {
        permisos.add(Permiso.VER_SUCURSALES);
        permisos.add(Permiso.VER_CUENTAS);
    }

    public void agregarPermisosAdmin() {
        permisos.add(Permiso.CREAR_CLIENTE);
        permisos.add(Permiso.CREAR_CUENTA);
        permisos.add(Permiso.DAR_BAJA_CUENTA);
        permisos.add(Permiso.VER_CLIENTES);
        permisos.add(Permiso.VER_CUENTAS);
        permisos.add(Permiso.VER_TRANSACCIONES);
        permisos.add(Permiso.DEPOSITAR_PROPIO);
        permisos.add(Permiso.TRANSFERIR_PROPIO);
    }

    public void agregarPermisosCliente() {
        permisos.add(Permiso.RETIRAR_PROPIO);
    }

    public boolean tienePermiso(Permiso permiso) {
        return permisos.contains(permiso);
    }
}
