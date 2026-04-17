package bancoGisela;
 import interfazComun.CuentaBase;
public class Cuenta extends Usuario  implements CuentaBase{
    //atributos de las cuentas
    public int numeroDeCuenta;
    public String tipoDeCuenta;
    public double saldo;//arranca en cero
    public int edad;// hacer un if mayor de 18
    public Direccion direccion;// Usamos el objeto Direccion

    //contrastc heredado
    public Cuenta(String user, String contra, String nombre, int nro, int edad) {
        super(user, contra, nombre);
        this.numeroDeCuenta = nro;
        this.edad = edad;
        this.saldo = 0.0;
    }
    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public void depositar(double monto) {
        saldo += monto;
    }

    @Override
    public void retirar(double monto) {
        saldo -= monto;
    }
}