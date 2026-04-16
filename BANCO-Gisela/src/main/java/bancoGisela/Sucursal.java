package bancoGisela;

import java.util.ArrayList;

public class Sucursal {

    public String nombreSucursal;
    public String direccionFisica;
    public AdminSucursal administrador; //OBJ HEREDADO
    public ArrayList<Cuenta> listaCuentas; // cuentas pertenecen a la sucursal y se guardan en este array

    public Sucursal(String nombre, String direccion, String u, String p) {
        this.nombreSucursal = nombre;
        this.direccionFisica = direccion;
        //se va a crear el administradoc uando se cree cada sucursal ya hay dos predefinidos
        this.administrador = new AdminSucursal(u, p, "Admin " + nombre);
        this.listaCuentas = new ArrayList<>();
    }
}