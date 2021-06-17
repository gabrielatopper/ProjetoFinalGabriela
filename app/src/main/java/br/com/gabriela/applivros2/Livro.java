package br.com.gabriela.applivros2;

public class Livro {

    public static final int NOTA_MINIA = 1;

    public int id;
    public String nome, descricao;
    private int nota;


    public Livro() {
    }

    public Livro(String nome, int nota) {
        this.nome = nome;
        this.setNota(nota);
    }

    public Livro(int id, String nome, String descricao, int nota) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.setNota(nota);
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        if (nota >= NOTA_MINIA )
        this.nota = nota;

    }

    @Override
    public String toString() {
        return id + " - " + nome + "-" + descricao + "|" + nota;
    }
}
