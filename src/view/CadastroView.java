package view;

import javax.swing.*;
import java.awt.*;

public class CadastroView extends JFrame {
    private JTextField txtNome;
    private JTextField txtMatricula;
    private JPasswordField txtSenha;
    private JComboBox<String> cbTipo;
    private JButton btnCadastrar;

    public CadastroView() {
        setTitle("Cadastro de Usuário");
        setSize(450, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitulo = new JLabel("Cadastro de Usuário");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        gbc.gridwidth = 1;

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        txtNome = new JTextField(20);
        panel.add(txtNome, gbc);

        // Matrícula
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Matrícula:"), gbc);

        gbc.gridx = 1;
        txtMatricula = new JTextField(20);
        panel.add(txtMatricula, gbc);

        // Senha
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        txtSenha = new JPasswordField(20);
        panel.add(txtSenha, gbc);

        // Tipo de usuário
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Tipo:"), gbc);

        gbc.gridx = 1;
        cbTipo = new JComboBox<>(new String[]{"comum", "admin"});
        panel.add(cbTipo, gbc);

        // Botão Cadastrar
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        btnCadastrar = new JButton("Cadastrar");
        panel.add(btnCadastrar, gbc);

        add(panel);
        setVisible(true);

        // Controlador será responsável por lidar com o clique
        new controller.CadastroController(this);
    }

    // Métodos de acesso para o controller
    public String getNome() {
        return txtNome.getText();
    }

    public String getMatricula() {
        return txtMatricula.getText();
    }

    public String getSenha() {
        return new String(txtSenha.getPassword());
    }

    public String getTipo() {
        return cbTipo.getSelectedItem().toString();
    }

    public JButton getBtnCadastrar() {
        return btnCadastrar;
    }
}
