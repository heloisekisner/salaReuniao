/**
 * Classe SalaReuniao
 * Representa a entidade Sala de Reunião no sistema.
 * Contém os atributos que correspondem às colunas da tabela no banco de dados.
 */
package com.exemplo.servlet;

public class SalaReuniao {
    private int id;
    private String nome;
    private int capacidade;
    private String localizacao;
    private boolean possuiProjetor;
    private boolean possuiArCondicionado;
    private int numeroCadeiras;
    private String tipoMesa;
    private String recursosAdicionais;

    // Getters e Setters para todos os atributos
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public boolean isPossuiProjetor() { return possuiProjetor; }
    public void setPossuiProjetor(boolean possuiProjetor) { this.possuiProjetor = possuiProjetor; }

    public boolean isPossuiArCondicionado() { return possuiArCondicionado; }
    public void setPossuiArCondicionado(boolean possuiArCondicionado) { this.possuiArCondicionado = possuiArCondicionado; }

    public int getNumeroCadeiras() { return numeroCadeiras; }
    public void setNumeroCadeiras(int numeroCadeiras) { this.numeroCadeiras = numeroCadeiras; }

    public String getTipoMesa() { return tipoMesa; }
    public void setTipoMesa(String tipoMesa) { this.tipoMesa = tipoMesa; }

    public String getRecursosAdicionais() { return recursosAdicionais; }
    public void setRecursosAdicionais(String recursosAdicionais) { this.recursosAdicionais = recursosAdicionais; }
}
