package negocio;

public class ContaDebEspecial extends Conta {
    private double limite;

    public ContaDebEspecial(String numero, double saldo, double limite) {
        super(numero, saldo);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public void creditar(double valor) {
        setSaldo(getSaldo() + valor);
    }

    @Override
    public void debitar(double valor) {
        if ((getSaldo() + limite) >= valor) {
            setSaldo(getSaldo() - valor);
        }
    }
}