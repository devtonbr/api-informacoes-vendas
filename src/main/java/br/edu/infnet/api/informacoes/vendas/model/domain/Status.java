package br.edu.infnet.api.informacoes.vendas.model.domain;

public class Status {

    private final int codigo;
    private final String resposta;

    public Status(int codigo, String resposta) {
        this.codigo = codigo;
        this.resposta = resposta;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getResposta() {
        return resposta;
    }
}
