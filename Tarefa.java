package ToDoList;

import java.util.Date;

public class Tarefa {
    private String nome;
    private String descricao;
    private Date dataTermino;
    private int prioridade;
    private String categoria;
    private String status;
    private Date dataAlarme;

    //Construtor
    public Tarefa(String nome, String descricao, Date dataTermino, int prioridade, String categoria, String status, Date dataAlarme) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataTermino = dataTermino;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.status = status;
        this.dataAlarme = dataAlarme;
    }

    // Métodos getters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getStatus() {
        return status;
    }
    public Date getDataAlarme() {
        return dataAlarme;
    }

    public void setDataAlarme(Date dataAlarme) {
        this.dataAlarme = dataAlarme;
    }
}

