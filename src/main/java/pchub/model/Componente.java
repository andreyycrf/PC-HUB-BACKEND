package pchub.model;

public class Componente {
    private String nome;
    private int score;
    private String tipo;

    public Componente(String nome, int score, String tipo) {
        this.nome = nome;
        this.score = score;
        this.tipo = tipo;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public int getScore() {
        return score;
    }

    public String getTipo() {
        return tipo;
    }
}
