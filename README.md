# Sistema Bancario con Transferencias Interbancarias

## Descripción

Este proyecto simula un sistema bancario desarrollado en **Java orientado a objetos**, donde dos bancos diferentes pueden realizar:

- Gestión de clientes
- Creación de cuentas
- Depósitos
- Retiros
- Transferencias internas
- Transferencias entre bancos

La comunicación entre bancos se implementa utilizando el **patrón de diseño Mediator**, evitando que un banco dependa directamente del otro.

## Estructura del proyecto

El sistema está dividido en tres módulos principales:

### Banco Ignacio
Contiene:
- Administración de sucursales
- Administración de clientes
- Cuentas ahorro/corriente
- Menú administrativo

### Banco Gisela
Contiene:
- Administración de sucursales
- Usuarios clientes
- Transferencias internas
- Menú de usuario y administrador

### Interfaz común
Contiene:
- `BancoConectable`
- `CuentaBase`
- `MediadorTransferencia`
- `MainInterfaz`

## Patrón Mediator implementado

El sistema utiliza un mediador central llamado: MediadorTransferencia

Su función es:
- Registrar ambos bancos
- Recibir la solicitud de transferencia
- Buscar la cuenta destino en el otro banco
- Depositar el dinero
- Descontar el saldo de la cuenta origen

De esta manera:
- Los bancos no se conocen entre sí
- Solo conocen al mediador
- Se reduce el acoplamiento entre módulos
