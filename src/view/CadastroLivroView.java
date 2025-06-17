package view;

import model.Livro;
import model.LivroDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CadastroLivroView extends JFrame {

    private JTextField txtTitulo, txtAutor, txtAno;
    private JCheckBox chkDisponivel;
    private JLabel lblImagemPreview;
    private String caminhoImagem = ""; // caminho relativo da imagem a ser salvo
    private Usuario usuario;
    private JFrame parent;

    public CadastroLivroView(JFrame parent, Usuario usuario) {
        setTitle("Cadastro de Livro");
        this.parent = parent;
        this.usuario = usuario;
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelCampos = new JPanel(new GridLayout(6, 2, 10, 10));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        txtTitulo = new JTextField();
        txtAutor = new JTextField();
        txtAno = new JTextField();
        chkDisponivel = new JCheckBox("Disponível", true);

        JButton btnSelecionarImagem = new JButton("Selecionar Imagem");
        lblImagemPreview = new JLabel("Nenhuma imagem selecionada", JLabel.CENTER);

        btnSelecionarImagem.addActionListener(e -> selecionarImagem());

        painelCampos.add(new JLabel("Título:"));
        painelCampos.add(txtTitulo);
        painelCampos.add(new JLabel("Autor:"));
        painelCampos.add(txtAutor);
        painelCampos.add(new JLabel("Ano:"));
        painelCampos.add(txtAno);
        painelCampos.add(new JLabel("Disponibilidade:"));
        painelCampos.add(chkDisponivel);
        painelCampos.add(new JLabel("Imagem do Livro:"));
        painelCampos.add(btnSelecionarImagem);

        add(painelCampos, BorderLayout.NORTH);
        add(lblImagemPreview, BorderLayout.CENTER);

        JButton btnCadastrar = new JButton("Cadastrar Livro");
        btnCadastrar.addActionListener(e -> cadastrarLivro());

        JPanel painelBotao = new JPanel();
        painelBotao.add(btnCadastrar);
        add(painelBotao, BorderLayout.SOUTH);
    }

    private void selecionarImagem() {
        JFileChooser chooser = new JFileChooser();
        int resultado = chooser.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File imagemSelecionada = chooser.getSelectedFile();
            String nomeArquivo = imagemSelecionada.getName();

            File pastaDestino = new File("imagens");
            if (!pastaDestino.exists()) {
                pastaDestino.mkdirs(); // cria a pasta se não existir
            }

            File destino = new File(pastaDestino, nomeArquivo);
            try {
                Files.copy(imagemSelecionada.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                caminhoImagem = "imagens/" + nomeArquivo;

                ImageIcon icon = new ImageIcon(destino.getAbsolutePath());
                Image imgReduzida = icon.getImage().getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                lblImagemPreview.setIcon(new ImageIcon(imgReduzida));
                lblImagemPreview.setText("");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao copiar imagem.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void cadastrarLivro() {
        String titulo = txtTitulo.getText().trim();
        String autor = txtAutor.getText().trim();
        String anoStr = txtAno.getText().trim();
        boolean disponivel = chkDisponivel.isSelected();

        if (titulo.isEmpty() || autor.isEmpty() || anoStr.isEmpty() || caminhoImagem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos e selecione uma imagem.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int ano;
        try {
            ano = Integer.parseInt(anoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ano inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAno(ano);
        livro.setDisponivel(disponivel);
        livro.setImagem(caminhoImagem); // <- Caminho correto salvo

        LivroDAO dao = new LivroDAO();
        boolean sucesso = dao.inserirLivro(livro);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
            dispose(); // fecha janela de cadastro
            parent.dispose(); // fecha a tela antiga do admin
            new AdminView(usuario).setVisible(true); // abre nova tela com livros atualizados
        	}
        else {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar livro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
