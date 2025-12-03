package pchub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Peca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;      // Ex: "Processador", "Placa-Mãe"
    private String nome;      // Ex: "Ryzen 5 5600"
    private String socket;    // Ex: "AM4", "LGA1700" (O CAMPO IMPORTANTE)
    private String frequencia;
    private String memoria;   // Ex: "DDR4", "DDR5"
    private String idp;
    private String leitura;
    private String gravacao;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSocket() { return socket; }
    public void setSocket(String socket) { this.socket = socket; }

    public String getFrequencia() { return frequencia; }
    public void setFrequencia(String frequencia) { this.frequencia = frequencia; }

    public String getMemoria() { return memoria; }
    public void setMemoria(String memoria) { this.memoria = memoria; }
    
    // Outros getters/setters omitidos para brevidade, mas mantenha os seus
}
