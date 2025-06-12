package model;

public class Usuario {
    private int id;
    private String nome;
    private String matricula;
    private String senha;
    private String tipo; 

    public Usuario() {}

    public Usuario(int id, String nome, String matricula, String senha, String tipo) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.senha = senha;
        this.tipo = tipo;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Opcional: para facilitar debug
    @Override
    public String toString() {
        return nome + " (" + tipo + ")";
    }
}
