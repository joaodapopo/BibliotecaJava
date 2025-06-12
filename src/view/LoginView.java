package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.LoginController;

public class LoginView extends JFrame {
    private JTextField txtMatricula;
    private JPasswordField txtSenha;
    private JButton btnLogin, btnCadastro;

    public LoginView() {
        setTitle("Login - Sistema Biblioteca");
        setSize(420, 300); // aumento de tamanho
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza na tela

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel lblTitulo = new JLabel("Bem Vindo à Biblioteca!");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitulo);

        panel.add(Box.createVerticalStrut(25));

        JLabel lblMatricula = new JLabel("Matrícula:");
        txtMatricula = new JTextField();
        txtMatricula.setMaximumSize(new Dimension(300, 30)); // largura fixa
        panel.add(lblMatricula);
        panel.add(txtMatricula);

        panel.add(Box.createVerticalStrut(15));

        JLabel lblSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField();
        txtSenha.setMaximumSize(new Dimension(300, 30)); // largura fixa
        panel.add(lblSenha);
        panel.add(txtSenha);

        panel.add(Box.createVerticalStrut(25));

        btnLogin = new JButton("Entrar");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnLogin);

        panel.add(Box.createVerticalStrut(15));

        btnCadastro = new JButton("Cadastrar-se");
        btnCadastro.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnCadastro);

        // Ações dos botões
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String matricula = txtMatricula.getText();
                String senha = new String(txtSenha.getPassword());
                LoginController.autenticar(LoginView.this, matricula, senha);
            }
        });

        btnCadastro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CadastroView();
            }
        });

        add(panel);
        setVisible(true);
    }
}
