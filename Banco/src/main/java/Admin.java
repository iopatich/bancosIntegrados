public class Admin extends Usuario{
    public Admin(int idAdmin, String nombre, String contrasenia) {
        super(idAdmin, nombre, contrasenia);
        agregarPermisosAdmin();
    }
}
