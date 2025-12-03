package pchub.model;

import java.util.List;
import pchub.model.NotaComponente;

public class ResultadoAvaliacao {

    private String titulo;
    private String descricao;
    private String compatibilidade;
    private String desempenho;
    private List<String> recomendacoes;
    private Integer notaGeral;
    private List<NotaComponente> notasComponentes;

    // Construtor, Getters e Setters
    public ResultadoAvaliacao(String titulo, String descricao, String compatibilidade, String desempenho, List<String> recomendacoes, Integer notaGeral, List<NotaComponente> notasComponentes) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.compatibilidade = compatibilidade;
        this.desempenho = desempenho;
        this.recomendacoes = recomendacoes;
        this.notaGeral = notaGeral;
        this.notasComponentes = notasComponentes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCompatibilidade() {
        return compatibilidade;
    }

    public void setCompatibilidade(String compatibilidade) {
        this.compatibilidade = compatibilidade;
    }

    public String getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(String desempenho) {
        this.desempenho = desempenho;
    }

    public List<String> getRecomendacoes() {
        return recomendacoes;
    }

    public void setRecomendacoes(List<String> recomendacoes) {
        this.recomendacoes = recomendacoes;
    }

    public Integer getNotaGeral() {
        return notaGeral;
    }

    public void setNotaGeral(Integer notaGeral) {
        this.notaGeral = notaGeral;
    }

    public List<NotaComponente> getNotasComponentes() {
        return notasComponentes;
    }

    public void setNotasComponentes(List<NotaComponente> notasComponentes) {
        this.notasComponentes = notasComponentes;
    }
}
