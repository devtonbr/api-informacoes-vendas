package br.edu.infnet.api.informacoes.vendas.model.exception;

public abstract class IAuthException extends RuntimeException {

    private static final long serialVersionUID = 5855841161576015514L;

    public IAuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public IAuthException(String message) {
        super(message);
    }

}