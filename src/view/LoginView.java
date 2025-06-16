package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Usuario;
import model.UsuarioDAO;

public class LoginView extends JFrame {
    private JTextField txtMatricula;
    private JPasswordField txtSenha;
    private JButton btnEntrar, btnCadastro;

    public LoginView() {
        setTitle("Login");
        setSize(420, 320);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // margem interna

        JLabel lblTitulo = new JLabel("Login da Biblioteca");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        // Campo Matrícula
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Matrícula:"), gbc);

        gbc.gridx = 1;
        txtMatricula = new JTextField(20);
        panel.add(txtMatricula, gbc);

        // Campo Senha
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        txtSenha = new JPasswordField(20);
        panel.add(txtSenha, gbc);

        // Botão Entrar
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        btnEntrar = new JButton("Entrar");
        panel.add(btnEntrar, gbc);

        // Botão Cadastro
        gbc.gridy = 4;
        btnCadastro = new JButton("Criar Conta");
        panel.add(btnCadastro, gbc);

        add(panel);
        setVisible(true);

        // Lógica do botão "Entrar"
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String matricula = txtMatricula.getText();
                String senha = new String(txtSenha.getPassword());

                UsuarioDAO dao = new UsuarioDAO();
                Usuario usuario = dao.autenticar(matricula, senha);

                if (usuario != null) {
                    JOptionPane.showMessageDialog(LoginView.this, "Bem-vindo(a), " + usuario.getNome() + "!");
                    dispose();
                    // abrir tela de acordo com o tipo
                    if (usuario.getTipo().equalsIgnoreCase("admin")) {
                    	new AdminView(usuario).setVisible(true);
                    } else {
                    	new UsuarioView(usuario).setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginView.this, "Matrícula ou senha incorretos.");
                }
            }
        });

        // Lógica do botão "Criar Conta"
        btnCadastro.addActionListener(e -> {
            dispose();
            new CadastroView();
        });
    }
}
