package controller;

import view.CadastroView;
import view.LoginView;
import model.Usuario;
import model.UsuarioDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroController {
    private CadastroView view;

    public CadastroController(CadastroView view) {
        this.view = view;
        this.view.getBtnCadastrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario();
            }
        });
    }

    private void cadastrarUsuario() {
        String nome = view.getNome();
        String matricula = view.getMatricula();
        String senha = view.getSenha();
        String tipo = view.getTipo();

        if (nome.isEmpty() || matricula.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor, preencha todos os campos.");
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setMatricula(matricula);
        usuario.setSenha(senha);
        usuario.setTipo(tipo);

        UsuarioDAO dao = new UsuarioDAO();
        boolean sucesso = dao.cadastrar(usuario);

        if (sucesso) {
            JOptionPane.showMessageDialog(view, "Usuário cadastrado com sucesso!");
            view.dispose();
            new LoginView(); // volta para a tela de login
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao cadastrar usuário. Matrícula pode já existir.");
        }
    }
}
