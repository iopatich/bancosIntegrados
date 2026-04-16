package bancoGisela;

public class Trasferencias {
    //metodo para trasferir
    public static void ejecutar(Cuenta origen, Cuenta destino, double monto){
        if (origen.saldo>= monto) {
            origen.saldo -= monto;
            destino.saldo += monto;
            System.out.println("trasferencia exitosa");
            System.out.printf("se envio$ %f desde la cuenta %d %n: " ,monto, origen.numeroDeCuenta, destino.numeroDeCuenta);
        }else{
            System.out.println(" error saldo insuficiente");
        }
    }
}
