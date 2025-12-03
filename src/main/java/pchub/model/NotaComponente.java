package pchub.model;

public class NotaComponente {
    private String nome;
    private int nota; // Nota de 1 a 10
    private String detalhe;

    public NotaComponente(String nome, int nota, String detalhe) {
        this.nome = nome;
        this.nota = nota;
        this.detalhe = detalhe;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public int getNota() {
        return nota;
    }

    public String getDetalhe() {
        return detalhe;
    }
}
