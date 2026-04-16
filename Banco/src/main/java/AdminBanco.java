public class AdminBanco extends Usuario{
    public AdminBanco(int idAdminBanco, String nombre, String contrasenia) {
        super(idAdminBanco, nombre, contrasenia);
        agregarPermisosAdminBanco();
    }
}
