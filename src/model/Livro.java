package model;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private boolean disponivel;
    private String imagem;
    private String matriculaUsuarioAlugou;
    
    public Livro() {};
    
	public Livro(int id, String titulo, String autor, int ano, boolean disponivel, String imagem, String matriculaUsuarioAlugou) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.ano = ano;
		this.disponivel = disponivel;
		this.imagem = imagem;
		this.matriculaUsuarioAlugou = matriculaUsuarioAlugou;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	
	public String getImagem() {
	    return imagem;
	}

	public void setImagem(String imagem) {
	    this.imagem = imagem;
	}
	
	public String getMatriculaUsuarioAlugou() {
	    return matriculaUsuarioAlugou;
	}

	public void setMatriculaUsuarioAlugou(String matriculaUsuarioAlugou) {
	    this.matriculaUsuarioAlugou = matriculaUsuarioAlugou;
	}


}
