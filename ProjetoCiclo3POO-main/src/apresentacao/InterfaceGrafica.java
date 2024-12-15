package apresentacao;

import negocio.*;
import acesso_aos_dados.*;

import javax.swing.*;
import java.awt.*;

public class InterfaceGrafica extends JFrame {

    private Banco banco;

    public InterfaceGrafica() {
        // Configuração do banco e conexão
        ConexaoBanco conexao = new ConexaoBanco();
        this.banco = new Banco(conexao);

        // Configuração da janela
        setTitle("Sistema Bancário");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painéis
        JPanel painelSuperior = new JPanel();
        painelSuperior.add(new JLabel("Bem-vindo ao Sistema Bancário"));

        JPanel painelCentral = criarPainelCentral();

        // Adicionar painéis à janela
        add(painelSuperior, BorderLayout.NORTH);
        add(painelCentral, BorderLayout.CENTER);
    }

    private JPanel criarPainelCentral() {
        JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));

        // Campos e botões
        JLabel lblNumero = new JLabel("Número da Conta:");
        JTextField txtNumero = new JTextField();

        JLabel lblSaldo = new JLabel("Saldo Inicial:");
        JTextField txtSaldo = new JTextField();

        JLabel lblLimite = new JLabel("Limite (opcional):");
        JTextField txtLimite = new JTextField();

        JButton btnCriarNormal = new JButton("Criar Conta Normal");
        JButton btnCriarEspecial = new JButton("Criar Conta Especial");
        JButton btnListarContas = new JButton("Listar Contas");

        JTextArea txtSaida = new JTextArea(10, 30);
        txtSaida.setEditable(false);

        // Adicionar componentes ao painel
        painel.add(lblNumero);
        painel.add(txtNumero);
        painel.add(lblSaldo);
        painel.add(txtSaldo);
        painel.add(lblLimite);
        painel.add(txtLimite);
        painel.add(btnCriarNormal);
        painel.add(btnCriarEspecial);
        painel.add(btnListarContas);

        // Adicionar área de saída
        JScrollPane scrollPane = new JScrollPane(txtSaida);
        painel.add(scrollPane);

        // Ações dos botões
        btnCriarNormal.addActionListener(e -> {
            String numero = txtNumero.getText();
            double saldo = Double.parseDouble(txtSaldo.getText());
            Conta conta = new ContaNormal(numero, saldo);
            banco.criarConta(conta);
            txtSaida.append("Conta Normal criada: " + numero + "\n");
        });

        btnCriarEspecial.addActionListener(e -> {
            String numero = txtNumero.getText();
            double saldo = Double.parseDouble(txtSaldo.getText());
            double limite = Double.parseDouble(txtLimite.getText());
            Conta conta = new ContaDebEspecial(numero, saldo, limite);
            banco.criarConta(conta);
            txtSaida.append("Conta Especial criada: " + numero + "\n");
        });

        btnListarContas.addActionListener(e -> {
            txtSaida.append("Lista de Contas:\n");
            banco.listarContas().forEach(conta ->
                txtSaida.append("Conta: " + conta.getNumero() + ", Saldo: " + conta.getSaldo() + "\n")
            );
        });
        return painel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfaceGrafica app = new InterfaceGrafica();
            app.setVisible(true);
        });
    }
}
