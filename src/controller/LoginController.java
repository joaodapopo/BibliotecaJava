package controller;

import javax.swing.JOptionPane;
import model.Usuario;
import model.UsuarioDAO;
import view.AdminView;
import view.LoginView;
import view.UsuarioView;

public class LoginController {
    // Guarda uma referência para a view que este controller gerencia.
    private final LoginView view;

    // O construtor recebe a view e adiciona os listeners (ouvintes de eventos) aos botões.
    public LoginController(LoginView view) {
        this.view = view;
        // Adiciona um ActionListener ao botão "Entrar".
        this.view.getBtnEntrar().addActionListener(e -> autenticar());
    }

    // Método que executa a lógica de autenticação.
    public void autenticar() {
        // Pega os dados diretamente da view.
        String matricula = view.getMatricula();
        String senha = view.getSenha();

        // Validação básica para campos vazios.
        if (matricula.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Os campos Matrícula e Senha são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Usa o DAO para autenticar no banco de dados.
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.autenticar(matricula, senha);

        // Lógica para tratar o resultado da autenticação.
        if (usuario != null) {
            JOptionPane.showMessageDialog(view, "Bem-vindo(a), " + usuario.getNome() + "!");
            view.dispose(); // Fecha a tela de login.

            // Abre a tela correspondente ao tipo de usuário.
            if (usuario.getTipo().equalsIgnoreCase("admin")) {
                new AdminView(usuario).setVisible(true);
            } else {
                new UsuarioView(usuario).setVisible(true);
            }

        } else {
            // Se o usuário for nulo, as credenciais estão incorretas.
            JOptionPane.showMessageDialog(view, "Matrícula ou senha incorretos.");
        }
    }
}