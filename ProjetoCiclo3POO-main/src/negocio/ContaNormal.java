package negocio;

public class ContaNormal extends Conta {

    public ContaNormal(String numero, double saldo) {
        super(numero, saldo);
    }

    @Override
    public void creditar(double valor) {
        setSaldo(getSaldo() + valor);
    }

    @Override
    public void debitar(double valor) {
        if (getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
        }
    }
}