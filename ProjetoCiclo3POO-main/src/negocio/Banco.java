package negocio;

import acesso_aos_dados.ConexaoBanco;
import java.util.ArrayList;
import java.util.List;

public class Banco {
    private final ConexaoBanco conexao;
    private List<Conta> contas;

    public Banco(ConexaoBanco conexao) {
        this.conexao = conexao;
        this.contas = new ArrayList<>();
    }

    public void criarConta(Conta conta) {
        contas.add(conta);
        conexao.salvarConta(conta);
    }

    public List<Conta> listarContas() {
        return contas; // Return the list of accounts
    }

    public void creditar(String numero, double valor) {
        Conta conta = buscarConta(numero);
        if (conta != null) {
            conta.creditar(valor);
            conexao.atualizarSaldo(conta);
        }
    }

    public void debitar(String numero, double valor) {
        Conta conta = buscarConta(numero);
        if (conta != null) {
            conta.debitar(valor);
            conexao.atualizarSaldo(conta);
        }
    }

    public void transferir(String origem, String destino, double valor) {
        Conta contaOrigem = buscarConta(origem);
        Conta contaDestino = buscarConta(destino);

        if (contaOrigem != null && contaDestino != null) {
            contaOrigem.debitar(valor);
            contaDestino.creditar(valor);
            conexao.atualizarSaldo(contaOrigem);
            conexao.atualizarSaldo(contaDestino);
        }
    }

    private Conta buscarConta(String numero) {
        return contas.stream().filter(c -> c.getNumero().equals(numero)).findFirst().orElse(null);
    }
}
